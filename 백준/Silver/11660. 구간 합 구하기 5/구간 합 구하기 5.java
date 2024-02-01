import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int count = Integer.parseInt(st.nextToken());
        int[][] map = new int[N+1][N+1];
        for (int i = 0; i < N; i++) {
            map[0][i] = 0;
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            map[i][0] = 0;
            for (int j = 1; j <= N; j++) {
                map[i][j] = map[i - 1][j] + Integer.parseInt(st.nextToken());
            }
        }

//        for(int i = 0; i <= N; i++) {
//            System.out.println(Arrays.toString(map[i]));
//        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());
//            System.out.println(startX/ + " : " + startY + " : " + endX + " : " + endY);
            int sum = 0;
            for (int y = startY; y <= endY; y++) {
//                System.out.print(map[endY - 1][x] - map[startY - 1][x]);
                sum += map[endX][y] - map[startX - 1][y];
            }
//            System.out.println();
            builder.append(sum).append("\n");
        }
        System.out.print(builder);
    }
}
