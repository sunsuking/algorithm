
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] array;
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        int count = Integer.parseInt(st.nextToken());
        array = new int[count];
        visited = new boolean[num];
        dfs(num, count, 0);
    }

    private static void dfs(int N, int M, int depth) {
        if(depth == M) {
            for(int val : array) {
                System.out.print(val + " ");
            }
            System.out.println();
            return;
        }

        for(int i=0; i<N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                array[depth] = i + 1;
                dfs(N, M, depth + 1);

                visited[i] = false;
            }
        }

    }
}
