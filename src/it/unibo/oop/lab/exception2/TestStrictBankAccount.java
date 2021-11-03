package it.unibo.oop.lab.exception2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * JUnit test to test {@link StrictBankAccount}.
 * 
 */
public final class TestStrictBankAccount {

	/**
	 * Used to test Exceptions on {@link StrictBankAccount}.
	 */
	@Test
	public void testBankOperations() {
		/*
		 * 1) Creare due StrictBankAccountImpl assegnati a due AccountHolder a scelta,
		 * con ammontare iniziale pari a 10000 e nMaxATMTransactions=10
		 * 
		 * 2) Effetture un numero di operazioni a piacere per verificare che le
		 * eccezioni create vengano lanciate soltanto quando opportuno, cioè in presenza
		 * di un id utente errato, oppure al superamento del numero di operazioni ATM
		 * gratuite.
		 */
		var Rossi = new AccountHolder("Paolo", "Rossi", 10);
		var Bianchi = new AccountHolder("Lucia", "Bianchi", 20);
		var AccRossi = new StrictBankAccount(Rossi.getUserID(), 10000, 10);
		var AccBianchi = new StrictBankAccount(Bianchi.getUserID(), 10000, 10);

		assertEquals(AccRossi.getBalance(), 10000, 0.5);
		assertEquals(AccBianchi.getBalance(), 10000, 0.5);
		System.out.println("Rossi " + AccRossi.getTransactionCount());
		System.out.println("Bianchi " + AccBianchi.getTransactionCount());
		try {
			AccRossi.deposit(Rossi.getUserID(), 10);
			AccBianchi.withdraw(Bianchi.getUserID(), 10);
		} catch (WrongAccountHolderException | NotEnoughFoundsException e) {
			fail("Shouldn't have failed");
		}
		System.out.println("Rossi " + AccRossi.getTransactionCount());
		System.out.println("Bianchi " + AccBianchi.getTransactionCount());
		try {
			AccBianchi.deposit(Rossi.getUserID(), 10);
			fail();
		} catch (WrongAccountHolderException e) {
			// Should fail
		}

		for (int i = 0; i < 7; i++) {
			try {
				AccRossi.depositFromATM(Rossi.getUserID(), 1);
				System.out.println("i " + i);
				System.out.println("Trans: " + AccRossi.getTransactionCount());// Perchè è la 11° transazione?
			} catch (WrongAccountHolderException e) {
				fail("Shouldn't have failed");
			} catch (TransactionsOverQuotaException f) {
				fail("Shouldn't have failed");
			}
			try {
				AccBianchi.withdrawFromATM(Bianchi.getUserID(), 1);
			} catch (WrongAccountHolderException | TransactionsOverQuotaException | NotEnoughFoundsException e) {
				fail("Shouldn't have failed");
			}
		}

		try {
			AccBianchi.depositFromATM(Bianchi.getUserID(), 10);
			AccBianchi.depositFromATM(Bianchi.getUserID(), 10);
			System.out.println("Trans: " + AccBianchi.getTransactionCount());
			AccBianchi.depositFromATM(Bianchi.getUserID(), 10);
			System.out.println("Trans: " + AccBianchi.getTransactionCount());
			fail();
		} catch (TransactionsOverQuotaException e) {
			// Should fail
		} catch (WrongAccountHolderException e) {
			fail("Shouldn't have failed");
		}

		try {
			AccRossi.computeManagementFees(Rossi.getUserID());
		} catch (WrongAccountHolderException | NotEnoughFoundsException e) {
			fail();
		}

		try {
			AccRossi.withdraw(Rossi.getUserID(), 100000);
		} catch (NotEnoughFoundsException e) {
			// Should fail
		} catch (WrongAccountHolderException e) {
			fail();
		}

	}
}
