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

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * A basic panel with vertical Box Layout
 *
 * @author Richard Linsdale (richard at theretiredprogrammer.uk)
 */
public class VerticalBoxPanel extends JPanel {

    /**
     * Constructor
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public VerticalBoxPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

}
