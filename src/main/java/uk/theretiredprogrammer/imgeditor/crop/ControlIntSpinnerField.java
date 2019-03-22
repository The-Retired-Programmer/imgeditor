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

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;

/**
 *
 * @author richard
 */
public class ControlIntSpinnerField extends JSpinner {
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public ControlIntSpinnerField(int initvalue, int minvalue, int maxvalue, ChangeListener changeListener) {
        @SuppressWarnings("LeakingThisInConstructor")
        JFormattedTextField textfield = getTextField(this);
        textfield.setHorizontalAlignment(JTextField.RIGHT);
        textfield.setColumns(6);
        addChangeListener(changeListener);
        setModel(new SpinnerNumberModel(initvalue, minvalue, maxvalue, 1));
        setEditor(new JSpinner.NumberEditor(this, "######"));
    }
    
    @Override
    public void setEnabled(boolean enable) {
        super.setEnabled(enable);
        getTextField(this).setBackground( enable ?new java.awt.Color(255, 255, 255):new java.awt.Color(192, 192, 192));
    }
    
    public int getIntValue() {
        return Integer.parseInt(getTextField(this).getText());
    }
    
    public void setIntValue(int value) {
        getTextField(this).setValue(value);
    }
    
    /**
     * Return the formatted text field used by the editor, or
     * null if the editor doesn't descend from JSpinner.DefaultEditor.
     */
    private JFormattedTextField getTextField(JSpinner spinner) {
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            return ((JSpinner.DefaultEditor)editor).getTextField();
        } else {
            System.err.println("Unexpected editor type: "
                               + spinner.getEditor().getClass()
                               + " isn't a descendant of DefaultEditor");
            return null;
        }
    }
}
