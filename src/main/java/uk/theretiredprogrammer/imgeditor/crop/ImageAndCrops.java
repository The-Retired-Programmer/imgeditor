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
import java.io.OutputStream;
import javax.imageio.ImageIO;
import org.openide.filesystems.FileObject;

/**
 *
 * @author richard
 */
public class ImageAndCrops {

    private final ScaledImage scaledimage;
    private final Zoom zoom;
    private String filetype;
    private FileObject folder;
    private String filename;
    private String fileext;
    private int left;
    private int right;
    private int top;
    private int bottom;
    private int width;
    private int height;

    public ImageAndCrops(BufferedImage image, Zoom zoom) {
        this.zoom = zoom;
        scaledimage = new ScaledImage(image, zoom);
        reset();
    }
    
    public final void reset(){
        left = top = 1;
        right = width = scaledimage.getFullImage().getWidth();
        bottom = height = scaledimage.getFullImage().getHeight();
    }
    
    public void setDefaultFiletype(FileObject fo) {
        String mimetype = fo.getMIMEType();
        filetype = mimetype.startsWith("image/")
                ? mimetype.substring(6)
                : "unknown";
        folder = fo.getParent();
        filename = fo.getName();
        fileext = fo.getExt();
    }
    
    public void crop() throws IOException {
        BufferedImage cropped = scaledimage.getFullImage().getSubimage(left-1, top-1, width, height);
        String f = filename+"-"+cropped.getWidth()+"x"+cropped.getHeight()+"."+fileext;
        try (OutputStream out = folder.createAndOpen(f)) {
            ImageIO.write(cropped, filetype, out);
        }
    }
    
    public BufferedImage getSizedImage() {
        return scaledimage.getSizedImage();
    }
    
    /**
     * @return the left
     */
    public int getLeft() {
        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(int left) {
        this.left = left;
    }

    /**
     * @return the right
     */
    public int getRight() {
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(int right) {
        this.right = right;
    }

    /**
     * @return the top
     */
    public int getTop() {
        return top;
    }

    /**
     * @param top the top to set
     */
    public void setTop(int top) {
        this.top = top;
    }

    /**
     * @return the bottom
     */
    public int getBottom() {
        return bottom;
    }

    /**
     * @param bottom the bottom to set
     */
    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getImageWidth() {
        return scaledimage.getFullImage().getWidth();
    }
    
    public int getImageHeight() {
        return scaledimage.getFullImage().getHeight();
    }
    
    public int getScaledLeft() {
        return scaledimage.zoomStart(left);
    }
    
    public int getScaledRight() {
        return scaledimage.zoomEnd(right);
    }
    
    public int getScaledTop() {
        return scaledimage.zoomStart(top);
    }
    
    public int getScaledBottom() {
        return scaledimage.zoomEnd(bottom);
    }
}
