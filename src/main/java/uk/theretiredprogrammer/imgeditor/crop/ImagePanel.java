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

import java.awt.image.BufferedImage;
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
    private BufferedImage originalimage;
    private int ratioin = 1;
    private int ratioout = 1;

    public ImagePanel(CropTopComponent parent) {
        this.parent = parent;
    }

    public void addImage(FileObject fo) throws IOException {
        originalimage = ImageIO.read(FileUtil.toFile(fo));
        imagefield = new ImageDisplay(originalimage);
        add(imagefield);
        InfoPanel infopanel = parent.getInfoPanel();
        infopanel.setImagewidth(originalimage.getWidth());
        infopanel.setImageheight(originalimage.getHeight());
    }

    public String zoomOut() {
        imagefield.setDisplayImage(ImageProcessing.zoomOut(imagefield.getDisplayImage()));
        return ratioin != 1 ? ratio(ratioin / 2, 1) : ratio(1, ratioout * 2);
    }

    public String zoomIn() {
        imagefield.setDisplayImage(ImageProcessing.zoomIn(imagefield.getDisplayImage()));
        return ratioout != 1 ? ratio(1, ratioout / 2) : ratio(ratioin * 2, 1);
    }

    public String zoomReset() {
        imagefield.setDisplayImage(originalimage);
        return ratio(1, 1);
    }
    
    private String ratio(int in, int out) {
        ratioin = in;
        ratioout = out;
        return in+":"+out;
    }
}
