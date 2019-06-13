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
public class ModelCrop extends Model {

    private int left;
    private int top;
    private int width;
    private int height;

    public ModelCrop(BufferedImage image) {
        super(image);
    }

    @Override
    public final boolean resetcontrols() {
        boolean res = false;
        if (left != 1) {
            left = 1;
            fireRequired = true;
            res = true;
        }
        if (top != 1){
            top = 1;
            fireRequired = true;
            res = true;
        }
        if (width != image.getWidth()){
            width = image.getWidth();
            fireRequired = true;
            res = true;
        }
        if (height != image.getHeight()) {
            height = image.getHeight();
            fireRequired = true;
            res = true;
        }
        return true;
    }

    @Override
    public BufferedImage transform() {
        return image.getSubimage(left - 1, top - 1, width, height);
    }
    
    public boolean leftChanged(int newleft) {
        return left == newleft ? false : changeleft(newleft);
    }
 
    private boolean changeleft(int newleft) {
        left = newleft + width - 1 > image.getWidth()
                ? image.getWidth() - width + 1
                : newleft;
        fireRequired = true;
        return true;
    }

    public  boolean topChanged(int newtop) {
        return top == newtop ? false : changetop(newtop);
    }
    
    private boolean changetop(int newtop){
        top = newtop + height - 1 > image.getHeight()
                ? image.getHeight() - height + 1
                : newtop;
        fireRequired = true;
        return true;
    }

    public boolean widthChanged(int newwidth) {
        return width == newwidth? false: changewidth(newwidth);
    }
    
    private boolean changewidth(int newwidth) {
        width = newwidth + left - 1 > image.getWidth()
                ? image.getWidth() - left + 1
                : newwidth;
        fireRequired = true;
        return true;
    }

    public boolean heightChanged(int newheight) {
        return height == newheight? false: changeheight(newheight);
    }
    
    private boolean changeheight(int newheight) {
        height = newheight + top - 1 > image.getHeight()
                ? image.getHeight() - top + 1
                : newheight;
        fireRequired = true;
        return true;
    }

    /**
     * @return the left
     */
    public int getLeft() {
        return left;
    }

    /**
     * @return the top
     */
    public int getTop() {
        return top;
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
}
