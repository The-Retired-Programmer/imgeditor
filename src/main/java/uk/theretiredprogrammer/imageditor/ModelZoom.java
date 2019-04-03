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
package uk.theretiredprogrammer.imageditor;

import java.awt.image.BufferedImage;

/**
 *
 * @author richard
 */
public class ModelZoom extends Model {

    public int zoomin;
    public int zoomout;

    public ModelZoom(BufferedImage image) {
        super(image);
        zoomin = 1;
        zoomout = 1;
    }

    @Override
    public final boolean resetcontrols() {
        return false;
    }

    @Override
    public BufferedImage transform() {
        if (zoomin > 1) {
            return ImageProcessing.zoomIn(image, zoomin);
        } else if (zoomout > 1) {
            return ImageProcessing.zoomOut(image, zoomout);
        } else {
            return image;
        }
    }

    public void zoomout() {
        if (zoomin != 1) {
            zoomin /= 2;
        } else {
            zoomout *= 2;
        }
        fireRequired = true;
        fireImageChange();
    }

    public void zoomin() {
        if (zoomout != 1) {
            zoomout /= 2;
        } else {
            zoomin *= 2;
        }
        fireRequired = true;
        fireImageChange();
    }

    public void zoomreset() {
        zoomin = 1;
        zoomout = 1;
        fireRequired = true;
        fireImageChange();
    }
    
    public String getZoomRatio() {
        return zoomin + ":" + zoomout;
    }
}