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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import org.netbeans.api.settings.ConvertAsProperties;
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

    private final JTextField bottom = new JTextField();
    private final JTextField height = new JTextField();
    private final JTextField left = new JTextField();
    private final JTextField right = new JTextField();
    private final JTextField top = new JTextField();
    private final JTextField width = new JTextField();
    private ImageDisplayComponent imgfield;
    private final JLabel zoomratiolabel = new JLabel();
    //
    private final JScrollPane scrollPane = new JScrollPane();
    private final JPanel imgpanel = new JPanel();
    private final JPanel controlPanel = new JPanel();
    
    public CropTopComponent() {
        initComponents();
        setName("Crop");
        setToolTipText("This is a Crop window");
    }

    private void initCentredLabel(String text, JPanel container, int row) {
        initCentredLabel(new JLabel(), text, container, row);
    }

    private void initCentredLabel(JLabel label, String text, JPanel container, int row) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(4,4,4,4);
        c.weightx = 1.0;
        c.gridx = 0;
        c.gridy = row;
        c.gridwidth = 2;
        //
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setText(text);
        container.add(label, c);
    }
    
    private void initDisabledLabeledTextField(JTextField field, String text,
            ActionListener actionListener, JPanel container, int row) {
        field.setEnabled(false);
        field.setBackground(new java.awt.Color(128, 128, 128));
        initLabeledTextField(field, text, actionListener, container, row);
    }

    private void initLabeledTextField(JTextField field, String text,
            ActionListener actionListener, JPanel container, int row) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(4,4,4,4);
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = row;
        c.gridwidth = 1;
        JLabel label = new JLabel();
        label.setText(text);
        label.setLabelFor(field);
        container.add(label, c);
        c.gridx++;
        field.setHorizontalAlignment(JTextField.RIGHT);
        field.setColumns(6);
        field.addActionListener(actionListener);
        container.add(field, c);
    }

    private void initLabeledCheckbox(String text, 
            ItemListener itemListener, JPanel container, int row) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(4,4,4,4);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.gridx = 0;
        c.gridy = row;
        c.gridwidth = 2;
        JCheckBox field = new JCheckBox();
        field.addItemListener(itemListener);
        field.setText(text);
        field.setHorizontalTextPosition(SwingConstants.LEFT);
        container.add(field, c);
    }
    
    private void initCentredButton(String text, 
            ActionListener actionListener, JPanel container, int row) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(4,4,4,4);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.gridx = 0;
        c.gridy = row;
        c.gridwidth = 2;
        JButton field = new JButton();
        field.addActionListener(actionListener);
        field.setText(text);
        container.add(field, c);
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());
        controlPanel.setLayout(new GridBagLayout());
        add(controlPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        scrollPane.setViewportView(imgpanel);
        int row = 0;
        initCentredLabel("CROP CONTROL", controlPanel, row++);
        initLabeledCheckbox("Use Width/Height", this::usewidthheightItemStateChanged, controlPanel, row++);
        initLabeledTextField(left, "Left:", this::leftActionPerformed, controlPanel, row++);
        initLabeledTextField(right, "Right:", this::rightActionPerformed, controlPanel, row++);
        initLabeledTextField(top, "Top:", this::topActionPerformed, controlPanel, row++);
        initLabeledTextField(bottom, "Bottom:", this::bottomActionPerformed, controlPanel, row++);
        initDisabledLabeledTextField(width, "Width:", this::widthActionPerformed, controlPanel, row++);
        initDisabledLabeledTextField(height, "Height:", this::heightActionPerformed, controlPanel, row++);
        row++;
        initCentredLabel("ZOOM DISPLAY", controlPanel, row++);
        initCentredButton("Zoom Out", this::zoomOutActionPerformed, controlPanel, row++);
        initCentredButton("Zoom In", this::zoomInActionPerformed, controlPanel, row++);
        initCentredButton("Zoom Reset", this::zoomResetActionPerformed, controlPanel, row++);
        initCentredLabel(zoomratiolabel, "1:1", controlPanel, row++);
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

    public void addImage(BufferedImage img) {
        imgfield = new ImageDisplayComponent(img);
        imgpanel.add(imgfield);
    }

    private void leftActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void rightActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void topActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void bottomActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void widthActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void heightActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void usewidthheightItemStateChanged(ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.DESELECTED) {
            // disable width/height
            disablefield(width);
            disablefield(height);
            // use right/bottom
            enablefield(right);
            enablefield(bottom);
        } else {
            // disable right/bottom
            disablefield(right);
            disablefield(bottom);
            // use width/height
            enablefield(width);
            enablefield(height);
        }
    }
    
    private void zoomOutActionPerformed(ActionEvent evt) {
        zoomratiolabel.setText(imgfield.zoomOut());
    }
    
    private void zoomInActionPerformed(ActionEvent evt) {
        zoomratiolabel.setText(imgfield.zoomIn());
    }
    
    private void zoomResetActionPerformed(ActionEvent evt) {
        zoomratiolabel.setText(imgfield.zoomReset());
    }

    private void enablefield(JTextField field) {
        field.setEnabled(true);
        field.setBackground(new java.awt.Color(255, 255, 255));
    }

    private void disablefield(JTextField field) {
        field.setEnabled(false);
        field.setBackground(new java.awt.Color(128, 128, 128));
    }
}
