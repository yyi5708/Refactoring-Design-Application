package bowling;

import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class ScoreHistoryFile {

	private static final String SCOREHISTORY_DAT = "SCOREHISTORY.DAT";

	/**
	 * Adds a score to the score history database
	 * 
	 * @param nick  the nickname of the player
	 * @param date  the date of the game
	 * @param score the score
	 * @throws IOException if an I/O error occurs
	 */

	public static void addScore(String nick, String date, String score) throws IOException {
		String data = nick + "\t" + date + "\t" + score + "\n";
		Files.write(Paths.get(SCOREHISTORY_DAT), data.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE,
				StandardOpenOption.APPEND);
	}

	/**
	 * Retrieves scores for a given nickname
	 * 
	 * @param nick the nickname of the player
	 * @return a Vector of Scores
	 * @throws IOException if an I/O error occurs
	 */

	public static Vector<Score> getScores(String nick) throws IOException {
		Vector<Score> scores = new Vector<>();
		List<String> lines = Files.readAllLines(Paths.get(SCOREHISTORY_DAT), StandardCharsets.UTF_8);
		for (String line : lines) {
			String[] scoredata = line.split("\t");
			if (nick.equals(scoredata[0])) {
				scores.add(new Score(scoredata[0], scoredata[1], scoredata[2]));
			}
		}
		return scores;
	}
}