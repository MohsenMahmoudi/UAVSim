/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Communication;

import Controller.Map.GPS.GPSUtil;
import Model.UAV;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class UAVComProvider {

    public static ArrayList<UAV> lst;

    static {
        lst = new ArrayList<>();
    }
    public static final int Max_Range_of_DataLink = 5;//in KM

    public static void refreshUAVsNearList() {
        double[][] distanceTable = new double[lst.size()][lst.size()];

        ////////////Flush Last Near UAV list from list.
        for (UAV tmp_uav : lst) {
            tmp_uav.flushNearList();
        }

        for (int i = 0; i < lst.size(); i++) {
            for (int j = i + 1; j < lst.size(); j++) {
                distanceTable[i][j] = GPSUtil.getDistance(lst.get(i).getPosition(), lst.get(j).getPosition());

                if (distanceTable[i][j] < Max_Range_of_DataLink && distanceTable[i][j] > 0) {
                    lst.get(i).addToNearList(lst.get(j));
                    lst.get(j).addToNearList(lst.get(i));
                }
            }
        }
    }
}
