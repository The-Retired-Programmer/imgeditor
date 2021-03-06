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
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;

/**
 * The Action to process image set generation (scaling images and creating html)
 *
 * It adds the "Process" menu item.
 *
 * @author Richard Linsdale (richard at theretiredprogrammer.uk)
 */
@ActionID(
        category = "Images",
        id = "uk.theretiredprogrammer.imageditor.imgsetgen"
)
@ActionRegistration(
        iconBase = "uk/theretiredprogrammer/imageditor/pictures.png",
        displayName = "Process"
)
@ActionReference(path = "Loaders/text/x-imgsetgen/Actions", position = 150)
public final class ImgsetgenAction implements ActionListener {

    private final DataObject context;

    /**
     * Constructor
     *
     * @param context the dataobject of the imgsetgen file
     */
    public ImgsetgenAction(DataObject context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        ImgsetgenWorker imgsetgenworker = new ImgsetgenWorker(context.getPrimaryFile());
        imgsetgenworker.run();
    }
}
