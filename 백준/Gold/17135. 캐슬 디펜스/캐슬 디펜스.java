import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Position {
    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

public class Main {
    private static int[][] squares;
    private static int[] array;
    private static List<Position> direction = new ArrayList<>();
    private static int row, col, distance, max;
    private static final int[][] delta = {{0, -1}, {-1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        array = new int[row];
        distance = Integer.parseInt(st.nextToken());
        squares = new int[col][row];
        max = Integer.MIN_VALUE;
        for (int i = 0; i < col; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < row; j++) {
                squares[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] pop = queue.poll();
                for (int[] ints : delta) {
                    int newX = pop[0] + ints[0];
                    int newY = pop[1] + ints[1];
                    Position position = new Position(newX, newY);
                    if (Math.abs(newX) + Math.abs(newY) <= distance && !direction.contains(position)) {
                        queue.offer(new int[] {newX, newY});
                        direction.add(position);
                    }
                }
            }
        }
        direction.remove(new Position(0, 0));
//        System.out.println(direction);
//        direction.stream().forEach(s -> System.out.println(s));

        combination(0, 0);
        System.out.println(max);
    }

    private static void combination(int index, int start) {
        if (index == 3) {
            int[][] copy = new int[col][row];
            for (int i = 0; i < col; i++) copy[i] = squares[i].clone();
            int[][] attacks = new int[3][2];
            for (int i = 0; i < index; i++) {
                attacks[i] = new int[]{col, array[i]};
            }
            int count = 0;
            while(!isOk(copy)) {
                int[][] hits = new int[3][2];
                for (int i = 0; i < 3; i++) {
                    hits[i] = new int[]{-1, -1};
                }
                main:
                for (int i = 0; i < 3; i++) {
                    for (Position position : direction) {
                        int newX = attacks[i][0] + position.x;
                        int newY = attacks[i][1] + position.y;
                        if (isIn(newX, newY) && copy[newX][newY] == 1) {
                            hits[i][0] = newX;
                            hits[i][1] = newY;
                            continue main;
                        }
                    }
                }

                for (int[] hit : hits) {
                    if (hit[0] == -1 && hit[1] == -1) continue;
                    if (copy[hit[0]][hit[1]] == 1) {
                        copy[hit[0]][hit[1]] = 0;
                        count++;
                    }
//                    System.out.println(Arrays.toString(hit) + " : " + count);
                }

                for (int i = col - 2; i >= 0; i--) {
                    copy[i + 1] = copy[i];
                    copy[i] = new int[row];
                }
//                for (int[] c : copy) System.out.println(Arrays.toString(c));
//                System.out.println("=============");
//                System.exit(0);
            }
            max = Math.max(max, count);
        }

        for (int i = start; i < row; i++) {
            array[index] = i;
            combination(index + 1, i + 1);
        }
    }

    private static boolean isOk(int[][] squares) {
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (squares[i][j] == 1) return false;
            }
        }
        return true;
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < col && y < row;
    }
}