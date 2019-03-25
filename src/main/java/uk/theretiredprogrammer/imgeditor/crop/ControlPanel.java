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
package uk.theretiredprogrammer.imgeditor.crop;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.event.ChangeEvent;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

/**
 *
 * @author richard
 */
public class ControlPanel extends VerticalGridBagPanel {

    private ControlIntSpinnerField bottom;
    private ControlIntSpinnerField height;
    private ControlIntSpinnerField left;
    private ControlIntSpinnerField right;
    private ControlIntSpinnerField top;
    private ControlIntSpinnerField width;
    //
    private ImagePanel imagePanel;

    private boolean usewidthheight;
    private ImageAndCrops cropdata;
    private Zoom zoom;
    private MessagePanel messagePanel;

    public void setup(ImagePanel imagePanel, MessagePanel messagePanel,
            FileObject fo) throws IOException {
        usewidthheight = false;
        this.imagePanel = imagePanel;
        this.messagePanel = messagePanel;
        zoom = new Zoom();
        cropdata = new ImageAndCrops(ImageIO.read(FileUtil.toFile(fo)), zoom);
        cropdata.setDefaultFiletype(fo);
        imagePanel.display(cropdata);
        //
        centredLabel("CROP CONTROL");
        labeledCheckbox("Use Width/Height", this::usewidthheightItemStateChanged);
        left = labeledIntSpinnerField("Left:", cropdata.getLeft(), cropdata.getLeft(), cropdata.getRight(), this::leftChanged);
        right = labeledIntSpinnerField("Right:", cropdata.getRight(), cropdata.getLeft(), cropdata.getRight(), this::rightChanged);
        top = labeledIntSpinnerField("Top:", cropdata.getTop(), cropdata.getTop(), cropdata.getBottom(), this::topChanged);
        bottom = labeledIntSpinnerField("Bottom:", cropdata.getBottom(), cropdata.getTop(), cropdata.getBottom(), this::bottomChanged);
        width = labeledIntSpinnerField("Width:", cropdata.getWidth(), 1, cropdata.getWidth(), this::widthChanged);
        width.setEnabled(false);
        height = labeledIntSpinnerField("Height:", cropdata.getHeight(), 1, cropdata.getHeight(), this::heightChanged);
        height.setEnabled(false);
        skipRow();
        centredButton("Reset", this::reset);
        skipRow();
        centredButton("Crop", this::crop);
    }

    public void displayMessage(String mess) {
        messagePanel.displayMessage(mess);
    }

    private void reset(ActionEvent evt) {
        cropdata.reset();
        left.setIntValue(cropdata.getLeft());
        right.setIntValue(cropdata.getRight());
        top.setIntValue(cropdata.getTop());
        bottom.setIntValue(cropdata.getBottom());
        width.setIntValue(cropdata.getWidth());
        height.setIntValue(cropdata.getHeight());
    }

    private void crop(ActionEvent evt) {
        try {
            cropdata.crop();
        } catch (IOException ex) {
            displayMessage("EXCEPTION FROM CROP");
        }
    }

    public int getImageWidth() {
        return cropdata.getImageWidth();
    }

    public int getImageHeight() {
        return cropdata.getImageHeight();
    }

    public String zoomOut() {
        zoom.zoomOut();
        imagePanel.display(cropdata);
        return zoom.getZoomText();
    }

    public String zoomIn() {
        zoom.zoomIn();
        imagePanel.display(cropdata);
        return zoom.getZoomText();
    }

    public String zoomReset() {
        zoom.zoomReset();
        imagePanel.display(cropdata);
        return zoom.getZoomText();
    }

