package gcj2016.b;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ProblemB {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])));
		int lineNum = Integer.parseInt(reader.readLine());

		ProblemB problemB = new ProblemB();
		for (int i = 0; i < lineNum; ++i) {
			String tokens[] = reader.readLine().split(" ");
			String[] result = problemB.solve(tokens[0], tokens[1]);
			System.out.print("Case #" + (i + 1) + ": ");
			System.out.print(result[0] + " ");
			System.out.println(result[1]);
		}

		reader.close();

	}

	private String[] solve(String a, String b) {
		char[] resultA = new char[a.length()];
		char[] resultB = new char[b.length()];

		for (int i = a.length()-1; i >=0; --i) {
			char scoreA = a.charAt(i);
			char scoreB = b.charAt(i);
			if (scoreA == '?' && scoreB == '?') {
				if (i == 0 || a.charAt(i - 1) == b.charAt(i - 1)) {
					resultA[i] = '0';
					resultB[i] = '0';
				} else if (a.charAt(i - 1) > b.charAt(i - 1)) {
					resultA[i] = '0';
					resultB[i] = '9';
				} else {
					resultA[i] = '9';
					resultB[i] = '0';
				}
			} else if (scoreA == '?' && scoreB != '?') {
				if (i + 1 == a.length() || (a.charAt(i + 1) == '?' ||  b.charAt(i + 1) == '?')) {
					resultA[i] = scoreB;
					resultB[i] = scoreB;
				}else if(Math.abs(a.charAt(i+1) - b.charAt(i+1)) <= 4){
					resultA[i] = scoreB;
					resultB[i] = scoreB;
				}else if(a.charAt(i+1) > b.charAt(i+1)){
					resultA[i] = score
				}
			} else if (scoreA != '?' && scoreB == '?') {
				resultA[i] = scoreA;
				resultB[i] = scoreA;
			} else {
				resultA[i] = scoreA;
				resultB[i] = scoreB;
			}
		}
		String[] result = new String[2];
		result[0] = new String(resultA);
		result[1] = new String(resultB);
		return result;
	}

	private char[] subfunc(char aTop, char bTop, char[] aRemain, char[] bRemain) {
		char[] result = new char[2];
		if (aTop != '?' && bTop != '?') {
			result[0] = aTop;
			result[1] = bTop;
			return result;
		}
		long a = Long.parseLong(new String(aRemain));
		long b = Long.parseLong(new String(bRemain));
		if (b > a) {
			char[] r = subfunc(bTop, aTop, bRemain, aRemain);
			result[1] = r[0];
			result[0] = r[1];
			return result;
		}
		long t = (long) Math.pow(10.0, aRemain.length + 1);
		if ((b - a) < t / 2) {
			if (aTop == '?' && bTop == '?') {
				result[0] = '0';
				result[1] = '0';
			} else if (aTop == '?' && bTop != '?') {
				result[0] = bTop;
				result[1] = bTop;
			} else {
				result[0] = aTop;
				result[1] = aTop;
			}
			return result;
		} else {
			if (aTop == '?' && bTop == '?') {
				result[0] = '1';
				result[1] = '0';
			} else if (aTop == '?' && bTop != '?') {
				result[0] = (char) (bTop + 1);
				result[1] = bTop;
			} else {
				result[0] = aTop;
				result[1] = (char) (aTop - 1);
			}
			return result;
		}
	}

}
