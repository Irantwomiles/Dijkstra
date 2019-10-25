package A5_Dijkstra;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class DiGraphPlayGround {

	public static void main(String[] args) {

		// thorough testing is your responsibility
		//
		// you may wish to create methods like
		// -- print
		// -- sort
		// -- random fill
		// -- etc.
		// in order to convince yourself your code is producing
		// the correct behavior
		// effiencyTest();
		// exTest();
		dijkstra();
	}

	public static void exTest() {
		DiGraph d = new DiGraph();
		d.addNode(1, "f");
		d.addNode(3, "s");
		d.addNode(7, "t");
		d.addNode(0, "fo");
		d.addNode(4, "fi");
		d.addNode(6, "si");
		d.addEdge(0, "f", "s", 0, null);
		d.addEdge(1, "f", "si", 0, null);
		d.addEdge(2, "s", "t", 0, null);
		d.addEdge(3, "fo", "fi", 0, null);
		d.addEdge(4, "fi", "si", 0, null);
		System.out.println("numEdges: " + d.numEdges());
		System.out.println("numNodes: " + d.numNodes());
		//d.print();
	}

	public static void effiencyTest() {
		DiGraph d = new DiGraph();
		RandomString r = new RandomString(10);
		String sr = r.nextString();
		d.addNode(1, sr);
		String mr = "";
		for (int i = 0; i < 1000000; i++) {
			mr = r.nextString();
			d.addNode(i + 2, mr);
			d.addEdge(i, sr, mr, 0, null);
			sr = mr;
		}
		// d.print();
	}

	public static void dijkstra() {
		DiGraph d = new DiGraph();
		d.addNode(0, "Raleigh");
		d.addNode(1, "Durham");
		d.addNode(2, "Pittsboro");
		d.addNode(3, "Los_angeles");
		d.addNode(4, "Graham");
		d.addNode(5, "Cary");
		d.addNode(6, "Chapel_hill");
		d.addNode(7, "Hillsborough");
		d.addNode(8, "Carrboro");
		d.addNode(9, "Sanford");
		d.addEdge(0, "Raleigh", "Durham", 14, null);
		d.addEdge(1, "Durham", "Hillsborough", 9, null);
		d.addEdge(2, "Chapel_hill", "Graham", 25, null);
		d.addEdge(3, "Chapel_hill", "Carrboro", 1, null);
		d.addEdge(4, "Carrboro", "Cary", 32, null);
		d.addEdge(5, "Cary", "Raleigh", 3, null);
		d.addEdge(6, "Pittsboro", "Cary", 17, null);
		d.addEdge(7, "Pittsboro", "Sanford", 15, null);
		d.addEdge(8, "Sanford", "Los_angeles", 3012, null);
		//d.print();
		ShortestPathInfo[] table = d.shortestPath("Pittsboro");
		for (int i = 0; i < table.length; i++) {
			System.out.println(d.shortestPath("Pittsboro")[i]);
		}
	}
}

class RandomString {

	/**
	 * Generate a random string.
	 */
	public String nextString() {
		for (int idx = 0; idx < buf.length; ++idx)
			buf[idx] = symbols[random.nextInt(symbols.length)];
		return new String(buf);
	}

	public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static final String lower = upper.toLowerCase(Locale.ROOT);

	public static final String digits = "0123456789";

	public static final String alphanum = upper + lower + digits;

	private final Random random;

	private final char[] symbols;

	private final char[] buf;

	public RandomString(int length, Random random, String symbols) {
		if (length < 1)
			throw new IllegalArgumentException();
		if (symbols.length() < 2)
			throw new IllegalArgumentException();
		this.random = Objects.requireNonNull(random);
		this.symbols = symbols.toCharArray();
		this.buf = new char[length];
	}

	/**
	 * Create an alphanumeric string generator.
	 */
	public RandomString(int length, Random random) {
		this(length, random, alphanum);
	}

	/**
	 * Create an alphanumeric strings from a secure generator.
	 */
	public RandomString(int length) {
		this(length, new SecureRandom());
	}

	/**
	 * Create session identifiers.
	 */
	public RandomString() {
		this(21);
	}

}