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
import javax.swing.JLabel;

/**
 * The information Panel for the img editor
 *
 * comprises two horizontal box panels, vertically stacked on each other:
 *
 * the top part contains file information the bottom part contains the zoom
 * information and controls
 *
 * @author Richard Linsdale (richard at theretiredprogrammer.uk)
 */
public class InfoPanel extends VerticalBoxPanel {

    private final HorizontalBoxPanel filePanel = new HorizontalBoxPanel();
    private final HorizontalBoxPanel zoomPanel = new HorizontalBoxPanel();

    private final JLabel imagewidth;
    private final JLabel imageheight;
    private final JLabel filename;
    private final JLabel filepath;
    private final JLabel zoomratiolabel;
    private final ModelZoom zoomtransformer;

    /**
     * Constructor
     *
     * @param zoommodel the zoom model
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public InfoPanel(ModelZoom zoommodel) {
        this.zoomtransformer = zoommodel;
        filename = filePanel.doubleLabel("File:");
        filepath = filePanel.doubleLabel("File location:");
        imagewidth = zoomPanel.doubleLabel("Image width:");
        imageheight = zoomPanel.doubleLabel("Image height:");
        zoomPanel.button("Zoom Out", this::zoomout);
        zoomPanel.button("Zoom In", this::zoomin);
        zoomPanel.button("Zoom Reset", this::zoomreset);
        zoomratiolabel = zoomPanel.doubleLabel("Zoom Ratio");
        zoomratiolabel.setText("1:1");
        add(filePanel);
        add(zoomPanel);
    }

    private void zoomout(ActionEvent evt) {
        zoomtransformer.zoomout();
        zoomratiolabel.setText(zoomtransformer.getZoomRatio());
    }

    private void zoomin(ActionEvent evt) {
        zoomtransformer.zoomin();
        zoomratiolabel.setText(zoomtransformer.getZoomRatio());
    }

    private void zoomreset(ActionEvent evt) {
        zoomtransformer.zoomreset();
        zoomratiolabel.setText(zoomtransformer.getZoomRatio());
    }

    /**
     * Set the filename in the file information panel.
     *
     * @param fn the filename
     */
    public void setFilename(String fn) {
        filename.setText(fn);
    }

    /**
     * Set the file path in the file information panel
     *
     * @param fp the file path
     */
    public void setFilepath(String fp) {
        filepath.setText(fp);
    }

    /**
     * Set the original image width in the zoom panel
     *
     * @param width the original image width
     */
    public void setImagewidth(int width) {
        imagewidth.setText(Integer.toString(width));
    }

    /**
     * Set the original image height in the zoom panel
     *
     * @param height the original image height
     */
    public void setImageheight(int height) {
        imageheight.setText(Integer.toString(height));
    }

    /**
     * Set the zoom ration in the zoom panel
     *
     * @param r the zoom ratio, expressed as a string, either (1:n or n:1)
     */
    public void setZoomRatio(String r) {
        zoomratiolabel.setText(r);
    }
}
