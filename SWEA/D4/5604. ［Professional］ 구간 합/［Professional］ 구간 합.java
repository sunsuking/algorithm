import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution {
    private static HashMap<Long, Long> map;
    private static long start, end;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        map = new HashMap<>();

        long sum = 0;

        for(int i = 0; i < 10; i++) {
            sum += i;
            map.put((long) i, sum);
        }

        StringBuilder builder = new StringBuilder();
        StringTokenizer st;
        for(int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());
            start = Long.parseLong(st.nextToken());
            end = Long.parseLong(st.nextToken());

            builder.append("#").append(testCase).append(" ");
            if(start > 0) builder.append(F(end) - F(start - 1));
            else builder.append(F(end) - F(start));
            builder.append("\n");
        }
        System.out.print(builder);
    }

    static long F(long i) {
        if(map.containsKey(i)) return map.get(i);
        if(i < 10) return map.get(i);

        long v = G(i);
        long F = F(i - 1 - i % v);
        long G = (i / v) * (i % v + 1) + F(i % v);
        long num = F+G;

        map.put(i, num);

        return num;
    }

    static long G(long i) {
        long v = 1;
        while(i >= 10) {
            v = v * 10;
            i = i / 10;
        }
        return v;
    }
}