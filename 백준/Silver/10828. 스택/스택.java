import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<Integer> stack = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch (command) {
                case "push":
                    stack.add(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    int pop = -1;
                    if (!stack.isEmpty()) {
                        pop = stack.remove(stack.size() - 1);
                    }
                    builder.append(pop).append("\n");
                    break;
                case "size":
                    builder.append(stack.size()).append("\n");
                    break;
                case "empty":
                    builder.append(stack.isEmpty() ? 1 : 0).append("\n");
                    break;
                case "top":
                    int peek = -1;
                    if (!stack.isEmpty()) {
                        peek = stack.get(stack.size() - 1);
                    }
                    builder.append(peek).append("\n");
                    break;
                default:
                    break;
            }
        }
        System.out.println(builder);
    }
}