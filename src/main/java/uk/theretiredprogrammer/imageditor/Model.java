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
public abstract class Model {
    
    ChangeObserver<BufferedImage> observer = new ChangeObserver<>();
    
    BufferedImage image;
    
    boolean fireRequired = false;
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Model(BufferedImage image){
        this.image = image;
        resetcontrols();
    }
    
    public boolean setImage(BufferedImage image){
        this.image = image;
        boolean res = resetcontrols();
        fireRequired=true;
        return res;
    }
    
    public final boolean reset() {
        return resetcontrols() ? fireChange() : false;
        
    }
    
    private boolean fireChange() {
        fireRequired = true;
        return true;
    }
    
    public void fireImageChange(){
        if (fireRequired) {
            observer.fire(transform());
            fireRequired = false;
        }
        
    }
    
    public void addChangeListener(ChangeListener<BufferedImage> listener){
        observer.add(listener);
    }
    
    public void removeChangeListener(ChangeListener<BufferedImage> listener){
        observer.remove(listener);
    }
    
    public void clearChangeListeners() {
        observer.clear();
    }
    
    protected abstract boolean resetcontrols();
       
    public abstract BufferedImage transform();
}
