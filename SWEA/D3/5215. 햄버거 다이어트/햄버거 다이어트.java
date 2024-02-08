import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Solution {
    private static int maxCal, max;
    private static int[][] map, keys, array;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            maxCal = Integer.parseInt(st.nextToken());
            max = Integer.MIN_VALUE;
            map = new int[count][2];
            for (int i = 0; i < count; i++) {
                st = new StringTokenizer(br.readLine());
                map[i][0] =Integer.parseInt(st.nextToken());
                map[i][1] =Integer.parseInt(st.nextToken());
            }
            keys = Arrays.stream(map).sorted((o1, o2) -> Integer.compare(o2[0], o1[0])).toArray(int[][]::new );
            array = new int[keys.length][];
            for(int i = keys.length; i > 0; i--) {
                combination(0, 0, i);
            }
            builder.append("#").append(testCase).append(" ").append(max).append("\n");
        }
        System.out.println(builder);
    }

    private static void combination(int index, int start, int end) {
        if (index == end) {
            int calSum = 0;
            int happySum = 0;
            for (int i = 0; i < index; i++) {
                calSum += array[i][1];
                happySum += array[i][0];
            }
            if (calSum <= maxCal) {
                max = Math.max(max, happySum);
            }
            return;
        }

        for (int i = start; i < keys.length; i++) {
            array[index] = keys[i];
            combination(index + 1, i + 1, end);
        }
    }
}