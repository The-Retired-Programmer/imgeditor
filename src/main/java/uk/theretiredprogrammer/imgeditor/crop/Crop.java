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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;

@ActionID(
        category = "Images",
        id = "uk.theretiredprogrammer.imgeditor.crop.Crop"
)
@ActionRegistration(
        iconBase = "uk/theretiredprogrammer/imgeditor/cut_red.png",
        displayName = "Crop"
)
@ActionReference(path = "Loaders/image/png-gif-jpeg-bmp/Actions", position = 350)
public final class Crop implements ActionListener {

    private final DataObject context;

    public Crop(DataObject context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        try {
            CropTopComponent tc = new CropTopComponent();
            tc.setImageFile(context.getPrimaryFile());
            tc.open();
            tc.requestActive();
        } catch (IOException ex) {
            // squash exception if a problem reading image file
        }
    }
}
