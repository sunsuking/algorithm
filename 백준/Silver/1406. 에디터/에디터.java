import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] characters = br.readLine().toCharArray();

        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();
        for (char c : characters) {
            left.push(c);
        }
        int num = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
//            System.out.println(command);
//            System.out.println(Arrays.toString(characters));
//            System.out.println(index);
            switch (command) {
                case "L":
                    if (!left.isEmpty()) right.push(left.pop());
                    break;
                case "D":
                    if (!right.isEmpty()) left.push(right.pop());
                    break;
                case "B":
                    if (!left.isEmpty()) left.pop();
                    break;
                case "P":
                    left.push(st.nextToken().charAt(0));
                    break;
            }
        }

        StringBuilder builder = new StringBuilder();
        for (char c : left) builder.append(c);
        while (!right.isEmpty()) builder.append(right.pop());
        System.out.println(builder);
    }
}