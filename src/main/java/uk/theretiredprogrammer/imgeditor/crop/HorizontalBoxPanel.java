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

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author richard
 */
public class HorizontalBoxPanel extends JPanel {
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public HorizontalBoxPanel() {
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
    }
    
    public final JLabel doubleLabel(String text) {
        JLabel field = new JLabel();
        //
        JLabel label = new JLabel(text);
        label.setBorder(new EmptyBorder(2,10,2,2));
        label.setLabelFor(field);
        add(label);
        field.setBorder(new EmptyBorder(2,2,2,10));
        field.setHorizontalAlignment(JLabel.RIGHT);
        add(field);
        return field;
    }
}
