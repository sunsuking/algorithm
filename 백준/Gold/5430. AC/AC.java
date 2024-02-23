import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder();
        main:
        for (int testCase = 0; testCase < T; testCase++) {
            char[] chars = br.readLine().toCharArray();
            int num = Integer.parseInt(br.readLine());
            String[] strings = br.readLine().replace("[", "").replace("]","").split(",");
            ArrayDeque<Integer> array = new ArrayDeque<>();
            for (int i = 0; i < num; i++) {
                array.add(Integer.parseInt(strings[i]));
            }

            boolean isReverse = false;

            for (char c : chars) {
                if (c == 'R') {
                    isReverse = !isReverse;
                } else {
                    if (array.isEmpty()) {
                        builder.append("error").append("\n");
                        continue main;
                    }

                    if (isReverse) {
                        array.pollLast();
                    } else {
                        array.pollFirst();
                    }
                }
            }
            int size = array.size();
            builder.append("[");
            if (isReverse) {
                while (!array.isEmpty()) builder.append(array.pollLast()).append(",");
            } else {
                while (!array.isEmpty()) builder.append(array.pollFirst()).append(",");
            }
            if (size > 0) {
                builder.setLength(builder.length() - 1);
            }
            builder.append("]").append("\n");
        }
        System.out.print(builder);
    }
}