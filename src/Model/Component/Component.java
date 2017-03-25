/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Component;

import Controller.Map.GPS.Trackpoint;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Administrator
 */
public abstract class Component {

    private Timer refreshTimer;

    private Trackpoint position;

    public Component(long refreshPeriod, boolean needRefresh) {
        if (needRefresh) {
            refreshTimer = new Timer();
            refreshTimer.schedule(new TimerTask() {

                @Override
                public void run() {
                    refresh();
                }
            }, 100, refreshPeriod);
        }
    }

    public Component(long refreshPeriod) {
        this(refreshPeriod, true);
    }

    protected Timer getRefreshTimer() {
        return refreshTimer;
    }

    protected void setRefreshTimer(Timer refreshTimer) {
        this.refreshTimer = refreshTimer;
    }

    public Trackpoint getPosition() {
        return position;
    }

    public void setPosition(Trackpoint position) {
        this.position = position;
    }

    public abstract void refresh();
}
