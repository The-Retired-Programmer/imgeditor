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

import java.awt.image.BufferedImage;

/**
 *
 * @author Richard Linsdale (richard at theretiredprogrammer.uk)
 */
public class ModelResize extends Model {

    private int width;
    private int height;

    public ModelResize(BufferedImage image) {
        super(image);
    }

    @Override
    public final boolean resetcontrols() {
        boolean res = false;
        if (width != image.getWidth()) {
            width = image.getWidth();
            fireRequired = true;
            res = true;
        }
        if (height != image.getHeight()) {
            height = image.getHeight();
            fireRequired = true;
            res = true;
        }
        return res;
    }

    @Override
    public BufferedImage transform() {
        return ImageProcessing.scale(image, width, height);
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    public boolean widthChanged(int newwidth) {
        return width == newwidth ? false : updatewidth(newwidth);
    }

    private boolean updatewidth(int newwidth) {
        width = newwidth;
        height = newwidth * image.getHeight() / image.getWidth();
        fireRequired = true;
        return true;
    }

    public boolean heightChanged(int newheight) {
        return height == newheight ? false : updateheight(newheight);
    }

    private boolean updateheight(int newheight) {
        height = newheight;
        width = newheight * image.getWidth() / image.getHeight();
        fireRequired = true;
        return true;
    }
}
