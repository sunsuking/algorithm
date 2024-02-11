import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        char[] chars = str.toCharArray();
        Stack<Character> operator = new Stack<>();
        StringBuilder builder = new StringBuilder();
        for (char c : chars) {
            if (c - 'A' >= 0 && c - 'A' <= 27) {
                builder.append(c);
            } else {
                if (operator.isEmpty()) {
                    operator.push(c);
                } else {
                    if (isUpper(operator.peek(), c)) {
                        operator.push(c);
                    } else if (c == ')') {
                        while (!operator.isEmpty() && operator.peek() != '(') builder.append(operator.pop());
                        if (!operator.isEmpty()) operator.pop();
                    } else if (operator.peek() == '(') {
                        operator.push(c);
                    } else {
                        while (!operator.isEmpty() && !isUpper(operator.peek(), c) && operator.peek() != '(') {
                            char pop = operator.pop();
                            if (pop != '(' && pop != ')') builder.append(pop);
                        }
                        operator.push(c);
                    }
                }
            }
        }
        while (!operator.isEmpty()) {
            char pop = operator.pop();
            if (pop != '(' && pop != ')') builder.append(pop);
        }

        System.out.println(builder);
    }

    private static StringBuilder print(StringBuilder builder, char c) {
        if (c != '(' && c != ')') builder.append(c);
        return builder;
    }

    private static boolean isUpper(char prev, char now) {
        if (prev == '(') return false;
        if (now == '(') return true;
        else if ((prev == '+' || prev == '-') && (now == '*' || now == '/')) return true;
        return false;
    }
}