import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int max = Integer.MIN_VALUE;
        int[] nums = new int[N];
        int[] sushi = new int[3001];
        int size = 0;
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < k; i++) {
            if (sushi[nums[i]] == 0) size++;
            sushi[nums[i]]++;

            if (sushi[c] == 0) {
                max = Math.max(max, size + 1);
            } else {
                max = Math.max(size, max);
            }
        }

        for (int i = 0; i < N; i++) {
            int n = nums[(i + k) % N];
            int start = nums[i];
            sushi[start]--;
            if (sushi[start] == 0) size--;
            if (sushi[n] == 0) size++;
            sushi[n]++;

            if (sushi[c] == 0) {
                max = Math.max(max, size + 1);
            } else {
                max = Math.max(size, max);
            }
        }

        System.out.println(max);
    }
}