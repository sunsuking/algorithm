
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = "";
		StringBuilder sb = new StringBuilder();
		Map<String, Integer> map = new HashMap();
		List<String> l = new ArrayList<String>();
		int sum = 0;
		while (true) {
			str = sc.nextLine();
			if (str.equals("0")) {
				break;
			}
			if (l.indexOf(str) == -1) {
				l.add(str);
			}
			map.put(str, map.getOrDefault(str, 0) + 1);
			sum++;
		}
		for (String key : l) {
			sb.append(key).append(": ").append(map.get(key)).append("\n");
		}
		sb.append("Grand Total: ").append(sum);
		System.out.println(sb.toString());
	}
}
