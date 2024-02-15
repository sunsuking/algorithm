import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    private static int[] array;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder();
        for (int testCase = 1; testCase <= T; testCase++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[][] root = new int[n][2];
            array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = i;
            }

            int officeX = Integer.parseInt(st.nextToken());
            int officeY = Integer.parseInt(st.nextToken());
            int homeX = Integer.parseInt(st.nextToken());
            int homeY = Integer.parseInt(st.nextToken());

            for (int i = 0; i < n; i++) {
                root[i][0] = Integer.parseInt(st.nextToken());
                root[i][1] = Integer.parseInt(st.nextToken());
            }


            int min = Integer.MAX_VALUE;
            do {
                int prevX = officeX;
                int prevY = officeY;
                int sum = 0;
                for (int i = 0; i < n; i++) {
                    int nowX = root[array[i]][0];
                    int nowY = root[array[i]][1];
                    sum += Math.abs(prevX - nowX) + Math.abs(prevY - nowY);
                    prevX = nowX;
                    prevY = nowY;
                }
                sum += Math.abs(prevX - homeX) + Math.abs(prevY - homeY);
                min = Math.min(sum, min);
            } while (nextPermutation());

            builder.append("#").append(testCase).append(" ").append(min).append("\n");
        }
        System.out.print(builder);
    }

    private static boolean nextPermutation() {
        int n = array.length - 1;
        while(n > 0 && array[n - 1] > array[n]) n--;
        if (n == 0) return false;

        int m = array.length - 1;
        while (m > n && array[n - 1] > array[m]) m--;

        swap(n - 1, m);
        int k = array.length - 1;
        while (n < k) swap(n++, k--);

        return true;
    }

    private static void swap(int x, int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
}