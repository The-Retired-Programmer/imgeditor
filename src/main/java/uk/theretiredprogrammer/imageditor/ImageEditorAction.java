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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;

/**
 *
 * @author Richard Linsdale (richard at theretiredprogrammer.uk)
 */
@ActionID(
        category = "Images",
        id = "uk.theretiredprogrammer.imageditor"
)
@ActionRegistration(
        iconBase = "uk/theretiredprogrammer/imageditor/paintbrush.png",
        displayName = "Open with Img Editor"
)
@ActionReference(path = "Loaders/image/png-gif-jpeg-bmp/Actions", position = 100)
public final class ImageEditorAction implements ActionListener {

    private final DataObject context;

    public ImageEditorAction(DataObject context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        try {
            ImageEditorTopComponent tc = new ImageEditorTopComponent();
            tc.configure(context.getPrimaryFile());
            tc.open();
            tc.requestActive();
        } catch (IOException ex) {
            // squash exception if a problem reading image file
        }
    }
}
