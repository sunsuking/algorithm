import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            char[] chars = br.readLine().toCharArray();
            Stack<Character> stack = new Stack<>();
            int x;
            for (x = 0; x < chars.length; x++) {
                if (chars[x] == '(') {
                    stack.push(chars[x]);
                } else {
                    if (stack.isEmpty()) break;
                    char pop = stack.pop();
                    if (pop != '(') break;
                }
            }
            if (x == chars.length && stack.isEmpty()) builder.append("YES").append("\n");
            else if (x == chars.length && stack.pop() == '(') builder.append("NO").append("\n");
            else builder.append("NO").append("\n");
        }
        System.out.println(builder);
    }
}