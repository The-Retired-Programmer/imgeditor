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

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import javax.swing.JComponent;

/**
 *
 * @author richard
 */
public class ImageDisplayComponent extends JComponent {

    private final MouseL mouseListener = new MouseL();
    private final BufferedImage img;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public ImageDisplayComponent(BufferedImage img) {
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        setBackground(Color.WHITE);
        setFocusable(true);
        this.img = img;
        setPreferredSize(new Dimension (img.getWidth(), img.getHeight()));
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawRenderedImage(img, AffineTransform.getTranslateInstance(0, 0));
    }

//    public BufferedImage getImage() {
//        int width = Math.min(getWidth(), 1600);
//        int height = Math.min(getHeight(), 1200);
//        if (backingImage == null || backingImage.getWidth() != width || backingImage.getHeight() != height) {
//            int newWidth = backingImage == null ? width : Math.max(width, backingImage.getWidth());
//            int newHeight = backingImage == null ? height : Math.max(height, backingImage.getHeight());
//            if (newHeight > height && newWidth > width && backingImage != null) {
//                return backingImage;
//            }
//            BufferedImage old = backingImage;
//            backingImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB_PRE);
//            Graphics2D g = backingImage.createGraphics();
//            g.setColor(Color.WHITE);
//            g.fillRect(0, 0, width, height);
//            if (old != null) {
//                g.drawRenderedImage(old,
//                        AffineTransform.getTranslateInstance(0, 0));
//            }
//            g.dispose();
//            setPreferredSize(new Dimension (newWidth, newHeight));
//        }
//        return img;
//    }

    private final class MouseL extends MouseAdapter implements MouseMotionListener {
        @Override
        public void mouseClicked(MouseEvent e) {
//            Point p = e.getPoint();
//            int half = brushDiameter / 2;
//            Graphics2D g = getImage().createGraphics();
//            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                               RenderingHints.VALUE_ANTIALIAS_ON);
//            g.setPaint(getColor());
//            g.fillOval(p.x - half, p.y - half, brushDiameter, brushDiameter);
//            g.dispose();
//            repaint(p.x - half, p.y - half, brushDiameter, brushDiameter);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            mouseClicked(e);
        }
    }
}