    private void leftChanged(ChangeEvent evt) {
        int newv = left.getIntValue();
        if (usewidthheight) {
            if (newv + cropdata.getWidth() - 1 > cropdata.getImageWidth()) {
                newv = cropdata.getImageWidth() - cropdata.getWidth() + 1;
            }
            cropdata.setRight(newv + cropdata.getWidth() - 1);
            right.setIntValue(cropdata.getRight());
        } else {
            if (newv > cropdata.getRight()) {
                newv = cropdata.getRight();
            }
            cropdata.setWidth(cropdata.getRight() - newv + 1);
            width.setIntValue(cropdata.getWidth());
        }
        cropdata.setLeft(newv);
        left.setIntValue(cropdata.getLeft());
        imagePanel.display(cropdata);
    }

    private void rightChanged(ChangeEvent evt) {
        int newv = right.getIntValue();
        if (!usewidthheight) {
            if (newv < cropdata.getLeft()) {
                newv = cropdata.getLeft();
            }
            cropdata.setWidth(newv - cropdata.getLeft() + 1);
            width.setIntValue(cropdata.getWidth());
        }
        cropdata.setRight(newv);
        right.setIntValue(cropdata.getRight());
        imagePanel.display(cropdata);
    }

    private void topChanged(ChangeEvent evt) {
        int newv = top.getIntValue();
        if (usewidthheight) {
            if (newv + cropdata.getHeight() - 1 > cropdata.getImageHeight()) {
                newv = cropdata.getImageHeight() - cropdata.getHeight() + 1;
            }
            cropdata.setBottom(newv + cropdata.getHeight() - 1);
            bottom.setIntValue(cropdata.getBottom());
        } else {
            if (newv > cropdata.getBottom()) {
                newv = cropdata.getBottom();
            }
            cropdata.setHeight(cropdata.getBottom() - newv + 1);
            height.setIntValue(cropdata.getHeight());
        }
        cropdata.setTop(newv);
        top.setIntValue(cropdata.getTop());
        imagePanel.display(cropdata);
    }

    private void bottomChanged(ChangeEvent evt) {
        int newv = bottom.getIntValue();
        if (!usewidthheight) {
            if (newv < cropdata.getTop()) {
                newv = cropdata.getTop();
            }
            cropdata.setHeight(newv - cropdata.getTop() + 1);
            height.setIntValue(cropdata.getHeight());
        }
        cropdata.setBottom(newv);
        bottom.setIntValue(cropdata.getBottom());
        imagePanel.display(cropdata);
    }

    private void widthChanged(ChangeEvent evt) {
        int newv = width.getIntValue();
        if (usewidthheight) {
            if (newv + cropdata.getLeft() - 1 > cropdata.getImageWidth()) {
                newv = cropdata.getImageWidth() - cropdata.getLeft() + 1;
            }
            cropdata.setRight(newv + cropdata.getLeft() - 1);
            right.setIntValue(cropdata.getRight());
        }
        cropdata.setWidth(newv);
        width.setIntValue(cropdata.getWidth());
        imagePanel.display(cropdata);
    }

    private void heightChanged(ChangeEvent evt) {
        int newv = height.getIntValue();
        if (usewidthheight) {
            if (newv + cropdata.getTop() - 1 > cropdata.getImageHeight()) {
                newv = cropdata.getImageHeight() - cropdata.getTop() + 1;
            }
            cropdata.setBottom(newv + cropdata.getTop() - 1);
            bottom.setIntValue(cropdata.getBottom());
        }
        cropdata.setHeight(newv);
        height.setIntValue(cropdata.getHeight());
        imagePanel.display(cropdata);
    }

    private void usewidthheightItemStateChanged(ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.DESELECTED) {
            // disable width/height
            width.setEnabled(false);
            height.setEnabled(false);
            // use right/bottom
            right.setEnabled(true);
            bottom.setEnabled(true);
            usewidthheight = false;
        } else {
            // use width/height
            width.setEnabled(true);
            height.setEnabled(true);
            // disable right/bottom
            right.setEnabled(false);
            bottom.setEnabled(false);
            usewidthheight = true;
        }
    }
}
