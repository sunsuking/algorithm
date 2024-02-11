import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] graph;
    static int N, count;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        count = 0;

        graph = new ArrayList[N+1];

        for (int i=1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }

        for (int i=1; i<N; i++){
            st = new StringTokenizer(br.readLine());

            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());

            graph[X].add(Y);
            graph[Y].add(X);
        }

        game(1, 0, 0);

        System.out.println(count % 2 == 0 ? "No" : "Yes");
    }

    private static void game(int x, int parent, int depth) {
        if (parent != 0 && graph[x].size() == 1 && graph[x].get(0) == parent){
            count+=depth;
            return;
        }

        for (int child : graph[x]){
            if (child != parent)
                game(child, x, depth + 1);
        }
    }
}