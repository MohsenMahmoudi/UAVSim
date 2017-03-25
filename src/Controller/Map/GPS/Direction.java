package Controller.Map.GPS;

/**
 * The <code>Direction</code> class provides some functionality for
 * determining the direction of a geographical coordinate.<br>
 * <br>
 * Modified source code from: http://code.google.com/p/gpsparser/
 * 
 * @author      jarontec gmail com
 * @version     1.2
 * @since       1.1
 */
public enum Direction {
            NORTH("N"),
            NORTHEAST("NE"),
            EAST("E"),
            SOUTHEAST("SE"),
            SOUTH("S"),
            SOUTHWEST("SW"),
            WEST("W"),
            NORTHWEST("NW");
        
        
        
        
	private final String value;
	
	private Direction(String value){
		this.value = value;
	}

	public String getValue(){
		return this.value;
	}

	public static Direction fromValue(String value) {
		for(Direction d:Direction.values()){
			if(d.getValue().equals(value)){
				return d;
			}
		}
		throw new IllegalArgumentException(value);
	}
}
