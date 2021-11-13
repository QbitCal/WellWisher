package WellWisher;

import java.applet.Applet;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Quenten
 */
public class ImageHandler {

    public static byte[] getLogoBytes() throws IOException{
        byte[] imgData;
        
        //Get the logo from 
        String currentDir = System.getProperty("user.dir");
        System.out.println(currentDir);
        URL url = new File(currentDir + "/src/main/java/WellWisher/WellWisher.png").toURI().toURL();
        //This only works in my own directory for now... have to fix the path to the image within the local files...
        BufferedImage buffImage = ImageIO.read(url);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(buffImage, "png", bos );
        imgData = bos.toByteArray();
        return imgData;
    }
}
