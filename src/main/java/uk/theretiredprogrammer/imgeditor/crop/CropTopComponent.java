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

import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.JScrollPane;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.filesystems.FileObject;
import org.openide.windows.TopComponent;

/**
 *
 * @author richard
 */
/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//uk.theretiredprogrammer.imgeditor.crop//Crop//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "CropTopComponent",
        iconBase = "uk/theretiredprogrammer/imgeditor/cut_red.png",
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
public class CropTopComponent extends TopComponent {
    
        
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public CropTopComponent() {
        setName("Crop");
        setToolTipText("This is a Crop window");
    }

    public void configure(FileObject fo) throws IOException {
        String filename = fo.getNameExt();
        setDisplayName(filename);
        // setup the control panel
        ControlPanel controlPanel = new ControlPanel();
        // config the message panel
        MessagePanel messagePanel = new MessagePanel();
        
        // configure the info panel
        InfoPanel infoPanel = new InfoPanel(controlPanel);
        infoPanel.setFilename(filename);
        infoPanel.setFilepath(fo.getParent().getPath());
        // configure the image panel
        ImagePanel imagePanel = new ImagePanel();
        // configure the control panel
        controlPanel.setup(imagePanel, messagePanel, fo);
        //
        infoPanel.setImageheight(controlPanel.getImageHeight());
        infoPanel.setImagewidth(controlPanel.getImageWidth());
        // create the tc layout and insert all required panels
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
