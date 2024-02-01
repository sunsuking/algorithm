
import java.io.*;
import java.util.*;
public class Main {
    static int[] in, post;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        post = new int[count];
        in = new int[count];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int x = 0; x < count; x++) {
            in[x] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int x = 0; x < count; x++) {
            post[x] = Integer.parseInt(st.nextToken());
        }
        preOrder(count - 1, 0, count);
        System.out.println(sb);
    }

    static void preOrder(int root, int start, int end) {
        if (root < 0 || start > end) return;
        for (int i = start; i < end; i++) {
            if (in[i] == post[root]) {
                sb.append(post[root]).append(" ");
                preOrder(root - (end - i), start, i);
                preOrder(root - 1, i + 1, end);
                return;
            }
        }
    }
}
