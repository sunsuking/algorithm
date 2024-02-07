import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int col, row, commandCount, min;
    private static int[][] squares, commands;
    private static int[] array;
    private static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        commandCount = Integer.parseInt(st.nextToken());
        squares = new int[col][row];
        int[][] newSquares = new int[col][row];
        for (int x = 0; x < col; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < row; y++) {
                squares[x][y] = Integer.parseInt(st.nextToken());
            }
        }
        min = Integer.MAX_VALUE;
        commands = new int[commandCount][3];
        array = new int[commandCount];
        visited = new boolean[commandCount];
        for (int i = 0; i < commandCount; i++) {
            st = new StringTokenizer(br.readLine());
            commands[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        permutation(0);

        System.out.println(min);
    }

    private static void permutation(int index) {
        if (index == commandCount) {
            int[][] newSquares = copy();
            for (int i = 0; i < commandCount; i++) {
                int[] command = commands[array[i]];
                newSquares = rotate(newSquares, command[0], command[1], command[2]);
            }
            for (int i = 0; i < col; i++) {
                int sum = 0;
                for (int j = 0; j < row; j++) {
                    sum += newSquares[i][j];
                }
                min = Math.min(sum, min);
            }
            return;
        }

        for (int i = 0; i < commandCount; i++) {
            if (!visited[i]) {
                visited[i] = true;
                array[index] = i;
                permutation(index + 1);
                visited[i] = false;
            }

        }
    }

    private static int[][] copy() {
        int[][] copy = new int[col][row];
        for (int i = 0; i < col; i++) {
            if (row >= 0) System.arraycopy(squares[i], 0, copy[i], 0, row);
        }
        return copy;
    }

    private static int[][] rotate(int[][] base, int r, int c, int s) {
        int[][] newSquares = new int[col][row];

        int startX = r - s - 1;
        int startY = c - s - 1;
        int endX = r + s;
        int endY = c + s;
        for (int i = 0; i < Math.min((endX - startX) / 2, (endY - startY) / 2); i++) {
            int count = (((endX - startX) - i * 2) * 2) + (((endY - startY) - i * 2) * 2) - 4;
//            System.out.println(count);
            int x = startX + i;
            int y = startY + i;
            for (int j = 0; j < count; j++) {
//                System.out.println("X: " + x + ", Y: " + y);
                int[] newP = nextPosition(x, y, i, startX + i, startY + i, endX - i, endY - i);
//                System.out.println("newX: " + newP[0] + ", newY: " + newP[1]);
                newSquares[newP[0]][newP[1]] = base[x][y];
                x = newP[0];
                y = newP[1];
            }
        }


        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (newSquares[i][j] == 0) newSquares[i][j] = base[i][j];
            }
        }
        return newSquares;
    }

    private static int[] nextPosition(int x, int y, int depth, int startX, int startY, int endX, int endY) {
        int newX = x;
        int newY = y;
//        System.out.println("x: " + x + ", y: " + y + ", depth: " + depth + ", startX: " + startX + ", startY: " + startY + ", endX" + endX + ", endY: " + endY);
        if (newY + 1 < endY && newX == startX) {
            newY++;
        } else if (newX + 1 < endX && newY == endY - 1) {
            newX++;
        } else if (newY - 1 >= startY && newX == endX - 1) {
            newY--;
        } else {
            newX--;
        }
        return new int[]{newX, newY};
    }
}