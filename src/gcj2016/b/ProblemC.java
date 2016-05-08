package gcj2016.b;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProblemC {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])));
		int lineNum = Integer.parseInt(reader.readLine());

		ProblemC problemC = new ProblemC();
		for (int i = 0; i < lineNum; ++i) {
			int topicNum = Integer.parseInt(reader.readLine());
			Map<String[], Boolean> topics = new HashMap<>();
			int tokenLength;
			for (int j = 0; j < topicNum; ++j) {
				String[] tokens = reader.readLine().split(" ");
				tokenLength = tokens.length;
				topics.put(tokens, new Boolean(false));
			}
			System.out.println(problemC.solve(topics, 0, tokenLength));
		}
		reader.close();
	}

	private int solve(Map<String[], Boolean> topics, int wordIndex, int max) {
		if( wordIndex == max ){
			return 0;
		}

		int count = 0;
		Set<String> set = new HashSet<>();
		List<String[]> next = new ArrayList<>();
		for (String[] topic : topics) {
			if (!set.contains(topic[wordIndex])) {
				set.add(topic[wordIndex]);
				next.add(topic);
			} else {
				count++;
			}
		}

		
		return count + solve(next, wordIndex+1);
	}
}
