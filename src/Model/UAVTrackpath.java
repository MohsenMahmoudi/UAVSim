/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Map.GPS.Trackpath;
import Controller.Map.GPS.Trackpoint;

/**
 *
 * @author Administrator
 */
public class UAVTrackpath {

    private String color;
    private Trackpath trackpath = new Trackpath();
    
    public UAVTrackpath(String color) {
        this.color = color;
    }

    public void trackpointChanged(Trackpoint trackpoint) {
        trackpath.addTrackpoint(trackpoint);
    }

    public String getColor() {
        return color;
    }

    public Trackpath getTrackpath() {
        return trackpath;
    }

}
