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

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JScrollPane;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.filesystems.FileObject;
import org.openide.windows.TopComponent;

/**
 * The image editor display (in the editor area).
 *
 * Contains both the image display, controls and various feedback areas.
 *
 * @author Richard Linsdale (richard at theretiredprogrammer.uk)
 */
@ConvertAsProperties(
        dtd = "-//uk.theretiredprogrammer.imageditor//ImageEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "ImageEditorTopComponent",
        iconBase = "uk/theretiredprogrammer/imageditor/paintbrush.png",
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
public class ImageEditorTopComponent extends TopComponent {

    /**
     * Contructor
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public ImageEditorTopComponent() {
        setName("ImageEditor");
        setToolTipText("Image Editor");
    }

    /**
     * Setup the image editor components within the top component.
     *
     * @param fo The fileobject of the image file to be edited/displayed
     * @throws IOException if a problem
     */
    public void configure(FileObject fo) throws IOException {
        String filename = fo.getNameExt();
        setDisplayName(filename);
        ImageInOut iio = new ImageInOut(fo);
        BufferedImage image = iio.getImage();
        // config the message panel
        MessagePanel messagePanel = new MessagePanel();
        // configure the image panel
        ImagePanel imagePanel = new ImagePanel();
        // configure the info panel
        ModelZoom zoommodel = new ModelZoom(image);
        zoommodel.addChangeListener(img -> imagePanel.display(img));
        InfoPanel infoPanel = new InfoPanel(zoommodel);
        infoPanel.setFilename(filename);
        infoPanel.setFilepath(fo.getParent().getPath());
        infoPanel.setImageheight(image.getHeight());
        infoPanel.setImagewidth(image.getWidth());
        // configure the control panel
        ModelCrop cropmodel = new ModelCrop(image);
        ModelResize resizemodel = new ModelResize(image);
        ModelSave savemodel = new ModelSave(image, iio);
        ControlPanel controlPanel = new ControlPanel(resizemodel, cropmodel, savemodel, zoommodel);
        resizemodel.addChangeListener(img -> controlPanel.resizeImageChanged(img));
        cropmodel.addChangeListener(img -> controlPanel.cropImageChanged(img));
        savemodel.addChangeListener(img -> controlPanel.saveImageChanged(img));
        //
        // create the tc layout and insert all required panels
        //
        setLayout(new BorderLayout());
        //left hand is control
        JScrollPane controlScrollPane = new JScrollPane();
        add(controlScrollPane, BorderLayout.WEST);
        controlScrollPane.setViewportView(controlPanel);
        // centre is image
        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane, BorderLayout.CENTER);
        scrollPane.setViewportView(imagePanel);
        // info is top
        add(infoPanel, BorderLayout.NORTH);
        // message is bottom
        add(messagePanel, BorderLayout.SOUTH);
    }

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
}
