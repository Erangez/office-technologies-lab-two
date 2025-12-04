package org.erangaz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageConverter {
    public static String getImage(String imagePath){
        if (imagePath.contains(".png")){
            return imagePath;
        }
        else{
            File fo = null;
            try {
                File fi = new File(imagePath);
                BufferedImage bufferedImage = ImageIO.read(fi);
                String newPath = fi.getAbsolutePath().substring(0, fi.getAbsolutePath().lastIndexOf(".")) + ".png";
                fo = new File(newPath);
                ImageIO.write(bufferedImage, "PNG", fo);
            } catch (IOException e){
                System.out.println("Ошибка при работе с файлом!");
            }
            if (fo != null)
                return fo.getAbsolutePath();
            else {
                System.out.println("Ошибка при работе с файлом. Возвращен исходный параметр.");
                return imagePath;
            }
        }
    }
}
