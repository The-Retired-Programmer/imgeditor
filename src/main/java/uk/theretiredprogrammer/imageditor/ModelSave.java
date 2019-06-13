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
import java.io.IOException;

/**
 *
 * @author Richard Linsdale (richard at theretiredprogrammer.uk)
 */
public class ModelSave extends Model {

    private final ImageInOut iio;
    private int percentage;
    private boolean inplace;
    private String savefoldername;
    private int minwidth;

    public ModelSave(BufferedImage image, ImageInOut iio) {
        super(image);
        this.iio = iio;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public int getMinwidth() {
        return minwidth;
    }

    public void setMinwidth(int minwidth) {
        this.minwidth = minwidth;
    }

    public boolean isInplace() {
        return inplace;
    }

    public void setInplace(boolean inplace) {
        this.inplace = inplace;
    }

    public String getSavefoldername() {
        return savefoldername;
    }

    public void setSavefoldername(String savefoldername) {
        this.savefoldername = savefoldername;
    }

    @Override
    public final boolean resetcontrols() {
        percentage = 80;
        inplace = false;
        savefoldername = "resources";
        minwidth = 100;
        return false;
    }

    @Override
    public BufferedImage transform() {
        return image;
    }

    public void save() {
        save(image);
    }

    private void save(BufferedImage img) {
        try {
            if (inplace) {
                iio.save(img);
            } else {
                iio.save(img, savefoldername);
            }
        } catch (IOException ex) {
            // need an error message here
        }
    }

    public void savemultiple() {
        save(image);
        savemultiple(image);
    }

    private void savemultiple(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        while (true) {
            width = width * percentage / 100;
            if (width < minwidth) {
                return;
            }
            height = height * percentage / 100;
            save(ImageProcessing.scale(img, width, height));
        }
    }
}
