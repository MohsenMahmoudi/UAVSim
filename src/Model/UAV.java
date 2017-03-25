/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Map.GPS.Trackpoint;
import Model.Component.Component;
import Model.Component.PhysicsProvider.PhysicsProvider;
import Model.Component.Sensor.Camera;
import Model.Component.Sensor.Compass;
import Model.Component.Sensor.Temperature;
import Model.Component.StepMotor;
import Model.Component.Thrust;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public abstract class UAV extends Component {

    private int UAV_ID;
    private UAVTrackpath MapTrackPath;

    private Camera bottomCamera;
    private Compass compass;
    private Temperature tempSensor;

    private Thrust forwardThrust;
    private StepMotor elevator;
    private StepMotor rudder;

    private PhysicsProvider PhysicEng;

    private ArrayList<UAV> nearUAVList;

    public UAV(int UAV_ID, Trackpoint position, Compass compass, Camera bottomCamera, Temperature tempSensor, Thrust forwardThrust, StepMotor elevator, StepMotor rudder, PhysicsProvider PhysicEng, String color, long refreshPeriod) {
        super(refreshPeriod);
        nearUAVList = new ArrayList<>();
        MapTrackPath = new UAVTrackpath(color);
        this.UAV_ID = UAV_ID;
        this.compass = compass;
        this.bottomCamera = bottomCamera;
        this.tempSensor = tempSensor;
        this.forwardThrust = forwardThrust;
        this.elevator = elevator;
        this.rudder = rudder;
        this.PhysicEng = PhysicEng;
        this.setPosition(position);
    }

    public UAV(int UAV_ID, Trackpoint position, Compass compass, Camera bottomCamera, Temperature tempSensor, Thrust forwardThrust, StepMotor elevator, StepMotor rudder, PhysicsProvider PhysicEng, String color) {
        this(UAV_ID, position, compass, bottomCamera, tempSensor, forwardThrust, elevator, rudder, PhysicEng, color, 1000);
    }

    public StepMotor getElevator() {
        return elevator;
    }

    public StepMotor getRudder() {
        return rudder;
    }

    public int getUAV_ID() {
        return UAV_ID;
    }

    public UAVTrackpath getMapTrackPath() {
        return MapTrackPath;
    }

    public Camera getBottomCamera() {
        return bottomCamera;
    }

    public Thrust getForwardThrust() {
        return forwardThrust;
    }

    public Compass getCompass() {
        return compass;
    }

    public Temperature getTempSensor() {
        return tempSensor;
    }

    public PhysicsProvider getPhysicEng() {
        return PhysicEng;
    }

    public ArrayList<UAV> getNearUAVList() {
        return nearUAVList;
    }

    public void addToNearList(UAV toAdd) {
        nearUAVList.add(toAdd);
    }

    public void flushNearList() {
        nearUAVList.clear();
    }

    @Override
    public void setPosition(Trackpoint lastTrackPoint) {
        super.setPosition(lastTrackPoint);
        MapTrackPath.trackpointChanged(lastTrackPoint);
        try {
            bottomCamera.setPosition(this.getPosition());
            forwardThrust.setPosition(this.getPosition());
            tempSensor.setPosition(this.getPosition());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean sendData(byte[] buffer, int UAVid) {
        for (UAV tmp_UAV : nearUAVList) {
            if (tmp_UAV.getUAV_ID() == UAVid) {
                tmp_UAV.reciveData(buffer);
                return true;
            }
        }
        return false;
    }

    public abstract void reciveData(byte[] buffer);
}
