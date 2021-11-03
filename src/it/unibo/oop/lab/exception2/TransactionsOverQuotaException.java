package it.unibo.oop.lab.exception2;

@SuppressWarnings("serial")
public class TransactionsOverQuotaException extends Exception {
	

	public TransactionsOverQuotaException() {
		
	}

	public String toString() {
        return "Finished transactions";
    }
	
	public String getMessage() {
        return this.toString();
    }
}
