import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, max;
    private static char[] direction = {'U', 'D', 'L', 'R'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        max = Integer.MIN_VALUE;
        int[][] squares = new int[N][N];
        StringTokenizer st;
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < N; y++) {
                squares[x][y] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(squares, 0, "");
        System.out.println(max);
    }

    private static void dfs(int[][] squares, int count, String s) {
        if (count == 5) {
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    max = Math.max(max, squares[x][y]);
                }
            }
            return;
        }

        for (char command : direction) {
            int[][] clone = clone(squares);
            switch (command) {
                case 'L':
                    doLeft(clone);
                    break;
                case 'R':
                    doRight(clone);
                    break;
                case 'U':
                    doUp(clone);
                    break;
                case 'D':
                    doDown(clone);
                    break;
            }
            int isSame = equals(squares, clone);
//            System.out.println("----------start----------");
//            System.out.println(s + command);
//            for (int i = 0; i < N; i++) System.out.println(Arrays.toString(squares[i]));
//            System.out.println("--------------------");
//            for (int i = 0; i < N; i++) System.out.println(Arrays.toString(clone[i]));
//            System.out.println("---------end-----------");
            if (isSame != -1) {
                max = Math.max(max, isSame);
            } else {
                dfs(clone, count + 1, s + command);
            }
        }
    }

    private static int[][] doLeft(int[][] squares) {
        for (int x = 0; x < N; x++) {
            int startY = 0;
            while (startY < N - 1) {
                if (squares[x][startY] != 0) {
                    startY++;
                    continue;
                }
                int swapIndex = startY;
                while (swapIndex < N - 1 && squares[x][swapIndex] == 0) swapIndex++;
                squares[x][startY] = squares[x][swapIndex];
                squares[x][swapIndex] = 0;
                startY++;
            }
            for (int y = 1; y < N; y++) {
                if (squares[x][y - 1] == squares[x][y]) {
                    squares[x][y - 1] *= 2;
                    squares[x][y] = 0;
                }
            }
            startY = 0;
            while (startY < N - 1) {
                if (squares[x][startY] != 0) {
                    startY++;
                    continue;
                }
                int swapIndex = startY;
                while (swapIndex < N - 1 && squares[x][swapIndex] == 0) swapIndex++;
                squares[x][startY] = squares[x][swapIndex];
                squares[x][swapIndex] = 0;
                startY++;
            }
        }
        return squares;
    }

    private static int[][] doRight(int[][] squares) {
        for (int x = 0; x < N; x++) {
            int startY = N - 1;
            while (startY > 0) {
                if (squares[x][startY] != 0) {
                    startY--;
                    continue;
                }
                int swapIndex = startY;
                while (swapIndex > 0 && squares[x][swapIndex] == 0) swapIndex--;
                squares[x][startY] = squares[x][swapIndex];
                squares[x][swapIndex] = 0;
                startY--;
            }
            for (int y = N - 2; y >= 0; y--) {
                if (squares[x][y + 1] == squares[x][y]) {
                    squares[x][y + 1] *= 2;
                    squares[x][y] = 0;
                }
            }
            startY = N - 1;
            while (startY > 0) {
                if (squares[x][startY] != 0) {
                    startY--;
                    continue;
                }
                int swapIndex = startY;
                while (swapIndex > 0 && squares[x][swapIndex] == 0) swapIndex--;
                squares[x][startY] = squares[x][swapIndex];
                squares[x][swapIndex] = 0;
                startY--;
            }
        }
        return squares;
    }

    private static void doUp(int[][] squares) {
        for (int y = 0; y < N; y++) {
            int startX = 0;
            while (startX < N - 1) {
                if (squares[startX][y] != 0) {
                    startX++;
                    continue;
                }
                int swapIndex = startX;
                while (swapIndex < N - 1 && squares[swapIndex][y] == 0) swapIndex++;
                squares[startX][y] = squares[swapIndex][y];
                squares[swapIndex][y] = 0;
                startX++;
            }
            for (int x = 1; x < N; x++) {
                if (squares[x - 1][y] == squares[x][y]) {
                    squares[x - 1][y] *= 2;
                    squares[x][y] = 0;
                }
            }
            startX = 0;
            while (startX < N - 1) {
                if (squares[startX][y] != 0) {
                    startX++;
                    continue;
                }
                int swapIndex = startX;
                while (swapIndex < N - 1 && squares[swapIndex][y] == 0) swapIndex++;
                squares[startX][y] = squares[swapIndex][y];
                squares[swapIndex][y] = 0;
                startX++;
            }
        }
//        for (int i = 0; i < N; i++) System.out.println(Arrays.toString(squares[i]));
//        System.exit(0);
    }

    private static int[][] doDown(int[][] squares) {
        for (int y = 0; y < N; y++) {
            int startX = N - 1;
            while (startX > 0) {
                if (squares[startX][y] != 0) {
                    startX--;
                    continue;
                }
                int swapIndex = startX;
                while (swapIndex > 0 && squares[swapIndex][y] == 0) swapIndex--;
                squares[startX][y] = squares[swapIndex][y];
                squares[swapIndex][y] = 0;
                startX--;
            }
            for (int x = N - 2; x >= 0; x--) {
                if (squares[x + 1][y] == squares[x][y]) {
                    squares[x + 1][y] *= 2;
                    squares[x][y] = 0;
                }
            }
            startX = N - 1;
            while (startX > 0) {
                if (squares[startX][y] != 0) {
                    startX--;
                    continue;
                }
                int swapIndex = startX;
                while (swapIndex > 0 && squares[swapIndex][y] == 0) swapIndex--;
                squares[startX][y] = squares[swapIndex][y];
                squares[swapIndex][y] = 0;
                startX--;
            }
        }
        return squares;
    }

    private static int equals(int[][] before, int[][] after) {
        int max = Integer.MIN_VALUE;
        boolean isSame = true;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                max = Math.max(max, after[x][y]);
                if (before[x][y] != after[x][y]) {
                    isSame = false;
                }
            }
        }
        return isSame ? max : -1;
    }

    private static int[][] clone(int[][] squares) {
        int[][] newSquares = new int[N][N];
        for (int i = 0; i < N; i++) newSquares[i] = squares[i].clone();
        return newSquares;
    }
}