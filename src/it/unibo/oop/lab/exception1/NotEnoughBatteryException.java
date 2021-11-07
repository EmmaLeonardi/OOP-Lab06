package it.unibo.oop.lab.exception1;

@SuppressWarnings("serial")
public class NotEnoughBatteryException extends Exception {
	
	/*This Exception extends Exception because exceptions are
	 * predictable problems that can't be fixed*/
	
	private final double battery;

	public NotEnoughBatteryException(double battery) {
		this.battery=battery;
	}
	
	public String toString() {
        return "Cannot move, not enough battery: "+battery+" %";
    }
	
	public String getMessage() {
        return this.toString();
    }

}
