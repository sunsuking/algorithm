import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int index = 0;
        int[] queue = new int[0];
        StringTokenizer st;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch (command) {
                case "push":
                    queue = Arrays.copyOf(queue, index + 1);
                    queue[index++] = Integer.parseInt(st.nextToken());
                    break;
                case "pop":
                    if (index == 0) {
                        builder.append(-1).append("\n");
                        break;
                    }
                    int pop = queue[0];
                    for (int x = 0; x < index - 1; x++) {
                        queue[x] = queue[x + 1];
                    }
                    queue = Arrays.copyOf(queue, --index);
                    builder.append(pop).append("\n");
                    break;
                case "size":
                    builder.append(index).append("\n");
                    break;
                case "empty":
                    builder.append(index == 0 ? 1 : 0).append("\n");
                    break;
                case "front":
                    builder.append(index == 0 ? -1 : queue[0]).append("\n");
                    break;
                case "back":
                    builder.append(index == 0 ? -1 : queue[index - 1]).append("\n");
                    break;
            }
        }
        System.out.println(builder);
    }
}