package ch.fhnw.oop2.module05.transactions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * This class implements a list of transactions performed by the traders over time.
 *
 */
public final class TransactionList {
	
	private final List<Transaction> allTransactions = new ArrayList<>();

	public void addTransaction(Transaction transaction) {
		allTransactions.add(transaction);
	}

	public int size() {
		return allTransactions.size();
	}

	/**
	 * Returns the transactions done in the year specified.
	 * The transactions are sorted by value (small to high).
	 * 
	 * @param year The year
	 * @return All transactions made in this year
	 */
	public List<Transaction> transactionsInYear(int year) {
		return allTransactions.stream()
				.filter(t -> t.getYear() == year)
				.sorted(Comparator.comparing(t -> t.getValue()))
				.collect(Collectors.toList());
    }

	/**
	 * Returns all the cities in which traders work.
	 * 
	 * @return The cities
	 */
	public List<String> cities() {
        return allTransactions
				.stream()
				.map(t -> t.getTrader())
				.map(t -> t.getCity())
				.distinct()
				.collect(Collectors.toList());

    }

	/**
	 * Returns all traders from a given city sorted by name.
	 * 
	 * @param city The trader's city
	 * @return All traders from given city sorted by name
	 */
	public List<Trader> traders(String city) {
        return allTransactions
				.stream()
				.map(t -> t.getTrader())
				.filter(t -> t.getCity().equals(city))
				.distinct()
				.sorted(Comparator.comparing(t -> t.toString()))
				.collect(Collectors.toList());
    }

	/**
	 * True if there are traders in the city, false otherwise.
	 * 
	 * @param city The city
	 * @return True if there are any trader based in given city
	 */
	public boolean traderInCity(String city) {
		return allTransactions
				.stream()
				.anyMatch(t -> t.getTrader().getCity().equals(city));
	}

	// TODO: AB06
	/**
	 * Moves all traders from their city to the city specified.
	 * 
	 * @param from the trader's current city
	 * @param to   the trader's new city
	 */
	public void relocateTraders(String from, String to) {
		allTransactions
				.stream()
				.map(t -> t.getTrader())
				.filter(t -> t.getCity().equals(from))
				.forEach(trader -> trader.setCity(to));
	}

	// TODO: AB07
	/**
	 * Returns the highest value of all transactions.
	 * 
	 * @return the highest value in all the transactions
	 */
	public int highestValue() {
		return allTransactions
				.stream()
				.map(Transaction::getValue)
				.mapToInt(t -> t)
				.max().orElseThrow(NoSuchElementException::new);
	}
}