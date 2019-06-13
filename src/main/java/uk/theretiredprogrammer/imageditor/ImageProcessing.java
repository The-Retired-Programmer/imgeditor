/*
 * Copyright 2015-2019 richard linsdale.
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

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;


/**
 * Image process methods
 * 
 * @author Richard Linsdale (richard at theretiredprogrammer.uk)
 */
public class ImageProcessing {
    
    /**
     * Create a image which is zoomed out.
     * 
     * @param img the original image
     * @param scale the zoom scaling
     * @return the zoomed image
     */
    public static BufferedImage zoomOut(BufferedImage img, int scale){
        return scaleImage(img, img.getWidth()/scale, img.getHeight()/scale, 
                img.getTransparency() == Transparency.OPAQUE
                ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB);
    }
    
    /**
     * Create a image which is zoomed in.
     * 
     * @param img the original image
     * @param scale the zoom scaling
     * @return the zoomed image
     */
    public static BufferedImage zoomIn(BufferedImage img, int scale){
        return scaleImage(img, img.getWidth()*scale, img.getHeight()*scale, 
                img.getTransparency() == Transparency.OPAQUE
                ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB);
    }
    
    /**
     * Scale an image to new width/height.
     * 
     * @param img the original image
     * @param width the new image width
     * @param height the new image height
     * @return the scaled image
     */
    public static BufferedImage scale(BufferedImage img, int width, int height){
        return scaleImage(img, width, height, 
                img.getTransparency() == Transparency.OPAQUE
                ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB);
    }

    private static BufferedImage scaleImage(BufferedImage img, int w, int h, int type) {
        BufferedImage tmp = new BufferedImage(w, h, type);
        Graphics2D g2 = tmp.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.drawImage(img, 0, 0, w, h, null);
        g2.dispose();
        return tmp;
    }
}