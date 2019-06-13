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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

/**
 *
 * @author Richard Linsdale (richard at theretiredprogrammer.uk)
 */
public class ImageDisplay extends JComponent {

    private BufferedImage image;

    public void display(BufferedImage image) {
        setBackground(Color.WHITE);
        setFocusable(true);
        this.image = image;
        setPreferredSize(new Dimension(image.getWidth(),
                image.getHeight()));
        revalidate();
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawRenderedImage(image, AffineTransform.getTranslateInstance(0,0));
    }
}
