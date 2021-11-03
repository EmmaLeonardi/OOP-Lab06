package it.unibo.oop.lab.exception2;

@SuppressWarnings("serial")
public class WrongAccountHolderException extends Exception {

	private final int id;

	public WrongAccountHolderException(int ID) {
		this.id=ID;
	}

	public String toString() {
        return "Wrong Account Holder: "+this.id;
    }
	
	public String getMessage() {
        return this.toString();
    }

}
