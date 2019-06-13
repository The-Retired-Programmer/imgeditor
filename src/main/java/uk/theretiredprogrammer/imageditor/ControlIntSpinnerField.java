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

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;

/**
 * A UI component which implements an integer valued Spinner.
 *
 * @author Richard Linsdale (richard at theretiredprogrammer.uk)
 */
public class ControlIntSpinnerField extends JSpinner {

    private final ChangeListener changeListener;

    /**
     * Create the UI component (integer spinner)
     *
     * @param initvalue the initial value to be displayed
     * @param minvalue the minimum value of the spinner
     * @param maxvalue the minimum value of the spinner
     * @param changeListener the change listener to be attached to this
     * component
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public ControlIntSpinnerField(int initvalue, int minvalue, int maxvalue, ChangeListener changeListener) {
        this.changeListener = changeListener;
        @SuppressWarnings("LeakingThisInConstructor")
        JFormattedTextField textfield = getTextField(this);
        textfield.setHorizontalAlignment(JTextField.RIGHT);
        textfield.setColumns(6);
        addChangeListener(changeListener);
        setModel(new SpinnerNumberModel(initvalue, minvalue, maxvalue, 1));
        setEditor(new JSpinner.NumberEditor(this, "######"));
    }

    /**
     * Get the integer value of this component
     *
     * @return the current integer value
     */
    public int getIntValue() {
        return Integer.parseInt(getTextField(this).getText());
    }

    /**
     * Set the value of this compoenent
     *
     * @param value the new integer value
     */
    public void setIntValue(int value) {
        removeChangeListener(changeListener);
        getTextField(this).setValue(value);
        addChangeListener(changeListener);
    }

    private JFormattedTextField getTextField(JSpinner spinner) {
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            return ((JSpinner.DefaultEditor) editor).getTextField();
        } else {
            System.err.println("Unexpected editor type: "
                    + spinner.getEditor().getClass()
                    + " isn't a descendant of DefaultEditor");
            return null;
        }
    }
}
