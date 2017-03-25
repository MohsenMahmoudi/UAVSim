/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Component.DataProvider;

import Controller.Map.GPS.Trackpoint;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author Administrator
 */
public class CameraDataProvider {

    private static final String API = "http://dev.virtualearth.net/REST/v1/Imagery/Map/Aerial/";
    private static final String API_Key = "AlBbhK-1uPFZozpLFe9lZ1kAeXaNnqccJq9YQruJHg-G6BtJIkvPcAAFSash9lst";

    private static BufferedImage noimage_File = null;
    
    public static Image getRealImage(Trackpoint position, int Image_Width, int Image_Height) {
        BufferedImage BItmp = null;
        try {
//            URL url = new URL(API + position.getLatitude().getDecimal() + "," + position.getLongitude().getDecimal() + "/" + convertRangeToZoom(position.getAltitude()) + "?mapSize=" + (Image_Width+12) + "," + (Image_Height + 12) + "&key=" + API_Key);
//            BItmp = ImageIO.read(url);
//            BItmp=BItmp.getSubimage(0, 0, Image_Width-12, Image_Height - 12);
            
        } catch (Exception ex) {
            try {
                if(noimage_File!=null)
                    BItmp = noimage_File;
                else{
                    noimage_File = ImageIO.read(new File("noimage.jpg"));
                    BItmp = noimage_File;
                }
                
            } catch (IOException ex1) {
                //Logger.getLogger(CameraDataProvider.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return BItmp;
    }

    private static int convertRangeToZoom(double range) {
        int zoom = (int) Math.round(Math.log(35200000 / range) / Math.log(2));
        if (zoom < 0) {
            zoom = 0;
        } else if (zoom > 19) {
            zoom = 19;
        }
        return zoom;
    }

}
