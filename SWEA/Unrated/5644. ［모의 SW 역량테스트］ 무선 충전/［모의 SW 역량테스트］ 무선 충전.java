import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Charger {
    int x;
    int y;
    int length;
    int power;

    public Charger(int x, int y, int length, int power) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.power = power;
    }

    boolean canCharge(int[] position) {
        return Math.abs(x - position[0]) + Math.abs(y - position[1]) <= length;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            int[] numA = new int[num + 1];
            int[] numB = new int[num + 1];
            st = new StringTokenizer(br.readLine());
            numA[0] = 0;
            for (int i = 1; i <= num; i++) {
                numA[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            numB[0] = 0;
            for (int i = 1; i <= num; i++) {
                numB[i] = Integer.parseInt(st.nextToken());
            }
            Charger[] chargers = new Charger[count];
            for (int i = 0; i < count; i++) {
                st = new StringTokenizer(br.readLine());
                chargers[i] = new Charger(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            int[] positionA = {0, 0};
            int[] positionB = {9, 9};

            int sum = 0;

            for (int i = 0; i <= num; i++) {
                move(positionA, numA[i]);
                move(positionB, numB[i]);

                List<Charger> scoreA = new ArrayList<>();
                List<Charger> scoreB = new ArrayList<>();
                for (int c = 0; c < count; c++) {
                    Charger charger = chargers[c];
                    if (charger.canCharge(positionA)) {
                        scoreA.add(charger);
                    }
                    if (charger.canCharge(positionB)) {
                        scoreB.add(charger);
                    }
                }
                int max = 0;

                if (!scoreA.isEmpty() && !scoreB.isEmpty()) {
                    for (Charger a : scoreA) {
                        for (Charger b : scoreB) {
                            if (a == b) {
                                max = Math.max(a.power, max);
                            } else {
                                max = Math.max(a.power + b.power, max);
                            }
                        }
                    }
                } else {
                    if (scoreA.isEmpty() && scoreB.isEmpty()) {
                        max = 0;
                    } else if (scoreA.isEmpty()) {
                        max = scoreB.stream().mapToInt(c -> c.power).max().orElse(0);
                    } else {
                        max = scoreA.stream().mapToInt(c -> c.power).max().orElse(0);
                    }
                }

//                scoreA.stream().mapToInt(c -> c.power).forEach(b -> System.out.print(b + " "));
//                System.out.print(" : ");
//                scoreB.stream().mapToInt(c -> c.power).forEach(b -> System.out.print(b + " "));
//                System.out.print(" : ");
//                System.out.println(max);
//                System.out.println("==================================");



                sum += max;
            }
            builder.append("#").append(testCase).append(" ").append(sum).append("\n");
//            System.out.println("----------------------------");
        }
        System.out.print(builder);
    }

    private static void move(int[] position, int command) {
        switch (command) {
            case 1:
                position[1]--;
                return;
            case 2:
                position[0]++;
                return;
            case 3:
                position[1]++;
                return;
            case 4:
                position[0]--;
                return;
            default:
                return;
        }
    }
}