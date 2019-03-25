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

/**
 *
 * @author richard
 */
public class Zoom {
     public int zoomin;
     public int zoomout;
     
     public Zoom (){
         zoomin = 1;
         zoomout = 1;
     }
     
      public String getZoomText() {
        return zoomin + ":" + zoomout;
    }

    public void zoomOut() {
        if (zoomin != 1) {
            zoomin /= 2;
        } else {
            zoomout *= 2;
        }
    }

    public void zoomIn() {
        if (zoomout != 1) {
            zoomout /= 2;
        } else {
            zoomin *= 2;
        }
    }

    public void zoomReset() {
        zoomin = 1;
        zoomout = 1;
    }
}
