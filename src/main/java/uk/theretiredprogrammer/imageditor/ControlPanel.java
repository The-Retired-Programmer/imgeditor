/*
 * Copyright 2019 richard linsdale.
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
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;

/**
 * The control panel - a segment of the img editor UI
 *
 * contains all control components - buttons, number entry, checkboxes etc.
 *
 * @author Richard Linsdale (richard at theretiredprogrammer.uk)
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
    private final ModelSave savemodel;
    private final JCheckBox inplace;
    private final ControlTextField savefoldername;
    private final ControlIntSpinnerField percentage;
    private final ControlIntSpinnerField minwidth;
    //
    private final ModelZoom zoommodel;
    /**
     * Create the control panel.
     *
     * @param resizemodel the resize model
     * @param cropmodel the crop model
     * @param savemodel the save model
     * @param zoommodel the zoom model
     * @throws IOException if problems
     */
    public ControlPanel(ModelResize resizemodel, ModelCrop cropmodel, ModelSave savemodel, ModelZoom zoommodel) throws IOException {
        this.resizemodel = resizemodel;
        this.cropmodel = cropmodel;
        this.savemodel = savemodel;
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
        centredLabel("SAVE CONTROL");
        inplace = labeledCheckbox("Save in place:", savemodel.isInplace(), this::inplaceChanged);
        savefoldername = labeledTextField("Save Directory (../<folder>):", savemodel.getSavefoldername(), this::savefoldernamechanged);
        centredButton("Save", this::save);
        percentage = labeledIntSpinnerField("%:", savemodel.getPercentage(), 1, 99, this::percentageChanged);
        minwidth = labeledIntSpinnerField("Min width:", savemodel.getMinwidth(), 1, Integer.MAX_VALUE, this::minwidthChanged);
        centredButton("Save Multiple", this::savemultiple);
    }

    /**
     * Change Listener for resize image - sets the crop model image as the
     * resulting resize image
     *
     * @param image the image resulting from the application of the resize
     * operation
     */
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

    private void resizeWidthChanged(ChangeEvent evt) {
        if (resizemodel.widthChanged(resizewidth.getIntValue())) {
            resizewidth.setIntValue(resizemodel.getWidth());
            resizeheight.setIntValue(resizemodel.getHeight());
        }
        resizemodel.fireImageChange();
    }

    private void resizeHeightChanged(ChangeEvent evt) {
        if (resizemodel.heightChanged(resizeheight.getIntValue())) {
            resizewidth.setIntValue(resizemodel.getWidth());
            resizeheight.setIntValue(resizemodel.getHeight());
        }
        resizemodel.fireImageChange();
    }

    /**
     * Change Listener for crop image - sets the save model image as the
     * resulting crop image
     *
     * @param image the image resulting from the application of the crop
     * operation
     */
    public void cropImageChanged(BufferedImage image) {
        savemodel.setImage(image);
        savemodel.fireImageChange();
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

    private void leftChanged(ChangeEvent evt) {
        if (cropmodel.leftChanged(left.getIntValue())) {
            left.setIntValue(cropmodel.getLeft());
        }
        cropmodel.fireImageChange();
    }

    private void topChanged(ChangeEvent evt) {
        if (cropmodel.topChanged(top.getIntValue())) {
            top.setIntValue(cropmodel.getTop());
        }
        cropmodel.fireImageChange();
    }

    private void widthChanged(ChangeEvent evt) {
        if (cropmodel.widthChanged(width.getIntValue())) {
            width.setIntValue(cropmodel.getWidth());
        }
        cropmodel.fireImageChange();
    }

    private void heightChanged(ChangeEvent evt) {
        if (cropmodel.heightChanged(height.getIntValue())) {
            height.setIntValue(cropmodel.getHeight());
        }
        cropmodel.fireImageChange();
    }

    /**
     * Change Listener for save image - sets the zoom model image as the
     * resulting save image
     *
     * @param image the image resulting from the application of the save
     * operation
     */
    public void saveImageChanged(BufferedImage image) {
        zoommodel.setImage(image);
        zoommodel.fireImageChange();
    }

    private void percentageChanged(ChangeEvent evt) {
        savemodel.setPercentage(percentage.getIntValue());
    }

    private void minwidthChanged(ChangeEvent evt) {
        savemodel.setMinwidth(minwidth.getIntValue());
    }

    private void inplaceChanged(ItemEvent ivt) {
        savemodel.setInplace(inplace.isSelected());
    }

    private void savefoldernamechanged(ActionEvent evt) {
        savemodel.setSavefoldername(savefoldername.getText());
    }

    private void save(ActionEvent evt) {
        savemodel.save();
    }

    private void savemultiple(ActionEvent evt) {
        savemodel.savemultiple();
    }
}
