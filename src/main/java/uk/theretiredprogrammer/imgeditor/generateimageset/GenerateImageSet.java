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
package uk.theretiredprogrammer.imgeditor.generateimageset;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;

@ActionID(
        category = "Images",
        id = "uk.theretiredprogrammer.imgeditor.generateimageset.GenerateImageSet"
)
@ActionRegistration(
        iconBase = "uk/theretiredprogrammer/imgeditor/folder_image.png",
        displayName = "Generate Imageset"
)
@ActionReference(path = "Loaders/image/png-gif-jpeg-bmp/Actions", position = 370, separatorAfter = 375)
public final class GenerateImageSet implements ActionListener {

    private final DataObject context;

    public GenerateImageSet(DataObject context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        
    }
}
