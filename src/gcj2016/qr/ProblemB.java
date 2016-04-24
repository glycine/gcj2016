package gcj2016.qr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class ProblemB {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])));
		int lineNum = Integer.parseInt(reader.readLine());

		ProblemB problemB = new ProblemB();
		for (int i = 1; i <= lineNum; ++i) {
			boolean[] in = problemB.parse(reader.readLine());
			System.out.println("Case #" + i + ": " + problemB.calc(0, in));
		}

	}

	private long calc(long count, boolean[] in) {
		if (check(in))
			return count;

		int inverseIndex = findInverse(in);
		boolean[] newIn = inverse(inverseIndex, in);

		return calc(count + 1, newIn);
	}

	private boolean[] inverse(int inverseIndex, boolean[] in) {
		boolean[] result = Arrays.copyOf(in, in.length);

		for (int i = inverseIndex; i >= 0; --i)
			result[inverseIndex - i] = !(in[i]);

		return result;
	}

	private int findInverse(boolean[] in) {
		// inの長さが1の場合
		if (in.length == 1) {
			if (!in[0])
				return 0;
			else
				return -1;
		}

		// inの長さが1以外の場合
		for (int i = 1; i < in.length; ++i) {
			if (in[i] != in[i - 1])
				return i - 1;
		}
		return in.length - 1;
	}

	private boolean[] parse(String str) {
		boolean[] result = new boolean[str.length()];
		for (int i = 0; i < result.length; ++i) {
			if (str.charAt(i) == '+')
				result[i] = true;
			else
				result[i] = false;
		}
		return result;
	}

	private boolean check(boolean[] in) {
		for (boolean elem : in)
			if (!elem)
				return false;

		return true;
	}
}
