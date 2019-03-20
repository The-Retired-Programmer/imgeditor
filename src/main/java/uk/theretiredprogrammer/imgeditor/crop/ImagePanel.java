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

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

/**
 *
 * @author richard
 */
public class ImagePanel extends JPanel {

    private ImageDisplay imagefield;
    private final CropTopComponent parent;
    //
    private ScaledImage image;

    public ImagePanel(CropTopComponent parent) {
        this.parent = parent;
    }

    public void setImage(FileObject fo) throws IOException {
        image = new ScaledImage(ImageIO.read(FileUtil.toFile(fo)));
        imagefield = new ImageDisplay(image.getImage());
        add(imagefield);
        InfoPanel infopanel = parent.getInfoPanel();
        infopanel.setImagewidth(image.getWidth());
        infopanel.setImageheight(image.getHeight());
    }

    public void zoomOut() {
        image.zoomOut();
        imagefield.setDisplayImage(image.getImage());
    }

    public void zoomIn() {
        image.zoomIn();
        imagefield.setDisplayImage(image.getImage());
    }

    public void zoomReset() {
        image.zoomReset();
        imagefield.setDisplayImage(image.getImage());
    }

    public String getZoomText() {
        return image.getZoomText();
    }
}
