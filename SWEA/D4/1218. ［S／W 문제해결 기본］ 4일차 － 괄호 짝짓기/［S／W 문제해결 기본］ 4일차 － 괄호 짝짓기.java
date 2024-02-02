import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= 10; i++) {
            int num = Integer.parseInt(br.readLine());
            String str = br.readLine();
            Stack<Character> stack = new Stack<>();
            int x = 0;
            while(num != x) {
                char newChar = str.charAt(x++);
                if (newChar == '{' || newChar == '(' || newChar == '[' || newChar == '<') {
                    stack.push(newChar);
                } else {
                    char popChar = stack.pop();
                    if (!isSame(popChar, newChar)) break;
                }
            }
            builder.append("#").append(i).append(" ").append(num == x ? 1 : 0).append("\n");
        }
        System.out.println(builder);
    }

    private static boolean isSame(char c1, char c2) {
        if (c1 == '{') {
            return c2 == '}';
        } else if (c1 == '[') {
            return c2 == ']';
        } else if (c1 == '(') {
            return c2 == ')';
        } else if (c1 == '<') {
            return c2 == '>';
        }
        return false;
    }
}