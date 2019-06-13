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

import java.awt.event.ActionListener;
import javax.swing.JTextField;

/**
 *
 * @author Richard Linsdale (richard at theretiredprogrammer.uk)
 */
public class ControlTextField extends JTextField {
    
    public ControlTextField() {
        super();
    }
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public ControlTextField(ActionListener actionListener) {
        setHorizontalAlignment(JTextField.RIGHT);
        setColumns(6);
        addActionListener(actionListener);
    }
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public ControlTextField(String defaultvalue, ActionListener actionListener) {
        this.setText(defaultvalue);
        setHorizontalAlignment(JTextField.RIGHT);
        setColumns(6);
        addActionListener(actionListener);
    }
    
    @Override
    public void setEnabled(boolean enable) {
        super.setEnabled(enable);
        setBackground( enable ?new java.awt.Color(255, 255, 255):new java.awt.Color(192, 192, 192));
    }
}
