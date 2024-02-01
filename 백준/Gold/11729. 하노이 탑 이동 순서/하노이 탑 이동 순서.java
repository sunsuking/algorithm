import java.io.*;
import java.util.*;

public class Main {
	private static StringBuilder builder;
	private static int count;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		builder = new StringBuilder();
		hanoi(T, 1, 2, 3);
		System.out.println(count);
		System.out.println(builder);
	}
	
	private static void hanoi(int num, int start, int empty, int end) {
		if (num == 0) return;
		count++;
		hanoi(num - 1, start, end, empty);
		builder.append(start).append(" ").append(end).append("\n");
		hanoi(num - 1, empty, start, end);
	}
}
