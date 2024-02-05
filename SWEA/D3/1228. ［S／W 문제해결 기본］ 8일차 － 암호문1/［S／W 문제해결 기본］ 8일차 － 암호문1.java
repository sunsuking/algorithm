import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int T = 1; T <= 10; T++) {
            int num = Integer.parseInt(br.readLine());
            List<Integer> nums = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < num; i++) {
                nums.add(Integer.parseInt(st.nextToken()));
            }
            int count = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < count; i++) {
                // I 처리
                st.nextToken();
                int start = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                for (int x = 0; x < c; x++) {
                    nums.add(start + x, Integer.parseInt(st.nextToken()));
                }
            }
            builder.append("#").append(T).append(" ");
            for (int i = 0; i < 10; i++) {
                builder.append(nums.get(i)).append(" ");
            }
            builder.append("\n");
        }
        System.out.println(builder);
    }
}