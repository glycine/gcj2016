package gcj2016.c;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProblemC {
	private static final int MAX = 10;
	private int[][] jacketPants = null;
	private int[][] jacketShirts = null;
	private int[][] pantsShirts = null;

	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])));
		int lineNum = Integer.parseInt(reader.readLine());
		ProblemC problemC = new ProblemC();
		for (int i = 0; i < lineNum; ++i) {
			problemC.init();
			String[] tokens = reader.readLine().split(" ");
			int jackets = Integer.parseInt(tokens[0]);
			int pants = Integer.parseInt(tokens[1]);
			int shirts = Integer.parseInt(tokens[2]);
			int kinds = Integer.parseInt(tokens[3]);
			List<String> results = problemC.solve(jackets, pants, shirts, kinds);
			System.out.println("Case #" + (i + 1) + ": " + results.size());
			for (String str : results) {
				System.out.println(str);
			}
		}
		reader.close();
	}

	public ProblemC() {
		jacketPants = new int[MAX][MAX];
		jacketShirts = new int[MAX][MAX];
		pantsShirts = new int[MAX][MAX];
		init();
	}

	public void init() {
		for (int i = 0; i < MAX; ++i) {
			for (int j = 0; j < MAX; ++j) {
				jacketPants[i][j] = 0;
				jacketShirts[i][j] = 0;
				pantsShirts[i][j] = 0;
			}
		}
	}

	public List<String> solve(int jackets, int pants, int shirts, int kinds) {
		List<String> results = new ArrayList<>();
		for (int i = 0; i < jackets; ++i) {
			for (int j = 0; j < pants; ++j) {
				for (int k = 0; k < shirts; ++k) {
					// if ((jacketPants[i][j] < kinds) && (jacketShirts[i][k] <
					// kinds) && (pantsShirts[j][k] < kinds)) {

					if ((jacketPants[i][j] + jacketShirts[i][k] + pantsShirts[j][k]) < kinds) {
						int count = countFalse(jacketPants[i][j], jacketShirts[i][k], pantsShirts[j][k]);
						if (count < 2) {
							results.add((i + 1) + " " + (j + 1) + " " + (k + 1));
							/*
							 * if (jacketPants[i][j] > 0) { kinds--; } if
							 * (jacketShirts[i][k] > 0) { kinds--; } if
							 * (pantsShirts[j][k] > 0) { kinds--; }
							 */
							jacketPants[i][j]++;
							jacketShirts[i][k]++;
							pantsShirts[j][k]++;
						}
					}
				}
			}
		}
		return results;
	}

	public int countFalse(int jacketPants, int jacketShirts, int pantsShirts) {
		int result = 0;
		if (jacketPants > 0)
			++result;
		if (jacketShirts > 0)
			++result;
		if (pantsShirts > 0)
			++result;
		return result;
	}
}
