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
 * The save model - handles save action which is applied to its defined image
 *
 * The save model supports saving the image (save) or saving a series of images
 * (save multiple). Images can be saved in-place (ie in the same folder as the
 * original input image) or in an alternative folder.
 *
 * @author Richard Linsdale (richard at theretiredprogrammer.uk)
 */
public class ModelSave extends Model {

    private final ImageInOut iio;
    private int percentage;
    private boolean inplace;
    private String savefoldername;
    private int minwidth;

    /**
     * Constructor
     *
     * @param image the defined image for this model
     * @param iio the image input/output object for image IO
     */
    public ModelSave(BufferedImage image, ImageInOut iio) {
        super(image);
        this.iio = iio;
    }

    /**
     * Get the percentage for multiple image size decreases.
     *
     * @return the percentage (int 1 to 99)
     */
    public int getPercentage() {
        return percentage;
    }

    /**
     * Set the percentage for multiple image size decreases.
     *
     * @param percentage the percentage (int 1 to 99)
     */
    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    /**
     * Get the minimum width for a multiple image size set.
     *
     * @return the minimum width
     */
    public int getMinwidth() {
        return minwidth;
    }

    /**
     * Set the minimum width for a multiple image size set.
     *
     * @param minwidth the minimum width
     */
    public void setMinwidth(int minwidth) {
        this.minwidth = minwidth;
    }

    /**
     * Test if in-place saves are to be used.
     *
     * @return true if in-place saves are required
     */
    public boolean isInplace() {
        return inplace;
    }

    /**
     * Set the in-place save option.
     *
     * @param inplace true if in-place save(s) required, otherwise false
     */
    public void setInplace(boolean inplace) {
        this.inplace = inplace;
    }

    /**
     * Get the folder name to be used if alternative folder save(s) required.
     *
     * @return the folder name
     */
    public String getSavefoldername() {
        return savefoldername;
    }

    /**
     * Set the folder name to be used if alternative folder save(s) required.
     *
     * @param savefoldername the folder name
     */
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

    /**
     * Save the current image - (single image save).
     */
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

    /**
     * Save the current image - (multiple image save).
     *
     * As well as the original image, multiple smaller images are created.
     *
     */
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
