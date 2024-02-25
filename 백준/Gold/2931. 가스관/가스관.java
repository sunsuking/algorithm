import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int[] UP = {-1, 0};
    private static final int[] DOWN = {1, 0};
    private static final int[] LEFT = {0, -1};
    private static final int[] RIGHT = {0, 1};
    private static final int[] RIGHT_UP = {-1, 1};
    private static final int[] RIGHT_DOWN = {1, 1};
    private static final int[] LEFT_UP = {-1, -1};
    private static final int[] LEFT_DOWN = {1, -1};
    private static int row, col;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        char[][] squares = new char[row][col];
        int startX = 0;
        int startY = 0;
        int endX = 0;
        int endY = 0;
        for (int x = 0; x < row; x++) {
            char[] chars = br.readLine().toCharArray();
            for (int y = 0; y < col; y++) {
                if (chars[y] == 'M') {
                    startX = x;
                    startY = y;
                } else if (chars[y] == 'S') {
                    endX = x;
                    endY = y;
                }
                squares[x][y] = chars[y];
            }
        }

        Map<Character, int[][]> map = new HashMap<>();
        map.put('+', new int[][]{UP, DOWN, LEFT, RIGHT});
        map.put('|', new int[][]{UP, DOWN});
        map.put('2', new int[][]{UP, RIGHT});
        map.put('3', new int[][]{UP, LEFT});
        map.put('-', new int[][]{LEFT, RIGHT});
        map.put('1', new int[][]{RIGHT, DOWN});
        map.put('4', new int[][]{LEFT, DOWN});

        int nX = 0;
        int nY = 0;

        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                if (squares[x][y] == '.' || squares[x][y] == 'M' || squares[x][y] == 'Z') continue;

                int[][] direction = map.get(squares[x][y]);
                for (int[] ints : direction) {
                    int newX = x + ints[0];
                    int newY = y + ints[1];

                    if (isIn(newX, newY)) {
                        if (squares[newX][newY] == '.') {
                            nX = newX;
                            nY = newY;
                        }
                    }
                }
            }
        }

//        System.out.println(nX + " : " + nY);
//        System.out.println("===========");
        List<int[]> can = new ArrayList<>();
        int[][] direction = new int[][]{UP, DOWN, LEFT, RIGHT};
        for (int[] ints : direction) {
            int newX = nX + ints[0];
            int newY = nY + ints[1];

            // 양 옆에 파이프가 있을 때
            if (isIn(newX, newY) && !(squares[newX][newY] == '.' || squares[newX][newY] == 'M' || squares[newX][newY] == 'Z')) {
//                System.out.println(newX + " : " + newY);
                int[][] newDirection = map.get(squares[newX][newY]);
//                System.out.println("===init===");
                for (int[] newInts : newDirection) {
                    int newNewX = newX + newInts[0];
                    int newNewY = newY + newInts[1];
//                    System.out.println(newNewX + " : " + newNewY);
                    if (isIn(newNewX, newNewY) && (newNewX == nX && newNewY == nY)) {
//                        System.out.println("파이프랑 연결됨.");
//                        System.out.println(newX + " : " + newY);
                        can.add(ints);
                    }
                }
//                System.out.println("===end===");
            }
        }
//        System.out.println("===========");

//        for (int i = 0; i < can.size(); i++) System.out.println(Arrays.toString(can.get(i)));

        int ansX = nX + 1;
        int ansY = nY + 1;

        if (can.size() == 4) {
            System.out.println(ansX + " " + ansY + " +");
        } else {
            if (can.contains(UP)) {
                if (can.contains(DOWN)) {
                    System.out.println(ansX + " " + ansY + " |");
                } else if (can.contains(RIGHT)) {
                    System.out.println(ansX + " " + ansY + " 2");
                } else {
                    System.out.println(ansX + " " + ansY + " 3");
                }
            } else {
                if (can.contains(LEFT)) {
                    if (can.contains(RIGHT)) {
                        System.out.println(ansX + " " + ansY + " -");
                    } else if (can.contains(DOWN)) {
                        System.out.println(ansX + " " + ansY + " 4");
                    }
                } else {
                    System.out.println(ansX + " " + ansY + " 1");
                }
            }
        }
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < row && y < col;
    }

    static class N2931Position {
        int x;
        int y;

        public N2931Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            N2931Position other = (N2931Position) obj;
            return this.x == other.x && this.y == other.y;
        }
    }
}