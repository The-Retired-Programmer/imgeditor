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
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.*;
import javax.swing.JComponent;
import org.openide.awt.StatusDisplayer;

/**
 *
 * @author richard
 */
public class ImageDisplay extends JComponent {

    private final static int BORDER = 50;
    private final static int CROPMARK = 25;

    private ImageAndCrops cropdata;

    public void display(ImageAndCrops cropdata) {
        setBackground(Color.WHITE);
        setFocusable(true);
        this.cropdata = cropdata;
        setPreferredSize(new Dimension(cropdata.getSizedImage().getWidth() + 2 * BORDER,
                cropdata.getSizedImage().getHeight() + 2 * BORDER));
        revalidate();
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage image = cropdata.getSizedImage();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawRenderedImage(image, AffineTransform.getTranslateInstance(BORDER, BORDER));
        g2d.setColor(Color.BLUE);
        //
        int leftpos = cropdata.getScaledLeft() - 2 + BORDER;
        int rightpos = cropdata.getScaledRight() + BORDER;
        int toppos = cropdata.getScaledTop() - 2 + BORDER;
        int bottompos = cropdata.getScaledBottom() + BORDER;
        StatusDisplayer.getDefault().setStatusText(leftpos+";"+rightpos+";"+toppos+";"+bottompos);
        //
        g2d.draw(new Line2D.Double(leftpos, BORDER - CROPMARK, leftpos, 2 * BORDER - CROPMARK + image.getHeight()));
        g2d.draw(new Line2D.Double(rightpos, BORDER - CROPMARK, rightpos, 2 * BORDER - CROPMARK + image.getHeight()));
        g2d.draw(new Line2D.Double(BORDER - CROPMARK, toppos, 2 * BORDER - CROPMARK + image.getWidth(), toppos));
        g2d.draw(new Line2D.Double(BORDER - CROPMARK, bottompos, 2 * BORDER - CROPMARK + image.getWidth(), bottompos));
    }
}
