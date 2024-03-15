import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int y = 0;
        int m = 0;
        for (int i = 0; i < N; i++) {
            int time = Integer.parseInt(st.nextToken()) + 1;
            y += ((int) Math.ceil(time / 30.0)) * 10;
            m += ((int) Math.ceil(time / 60.0)) * 15;
        }
        
        if (y > m) {
            System.out.println("M " + m);
        } else if (y < m) {
            System.out.println("Y " + y);
        } else {
            System.out.println("Y M " + m);
        }
    }
}