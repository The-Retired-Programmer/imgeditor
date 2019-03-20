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
import javax.swing.BoxLayout;
import javax.swing.JLabel;

/**
 *
 * @author richard
 */
public class InfoPanel extends HorizontalBoxPanel {

    private final JLabel imagewidth;
    private final JLabel imageheight;
    private final JLabel filename;
    private final JLabel filepath;
    private final JLabel zoomratiolabel;

    private final CropTopComponent parent;

    public InfoPanel(CropTopComponent parent) {
        this.parent = parent;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        filename = doubleLabel("File:");
        filepath = doubleLabel("File location:");
        imagewidth = doubleLabel("Image width:");
        imageheight = doubleLabel("Image height:");
        button("Zoom Out", this::zoomOutActionPerformed);
        button("Zoom In", this::zoomInActionPerformed);
        button("Zoom Reset", this::zoomResetActionPerformed);
        zoomratiolabel = doubleLabel("Zoom Ratio");
        zoomratiolabel.setText("1:1");
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

    public void setFilename(String fn) {
        filename.setText(fn);
    }

    public void setFilepath(String fp) {
        filepath.setText(fp);
    }

    public void setImagewidth(int width) {
        imagewidth.setText(Integer.toString(width));
    }

    public void setImageheight(int height) {
        imageheight.setText(Integer.toString(height));
    }
}