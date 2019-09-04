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
package uk.theretiredprogrammer.imageditor;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import static java.lang.Math.round;
import static java.lang.System.currentTimeMillis;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.imageio.ImageIO;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import org.openide.windows.OutputWriter;

/**
 *
 * @author richard
 */
public class ImgsetgenWorker implements Runnable {

    private final FileObject imgsetgenfile;
    private final Properties directives = new Properties();
    private String imagefiletype;

    public ImgsetgenWorker(FileObject imgedtfile) {
        this.imgsetgenfile = imgedtfile;
    }

    @Override
    public void run() {
        boolean errflag = false;
        long start = currentTimeMillis();
        InputOutput io = IOProvider.getDefault().getIO("ImageSet Generation - " + imgsetgenfile.getName(), false);
        io.select();
        try (OutputWriter msg = io.getOut(); OutputWriter err = io.getErr()) {
            try {
                msg.reset();
                String basename = imgsetgenfile.getName();
                getdirectives();
                createimageset(basename, msg);
                createimghtml(basename, msg);
            } catch (IOException ex) {
                err.println(ex.getLocalizedMessage());
                errflag = true;
            }
            int elapsed = round((currentTimeMillis() - start) / 1000F);
            msg.println("BUILD COMPLETED" + (errflag ? " WITH ERRORS" : "") + " (total time: " + Integer.toString(elapsed) + " seconds)");
        }
    }

    private void getdirectives() throws IOException {
        Reader from = new InputStreamReader(imgsetgenfile.getInputStream());
        directives.load(from);
    }

    private void createimageset(String basename, OutputWriter msg) throws IOException {
        BufferedImage baseimage = getbaseimage();
        for (int width : getrequiredwidths()) {
            createscaledimage(basename, width, baseimage, gettargetfolder(), msg);
        }
    }

    private FileObject gettargetfolder() throws IOException {
        FileObject pagefolder = imgsetgenfile.getParent().getParent().getParent();
        if (pagefolder == null) {
            throw new IOException("Can't find page folder");
        }
        FileObject targetfolder = pagefolder.getFileObject("resources");
        if (targetfolder == null) {
            targetfolder = pagefolder.createFolder("resources");
        }
        return targetfolder;
    }

    private BufferedImage getbaseimage() throws IOException {
        String baseimagename = imgsetgenfile.getName();
        FileObject parentfolder = imgsetgenfile.getParent().getParent();
        FileObject sourcefile = parentfolder.getFileObject("edited/" + baseimagename + ".jpg");
        if (sourcefile == null) {
            sourcefile = parentfolder.getFileObject("edited/" + baseimagename + ".png");
        }
        if (sourcefile == null) {
            sourcefile = parentfolder.getFileObject("masters/" + baseimagename + ".jpg");
        }
        if (sourcefile == null) {
            sourcefile = parentfolder.getFileObject("masters/" + baseimagename + ".png");
        }
        if (sourcefile == null) {
            throw new IOException("Can't find base image matching this directive file");
        }
        imagefiletype = sourcefile.getExt();
        return ImageIO.read(FileUtil.toFile(sourcefile));
    }

    private List<Integer> getrequiredwidths() throws IOException {
        String widths = directives.getProperty("widths");
        if (widths == null) {
            throw new IOException("no widths value defined in directive file");
        }
        try {
            List<Integer> widthslist = new ArrayList<>();
            for (String w : widths.split(",")) {
                widthslist.add(Integer.parseInt(w));
            }
            return widthslist;
        } catch (NumberFormatException ex) {
            throw new IOException("badly formated number in widths directive");
        }
    }

    private void createscaledimage(String basename, int targetwidth, BufferedImage baseimage,
            FileObject targetfolder, OutputWriter msg) throws IOException {
        String outputimagefiletype = getoutput();
        String newimagename = basename + "-" + targetwidth + "." + outputimagefiletype;
        int width = baseimage.getWidth();
        int height = baseimage.getHeight();
        int targetheight = height * targetwidth / width;
        FileObject testtargetexists = targetfolder.getFileObject(newimagename);
        if (testtargetexists != null) {
            testtargetexists.delete();
        }
        try (OutputStream out = targetfolder.createAndOpen(newimagename)) {
            ImageIO.write(ImageProcessing.scale(baseimage, targetwidth, targetheight),
                    (outputimagefiletype.equals("jpg") ? "jpeg" : "png"), out);
        }
        msg.println("Created image - " + newimagename);
    }

    private void createimghtml(String basename, OutputWriter msg) throws IOException {
        String outputimagefiletype = getoutput();
        FileObject sourcefolder = imgsetgenfile.getParent().getParent().getParent();
        String filename = basename + ".html";
        FileObject testtargetexists = sourcefolder.getFileObject(filename);
        if (testtargetexists != null) {
            testtargetexists.delete();
        }

        try (PrintWriter out = new PrintWriter(sourcefolder.createAndOpen(filename))) {
            out.println("<img width=\"" + getwidth() + "\"");
            out.println("    src=\"resources/" + basename + "-" + getdefaultwidth() + "." + outputimagefiletype + "\"");
            out.println("    class=\"" + getclass() + "\" alt=\"\"");

            String prefix = "    srcset=\"";
            for (int width : getrequiredwidths()) {
                out.print(prefix + "resources/" + basename + "-" + width + "." + outputimagefiletype + " " + width + "w");
                prefix = ",\n        ";
            }
            out.println("\"");
            out.println("    sizes=\"" + getsizes() + "\"/>");
        }
        msg.println("Created html fragment (<img ...) - " + filename);
    }

    private String getwidth() throws IOException {
        String width = directives.getProperty("width");
        if (width == null) {
            throw new IOException("no width value defined in directive file");
        }
        return width;
    }
    
    private String getoutput() throws IOException {
        String output = directives.getProperty("output");
        return output == null ? imagefiletype : output.trim();
    }

    private String getdefaultwidth() throws IOException {
        String defaultwidth = directives.getProperty("defaultwidth");
        if (defaultwidth == null) {
            throw new IOException("no defaultwidth value defined in directive file");
        }
        return defaultwidth;
    }

    private String getclass() throws IOException {
        String classs = directives.getProperty("class");
        if (classs == null) {
            throw new IOException("no class value defined in directive file");
        }
        return classs;
    }

    private String getsizes() throws IOException {
        String sizes = directives.getProperty("sizes");
        if (sizes == null) {
            throw new IOException("no sizes value defined in directive file");
        }
        return sizes;
    }
}
