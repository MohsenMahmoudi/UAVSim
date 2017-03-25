/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Component.DataProvider;

import Controller.Map.GPS.Trackpoint;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Administrator
 */
public class TemperatureDataProvider {

    private static final String API = "http://api.openweathermap.org/data/2.5/";

    public static int getRealTemperature(Trackpoint position) {
        try {
            URL url = new URL(API + "weather?lat=" + position.getLatitude().getDecimal() + "&lon=" + position.getLongitude().getDecimal());
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(new InputStreamReader(url.openStream()));
            int temp_in_Kelvin = (int) Double.parseDouble(((JSONObject) obj.get("main")).get("temp").toString());
            return (int) (temp_in_Kelvin - 273.15);
        } catch (IOException ex) {
            return (int) (Math.random() * 10);
        } catch (ParseException ex) {
            return (int) (Math.random() * 10);
        }
    }
}
