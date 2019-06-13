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
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * A panel which uses a horizontal box layout, which contains n information
 * fields and buttons
 *
 * @author Richard Linsdale (richard at theretiredprogrammer.uk)
 */
public class HorizontalBoxPanel extends JPanel {

    /**
     * Contructor
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public HorizontalBoxPanel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    /**
     * Add a double label to this panel - the first being a traditional label,
     * the second being a field to contain a displayable value.
     *
     * @param text the label text
     * @return the second label field
     */
    public final JLabel doubleLabel(String text) {
        JLabel field = new JLabel();
        //
        JLabel label = new JLabel(text);
        label.setBorder(new EmptyBorder(2, 10, 2, 2));
        label.setLabelFor(field);
        add(label);
        field.setBorder(new EmptyBorder(2, 2, 2, 10));
        field.setHorizontalAlignment(JLabel.RIGHT);
        add(field);
        return field;
    }

    /**
     * Add a button to this panel.
     *
     * @param text the button text
     * @param actionListener listener for the button press
     * @return the button
     */
    public final JButton button(String text, ActionListener actionListener) {
        JButton field = new JButton();
        field.setText(text);
        field.setActionCommand(text);
        field.addActionListener(actionListener);
        add(field);
        return field;
    }
}
