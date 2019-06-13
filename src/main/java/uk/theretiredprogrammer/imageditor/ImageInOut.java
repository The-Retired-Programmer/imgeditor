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

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

/**
 *
 * @author Richard Linsdale (richard at theretiredprogrammer.uk)
 */
public class ImageInOut {
    
    private FileObject from;

    public ImageInOut(FileObject fo) throws IOException {
        this.from = fo;
    }
    
    public BufferedImage getImage() throws IOException {
        return ImageIO.read(FileUtil.toFile(from));
    }
    
    public final void save(BufferedImage image) throws IOException {
        String f = from.getName() + "-" + image.getWidth() + "x" + image.getHeight() + "." + from.getExt();
        try (OutputStream out = from.getParent().createAndOpen(f)) {
            String mimetype = from.getMIMEType();
            ImageIO.write(image, mimetype.startsWith("image/") ? mimetype.substring(6) : "unknown", out);
        }
    }
    
    public final void save(BufferedImage image, String foldername) throws IOException {
        FileObject tofolder = from.getParent().getParent().getFileObject(foldername);
        String f = from.getName() + "-" + image.getWidth() + "x" + image.getHeight() + "." + from.getExt();
        try (OutputStream out = tofolder.createAndOpen(f)) {
            String mimetype = from.getMIMEType();
            ImageIO.write(image, mimetype.startsWith("image/") ? mimetype.substring(6) : "unknown", out);
        }
    }
}
