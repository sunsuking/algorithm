import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;
    private static final char[] cDirection = {'U', 'D', 'L', 'R'};
    private static final int[][] direction = {{UP, -1, 0}, {DOWN, 1, 0}, {LEFT, 0, -1}, {RIGHT, 0, 1}};
    private static int row, col;
    private static Set<N13459Board> set;
    private static char[][] squares;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        squares = new char[row][col];
        set = new HashSet<>();
        int redX = 0, redY = 0, blueX = 0, blueY = 0;
        for (int x = 0; x < row; x++) {
            char[] chars = br.readLine().toCharArray();
            for (int y = 0; y < col; y++) {
                squares[x][y] = chars[y];
                if (squares[x][y] == 'R') {
                    redX = x;
                    redY = y;
                    squares[x][y] = '.';
                } else if (squares[x][y] == 'B') {
                    blueX = x;
                    blueY = y;
                    squares[x][y] = '.';
                }
            }
        }

        Queue<N13459Board> queue = new LinkedList<>();
        queue.offer(new N13459Board(redX, redY, blueX, blueY, 0, ""));
        while (!queue.isEmpty()) {
            N13459Board pop = queue.poll();
            if (set.contains(pop)) continue;
            set.add(pop);
//            System.out.println(pop);
            if (pop.count >= 10) continue;

            for (int[] ints : direction) {
                int command = ints[0];
                int dx = ints[1];
                int dy = ints[2];
                int strLen = pop.direction.length();
                if (strLen > 0) {
                    char prev = pop.direction.charAt(strLen - 1);
                    if (command == UP && prev == 'D') continue;
                    else if (command == DOWN && prev == 'U') continue;
                    else if (command == LEFT && prev == 'R') continue;
                    else if (command == RIGHT && prev == 'L') continue;
                }

                if (command == UP || command == DOWN) {
                    if (pop.blueY == pop.redY) {
                        if ((command == UP && pop.blueX < pop.redX) || (command == DOWN && pop.blueX > pop.redX)) {
                            int[] bluePosition = move(pop.blueX, pop.blueY, dx, dy);
                            int[] redPosition = moveWithOther(pop.redX, pop.redY, dx, dy, true, bluePosition[0]);
                            if (bluePosition[0] == -1) {
                                continue;
                            } else if (redPosition[0] == -1) {
                                System.out.println(1);
                                System.exit(0);
                            } else if (isSame(pop.blueX, pop.blueY, bluePosition) && isSame(pop.redX, pop.redY, redPosition)) {
                                continue;
                            }

                            queue.offer(new N13459Board(redPosition[0], redPosition[1], bluePosition[0], bluePosition[1], pop.count + 1, pop.direction + cDirection[command]));
                        } else {
                            int[] redPosition = move(pop.redX, pop.redY, dx, dy);
                            int[] bluePosition = moveWithOther(pop.blueX, pop.blueY, dx, dy, true, redPosition[0]);
                            if (bluePosition[0] == -1) {
                                continue;
                            } else if (redPosition[0] == -1) {
                                if (move(pop.blueX, pop.blueY, dx, dy)[0] == -1) {
                                    continue;
                                } else {
                                    System.out.println(1);
                                    System.exit(0);
                                }
                            } else if (isSame(pop.blueX, pop.blueY, bluePosition) && isSame(pop.redX, pop.redY, redPosition)) {
                                continue;
                            }

                            queue.offer(new N13459Board(redPosition[0], redPosition[1], bluePosition[0], bluePosition[1], pop.count + 1, pop.direction + cDirection[command]));
                        }
                    } else {
                        // 순서 상관 없이 움직이면 됨.
                        int[] redPosition = move(pop.redX, pop.redY, dx, dy);
                        int[] bluePosition = move(pop.blueX, pop.blueY, dx, dy);
                        if (bluePosition[0] == -1) {
                            continue;
                        } else if (redPosition[0] == -1) {
                            System.out.println(1);
                            System.exit(0);
                        } else if (isSame(pop.blueX, pop.blueY, bluePosition) && isSame(pop.redX, pop.redY, redPosition)) {
                            continue;
                        }

                        queue.offer(new N13459Board(redPosition[0], redPosition[1], bluePosition[0], bluePosition[1], pop.count + 1, pop.direction + cDirection[command]));
                    }
                } else {
                    if (pop.blueX == pop.redX) {
                        if ((command == LEFT && pop.blueY < pop.redY) || (command == RIGHT && pop.blueY > pop.redY)) {
                            int[] bluePosition = move(pop.blueX, pop.blueY, dx, dy);
                            int[] redPosition = moveWithOther(pop.redX, pop.redY, dx, dy, false, bluePosition[1]);
                            if (bluePosition[0] == -1) {
                                continue;
                            } else if (redPosition[0] == -1) {
                                System.out.println(1);
                                System.exit(0);
                            } else if (isSame(pop.blueX, pop.blueY, bluePosition) && isSame(pop.redX, pop.redY, redPosition)) {
                                continue;
                            }

                            queue.offer(new N13459Board(redPosition[0], redPosition[1], bluePosition[0], bluePosition[1], pop.count + 1, pop.direction + cDirection[command]));
                        } else {
                            int[] redPosition = move(pop.redX, pop.redY, dx, dy);
                            int[] bluePosition = moveWithOther(pop.blueX, pop.blueY, dx, dy, false, redPosition[1]);
                            if (bluePosition[0] == -1) {
                                continue;
                            } else if (redPosition[0] == -1) {
                                if (move(pop.blueX, pop.blueY, dx, dy)[0] == -1) {
                                    continue;
                                } else {
                                    System.out.println(1);
                                    System.exit(0);
                                }
                            } else if (isSame(pop.blueX, pop.blueY, bluePosition) && isSame(pop.redX, pop.redY, redPosition)) {
                                continue;
                            }
                            queue.offer(new N13459Board(redPosition[0], redPosition[1], bluePosition[0], bluePosition[1], pop.count + 1, pop.direction + cDirection[command]));
                        }
                    } else {
                        // 순서 상관 없이 움직이면 됨.
                        int[] redPosition = move(pop.redX, pop.redY, dx, dy);
                        int[] bluePosition = move(pop.blueX, pop.blueY, dx, dy);

                        if (bluePosition[0] == -1) {
                            continue;
                        } else if (redPosition[0] == -1) {
                            System.out.println(1);
                            System.exit(0);
                        } else if (isSame(pop.blueX, pop.blueY, bluePosition) && isSame(pop.redX, pop.redY, redPosition)) {
                            continue;
                        }
                        queue.offer(new N13459Board(redPosition[0], redPosition[1], bluePosition[0], bluePosition[1], pop.count + 1, pop.direction + cDirection[command]));
                    }
                }
            }
        }
        System.out.println(0);
    }

    private static boolean isSame(int x, int y, int[] newPosition) {
        return x == newPosition[0] && y == newPosition[1];
    }

    private static int[] move(int x, int y, int dx, int dy) {
        int newX = x;
        int newY = y;
        while (isIn(newX, newY) && squares[newX + dx][newY + dy] != '#') {
            if (squares[newX][newY] == 'O') return new int[]{-1, -1};
            newX += dx;
            newY += dy;
        }
        if (squares[newX][newY] == 'O') {
            return new int[]{-1, -1};
        }
        return new int[]{newX, newY};
    }

    private static int[] moveWithOther(int x, int y, int dx, int dy, boolean isX, int other) {
        int newX = x;
        int newY = y;
        while (isIn(newX, newY) && squares[newX + dx][newY + dy] != '#' && ((isX && newX + dx != other) || (!isX && newY + dy != other))) {
            newX += dx;
            newY += dy;
            if (squares[newX][newY] == 'O') return new int[]{-1, -1};
        }
        if (squares[newX][newY] == 'O') {
            return new int[]{-1, -1};
        }
        return new int[]{newX, newY};
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < row && y < col;
    }

    private static void printSquares() {
        for (int i = 0; i < row; i++) System.out.println(Arrays.toString(squares[i]));
    }

    static class N13459Board {
        int redX;
        int redY;
        int blueX;
        int blueY;
        int count;
        String direction;

        public N13459Board(int redX, int redY, int blueX, int blueY, int count, String direction) {
            this.redX = redX;
            this.redY = redY;
            this.blueX = blueX;
            this.blueY = blueY;
            this.count = count;
            this.direction = direction;
        }

        @Override
        public int hashCode() {
            return Objects.hash(redX, redY, blueX, blueY);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            N13459Board other = (N13459Board) obj;
            return this.redX == other.redX && this.redY == other.redY && this.blueX == other.blueX && this.blueY == other.blueY;
        }
    }
}