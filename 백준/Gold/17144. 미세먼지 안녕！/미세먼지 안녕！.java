import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int row, col, T;
    private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        int[][] squares = new int[row][col];
        int[][] sum = new int[row][col];
        T = Integer.parseInt(st.nextToken());
        int startX = -1;
        int endX = -1;
        for (int x = 0; x < row; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < col; y++) {
                squares[x][y] = Integer.parseInt(st.nextToken());
                if (squares[x][y] == -1) {
                    if (startX == -1) {
                        startX = x;
                    } else {
                        endX = x;
                    }
                }
            }
        }

        for (int testCase = 0; testCase < T; testCase++) {
            for (int x = 0; x < row; x++) {
                for (int y = 0; y < col; y++) {
                    if (squares[x][y] <= 0) continue;;
                    int remainCount = 0;
                    for (int[] ints : direction) {
                        int newY = y + ints[1];
                        int newX = x + ints[0];

                        if (isIn(newX, newY) && squares[newX][newY] >= 0) {
                            sum[newX][newY] += squares[x][y] / 5;
                            remainCount += squares[x][y] / 5;
                        }
                    }
                    sum[x][y] -= remainCount;
                }
            }

            for (int x = 0; x < row; x++) {
                for (int y = 0; y < col; y++) {
                    squares[x][y] += sum[x][y];
                    sum[x][y] = 0;
                }
            }

            rotateUpper(squares, startX);
            rotateDown(squares, endX);
        }

        int answer = 0;
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                if (squares[x][y] > 0) answer += squares[x][y];
            }
        }
        System.out.println(answer);
    }

    private static void rotateDown(int[][] squares, int endX) {
        int prev = 0;

        int x = endX;
        int y = 1;
        while (y < col) {
            int temp = squares[x][y];
            squares[x][y] = prev;
            prev = temp;
            y++;
        }

        x = endX + 1;
        y = col - 1;
        while (x < row) {
            int temp = squares[x][y];
            squares[x][y] = prev;
            prev = temp;
            x++;
        }

        x = row - 1;
        y = col - 2;
        while (y >= 0) {
            int temp = squares[x][y];
            squares[x][y] = prev;
            prev = temp;
            y--;
        }

        x = row - 2;
        y = 0;
        while (x > endX) {
            int temp = squares[x][y];
            squares[x][y] = prev;
            prev = temp;
            x--;
        }
    }

    private static void rotateUpper(int[][] squares, int startX) {
        int prev = 0;

        int x = startX;
        int y = 1;
        while (y < col) {
            int temp = squares[x][y];
            squares[x][y] = prev;
            prev = temp;
            y++;
        }
        x = startX - 1;
        y = col - 1;
        while (x >= 0) {
            int temp = squares[x][y];
            squares[x][y] = prev;
            prev = temp;
            x--;
        }
        x = 0;
        y = col - 2;
        while (y >= 0) {
            int temp = squares[x][y];
            squares[x][y] = prev;
            prev = temp;
            y--;
        }
        x = 1;
        y = 0;
        while (x < startX) {
            int temp = squares[x][y];
            squares[x][y] = prev;
            prev = temp;
            x++;
        }
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < row && y < col;
    }

    private static int[] next(int x, int y, int depth) {
        int newX = x;
        int newY = y;
        if (newY - 1 >= depth && newX == depth) {
            newY--;
        } else if (newX + 1 < row - depth && newY == depth) {
            newX++;
        } else if (newY + 1 < col - depth && newX == row - depth - 1) {
            newY++;
        } else {
            newX--;
        }
        return new int[]{newX, newY};
    }
}