package Controller.Map.GPS;

/**
 * The <code>GPSUtil</code> class provides some utility functionality
 * for the GPS data handling. 
 * 
 * @author      jarontec gmail com
 * @version     1.2
 * @since       1.1
 */
public class GPSUtil {
  /**
   * Returns the distance between two locations.
   * 
   * @param lat1 the latitude coordinate of the first location
   * @param lon1 the longitude coordinate of the first location
   * @param lat2 the latitude coordinate of the second location
   * @param lon2 the longitude coordinate of the second location
   * @return  the calculated distance in kilometers
   */
  public static double getDistance(double lat1, double lon1, double lat2, double lon2) {
    /*
    Source: http://www.movable-type.co.uk/scripts/latlong.html

    var R = 6371; // km
    var dLat = (lat2-lat1).toRad();
    var dLon = (lon2-lon1).toRad(); 
    var a = Math.sin(dLat/2) * Math.sin(dLat/2) +
            Math.cos(lat1.toRad()) * Math.cos(lat2.toRad()) * 
            Math.sin(dLon/2) * Math.sin(dLon/2); 
    var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
    var d = R * c;
     */
    int R = 6371;
    double dLat = Math.toRadians((lat2 - lat1));
    double dLon = Math.toRadians((lon2 - lon1)); 
    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
    Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * 
    Math.sin(dLon / 2) * Math.sin(dLon / 2); 
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)); 
    double d = R * c;
    return d;
  }

  /**
   * Returns the distance between two locations.
   * 
   * @param lat1 a <code>Latitude</code> object containing the latitude coordinate of the first location
   * @param lon1 a <code>Latitude</code> object containing the longitude coordinate of the first location
   * @param lat2 a <code>Latitude</code> object containing the latitude coordinate of the second location
   * @param lon2 a <code>Latitude</code> object containing the longitude coordinate of the second location
   * @return  the calculated distance in kilometers
   */
  public static double getDistance(Latitude lat1, Longitude lon1, Latitude lat2, Longitude lon2){
    return getDistance(lat1.getDecimal(), lon1.getDecimal(), lat2.getDecimal(), lon2.getDecimal());
  }
  
      public static double bearing(Trackpoint p1, Trackpoint p2) {
        double lat1 = p1.getLatitude().getDecimal();
        double lon1 = p1.getLongitude().getDecimal();
        double lat2 = p2.getLatitude().getDecimal();
        double lon2 = p2.getLongitude().getDecimal();

        return bearing(lat1, lon1, lat2, lon2);
    }
    
    /**
     * Computes the bearing in degrees between two points on Earth.
     * 
     * @param lat1 Latitude of the first point
     * @param lon1 Longitude of the first point
     * @param lat2 Latitude of the second point
     * @param lon2 Longitude of the second point
     * @return Bearing between the two points in degrees. A value of 0 means due
     *         north.
     */
    public static double bearing(double lat1, double lon1, double lat2, double lon2) {
        double lon=lon2-lon1;
        double lat=lat2-lat1;
        
        return Math.toDegrees(Math.atan2(lat, lon));
        
        
        /*double lat1Rad = Math.toRadians(lat1);
        double lat2Rad = Math.toRadians(lat2);
        
        double deltaLonRad = Math.toRadians(lon2 - lon1);

        double y = Math.sin(deltaLonRad) * Math.cos(lat2Rad);
        double x = Math.cos(lat1Rad) * Math.sin(lat2Rad) - Math.sin(lat1Rad) * Math.cos(lat2Rad)
                * Math.cos(deltaLonRad);
        return radToBearing(Math.atan2(y, x));*/
    }
    
    /**
     * Converts an angle in radians to degrees
     */
    public static double radToBearing(double rad) {
       return (Math.toDegrees(rad)) % 360;
        
    }
  
  
  /**
   * Returns the distance between two locations.
   * 
   * @param p1 a <code>Trackpoint</code> object containing the first location
   * @param p2 a <code>Trackpoint</code> object containing the second location
   * @return  the calculated distance in kilometers
   */
  public static double getDistance(Trackpoint p1, Trackpoint p2) {
    return getDistance(p1.getLatitude().getDecimal(), p1.getLongitude().getDecimal(), p2.getLatitude().getDecimal(), p2.getLongitude().getDecimal());
  }
}
