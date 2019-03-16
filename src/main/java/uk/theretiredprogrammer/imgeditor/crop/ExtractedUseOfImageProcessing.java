/*
 * Copyright 2018 richard.
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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;


/**
 *
 * @author richard
 */
public class ExtractedUseOfImageProcessing {

    private void makeThumbnails(FileObject file) throws IOException {
        File f = FileUtil.toFile(file);
        BufferedImage img;
        img = ImageIO.read(f);
//        ImageProcessing ip = new ImageProcessing();
//        ip.setImage(img);
//        ip.makeThumbnails();
//        File mtn = new File(mediumthumbnailroot + file.getName() + ".jpg");
//        ImageIO.write(ip.getMediumThumbnail(), "jpg", mtn);
//        File stn = new File(smallthumbnailroot + file.getName() + ".jpg");
//        ImageIO.write(ip.getSmallThumbnail(), "jpg", stn);
    }
}
