/*
 * Copyright 2019 richard.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.theretiredprogrammer.imageditor;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author richard
 */
public class ControlPanel extends VerticalGridBagPanel {

    private final ModelResize resizemodel;
    private final ControlIntSpinnerField resizewidth;
    private final ControlIntSpinnerField resizeheight;
    //
    private final ModelCrop cropmodel;
    private final ControlIntSpinnerField height;
    private final ControlIntSpinnerField left;
    private final ControlIntSpinnerField top;
    private final ControlIntSpinnerField width;
    //
    private final ModelZoom zoommodel;

    //
    public ControlPanel(ModelResize resizemodel, ModelCrop cropmodel, ModelZoom zoommodel) throws IOException {
        this.resizemodel = resizemodel;
        this.cropmodel = cropmodel;
        this.zoommodel = zoommodel;
        //
        centredLabel("RESIZE CONTROL");
        resizewidth = labeledIntSpinnerField("Width:", resizemodel.getWidth(), 1, Integer.MAX_VALUE, this::resizeWidthChanged);
        resizeheight = labeledIntSpinnerField("Height:", resizemodel.getHeight(), 1, Integer.MAX_VALUE, this::resizeHeightChanged);
        skipRow();
        centredButton("Reset", this::resetResize);
        skipRow();
        centredLabel("CROP CONTROL");
        left = labeledIntSpinnerField("Left:", cropmodel.getLeft(), 1, Integer.MAX_VALUE, this::leftChanged);
        top = labeledIntSpinnerField("Top:", cropmodel.getTop(), 1, Integer.MAX_VALUE, this::topChanged);
        width = labeledIntSpinnerField("Width:", cropmodel.getWidth(), 1, Integer.MAX_VALUE, this::widthChanged);
        height = labeledIntSpinnerField("Height:", cropmodel.getHeight(), 1, Integer.MAX_VALUE, this::heightChanged);
        skipRow();
        centredButton("Reset Crop", this::resetCrop);
        skipRow();
        centredButton("Save", this::save);
    }
    
    public void resizeImageChanged(BufferedImage image) {
        if (cropmodel.setImage(image)) {
            left.setIntValue(cropmodel.getLeft());
            top.setIntValue(cropmodel.getTop());
            width.setIntValue(cropmodel.getWidth());
            height.setIntValue(cropmodel.getHeight());
        }
        cropmodel.fireImageChange();
    }

    private void resetResize(ActionEvent evt) {
        if (resizemodel.reset()) {
            resizewidth.setIntValue(resizemodel.getWidth());
            resizeheight.setIntValue(resizemodel.getHeight());
        }
        resizemodel.fireImageChange();
    }

    public void resizeWidthChanged(ChangeEvent evt) {
        if (resizemodel.widthChanged(resizewidth.getIntValue())) {
            resizewidth.setIntValue(resizemodel.getWidth());
            resizeheight.setIntValue(resizemodel.getHeight());
        }
        resizemodel.fireImageChange();
    }

    public void resizeHeightChanged(ChangeEvent evt) {
        if (resizemodel.heightChanged(resizeheight.getIntValue())) {
            resizewidth.setIntValue(resizemodel.getWidth());
            resizeheight.setIntValue(resizemodel.getHeight());
        }
        resizemodel.fireImageChange();
    }
    
    public void cropImageChanged(BufferedImage image) {
        zoommodel.setImage(image);
        zoommodel.fireImageChange();
    }

    private void resetCrop(ActionEvent evt) {
        if (cropmodel.reset()) {
            left.setIntValue(cropmodel.getLeft());
            top.setIntValue(cropmodel.getTop());
            width.setIntValue(cropmodel.getWidth());
            height.setIntValue(cropmodel.getHeight());
        }
        cropmodel.fireImageChange();
    }

    public void leftChanged(ChangeEvent evt) {
        if (cropmodel.leftChanged(left.getIntValue())) {
            left.setIntValue(cropmodel.getLeft());
        }
        cropmodel.fireImageChange();
    }

    public void topChanged(ChangeEvent evt) {
        if (cropmodel.topChanged(top.getIntValue())) {
            top.setIntValue(cropmodel.getTop());
        }
        cropmodel.fireImageChange();
    }

    public void widthChanged(ChangeEvent evt) {
        if (cropmodel.widthChanged(width.getIntValue())) {
            width.setIntValue(cropmodel.getWidth());
        }
        cropmodel.fireImageChange();
    }

    public void heightChanged(ChangeEvent evt) {
        if (cropmodel.heightChanged(height.getIntValue())) {
            height.setIntValue(cropmodel.getHeight());
        }
        cropmodel.fireImageChange();
    }

    private void save(ActionEvent evt) {
        // TODO - action removed at the moment
    }
}
