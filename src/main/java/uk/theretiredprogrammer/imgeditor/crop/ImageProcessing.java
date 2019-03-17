/*
 * Copyright 2015-2017 Richard Linsdale.
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
    
    public static BufferedImage zoomOut(BufferedImage img){
        return scaleImage(img, img.getWidth()/2, img.getHeight()/2, 
                img.getTransparency() == Transparency.OPAQUE
                ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB);
    }
    
    public static BufferedImage zoomIn(BufferedImage img){
        return scaleImage(img, img.getWidth()*2, img.getHeight()*2, 
                img.getTransparency() == Transparency.OPAQUE
                ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB);
    }

//    private static final int SMALLTHUMBNAILHEIGHT = 100;
//    private static final int MEDIUMTHUMBNAILHEIGHT = 400;
//    private static final int MEDIUM2SMALLPOWER = 2; // ie small* 2^power = medium
    private BufferedImage img;
//    private BufferedImage mediumThumbnail;
//    private BufferedImage smallThumbnail;
//
//    public void setImage(BufferedImage img) {
//        this.img = img;
//        int type = (img.getTransparency() == Transparency.OPAQUE)
//                ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
//        this.mediumThumbnail = null;
//        this.smallThumbnail = null;
//    }
//
//    public void makeThumbnails() {
//        int type = (img.getTransparency() == Transparency.OPAQUE)
//                ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
//        int ih = img.getHeight();
//        int iw = img.getWidth();
//        if (ih > MEDIUMTHUMBNAILHEIGHT) {
//            mediumThumbnail = reduceImage(img, MEDIUMTHUMBNAILHEIGHT, type);
//            smallThumbnail = reduceImageByHalfs(mediumThumbnail, MEDIUM2SMALLPOWER, type);
//        } else if (ih == MEDIUMTHUMBNAILHEIGHT) {
//            mediumThumbnail = img;
//            smallThumbnail = reduceImageByHalfs(img, MEDIUM2SMALLPOWER, type);
//        } else if (ih > SMALLTHUMBNAILHEIGHT) { // intermediate size image - mediumthumbnail is an expansion - small is a reduction 
//            mediumThumbnail = scaleImage(img, width(iw, ih, MEDIUMTHUMBNAILHEIGHT), MEDIUMTHUMBNAILHEIGHT, type);
//            smallThumbnail = reduceImage(img, SMALLTHUMBNAILHEIGHT, type);
//        } else if (ih == SMALLTHUMBNAILHEIGHT) {
//            mediumThumbnail = scaleImage(img, width(iw, ih, MEDIUMTHUMBNAILHEIGHT), MEDIUMTHUMBNAILHEIGHT, type);
//            smallThumbnail = img;
//        } else { // tiny image - both thumbnails are in fact expansions
//            smallThumbnail = scaleImage(img, width(iw, ih, SMALLTHUMBNAILHEIGHT), SMALLTHUMBNAILHEIGHT, type);
//            mediumThumbnail = scaleImage(img, width(iw, ih, MEDIUMTHUMBNAILHEIGHT), MEDIUMTHUMBNAILHEIGHT, type);
//        }
//    }
//
//    private int width(int w, Integer h, int th) {
//        Float res = w / (h.floatValue() / th);
//        return res.intValue();
//    }
//
//    private BufferedImage reduceImage(BufferedImage img, int targetHeight, int type) {
//        int h = img.getHeight();
//        // calculate the maximum power of targetHeight steps which is less that H
//        int thm = targetHeight;
//        int power = 0;
//        while (h / thm > 1) {
//            thm = thm + thm;
//            power++;
//        }
//        // first reduce the image to an exact multiple of of target height
//        BufferedImage ret = scaleImage(img, width(img.getWidth(), h, thm), thm, type);
//        // and then do repeated exact binary reductions
//        return reduceImageByHalfs(ret, power, type);
//    }
//
//    private BufferedImage reduceImageByHalfs(BufferedImage img, int power, int type) {
//        BufferedImage ret = img;
//        int w = img.getWidth();
//        int h = img.getHeight();
//        while (power > 0) {
//            w = w / 2;
//            h = h / 2;
//            ret = scaleImage(ret, w, h, type);
//            power--;
//        }
//        return ret;
//    }
//
    private static BufferedImage scaleImage(BufferedImage img, int w, int h, int type) {
        BufferedImage tmp = new BufferedImage(w, h, type);
        Graphics2D g2 = tmp.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.drawImage(img, 0, 0, w, h, null);
        g2.dispose();
        return tmp;
    }
//
//    public BufferedImage getMediumThumbnail() {
//        return mediumThumbnail;
//    }
//
//    public BufferedImage getSmallThumbnail() {
//        return smallThumbnail;
//    }
//*************************   below here was comments out in original source  *******************************
//    // source of this code is: http://today.java.net/pub/a/today/2007/04/03/perils-of-image-getscaledinstance.html
//    
//    /**
//     * Convenience method that returns a scaled instance of the
//     * provided {@code BufferedImage}.
//     *
//     * @param img the original image to be scaled
//     * @param targetWidth the desired width of the scaled instance,
//     *    in pixels
//     * @param targetHeight the desired height of the scaled instance,
//     *    in pixels
//     * @param hint one of the rendering hints that corresponds to
//     *    {@code RenderingHints.KEY_INTERPOLATION} (e.g.
//     *    {@code RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR},
//     *    {@code RenderingHints.VALUE_INTERPOLATION_BILINEAR},
//     *    {@code RenderingHints.VALUE_INTERPOLATION_BICUBIC})
//     * @param higherQuality if true, this method will use a multi-step
//     *    scaling technique that provides higher quality than the usual
//     *    one-step technique (only useful in downscaling cases, where
//     *    {@code targetWidth} or {@code targetHeight} is
//     *    smaller than the original dimensions, and generally only when
//     *    the {@code BILINEAR} hint is specified)
//     * @return a scaled version of the original {@code BufferedImage}
//     */
//    public BufferedImage getScaledInstance(BufferedImage img,
//                                           int targetWidth,
//                                           int targetHeight,
//                                           Object hint,
//                                           boolean higherQuality)
//    {
//        int type = (img.getTransparency() == Transparency.OPAQUE) ?
//            BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
//        BufferedImage ret = (BufferedImage)img;
//        int w, h;
//        if (higherQuality) {
//            // Use multi-step technique: start with original size, then
//            // scale down in multiple passes with drawImage()
//            // until the target size is reached
//            w = img.getWidth();
//            h = img.getHeight();
//        } else {
//            // Use one-step technique: scale directly from original
//            // size to target size with a single drawImage() call
//            w = targetWidth;
//            h = targetHeight;
//        }
//        
//        do {
//            if (higherQuality && w > targetWidth) {
//                w /= 2;
//                if (w < targetWidth) {
//                    w = targetWidth;
//                }
//            }
//
//            if (higherQuality && h > targetHeight) {
//                h /= 2;
//                if (h < targetHeight) {
//                    h = targetHeight;
//                }
//            }
//
//            BufferedImage tmp = new BufferedImage(w, h, type);
//            Graphics2D g2 = tmp.createGraphics();
//            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
//            g2.drawImage(ret, 0, 0, w, h, null);
//            g2.dispose();
//
//            ret = tmp;
//        } while (w != targetWidth || h != targetHeight);
//
//        return ret;
//    }
}