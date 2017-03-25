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
public class StepMotor extends Component {

    private int stepCount;

    public StepMotor(int defulteStepCount, long refreshPeriod) {
        super(refreshPeriod,false);
        setStepCount(stepCount);
    }

    public StepMotor(int defulteStepCount) {
        this(defulteStepCount, 100);
    }
    
    public StepMotor()
    {
        this(0, 100);
    }

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        if (stepCount <= 100 && stepCount >= -100) {
            this.stepCount = stepCount;
        } 
        else if(stepCount > 100)
            this.stepCount=100;
        else if(stepCount < -100)
            this.stepCount=-100;
    }

    public void increaseStepCount() {
        if (stepCount <= 100);
        this.stepCount++;
    }

    public void decreaseStepCount() {
        if (stepCount >= -100) {
            this.stepCount--;
        }
    }

    @Override
    public void refresh() {
       //This Component isn't refreshable
    }
}
