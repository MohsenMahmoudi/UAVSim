/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Component.Sensor;

import Controller.Map.GPS.GPSUtil;
import Controller.Map.GPS.Trackpoint;
import Model.Component.Component;
import Model.Component.DataProvider.TemperatureDataProvider;

/**
 *
 * @author Administrator
 */
public class Temperature extends Component {

   
    private static final Double Resulation = 10.0;//Use Cached Data in a local area(in KM)
    private int Temperature_Value_C;
    private boolean useCachedData;
    private Trackpoint lastCachePosition;

    public Temperature(Trackpoint position, boolean useCachedData) {
        this(position, useCachedData, 100);
    }

    public Temperature(Trackpoint position, boolean useCachedData, long refreshPeriod) {
        super(refreshPeriod);
        this.setPosition(position);
        this.useCachedData = useCachedData;
        this.Temperature_Value_C = callWebService();
    }

    private boolean isCached(Trackpoint position) {
        if (lastCachePosition != null) {
            return GPSUtil.getDistance(lastCachePosition.getLatitude(), lastCachePosition.getLongitude(), position.getLatitude(), position.getLongitude()) < Resulation; //Check that are we still in cache area
        }
        return false;
    }

    private int callWebService() {
            this.lastCachePosition = this.getPosition();
            return TemperatureDataProvider.getRealTemperature(this.getPosition());
    }

    public int getTemperature() {
        return Temperature_Value_C;
    }

    public boolean isUseCachedData() {
        return useCachedData;
    }

    public void setUseCachedData(boolean useCachedData) {
        this.useCachedData = useCachedData;
    }

    @Override
    public void refresh() {
        if (!(useCachedData && isCached(this.getPosition()))) {
            this.Temperature_Value_C = callWebService();
        }
    }
}
