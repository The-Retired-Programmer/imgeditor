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
 * The abstract Class which provides the basis for the models used in the image
 * editor.
 *
 * Each module represents a single transform or major action.
 *
 * Models are chained using the observer/listener pattern so that changes to an
 * image in one model are passed along the chain.
 *
 * @author Richard Linsdale (richard at theretiredprogrammer.uk)
 */
public abstract class Model {

    ChangeObserver<BufferedImage> observer = new ChangeObserver<>();

    BufferedImage image;

    boolean fireRequired = false;

    /**
     * Constructor - create a model with a defined input image
     * 
     * @param image the input image
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Model(BufferedImage image) {
        this.image = image;
        resetcontrols();
    }

    /**
     * Set a new input image for this model.
     * 
     * The controls for the model are reset, so the model restarts itself when presented with a new image.
     * 
     * @param image the new input image
     * @return true if controls were reset;
     */
    public boolean setImage(BufferedImage image) {
        this.image = image;
        boolean res = resetcontrols();
        fireRequired = true;
        return res;
    }

    /**
     * Reset the model - firing any listeners, if reset changes any associated control value.
     * 
     * @return true if any control changed
     */
    public final boolean reset() {
        return resetcontrols() ? fireChange() : false;

    }

    private boolean fireChange() {
        fireRequired = true;
        return true;
    }

    /**
     * Fire the change if it has been requested.
     */
    public void fireImageChange() {
        if (fireRequired) {
            observer.fire(transform());
            fireRequired = false;
        }
    }

    /**
     * add a listener for changes to this model
     * 
     * @param listener the listener
     */
    public void addChangeListener(ChangeListener<BufferedImage> listener) {
        observer.add(listener);
    }

    /**
     * remove a listener for changes to this model
     * 
     * @param listener the listener
     */
    public void removeChangeListener(ChangeListener<BufferedImage> listener) {
        observer.remove(listener);
    }

    /**
     * clear all change listeners for this model
     */
    public void clearChangeListeners() {
        observer.clear();
    }

    /**
     * Reset all controls for this model.
     * 
     * @return true if controls have changed.
     */
    protected abstract boolean resetcontrols();

    /**
     * Create an image which is based on the input image, transformed as defined by the
     * various model parameters to create the resulting image
     * 
     * @return the transformed) image.
     */
    public abstract BufferedImage transform();
}
