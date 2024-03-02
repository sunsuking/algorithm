import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());
            int arraySize = Integer.parseInt(st.nextToken());
            int codeSize = Integer.parseInt(st.nextToken());
            int inputSize = Integer.parseInt(st.nextToken());
            byte[] array = new byte[arraySize];
            char[] chars = br.readLine().toCharArray();
            char[] input = br.readLine().toCharArray();
            Map<Integer, Integer> map = new HashMap<>();

            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '[') {
                    stack.push(i);
                } else if (chars[i] == ']') {
                    int pop = stack.pop();
                    map.put(pop, i);
                    map.put(i, pop);
                }
            }

            int arrayIndex = 0;
            int commandIndex = 0;
            int inputIndex = 0;

            int i;
            int prevReturn = 0;
            for (i = 0; i < 100_000_000; i++) {
                if (commandIndex == codeSize) break;
                char c = chars[commandIndex];

                if (c == '>') {
                    arrayIndex++;
                    if (arrayIndex == arraySize) arrayIndex = 0;
                } else if (c == '<') {
                    arrayIndex--;
                    if (arrayIndex == -1) arrayIndex = arraySize - 1;
                } else if (c == '-') {
                    array[arrayIndex]--;
                } else if (c == '+') {
                    array[arrayIndex]++;
                } else if (c == '[') {
                    if (array[arrayIndex] == 0) commandIndex = map.get(commandIndex);
                } else if (c == ']') {
                    if (array[arrayIndex] != 0) {
                        if (i > 50_000_000) {
                            prevReturn = Math.max(prevReturn, commandIndex);
                        }
                        commandIndex = map.get(commandIndex);
                    }
                } else if (c == '.') {

                } else if (c == ',') {
                    if (inputIndex == inputSize) {
                        array[arrayIndex] = (byte) 255;
                    } else {
                        array[arrayIndex] = (byte) input[inputIndex++];
                    }
                }
                commandIndex++;
            }

            if (i == 100_000_000) {
                builder.append("Loops").append(" ").append(map.get(prevReturn)).append(" ").append(prevReturn).append("\n");
            } else {
                builder.append("Terminates").append("\n");
            }
        }
        System.out.print(builder);
    }
}