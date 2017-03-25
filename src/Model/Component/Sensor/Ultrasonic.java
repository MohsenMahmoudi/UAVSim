/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Component.Sensor;

import Model.Component.Component;

/**
 *
 * @author Administrator
 */
public class Ultrasonic extends Component{

    public Ultrasonic(long refreshPeriod) {
        super(refreshPeriod);
    }

    public  Ultrasonic(){
        this(100);
    }
    
    @Override
    public void refresh() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
