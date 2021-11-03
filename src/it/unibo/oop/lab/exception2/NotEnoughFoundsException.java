package it.unibo.oop.lab.exception2;


@SuppressWarnings("serial")
public class NotEnoughFoundsException extends Exception {

	private final double amount;

	public NotEnoughFoundsException(double amount) {
		this.amount=amount;
	}

	public String toString() {
        return "Not enough founds: "+this.amount;
    }
	
	public String getMessage() {
        return this.toString();
    }

}
