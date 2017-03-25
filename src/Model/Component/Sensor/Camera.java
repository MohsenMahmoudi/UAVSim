/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Component.Sensor;

import Controller.Map.GPS.Trackpoint;
import Model.Component.Component;
import java.awt.Image;

/**
 *
 * @author Administrator
 */
public class Camera extends Component {


    private int Image_Width;
    private int Image_Height;
    private Image image;
    private boolean enable;

    public Camera(Trackpoint position, int Image_Width, int Image_Height, long refreshPeriod) {
        super(refreshPeriod);
        this.setPosition(position);
        this.Image_Width = Image_Width;
        this.Image_Height = Image_Height;
        this.enable=true;
        this.refresh();
    }
    
    public Camera(Trackpoint position, int Image_Width, int Image_Height) {
        this(position, Image_Width, Image_Height,100);
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    
    
    public Image getImage() {
        return image;
    }


    @Override
    public void refresh() {
        if(this.enable)
        this.image = Model.Component.DataProvider.CameraDataProvider.getRealImage(this.getPosition(),Image_Width, Image_Height);
    }
}
