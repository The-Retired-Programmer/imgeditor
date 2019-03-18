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
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
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

    private final JTextField bottom;
    private final JTextField height;
    private final JTextField left;
    private final JTextField right;
    private final JTextField top;
    private final JTextField width;
    private ImageDisplay imgfield;
    private final JLabel zoomratiolabel;
    private final JLabel imagewidth;
    private final JLabel imageheight;
    private final JLabel filename;
    private final JLabel filepath;
    //
    private final JScrollPane scrollPane = new JScrollPane();
    private final JPanel imgpanel = new JPanel();
    private final JScrollPane controlScrollPane = new JScrollPane();
    private final JPanel controlPanel = new JPanel();
    private final JPanel infoPanel = new JPanel();
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public CropTopComponent() {
        setName("Crop");
        setToolTipText("This is a Crop window");
        //
        this.setLayout(new BorderLayout());
        controlPanel.setLayout(new GridBagLayout());
        add(controlScrollPane, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        scrollPane.setViewportView(imgpanel);
        controlScrollPane.setViewportView(controlPanel);
        infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.X_AXIS));
        add(infoPanel,BorderLayout.NORTH);
        //doubleLabel
        filename = doubleLabel("File:", "xyz", infoPanel);
        filepath = doubleLabel("File location:", "/abc/def/ghi/", infoPanel);
        imagewidth = doubleLabel("Image width:", "1234", infoPanel);
        imageheight = doubleLabel("Image height:", "7890", infoPanel);
        int row = 0;
        centredLabel("CROP CONTROL", controlPanel, row++);
        labeledCheckbox("Use Width/Height", this::usewidthheightItemStateChanged, controlPanel, row++);
        left = labeledTextField("Left:", this::leftActionPerformed, controlPanel, row++);
        right = labeledTextField("Right:", this::rightActionPerformed, controlPanel, row++);
        top = labeledTextField("Top:", this::topActionPerformed, controlPanel, row++);
        bottom = labeledTextField("Bottom:", this::bottomActionPerformed, controlPanel, row++);
        width = labeledTextField("Width:", this::widthActionPerformed, controlPanel, row++);
        disablefield(width);
        height = labeledTextField("Height:", this::heightActionPerformed, controlPanel, row++);
        disablefield(height);
        row++;
        centredLabel("ZOOM DISPLAY", controlPanel, row++);
        centredButton("Zoom Out", this::zoomOutActionPerformed, controlPanel, row++);
        centredButton("Zoom In", this::zoomInActionPerformed, controlPanel, row++);
        centredButton("Zoom Reset", this::zoomResetActionPerformed, controlPanel, row++);
        zoomratiolabel = doubleLabel("Zoom Ratio","1:1", controlPanel, row++);
    }
    
    public void setImageFile(FileObject fo) {
        filename.setText(fo.getNameExt());
        filepath.setText(fo.getParent().getPath());
    }
    
    private JLabel doubleLabel(String text, String text2, JPanel container) {
        JLabel field = new JLabel(text2);
        //
        JLabel label = new JLabel(text);
        label.setBorder(new EmptyBorder(2,6,2,2));
        label.setLabelFor(field);
        container.add(label);
        field.setBorder(new EmptyBorder(2,2,2,6));
        field.setHorizontalAlignment(JLabel.RIGHT);
        container.add(field);
        return field;
    }

    public ImageDisplay centredImage(BufferedImage img) {
        imgfield = new ImageDisplay(img);
        imgpanel.add(imgfield);
        imagewidth.setText(Integer.toString(img.getWidth()));
        imageheight.setText(Integer.toString(img.getHeight()));
        return imgfield;
    }
    
    private JLabel centredLabel(String text, JPanel container, int row) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(4,4,4,4);
        c.weightx = 1.0;
        c.gridx = 0;
        c.gridy = row;
        c.gridwidth = 2;
        //
        JLabel label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setText(text);
        container.add(label, c);
        return label;
    }
    
    private JLabel doubleLabel(String text, String text2, JPanel container, int row) {
        JLabel field = new JLabel(text2);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(4,4,4,4);
        c.weightx = 1.0;
        c.gridx = 0;
        c.gridy = row;
        c.gridwidth = 1;
        //
        JLabel label = new JLabel();
        label.setText(text);
        label.setLabelFor(field);
        container.add(label, c);
        c.gridx++;
        field.setHorizontalAlignment(JLabel.RIGHT);
        container.add(field, c);
        return field;
    }
    
    private JTextField labeledTextField(String text, ActionListener actionListener, JPanel container, int row) {
        JTextField field = new JTextField();
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
        return field;
    }
    
    private void enablefield(JTextField field) {
        field.setEnabled(true);
        field.setBackground(new java.awt.Color(255, 255, 255));
    }

    private void disablefield(JTextField field) {
        field.setEnabled(false);
        field.setBackground(new java.awt.Color(128, 128, 128));
    }

    private JCheckBox labeledCheckbox(String text, 
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
        return field;
    }
    
    private JButton centredButton(String text, 
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
        return field;
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
}
