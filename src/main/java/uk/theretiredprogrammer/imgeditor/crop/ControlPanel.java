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
import javax.swing.JLabel;

/**
 *
 * @author richard
 */
public class ControlPanel extends VerticalGridBagPanel {
    
    private final ControlTextField bottom;
    private final ControlTextField height;
    private final ControlTextField left;
    private final ControlTextField right;
    private final ControlTextField top;
    private final ControlTextField width;
    private final JLabel zoomratiolabel;
    //
    private final CropTopComponent parent;

    public ControlPanel(CropTopComponent parent) {
        this.parent = parent;
        centredLabel("CROP CONTROL");
        labeledCheckbox("Use Width/Height", this::usewidthheightItemStateChanged);
        left = labeledTextField("Left:", this::leftActionPerformed);
        right = labeledTextField("Right:", this::rightActionPerformed);
        top = labeledTextField("Top:", this::topActionPerformed);
        bottom = labeledTextField("Bottom:", this::bottomActionPerformed);
        width = labeledTextField("Width:", this::widthActionPerformed);
        width.setEnabled(false);
        height = labeledTextField("Height:", this::heightActionPerformed);
        height.setEnabled(false);
        skipRow();
        centredLabel("ZOOM DISPLAY");
        centredButton("Zoom Out", this::zoomOutActionPerformed);
        centredButton("Zoom In", this::zoomInActionPerformed);
        centredButton("Zoom Reset", this::zoomResetActionPerformed);
        zoomratiolabel = doubleLabel("Zoom Ratio", "1:1");
    }
    
    private void leftActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void rightActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void topActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void bottomActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void widthActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void heightActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void usewidthheightItemStateChanged(ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.DESELECTED) {
            // disable width/height
            width.setEnabled(false);
            height.setEnabled(false);
            // use right/bottom
            right.setEnabled(true);
            bottom.setEnabled(true);
        } else {
            // use width/height
            width.setEnabled(true);
            height.setEnabled(true);
            // disable right/bottom
            right.setEnabled(false);
            bottom.setEnabled(false);
        }
    }

    private void zoomOutActionPerformed(ActionEvent evt) {
        ImagePanel imagePanel = parent.getImagePanel();
        zoomratiolabel.setText(imagePanel.zoomOut());
    }

    private void zoomInActionPerformed(ActionEvent evt) {
        ImagePanel imagePanel = parent.getImagePanel();
        zoomratiolabel.setText(imagePanel.zoomIn());
    }

    private void zoomResetActionPerformed(ActionEvent evt) {
        ImagePanel imagePanel = parent.getImagePanel();
        zoomratiolabel.setText(imagePanel.zoomReset());
    }
}
