/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Component.PhysicsProvider;

import Controller.Map.GPS.Latitude;
import Controller.Map.GPS.Longitude;
import Controller.Map.GPS.Trackpoint;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class MQ1PhysicsProvider extends PhysicsProvider {

    private double speed;
    private double horizontalHeading;
    private double verticalHeading;
    
    private double directionDegree;

    public MQ1PhysicsProvider(double speed) {
        this.speed = speed;
    }

    public double getHorizontalHeading() {
        return horizontalHeading;
    }

    public double getVerticalHeading() {
        return verticalHeading;
    }

    public void horizontalHeadingStablizer() {
        if (horizontalHeading > 0) {
            horizontalHeading /= 2;
        } else if (horizontalHeading < 0) {
            horizontalHeading /= 2;
        }
    }

    public void verticalHeadingStablizer() {
        if (verticalHeading > 0) {
            verticalHeading -= 1;
        } else if (verticalHeading < 0) {
            verticalHeading += 1;
        }
    }

    public double getSpeed() {
        return speed;
    }

    public double getDirectionDegree() {
        return directionDegree;
    }

    
    
    public Trackpoint render(Trackpoint currectPosition, int hori, int vert, Double power) {
        speed += (power - 25) / 25;

        if (speed < 0) {
            speed = 0;
        }

        horizontalHeading += hori *0.6;
        verticalHeading += vert / 8;

        if(horizontalHeading>6)
            horizontalHeading=6;
        else if (horizontalHeading<-6)
            horizontalHeading=-6;
        
        if (verticalHeading > 20) {
            verticalHeading = 20;
        } else if (verticalHeading < -20) {
            verticalHeading = -20;
        }

        speed += -((double) vert / 20);

        if (speed > 217) {
            speed = 217+(217-speed)/2;
        }

        double Alti = currectPosition.getAltitude() - ((217 - speed) * 0.1) + verticalHeading;

        if (speed < 27) {
            Alti = currectPosition.getAltitude() - (10 - (speed / 2.7));
        }
        if(speed <0)
            speed=0;

        if (Alti < 1) {
            Alti = 1;
        }

        double length = speed * 1 * (9e-6);

        
        directionDegree+=this.getHorizontalHeading();
        
        directionDegree%=360;
        
        double Lat = Math.sin((directionDegree  / 180) * Math.PI) * length;
        double Lon = Math.cos((directionDegree  / 180) * Math.PI) * length;

        return (new Trackpoint(Alti, new Latitude(currectPosition.getLatitude().getDecimal() + Lat), new Longitude(currectPosition.getLongitude().getDecimal() + Lon), new Date()));
    }

    @Override
    public Trackpoint render() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

/*
 -Speed of UAV calculate by power of forwardThrust. by activing temp control of Thrust, temp will calc by power. in case of over heat, motor will be shutdown for a while.
 -Max Speed of MQ1 is 135 mph (117 knots, 217 km/h , 60 m/s)
 -Stall speed of MQ1 is about 62 mph (54 knots, 100 km/h, 27 m/s).
 -In Stall case UAV lost 30 meter altitude every second
 -Every 9e-6 in an axis move is approximately equal one meter.
 */
//    public Trackpoint refresh(){
//   
////        speed += (forwardThrust.getPower() / 25);
////        speed %= 60;
////
////        this.compass.setDegree(this.compass.getDegree() + (getRudder().getStepCount() / 2));
////
////        double Alti = 0;
////        if (speed < 27) {
////                Alti = this.position.getAltitude() - 10;
////        } else {
////            Alti = this.position.getAltitude() + (this.getElevator().getStepCount() / 20) * (speed / 10);
////        }
////
////        if(Alti<1)Alti=1;
////        
////        double length = speed * 1 * (9e-6);
////
////        double Lat = Math.sin((this.compass.getDegree() / 180) / Math.PI) * length;
////        double Lon = Math.cos((this.compass.getDegree() / 180) / Math.PI) * length;
////
////        return (new Trackpoint(Alti, new Latitude(this.position.getLatitude().getDecimal() + Lat), new Longitude(this.position.getLongitude().getDecimal() + Lon), new Date()));
//    }
