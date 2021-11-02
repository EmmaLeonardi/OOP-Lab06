package it.unibo.oop.lab.exception1;

@SuppressWarnings("serial")
public class NotEnoughBatteryException extends Exception {
	
	private final double battery;

	public NotEnoughBatteryException(double battery) {
		this.battery=battery;
	}

//	public NotEnoughBatteryException(String message) {
//		super(message);
//		// TODO Auto-generated constructor stub
//	}
//
//	public NotEnoughBatteryException(Throwable cause) {
//		super(cause);
//		// TODO Auto-generated constructor stub
//	}
//
//	public NotEnoughBatteryException(String message, Throwable cause) {
//		super(message, cause);
//		// TODO Auto-generated constructor stub
//	}
//
//	public NotEnoughBatteryException(String message, Throwable cause, boolean enableSuppression,
//			boolean writableStackTrace) {
//		super(message, cause, enableSuppression, writableStackTrace);
//		// TODO Auto-generated constructor stub
//	}
	
	public String toString() {
        return "Can not move to, not enough battery: "+battery+" %";
    }
	
	public String getMessage() {
        return this.toString();
    }

}
