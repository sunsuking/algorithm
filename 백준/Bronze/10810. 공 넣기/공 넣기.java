// 5 4
// 1 2 3
// 3 4 4
// 1 4 1
// 2 2 2

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] nums = new int[N];
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int n = Integer.parseInt(st.nextToken());
            for (int x = s; x <= e; x++) {
                nums[x] = n;
            }
        }
        
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < N; i++) {
            builder.append(nums[i]).append(" ");
        }
        System.out.print(builder);
    }
}