import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N, R, min;
    private static int[] array;
    private static int[][] nodes;
    private static int[] weight;
    private static boolean[] mainVisited, subVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nodes = new int[N][];
        weight = new int[N];
        min = Integer.MAX_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            nodes[i] = new int[num];
            for (int n = 0; n < num; n++) {
                nodes[i][n] = Integer.parseInt(st.nextToken()) - 1;
            }
        }

        for (int i = 1; i <= N / 2; i++) {
            R = i;
            array = new int[i];
            combination(0, 0);
        }
        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }

    private static void combination(int index, int start) {
        if (index == R) {
            List<Integer> main = new ArrayList<>();
            for (int i = 0; i < R; i++) main.add(array[i]);
            int mainScore = 0;
            int subScore = 0;
            mainVisited = new boolean[N];
            subVisited = new boolean[N];

            int mainStart = 0;
            int subStart = 0;

            for (int i = 0; i < N; i++) {
                if (main.contains(i)) {
                    mainScore += weight[i];
                    subVisited[i] = true;
                    mainStart = i;
                } else {
                    subScore += weight[i];
                    mainVisited[i] = true;
                    subStart = i;
                }
            }

            mainDfs(mainStart);
            subDfs(subStart);

            for (int i = 0; i < N; i++) if (!mainVisited[i] || !subVisited[i]) return;
            min = Math.min(min, Math.abs(mainScore - subScore));
            return;
        }

        for (int i = start; i < N; i++) {
            array[index] = i;
            combination(index + 1, i + 1);
        }
    }

    private static void mainDfs(int n) {
        mainVisited[n] = true;
        for (int v : nodes[n]) {
            if (mainVisited[v]) continue;
            mainDfs(v);
        }
    }

    private static void subDfs(int n) {
        subVisited[n] = true;
        for (int v : nodes[n]) {
            if (subVisited[v]) continue;
            subDfs(v);
        }
    }
}