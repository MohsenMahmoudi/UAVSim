/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Component.Sensor;

import Controller.Map.GPS.Direction;
import Model.Component.Component;

/**
 *
 * @author Administrator
 */
public class Compass extends Component{
    private double Degree;

    public Compass(double Degree, long refreshPeriod ) {
        super(refreshPeriod);
        this.Degree = Degree;
    }

    public Compass(double Degree){
        super(100);
        this.Degree=Degree;
    }
    
    public double getDegree() {
        return Degree;
    }
    
    public double getNorthDegree(int offset){
        return (Degree+offset)%360;
    }
    
    public double getNorthDegree()
    {
        return this.getNorthDegree(90);
    }
    
    public Direction getDirection(){
        double degree=getDegree()%360;
        if(degree>0 && degree<90)
            return Direction.NORTHEAST;
        else if(degree==90)
            return Direction.NORTH;
        else if(degree>90 && degree<180)
            return Direction.NORTHWEST;
        else if(degree==180)
            return Direction.WEST;
        else if(degree>180 && degree<270)
            return Direction.SOUTHWEST;
        else if(degree==270)
            return Direction.SOUTH;
        else if(degree>270 && degree<360)
            return Direction.SOUTHEAST;
        else
            return Direction.EAST;
    }

    public void setDegree(double Degree) {
        this.Degree = Degree%360;
    }

    @Override
    public void refresh() {
       //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
