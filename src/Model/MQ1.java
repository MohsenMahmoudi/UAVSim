/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Map.GPS.Trackpoint;
import Model.Component.PhysicsProvider.MQ1PhysicsProvider;
import Model.Component.Sensor.Camera;
import Model.Component.Sensor.Compass;
import Model.Component.Sensor.Temperature;
import Model.Component.StepMotor;
import Model.Component.Thrust;

/**
 *
 * @author Administrator
 */
public class MQ1 extends UAV {

    public MQ1(int UAV_ID, Trackpoint position, double Degree, String color,int startSpeed) {
        super(UAV_ID, position, new Compass(Degree), new Camera(position, 400, 400), new Temperature(position, true), new Thrust(false), new StepMotor(0), new StepMotor(0), new MQ1PhysicsProvider(startSpeed), color);
    }

    @Override
    public void reciveData(byte[] buffer) {
        System.out.println(new String(buffer));
    }

    @Override
    public void refresh() {
        this.setPosition(((MQ1PhysicsProvider) this.getPhysicEng()).render(this.getPosition(), this.getRudder().getStepCount(), this.getElevator().getStepCount(), this.getForwardThrust().getPower()));
    }
}
