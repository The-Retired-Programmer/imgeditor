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

import java.awt.event.ItemEvent;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author richard
 */
public class ControlPanel extends VerticalGridBagPanel {

    private final ControlIntSpinnerField bottom;
    private final ControlIntSpinnerField height;
    private final ControlIntSpinnerField left;
    private final ControlIntSpinnerField right;
    private final ControlIntSpinnerField top;
    private final ControlIntSpinnerField width;
    //
    private final CropTopComponent parent;

    private final int imageheight;
    private final int imagewidth;

    private int topv;
    private int bottomv;
    private int leftv;
    private int rightv;
    private int widthv;
    private int heightv;
    private boolean usewidthheight;

    public ControlPanel(CropTopComponent parent, int imagewidth, int imageheight) {
        this.parent = parent;
        this.imageheight = imageheight;
        this.imagewidth = imagewidth;
        usewidthheight = false;
        topv = 1;
        leftv = 1;
        bottomv = imageheight;
        rightv = imagewidth;
        widthv = imagewidth;
        heightv = imageheight;
        //
        centredLabel("CROP CONTROL");
        labeledCheckbox("Use Width/Height", this::usewidthheightItemStateChanged);
        left = labeledIntSpinnerField("Left:", leftv, leftv, rightv, this::leftChanged);
        right = labeledIntSpinnerField("Right:", rightv, leftv, rightv, this::rightChanged);
        top = labeledIntSpinnerField("Top:", topv, topv, bottomv, this::topChanged);
        bottom = labeledIntSpinnerField("Bottom:", bottomv, topv, bottomv, this::bottomChanged);
        width = labeledIntSpinnerField("Width:", widthv, 1, widthv, this::widthChanged);
        width.setEnabled(false);
        height = labeledIntSpinnerField("Height:", heightv, 1, heightv, this::heightChanged);
        height.setEnabled(false);
    }

    private void leftChanged(ChangeEvent evt) {
        int newv = left.getIntValue();
        if (usewidthheight) {
            if (newv + widthv -1 > imagewidth) {
                newv = imagewidth - widthv +1;
            }
            rightv = newv + widthv - 1;
            right.setIntValue(rightv);
        } else {
            if (newv > rightv) {
                newv = rightv;
            }
            widthv = rightv - newv + 1;
            width.setIntValue(widthv);
        }
        leftv = newv;
        left.setIntValue(leftv);
    }

    private void rightChanged(ChangeEvent evt) {
        int newv = right.getIntValue();
        if (!usewidthheight) {
            if (newv < leftv) {
                newv = leftv;
            }
            widthv = newv - leftv + 1;
            width.setIntValue(widthv);
        }
        rightv = newv;
        right.setIntValue(rightv);
    }

    private void topChanged(ChangeEvent evt) {
        int newv = top.getIntValue();
        if (usewidthheight) {
            if (newv + heightv -1 > imageheight) {
                newv = imageheight - heightv +1;
            }
            bottomv = newv + heightv - 1;
            bottom.setIntValue(bottomv);
        } else {
            if (newv > bottomv) {
                newv = bottomv;
            }
            heightv = bottomv - newv + 1;
            height.setIntValue(heightv);
        }
        topv = newv;
        top.setIntValue(topv);
    }

    private void bottomChanged(ChangeEvent evt) {
        int newv = bottom.getIntValue();
        if (!usewidthheight) {
            if (newv < topv) {
                newv = topv;
            }
            heightv = newv - topv + 1;
            height.setIntValue(heightv);
        }
        bottomv = newv;
        bottom.setIntValue(bottomv);
    }

    private void widthChanged(ChangeEvent evt) {
        int newv = width.getIntValue();
        if (usewidthheight) {
            if (newv + leftv -1 > imagewidth) {
                newv = imagewidth - leftv +1;
            }
            rightv = newv + leftv - 1;
            right.setIntValue(rightv);
        }
        widthv = newv;
        width.setIntValue(widthv);
    }

    private void heightChanged(ChangeEvent evt) {
        int newv = height.getIntValue();
        if (usewidthheight) {
            if (newv + topv -1> imageheight) {
                newv = imageheight - topv+ 1;
            }
            bottomv = newv + topv - 1;
            bottom.setIntValue(bottomv);
        }
        heightv = newv;
        height.setIntValue(heightv);
    }

    private void usewidthheightItemStateChanged(ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.DESELECTED) {
            // disable width/height
            width.setEnabled(false);
            height.setEnabled(false);
            // use right/bottom
            right.setEnabled(true);
            bottom.setEnabled(true);
            usewidthheight = false;
        } else {
            // use width/height
            width.setEnabled(true);
            height.setEnabled(true);
            // disable right/bottom
            right.setEnabled(false);
            bottom.setEnabled(false);
            usewidthheight = true;
        }
    }
}
