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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;

/**
 *
 * @author richard
 */
public class VerticalGridBagPanel extends JPanel {
    
    private int row;
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public VerticalGridBagPanel() {
        setLayout(new GridBagLayout());
        row = 0;
    }
    
    public final JLabel centredLabel(String text) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(4,4,4,4);
        c.weightx = 1.0;
        c.gridx = 0;
        c.gridy = row++;
        c.gridwidth = 2;
        //
        JLabel label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setText(text);
        add(label, c);
        return label;
    }
    
    public final JLabel doubleLabel(String text, String text2) {
        JLabel field = new JLabel(text2);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(4,4,4,4);
        c.weightx = 1.0;
        c.gridx = 0;
        c.gridy = row++;
        c.gridwidth = 1;
        //
        JLabel label = new JLabel();
        label.setText(text);
        label.setLabelFor(field);
        add(label, c);
        c.gridx++;
        field.setHorizontalAlignment(JLabel.RIGHT);
        add(field, c);
        return field;
    }
    
    public final ControlTextField labeledTextField(String text, ActionListener actionListener) {
        ControlTextField field = new ControlTextField(actionListener);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(4,4,4,4);
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = row++;
        c.gridwidth = 1;
        JLabel label = new JLabel();
        label.setText(text);
        label.setLabelFor(field);
        add(label, c);
        c.gridx++;
        add(field, c);
        return field;
    }
    
    public final ControlIntSpinnerField labeledIntSpinnerField(String text, int init, int min, int max, ChangeListener changeListener) {
        ControlIntSpinnerField field = new ControlIntSpinnerField(init, min, max, changeListener);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(4,4,4,4);
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = row++;
        c.gridwidth = 1;
        JLabel label = new JLabel();
        label.setText(text);
        label.setLabelFor(field);
        add(label, c);
        c.gridx++;
        add(field, c);
        return field;
    }
    
    public final JCheckBox labeledCheckbox(String text, 
            ItemListener itemListener) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(4,4,4,4);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.gridx = 0;
        c.gridy = row++;
        c.gridwidth = 2;
        JCheckBox field = new JCheckBox();
        field.addItemListener(itemListener);
        field.setText(text);
        field.setHorizontalTextPosition(SwingConstants.LEFT);
        add(field, c);
        return field;
    }
    
    public final JButton centredButton(String text, 
            ActionListener actionListener) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(4,4,4,4);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.gridx = 0;
        c.gridy = row++;
        c.gridwidth = 2;
        JButton field = new JButton();
        field.addActionListener(actionListener);
        field.setText(text);
        add(field, c);
        return field;
    }
    
    public final void skipRow() {
        row++;
    }
}
