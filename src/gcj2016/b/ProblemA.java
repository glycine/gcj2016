package gcj2016.b;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProblemA {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])));
		int lineNum = Integer.parseInt(reader.readLine());

		ProblemA problemA = new ProblemA();
		for (int i = 0; i < lineNum; ++i) {
			String line = reader.readLine();
			// System.out.println(line);
			int[] chars = problemA.prepare(line);
			List<Integer> result = problemA.solve(chars, new ArrayList<Integer>());
			System.out.print("Case #" + (i + 1) + ": ");
			for (Integer elem : result) {
				System.out.print(elem);
			}
			System.out.println("");
		}
		reader.close();

	}

	private int[] prepare(String in) {
		int[] result = new int[26];
		for (int i = 0; i < result.length; ++i)
			result[i] = 0;

		for (int i = 0; i < in.length(); ++i) {
			char c = in.charAt(i);
			int index = (int) c - 65;
			result[index]++;
		}

		return result;
	}

	private List<Integer> solve(int[] chars, List<Integer> values) {
		boolean flag = true;
		for (int i = 0; i < chars.length; ++i) {
			if (chars[i] != 0) {
				flag = false;
				break;
			}
		}
		if (flag) {
			return values;
		}

		List<Integer> result = null;

		for (int i = 0; i < 10; ++i) {
			int[] newChars = match(chars, i);
			if (newChars != null) {
				List<Integer> newValues = new ArrayList<Integer>(values);
				newValues.add(i);
				List<Integer> l = solve(newChars, newValues);
				if (l != null) {
					result = l;
					break;
				}
			}
		}

		return result;

	}

	private int[] match(int[] chars, int number) {
		int[] result = Arrays.copyOf(chars, chars.length);
		switch (number) {
		case 0: // ZERO
		default:
			if (chars[25] > 0 && chars[4] > 0 && chars[17] > 0 && chars[14] > 0) {
				result[25]--;
				result[4]--;
				result[17]--;
				result[14]--;
				return result;
			} else {
				return null;
			}
		case 1: // ONE
			if (chars[14] > 0 && chars[13] > 0 && chars[4] > 0) {
				result[14]--;
				result[13]--;
				result[4]--;
				return result;
			} else {
				return null;
			}
		case 2: // TWO
			if (chars[19] > 0 && chars[22] > 0 && chars[14] > 0) {
				result[19]--;
				result[22]--;
				result[14]--;
				return result;
			} else
				return null;
		case 3: // THREE
			if (chars[19] > 0 && chars[7] > 0 && chars[17] > 0 && chars[4] > 1) {
				result[19]--;
				result[7]--;
				result[17]--;
				result[4] = result[4] - 2;
				return result;
			} else
				return null;
		case 4: // FOUR
			if (chars[5] > 0 && chars[14] > 0 && chars[20] > 0 && chars[17] > 0) {
				result[5]--;
				result[14]--;
				result[20]--;
				result[17]--;
				return result;
			} else
				return null;
		case 5: // FIVE
			if (chars[5] > 0 && chars[8] > 0 && chars[21] > 0 && chars[4] > 0) {
				result[5]--;
				result[8]--;
				result[21]--;
				result[4]--;
				return result;
			} else
				return null;
		case 6: // SIX
			if (chars[18] > 0 && chars[8] > 0 & chars[23] > 0) {
				result[18]--;
				result[8]--;
				result[23]--;
				return result;
			} else
				return null;
		case 7: // SEVEN
			if (chars[18] > 0 && chars[4] > 1 && chars[21] > 0 && chars[13] > 0) {
				result[18]--;
				result[4] = result[4] - 2;
				result[21]--;
				result[13]--;
				return result;
			} else
				return null;
		case 8: // EIGHT
			if (chars[4] > 0 && chars[8] > 0 && chars[6] > 0 && chars[7] > 0 && chars[19] > 0) {
				result[4]--;
				result[8]--;
				result[6]--;
				result[7]--;
				result[19]--;
				return result;
			} else
				return null;
		case 9: // NINE
			if (chars[13] > 1 && chars[8] > 0 && chars[4] > 0) {
				result[13] = result[13] - 2;
				result[8]--;
				result[4]--;
				return result;
			} else
				return null;
		}
	}
}
