/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uavsim;

import Controller.Map.GPS.GPSUtil;
import Controller.Map.GPS.Trackpoint;
import Model.Component.PhysicsProvider.MQ1PhysicsProvider;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class MQ1_WaypointTest extends Model.MQ1 {

    private ArrayList<Trackpoint> targetWaypoint;

    public MQ1_WaypointTest(ArrayList<Trackpoint> targetWaypoint, Trackpoint startPos, int ID, String color) {
        super(ID, startPos, 10, color, 150);
        this.targetWaypoint = targetWaypoint;
    }

    public Trackpoint getTargetWaypoint() {
        return targetWaypoint.get(step);
    }

    public ArrayList<Trackpoint> getTargetWaypointList() {
        return targetWaypoint;
    }

    private void stablizer() {
        ((MQ1PhysicsProvider) this.getPhysicEng()).verticalHeadingStablizer();
        this.getForwardThrust().setPower(30.0);
        this.getRudder().setStepCount(-50);
    }

    private void navigate() {

        double power = GPSUtil.getDistance(this.getPosition(), this.getTargetWaypoint()) + Math.abs((this.getPosition().getAltitude() - this.targetWaypoint.get(step).getAltitude())) * 2;

        int x = (int) (this.targetWaypoint.get(step).getAltitude() - this.getPosition().getAltitude());
        if (x > 0) {
            if (((MQ1PhysicsProvider) this.getPhysicEng()).getSpeed() < 100) {
                power = 100;
                this.getElevator().setStepCount(50);
            } else {
                this.getElevator().setStepCount((int) ((Math.pow(x, 3.3)) / 500));
            }
        } else {
            this.getElevator().setStepCount(-(int) ((Math.pow(-x, 3.3)) / 500));
        }

        this.getCompass().setDegree((((MQ1PhysicsProvider) this.getPhysicEng()).getDirectionDegree() + 360) % 360);

        double degreeToTarget = (GPSUtil.bearing(this.getPosition(), targetWaypoint.get(step)) + 360) % 360;

        double neededRotate;

        neededRotate = degreeToTarget - this.getCompass().getDegree();

//        System.out.println(degreeToTarget + "\t" + this.getCompass().getDegree() + "\t" + neededRotate + "\t@\t" + x);
//        
        if (neededRotate > 180) {
            neededRotate -= 360;
        }

//        if (Math.abs(neededRotate) < 10 && Math.abs(neededRotate) >= 5)
//            neededRotate -= neededRotate;
//            
        if (Math.abs(neededRotate) >= 5) {
            x = (int) (neededRotate * 0.2777);
        } else {
            ((MQ1PhysicsProvider) this.getPhysicEng()).horizontalHeadingStablizer();
            x = -(int) (neededRotate * 0.2777);
        }

        this.getRudder().setStepCount(x);

        this.getForwardThrust().setPower(power);
    }

    int step = 0;

    @Override
    public void refresh() {
        super.refresh();

        if (GPSUtil.getDistance(this.getPosition(), this.getTargetWaypoint()) < 1) {
            if (step + 1 < targetWaypoint.size()) {
                step++;
            }
        }
        this.navigate();
    }

}
