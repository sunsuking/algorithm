import javax.swing.text.html.HTMLDocument;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(br.readLine());

        boolean[] visited = new boolean[num + 1];
        LinkedList<Integer>[] adjList = new LinkedList[num + 1];
        for (int i = 0; i <= num; i++) {
            adjList[i] = new LinkedList<>();
        }

        int v = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            adjList[num1].add(num2);
            adjList[num2].add(num1);
        }

        for (int i = 0; i <= num; i++) {
            Collections.sort(adjList[i]);
        }

//        for(LinkedList<Integer> l : adjList) System.out.println(l);

        Queue<int[]> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(new int[] {start, 0});

        while (!queue.isEmpty()) {
            int[] pop = queue.poll();
            if (end == pop[0]) {
                System.out.println(pop[1]);
                System.exit(0);
            }


            for (int w : adjList[pop[0]]) {
                if (!visited[w]) {
                    visited[w] = true;
                    queue.add(new int[] {w, pop[1] + 1});
                }
            }
        }

        System.out.println(-1);
    }
}