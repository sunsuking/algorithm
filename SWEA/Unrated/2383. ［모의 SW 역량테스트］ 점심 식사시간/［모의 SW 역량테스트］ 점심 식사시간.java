import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Solution {
    static class N2383Vector {
        int x;
        int y;
        int weight;
        boolean isA;

        public N2383Vector(int x, int y, int weight, boolean isA) {
            this.x = x;
            this.y = y;
            this.weight = weight;
            this.isA = isA;
        }
    }
    private static List<N2383Vector> peoples, stairs;
    private static N2383Vector[] array;
    private static int C, min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int testCase = 1; testCase <= T; testCase++) {
            min = Integer.MAX_VALUE;
            int N = Integer.parseInt(br.readLine());
            peoples = new ArrayList<>();
            stairs = new ArrayList<>();
            for (int x = 0; x < N; x++) {
                st = new StringTokenizer(br.readLine());
                for (int y = 0; y < N; y++) {
                    int n = Integer.parseInt(st.nextToken());
                    if (n == 1) {
                        peoples.add(new N2383Vector(x, y, 0, false));
                    } else if (n >= 2) {
                        stairs.add(new N2383Vector(x, y, n, false));
                    }
                }
            }
            for (int i = 0; i <= peoples.size(); i++) {
                C = i;
                array = new N2383Vector[C];
                combination(0, 0);
            }

            builder.append("#").append(testCase).append(" ").append(min).append("\n");
        }
        System.out.print(builder);
    }

    private static void combination(int index, int start) {
        if (index == C) {
            N2383Vector stairA = stairs.get(0);
            N2383Vector stairB = stairs.get(1);
            Queue<N2383Vector> stairAPeople = new LinkedList<>();
            Queue<N2383Vector> stairBPeople = new LinkedList<>(peoples);
            Queue<N2383Vector> queue = new LinkedList<>();
            for (N2383Vector person : array) {
                stairAPeople.add(person);
                stairBPeople.remove(person);
            }

            while (!stairAPeople.isEmpty()) {
                N2383Vector pop = stairAPeople.poll();
                queue.offer(new N2383Vector(pop.x, pop.y, Math.abs(pop.x - stairA.x) + Math.abs(pop.y - stairA.y), true));
            }

            while (!stairBPeople.isEmpty()) {
                N2383Vector pop = stairBPeople.poll();
                queue.offer(new N2383Vector(pop.x, pop.y, Math.abs(pop.x - stairB.x) + Math.abs(pop.y - stairB.y), false));
            }

            Queue<N2383Vector> stairAWait = new LinkedList<>();
            Queue<N2383Vector> stairBWait = new LinkedList<>();

            int time = 1;
            while (!queue.isEmpty() || !(stairAWait.isEmpty() && stairBWait.isEmpty())) {
                int stairASize = stairAWait.size();
                for (int i = 0; i < stairASize; i++) {
                    N2383Vector person = stairAWait.poll();
                    person.weight -= 1;
                    if (person.weight != 0) stairAWait.offer(person);
                }

                int stairBSize = stairBWait.size();
                for (int i = 0; i < stairBSize; i++) {
                    N2383Vector person = stairBWait.poll();
                    person.weight -= 1;
                    if (person.weight != 0) stairBWait.offer(person);
                }

                int size = queue.size();
                for (int _x = 0; _x < size; _x++) {
                    N2383Vector pop = queue.poll();
                    pop.weight = Math.max(pop.weight - 1, 0);

                    if (pop.weight > 0) {
                        queue.offer(pop);
                    } else {
                        if (pop.isA) {
                            if (stairAWait.size() > 2) {
                                queue.offer(pop);
                                continue;
                            }
                            pop.weight = stairA.weight;
                            stairAWait.offer(pop);
                        } else {
                            if (stairBWait.size() > 2) {
                                queue.offer(pop);
                                continue;
                            }
                            pop.weight = stairB.weight;
                            stairBWait.offer(pop);
                        }
                    }
                }
                time++;
            }
            min = Math.min(min, time);
//            System.exit(0);


            return;
        }

        for (int i = start; i < peoples.size(); i++) {
            array[index] = peoples.get(i);
            combination(index + 1, i + 1);
        }
    }
}
