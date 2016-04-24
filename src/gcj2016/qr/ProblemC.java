package gcj2016.qr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

public class ProblemC {
	private static final BigInteger TWO = new BigInteger("2");
	private static Random random = new Random();

	public static void main(String[] args) throws NumberFormatException, IOException {
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])));
		int lineNum = Integer.parseInt(reader.readLine());
		ProblemC problemC = new ProblemC();

		for (int i = 1; i <= lineNum; ++i) {
			String[] tokens = reader.readLine().split(" ");
			int n = Integer.parseInt(tokens[0]);
			int j = Integer.parseInt(tokens[1]);

			Seed seed = new Seed(n);
			int resultNum = 0;
			System.out.println("Case #" + i + ":");
			while (resultNum < j) {
				boolean[] proceed = seed.get();
				BigInteger[] values = problemC.convert(proceed);
				BigInteger[] result = problemC.check(values);
				if (result != null) {
					System.out.print(problemC.proceedToStr(proceed) + " ");
					for (int k = 0; k < result.length; ++k) {
						// System.out.print("(" + values[k] + ", " + result[k] + ") ");
						System.out.print(result[k] + " ");
					}
					System.out.println("");
					resultNum++;
				}
			}
		}
	}

	private String proceedToStr(boolean[] proceed) {
		StringBuilder result = new StringBuilder();
		for (boolean elem : proceed) {
			if (elem)
				result.append("1");
			else
				result.append("0");
		}
		return result.toString();
	}

	private BigInteger[] check(BigInteger[] values) {
		final int tryCount = 10000;
		BigInteger[] result = new BigInteger[values.length];
		for (int i = 0; i < values.length; ++i) {
			BigInteger checkResult = fermatCheck(values[i], tryCount);
			if (checkResult == null)
				return null;
			result[i] = checkResult;
		}
		return result;
	}

	private BigInteger[] convert(boolean[] bitArray) {
		BigInteger[] result = new BigInteger[9];
		for (int i = 2; i <= 10; ++i) {
			BigInteger base = new BigInteger(Integer.toString(i));
			BigInteger b = new BigInteger("1");
			BigInteger val = new BigInteger("0");

			for (int j = bitArray.length - 1; j >= 0; --j) {
				if (bitArray[j]) {
					val = val.add(b);
				}
				b = b.multiply(base);
			}

			result[i - 2] = val;
		}
		return result;
	}

	private BigInteger fermatCheck(BigInteger n, int count) {
		for (int i = 0; i < count; ++i) {
			if (n.divide(TWO).compareTo(BigInteger.ZERO) == 0)
				return TWO;

			BigInteger a = getTestNum(n);
			BigInteger g = n.gcd(a);
			if (g.compareTo(BigInteger.ONE) != 0) {
				return g;
			}
			/*
			if (a.modPow(n, n).compareTo(a) != 0) {
				return a;
			}
			*/
		}
		return null;
	}

	private BigInteger getTestNum(BigInteger n) {
		while (true) {
			BigInteger a = new BigInteger(n.bitLength(), random).add(TWO);
			if (a.compareTo(n) < 0)
				return a;
		}
	}

	private static class Seed {
		private int val = 0;
		private int length;

		public Seed(int length) {
			this.length = length;
		}

		public boolean[] get() {
			boolean[] result = new boolean[length];
			Arrays.fill(result, false);
			result[0] = true;
			result[result.length - 1] = true;

			String bin = Integer.toBinaryString(val);
			int index = result.length - 2;
			for (int i = bin.length() - 1; i >= 0; --i) {
				if (bin.charAt(i) == '1') {
					result[index] = true;
				}
				index--;
			}
			this.val++;
			return result;
		}

	}

}
