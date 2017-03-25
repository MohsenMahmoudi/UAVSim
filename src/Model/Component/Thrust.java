/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Component;

/**
 *
 * @author Administrator
 */
public class Thrust extends Component {

    private Double Power;
    private Double Temperature;
    private Boolean overHeatController;

    public Thrust(Boolean overHeatController, long refreshPeriod) {
        super(refreshPeriod);
        
        this.overHeatController = overHeatController;
    }

    public Thrust(Boolean overHeatController) {
        super(100);
        this.Power=0.0;
        this.Temperature=0.0;
        this.overHeatController = overHeatController;
    }

    public Double getTemperature() {
        return Temperature;
    }

    public Double getPower() {
        return Power;
    }

    public void setPower(Double Power) {
        if (Power <= 100 && Power >= 0) {
            this.Power = Power;
        }
    }

    public Boolean getOverHeatController() {
        return overHeatController;
    }

    public void setOverHeatController(Boolean overHeatController) {
        this.overHeatController = overHeatController;
    }
    /*
        Temperature:
                    0-150    : Normal
                    150-180  : Critical
                    180-200  : Over heat
    */
    @Override
    public void refresh() {
        Temperature+=(getPower()/100)-0.1;
        if(overHeatController && Temperature>180){
            setPower(0.0);
            Temperature -= 1;
        }
    }
}
