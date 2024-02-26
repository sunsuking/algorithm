import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    private static StringBuilder builder;
    private static BigInteger count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        builder = new StringBuilder();
        count = new BigInteger("1").shiftLeft(T).subtract(new BigInteger("1"));
        System.out.println(count);
        if (T <= 20) {
            hanoi(T, 1, 2, 3);
            System.out.println(builder);
        }
    }

    private static void hanoi(int num, int start, int empty, int end) {
        if (num == 0) return;
        hanoi(num - 1, start, end, empty);
        builder.append(start).append(" ").append(end).append("\n");
        hanoi(num - 1, empty, start, end);
    }
}