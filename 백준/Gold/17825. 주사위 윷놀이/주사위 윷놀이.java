import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[] nums;
    private static Map<Integer, Integer> dices;
    private static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        nums = new int[10];
        for (int i = 0; i < 10; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        dices = new HashMap<>();

        for (int i = 1; i <= 20; i++) {
            dices.put(i, i * 2);
        }
        dices.put(21, 13);
        dices.put(22, 16);
        dices.put(23, 19);
        dices.put(24, 22);
        dices.put(25, 24);
        dices.put(26, 28);
        dices.put(27, 27);
        dices.put(28, 26);
        dices.put(29, 25);
        dices.put(30, 30);
        dices.put(31, 35);
        dices.put(32, 40);

        dfs(new Integer[]{0, 0, 0, 0}, 0, 0);
        System.out.println(max);
    }

    private static void dfs(Integer[] p, int score, int depth) {
        if (depth == 10) {
            max = Math.max(max, score);
            return;
        }

        Set<Integer> set = new HashSet<>(Arrays.asList(p).subList(0, 4));
        for (int i : set) {
            int index = indexOf(p, i);
            int newPosition = calculatePosition(p[index], nums[depth]);
            if ((newPosition <= 32 && set.contains(newPosition)) || (newPosition == 32 && set.contains(20)) || (newPosition == 20 && set.contains(32))) continue;
            Integer[] newP = Arrays.copyOf(p, 4);
            int newScore = score;
            if (newPosition <= 32) {
                newScore += dices.get(newPosition);
            }
            newP[index] = newPosition;
            dfs(newP, newScore, depth + 1);
        }
    }

    private static int calculatePosition(int position, int dice) {
        if (position > 20) {
            if (position <= 23) {
                if (position == 21 && dice == 1) {
                    position = 22;
                } else if (position == 21 && dice == 2) {
                    position = 23;
                } else if (position == 22 && dice == 1) {
                    position = 23;
                } else {
                    position += 5 + dice;
                }
            } else if (position <= 25) {
                if (position == 24 && dice == 1) {
                    position = 25;
                } else {
                    position += 3 + dice;
                }
            } else {
                position += dice;
            }
        } else if (position == 5) {
            if (1 <= dice && dice <= 3) {
                position = 20 + dice;
            } else {
                position = 25 + dice;
            }
        } else if (position == 10) {
            if (1 <= dice && dice <= 2) {
                position = 23 + dice;
            } else {
                position = 26 + dice;
            }
        } else if (position == 15) {
            position = 25 + dice;
        } else {
            position += dice;
            if (position > 20) {
                position = 35;
            }
        }
        return position;
    }

    private static int indexOf(Integer[] p, int key) {
        for(int i = 0; i < 4; i++) {
            if(p[i] == key) return i;
        }
        return -1;
    }
}