package bowling;

import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.print.*;

public class ScoreReport {

	private String content;

	public ScoreReport(Bowler bowler, int[] scores, int games) {
		String nick = bowler != null ? bowler.getNick() : "Unknown";
		String full = bowler != null ? bowler.getFullName() : "Unknown";
		Vector<Score> v = null;
		try {
			v = ScoreHistoryFile.getScores(nick);
		} catch (Exception e) {
			System.err.println("Error fetching scores: " + e.getMessage());
		}
		content = "--Lucky Strike Bowling Alley Score Report--\n\n";
		content += "Report for " + full + ", aka \"" + nick + "\":\n\n";
		content += "Final scores for this session: " + (scores != null && scores.length > 0 ? scores[0] : "N/A");
		if (scores != null) {
			for (int i = 1; i < games && i < scores.length; i++) {
				content += ", " + scores[i];
			}
		}
		content += ".\n\n";
		content += "Previous scores by date:\n";
		if (v != null) {
			for (Score score : v) {
				content += "  " + score.getDate() + " - " + score.getScore() + "\n";
			}
		} else {
			content += "  No previous scores available.\n";
		}
		content += "\nThank you for your continuing patronage.";
	}

	public void sendEmail(String recipient) {
		try {
			Socket s = new Socket("osfmail.rit.edu", 25);
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream(), "8859_1"));
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream(), "8859_1"));
			sendln(in, out, "HELO world");
			sendln(in, out, "MAIL FROM: <abc1234@rit.edu>");
			sendln(in, out, "RCPT TO: <" + recipient + ">");
			sendln(in, out, "DATA");
			sendln(out, "Subject: Bowling Score Report ");
			sendln(out, "From: <Lucky Strikes Bowling Club>");
			sendln(out, "Content-Type: text/plain; charset=\"us-ascii\"\r\n");
			sendln(out, content + "\n\n");
			sendln(out, "\r\n");
			sendln(in, out, ".");
			sendln(in, out, "QUIT");
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendPrintout() {
		PrinterJob job = PrinterJob.getPrinterJob();
		PrintableText printobj = new PrintableText(content);
		job.setPrintable(printobj);
		if (job.printDialog()) {
			try {
				job.print();
			} catch (PrinterException e) {
				System.out.println(e);
			}
		}
	}

	private void sendln(BufferedReader in, BufferedWriter out, String s) {
		try {
			out.write(s + "\r\n");
			out.flush();
			s = in.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendln(BufferedWriter out, String s) {
		try {
			out.write(s + "\r\n");
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}