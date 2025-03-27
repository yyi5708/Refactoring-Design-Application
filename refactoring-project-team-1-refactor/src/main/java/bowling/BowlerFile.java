package bowling;

import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

class BowlerFile {

	private static final String BOWLER_DAT = "BOWLERS.DAT";

	/**
	 * Retrieves bowler information from the database and returns a Bowler object
	 * with populated fields.
	 * 
	 * @param nickName the nickname of the bowler to retrieve
	 * @return a Bowler object
	 * @throws IOException if an I/O error occurs
	 */

	public static Bowler getBowlerInfo(String nickName) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(BOWLER_DAT), StandardCharsets.UTF_8);
		for (String line : lines) {
			String[] bowler = line.split("\t");
			if (nickName.equals(bowler[0])) {
				System.out.println("Nick: " + bowler[0] + " Full: " + bowler[1] + " email: " + bowler[2]);
				return new Bowler(bowler[0], bowler[1], bowler[2]);
			}
		}
		System.out.println("Nick not found...");
		return null;
	}

	/**
	 * Stores a Bowler in the database
	 * 
	 * @param nickName the nickname of the Bowler
	 * @param fullName the full name of the Bowler
	 * @param email    the email address of the Bowler
	 * @throws IOException if an I/O error occurs
	 */

	public static void putBowlerInfo(String nickName, String fullName, String email) throws IOException {
		String data = nickName + "\t" + fullName + "\t" + email + "\n";
		Files.write(Paths.get(BOWLER_DAT), data.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE,
				StandardOpenOption.APPEND);
	}

	/**
	 * Retrieves a list of nicknames in the bowler database
	 * 
	 * @return a Vector of Strings
	 * @throws IOException if an I/O error occurs
	 */

	public static Vector<String> getBowlers() throws IOException {
		Vector<String> allBowlers = new Vector<>();
		List<String> lines = Files.readAllLines(Paths.get(BOWLER_DAT), StandardCharsets.UTF_8);
		for (String line : lines) {
			String[] bowler = line.split("\t");
			allBowlers.add(bowler[0]);
		}
		return allBowlers;
	}
}