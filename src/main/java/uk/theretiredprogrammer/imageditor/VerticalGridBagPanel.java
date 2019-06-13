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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;

/**
 * A panel with a VerticalGridBag Layout, includes method to insert various
 * components within the grid.
 *
 * @author Richard Linsdale (richard at theretiredprogrammer.uk)
 */
public class VerticalGridBagPanel extends JPanel {

    private int row;

    /**
     * Constructor
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public VerticalGridBagPanel() {
        setLayout(new GridBagLayout());
        row = 0;
    }

    /**
     * Add a labelled Checkbox as a row
     *
     * @param text the label text
     * @param itemListener the listener for changes to the checkbox value
     * @return the created checkbox item
     */
    public final JCheckBox labeledCheckbox(String text,
            ItemListener itemListener) {
        JCheckBox field = new JCheckBox();
        field.addItemListener(itemListener);
        field.setText(text);
        field.setHorizontalTextPosition(SwingConstants.LEFT);
        insertfield(field);
        return field;
    }

    /**
     * Add a labelled Checkbox as a row
     *
     * @param text the label text
     * @param state the initial state of the checkbox
     * @param itemListener the listener for changes to the checkbox value
     * @return the created checkbox item
     */
    public final JCheckBox labeledCheckbox(String text,
            boolean state, ItemListener itemListener) {
        JCheckBox cbox = labeledCheckbox(text, itemListener);
        cbox.setSelected(state);
        return cbox;
    }

    /**
     * Add a Button spanning the two columns as a row
     *
     * @param text the button text
     * @param actionListener the listener for button click actions
     * @return the created button component
     */
    public final JButton centredButton(String text,
            ActionListener actionListener) {
        JButton field = new JButton();
        field.addActionListener(actionListener);
        field.setText(text);
        insertfield(field);
        return field;
    }

    /**
     * Add a centred label span two grid columns as a row
     *
     * @param text the label text
     * @return the created label component
     */
    public final JLabel centredLabel(String text) {
        JLabel label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setText(text);
        insertfield(label);
        return label;
    }

    /**
     * Add a pair of labels as a row
     *
     * @param text the text of first label
     * @param text2 the text of the second label
     * @return the created second (rh) label
     */
    public final JLabel doubleLabel(String text, String text2) {
        JLabel field = new JLabel(text2);
        field.setHorizontalAlignment(JLabel.RIGHT);
        insertfield(text, field);
        return field;
    }

    /**
     * add a label and a text field as a row
     *
     * @param text the label text
     * @param text2 the text field initial value
     * @param actionListener the listener for changes to text field value
     * @return the created text field component
     */
    public final ControlTextField labeledTextField(String text, String text2, ActionListener actionListener) {
        ControlTextField field = new ControlTextField(text2, actionListener);
        insertfield(text, field);
        return field;
    }

    /**
     * add a label and a text field as a row
     *
     * @param text the label text
     * @param actionListener the listener for changes to text field value
     * @return the created text field component
     */
    public final ControlTextField labeledTextField(String text, ActionListener actionListener) {
        ControlTextField field = new ControlTextField(actionListener);
        insertfield(text, field);
        return field;
    }

    /**
     * Add a label and a intSpinnerField as a row
     *
     * @param text the label text
     * @param init the initial value of the spinner
     * @param min the minimum value of the spinner
     * @param max the maximum value of the spinner
     * @param changeListener the listener for changes to the spinner value
     * @return the created intSpinnerField object
     */
    public final ControlIntSpinnerField labeledIntSpinnerField(String text, int init, int min, int max, ChangeListener changeListener) {
        ControlIntSpinnerField field = new ControlIntSpinnerField(init, min, max, changeListener);
        insertfield(text, field);
        return field;
    }

    private void insertfield(JComponent component) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(4, 4, 4, 4);
        c.weightx = 1.0;
        c.gridx = 0;
        c.gridy = row++;
        c.gridwidth = 2;
        add(component, c);
    }

    private void insertfield(String labeltext, JComponent component) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(4, 4, 4, 4);
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = row++;
        c.gridwidth = 1;
        JLabel label = new JLabel();
        label.setText(labeltext);
        label.setLabelFor(component);
        add(label, c);
        c.gridx++;
        add(component, c);
    }

    /**
     * Insert an empty row into the layout (is skip a row)
     */
    public final void skipRow() {
        row++;
    }
}
