package gcj2016.qr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class ProblemA {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])));
		int lineNum = Integer.parseInt(reader.readLine());

		ProblemA problemA = new ProblemA();

		for (int i = 1; i <= lineNum; ++i) {
			long n = Long.parseLong(reader.readLine());
			boolean[] flag = new boolean[10];
			Arrays.fill(flag, false);
			String result = problemA.calc(n, n, flag);
			System.out.println("Case #" + i + ": " + result);
		}
	}

	private String calc(long base, long in, boolean[] flag) {
		boolean[] updateFlag = flagUpdate(in, flag);
		if (checkFlag(updateFlag)) {
			return Long.toString(in);
		}

		if (checkSame(flag, updateFlag)) {
			if (in > Long.MAX_VALUE - base || base == 0)
				return "INSOMNIA";
		}

		return calc(base, in + base, updateFlag);
	}

	private boolean[] flagUpdate(long in, boolean[] flag) {
		boolean[] newFlag = Arrays.copyOf(flag, flag.length);
		String inStr = Long.toString(in);
		for (int i = 0; i < inStr.length(); ++i) {
			int digit = Integer.parseInt(inStr.substring(i, i + 1));
			newFlag[digit] = true;
		}
		return newFlag;
	}

	private boolean checkFlag(boolean[] flag) {
		for (boolean elem : flag)
			if (!elem)
				return false;

		return true;
	}

	private boolean checkSame(boolean[] previous, boolean[] current) {
		for (int i = 0; i < previous.length; ++i)
			if (previous[i] != current[i])
				return false;

		return true;
	}

}
