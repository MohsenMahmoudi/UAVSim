/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Map;

import Controller.Map.GPS.GPSUtil;
import Controller.Map.GPS.Trackpoint;
import Model.UAV;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Administrator
 */
public class KMLProvider {

    public static final String ALTITUDE_MODE_ABSOLUTE = "absolute";

    public static final String CLAMP_TO_GROUND = "clampToGround";

    public static final String RELATIVE_TO_GROUND = "relativeToGround";

    public static final int DEFAULT_PORT = 8080;

    private ArrayList<Model.UAV> UAVs;

    private int followingUAVID;

    private ArrayList<Trackpoint> wayPoints;

    private Boolean writeTrack = true;
    private Boolean writePlacemaks = true;
    private String trackAltitudeMode = ALTITUDE_MODE_ABSOLUTE;
    private String placemarkAltitudeMode = ALTITUDE_MODE_ABSOLUTE;

    public KMLProvider(ArrayList<Model.UAV> UAVs, int port) {
        this.UAVs = UAVs;
        this.followingUAVID = -1;
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/", new KMLCodeProvider());
            server.start();
        } catch (IOException e) {
            System.out.println("IOException in GoogleEarthKMLProvider(): " + e.getMessage());
        }
    }

    public KMLProvider(ArrayList<Model.UAV> UAVs, ArrayList<Trackpoint> waypoint) {
        this(UAVs, DEFAULT_PORT);
        this.wayPoints = waypoint;
    }

    public KMLProvider(ArrayList<Model.UAV> UAVs) {
        this(UAVs, DEFAULT_PORT);
    }

    public void addUAV(Model.UAV uav) {
        UAVs.add(uav);
    }

    public int getFollowingUAVID() {
        return followingUAVID;
    }

    public void setFollowingUAVID(int followingUAVID) {
        if (followingUAVID != -1) {
            for (UAV UAV1 : UAVs) {
                if (UAV1.getUAV_ID() == followingUAVID) {
                    this.followingUAVID = followingUAVID;
                }
            }
        } else {
            this.followingUAVID = followingUAVID;
        }
    }

    private class KMLCodeProvider implements HttpHandler {

        public void handle(HttpExchange httpExchange) throws IOException {
            httpExchange.getResponseHeaders().add("Content-type", "text/html");
            String response = getKML();
            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    public void setWriteTrack(Boolean flag) {
        this.writeTrack = flag;
    }

    public void setWritePlacemaks(Boolean flag) {
        this.writePlacemaks = flag;
    }

    public void setTrackAltitudeMode(String trackAltitudeMode) {
        this.trackAltitudeMode = trackAltitudeMode;
    }

    public void setPlacemarkAltitudeMode(String placemarkAltitudeMode) {
        this.placemarkAltitudeMode = placemarkAltitudeMode;
    }

    public ArrayList<Trackpoint> getWayPoints() {
        return wayPoints;
    }

    public void setWayPoints(ArrayList<Trackpoint> wayPoints) {
        this.wayPoints = wayPoints;
    }

    public String getKML() {
        Date now = new Date();

        StringBuilder xml = new StringBuilder();

        // header
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<kml xmlns=\"http://earth.google.com/kml/2.2\">\n");
        xml.append("<Document>\n");
        xml.append("<open>1</open>\n\n");
        

        
        xml.append("<Style id=\"icon1\">\n");
        xml.append("<IconStyle>\n");
        xml.append("<scale>0.5</scale>\n");
        xml.append("</IconStyle>\n");
        xml.append("</Style>\n");
        for (Model.UAV uav : UAVs) {
            xml.append("<Style id=\"UAV" + uav.getUAV_ID() + "color\">\n");
            xml.append("<LineStyle>\n");
            xml.append("<color>" + (uav.getMapTrackPath().getColor().length() == 6 ? "ff" : "") + uav.getMapTrackPath().getColor() + "</color>\n");
            xml.append("<colorMode>normal</colorMode>\n");
            xml.append("<width>4</width>\n");
            xml.append("</LineStyle>\n");
            xml.append("</Style>\n");
        }
        // track name
//        for (Model.UAV uav : UAVs) {
//            xml.append("<name>" + uav.getMapTrackPath().getTrackpath().getName() + "</name>\n");
//
//            // track description
//            xml.append("<description><![CDATA[<h3>Track&nbsp;statistics</h3>\n");
//            xml.append("<table>\n");
//            xml.append("<tr><td>Duration: </td><td>" + uav.getMapTrackPath().getTrackpath().getDuration() + "</td></tr>\n");
//            xml.append("<tr><td>Distance: </td><td>" + String.format(Locale.US, "%.3f", uav.getMapTrackPath().getTrackpath().getDistance()) + "&nbsp;km</td></tr>\n");
//            xml.append("<tr><td>Max.&nbsp;speed: </td><td>" + String.format(Locale.US, "%.2f", uav.getMapTrackPath().getTrackpath().getVMax()) + "&nbsp;km/h</td></tr>\n");
//            xml.append("<tr><td>Av.&nbsp;speed: </td><td>" + String.format(Locale.US, "%.2f", uav.getMapTrackPath().getTrackpath().getVAverage()) + "&nbsp;km/h</td></tr>\n");
//            xml.append("<tr><td>Max.&nbsp;satellites: </td><td>" + uav.getMapTrackPath().getTrackpath().getSatMax() + "</td></tr>\n");
//            xml.append("<tr><td>Min.&nbsp;satellites: </td><td>" + uav.getMapTrackPath().getTrackpath().getSatMin() + "</td></tr>\n");
//            xml.append("</table>\n");
//        }
//        xml.append("]]></description>\n");
        // placemarks

        if (writePlacemaks) {
            xml.append("<Folder>\n");
            xml.append("<name>Trackpoints</name>\n");
            xml.append("<open>0</open>\n");

            for (Trackpoint t : wayPoints) {
                xml.append("<Placemark>\n");
                xml.append("<name>" + t.getName() + "</name>\n");
                xml.append("<styleUrl>#icon1</styleUrl>\n");
                xml.append("<Point>\n");
                xml.append("<altitudeMode>" + placemarkAltitudeMode + "</altitudeMode>\n");
                xml.append("<coordinates>" + t.getLongitude().getDecimal() + "," + t.getLatitude().getDecimal() + "," + t.getAltitude() + "</coordinates>\n");
                xml.append("</Point>\n");
                xml.append("</Placemark>\n");
            }
            xml.append("</Folder>\n");
        }
        // track

        for (Model.UAV uav : UAVs) {
            if (followingUAVID >= 0 && uav.getUAV_ID() != followingUAVID) {
                continue;
            }
            xml.append("<Folder>\n");
                  

            ArrayList<Trackpoint> tmp = new ArrayList<>();
                if (uav.getMapTrackPath().getTrackpath().getTrackpoints().size() > 100) {
                    for (int i = uav.getMapTrackPath().getTrackpath().getTrackpoints().size() - 100; i < uav.getMapTrackPath().getTrackpath().getTrackpoints().size(); i++) {
                        tmp.add(uav.getMapTrackPath().getTrackpath().getTrackpoints().get(i));
                    }
                } else {
                    tmp.addAll(uav.getMapTrackPath().getTrackpath().getTrackpoints());
                }

            if (writeTrack) {
                xml.append("<name>UAV #" + uav.getUAV_ID() + "</name>\n");
                xml.append("<open>1</open>\n");
                xml.append("<Placemark>\n");
                xml.append("<name>UAV #" + uav.getUAV_ID() + " GPS</name>\n");
                xml.append("<styleUrl>#UAV" + uav.getUAV_ID() + "color</styleUrl>\n");
                xml.append("<LineString>\n");
                xml.append("<extrude>0</extrude>\n");
                xml.append("<tessellate>1</tessellate>\n");
                xml.append("<altitudeMode>" + trackAltitudeMode + "</altitudeMode>\n");
                xml.append("<coordinates>\n");


                for (Trackpoint t : tmp) {
                    xml.append(t.getLongitude().getDecimal() + "," + t.getLatitude().getDecimal() + "," + t.getAltitude() + "\n");
                }
                xml.append("</coordinates>\n");
                xml.append("</LineString>\n");

                xml.append("</Placemark>\n");
            }
            
            double UAV_Head_Degree = 0;
            
            for(int i=0;tmp.size()>5 && i<4 ; i++)
            {
                UAV_Head_Degree += GPSUtil.bearing(tmp.get(tmp.size()-2-i), tmp.get(tmp.size()-1-i))*(4-i) / 10;
            }
            
            xml.append("<Placemark>" + "<name>#" + uav.getUAV_ID() + "</name>");

            xml.append("<Style id=\"default\" /><Model>");

            xml.append("<altitudeMode>" + trackAltitudeMode + "</altitudeMode>"
                    + "<Location>"
                    + "<latitude>" + uav.getPosition().getLatitude().getDecimal() + "</latitude>"
                    + "<longitude>" + uav.getPosition().getLongitude().getDecimal() + "</longitude>"
                    + "<altitude>" + uav.getPosition().getAltitude() + "</altitude>"
                    + "</Location>"
                    + "<Orientation>"
                    + "<heading>" + (-UAV_Head_Degree+100) + "</heading>"
                    + "<tilt>"+uav.getElevator().getStepCount()/10+"</tilt>"
                    + "<roll>0</roll>"
                    + "</Orientation>"
                    + "<Scale><x>3</x><y>3</y><z>3</z></Scale>"
                    + "<Link>"
                    + "<href>C:/Users/Administrator/Desktop/UAV3DModel.dae</href>"
                    + "</Link>"
                    + "</Model>"
                    + "</Placemark>");

            xml.append("</Folder>\n");
        }
        // footer
        xml.append("</Document>\n");
        xml.append("</kml>\n");

        return xml.toString();
    }
}
