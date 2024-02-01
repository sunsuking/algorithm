import java.io.*;
import java.util.*;


public class Main {
    static int[] pre, in;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int i = 0; i < T; i++) {
            int count = sc.nextInt();
            pre = new int[count];
            in = new int[count];
            for (int x = 0; x < count; x++) {
                pre[x] = sc.nextInt();
            }
            for (int x = 0; x < count; x++) {
                in[x] = sc.nextInt();
            }
            postOrder(0, 0, count);
            System.out.println();
        }
    }

    static void postOrder(int root, int start, int end) {
        for (int i = start; i < end; i++) {
            if (in[i] == pre[root]) {
                postOrder(root + 1, start, i);
                postOrder(root + i + 1 - start, i + 1, end);
                System.out.print(pre[root] + " ");
            }
        }
    }
}
