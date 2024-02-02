import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[] dices;
    private static int max = Integer.MIN_VALUE;
    private static int[] path = {
            0,
            // 일반 1 ~ 25
            2, 4, 6, 8, 0,
            12, 14, 16, 18, 0,
            22, 24, 26, 28, 0,
            32, 34, 36, 38, 40,
            0, 0, 0, 0, 0,
            // 파란 경로 26 ~ 45
            10, 13, 16, 19, 25,
            30, 35, 40, 0, 0,
            0, 0, 0, 0, 0,
            // 파란 경로 41 ~ 55
            20, 22, 24, 25, 30,
            35, 40, 0, 0, 0,
            0, 0, 0, 0, 0,
            // 파란 경로 56 ~ 70
            30, 28, 27, 26, 25,
            30, 35, 40, 0, 0,
            0, 0, 0, 0, 0
    };

    private static boolean contain(Integer[] p, int position) {
        for (int j : p) {
            if (position == j) return true;
            if (path[j] == 40 && path[position] == 40) return true;
            if (path[j] == 25 && path[position] == 25) return true;
            if (path[j] == 30 && path[position] == 20) return true;
            if (path[j] == 35 && path[position] == 35) return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        dices = new int[10];
        for (int i = 0; i < 10; i++) {
            dices[i] = Integer.parseInt(st.nextToken());
        }

        dfs(new Integer[]{0, 0, 0, 0}, 0, 0);
        System.out.println(max);
    }

    private static void dfs(Integer[] p, int score, int depth) {
        if (depth == 10) {
            max = Math.max(max, score);
            return;
        }

        Set<Integer> set = new HashSet<>(Arrays.asList(p).subList(0, p.length));
        for (int i : set) {
            int index = indexOf(p, i);
            int newPosition = calculatePosition(p[index], dices[depth]);
            if (newPosition != -1 && contain(p, newPosition)) continue;
            int newScore = 0;
            Integer[] newP;
            if (newPosition == -1) {
                newP = new Integer[p.length - 1];
                int k = 0;
                for (int x = 0; x < p.length; x++) {
                    if (x != index) {
                        newP[k++] = p[x];
                    }
                }
            } else {
                newScore = path[newPosition];
                newP = Arrays.copyOf(p, p.length);
                newP[index] = newPosition;
            }
            dfs(newP, score + newScore, depth + 1);
        }
    }

    private static int calculatePosition(int position, int dice) {
        int nextPosition = position + dice;
        if (nextPosition == 5) {
            nextPosition = 26;
        } else if (nextPosition == 10) {
            nextPosition = 41;
        } else if (nextPosition == 15) {
            nextPosition = 56;
        }
        if (path[nextPosition] == 0) {
            nextPosition = -1;
        }
        return nextPosition;
    }

    private static int indexOf(Integer[] p, int key) {
        for(int i = 0; i < 4; i++) {
            if(p[i] == key) return i;
        }
        return -1;
    }
}