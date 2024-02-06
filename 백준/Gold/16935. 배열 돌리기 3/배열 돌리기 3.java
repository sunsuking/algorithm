import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int[][] squares = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                squares[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int[][] newSquares = squares;
        for (int i = 0; i < R; i++) {
            int way = Integer.parseInt(st.nextToken());
            switch (way) {
                case 1:
                    newSquares = way1(newSquares);
                    break;
                case 2:
                    newSquares = way2(newSquares);
                    break;
                case 3:
                    newSquares = way2(way3(newSquares));
                    break;
                case 4:
                    newSquares = way1(way2(way4(newSquares)));
                    break;
                case 5:
                    newSquares = way5(newSquares);
                    break;
                case 6:
                    newSquares = way6(newSquares);
                    break;
            }
        }


        StringBuilder builder = new StringBuilder();
        for (int[] newSquare : newSquares) {
            for (int j = 0; j < newSquares[0].length; j++) {
                builder.append(newSquare[j]).append(" ");
            }
            builder.append("\n");
        }
        System.out.println(builder);
    }

    private static int[][] way1(int[][] squares) {
        int N = squares.length;
        int M = squares[0].length;
        int[][] newSquares = new int[N][M];
        for (int i = 0; i < N; i++) {
            newSquares[i] = squares[N - 1 - i];
        }
        return newSquares;
    }

    private static int[][] way2(int[][] squares) {
        int N = squares.length;
        int M = squares[0].length;
        int[][] newSquares = new int[N][M];
        for (int i = 0; i < M; i++) {
            for (int x = 0; x < N; x++) {
                newSquares[x][M - 1 - i] = squares[x][i];
            }
        }
        return newSquares;
    }

    private static int[][] way3(int[][] squares) {
        int N = squares.length;
        int M = squares[0].length;
        int[][] newSquares = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int x = 0; x < N; x++) {
                newSquares[i][x] = squares[x][i];
            }
        }
        return newSquares;
    }

    private static int[][] way4(int[][] squares) {
        int N = squares.length;
        int M = squares[0].length;
        int[][] newSquares = new int[M][N];
        for (int i = 0; i < N; i++) {
            for (int x = 0; x < M; x++) {
                newSquares[x][N - i - 1] = squares[i][x];
            }
        }
        return newSquares;
    }

    private static int[][] way5(int[][] squares) {
        int N = squares.length;
        int M = squares[0].length;
        int[][] start = {{1, 0, 0}, {2, 0, M/2}, {3, N/2, 0}, {4, N/2, M/2}};
        int[][] newSquares = new int[N][M];
        for(int[] position : start) {
            for (int i = position[1]; i < position[1] + N/2; i++) {
                for (int j = position[2]; j < position[2] + M/2; j++) {
                    if (position[0] == 1) {
                        newSquares[i][j] = squares[i + N / 2][j];
                    } else if (position[0] == 2) {
                        newSquares[i][j] = squares[i][j - M / 2];
                    } else if (position[0] == 3) {
                        newSquares[i][j] = squares[i][j + M / 2];
                    } else if (position[0] == 4) {
                        newSquares[i][j] = squares[i - N / 2][j];
                    }
                }
            }
        }
        return newSquares;
    }

    private static int[][] way6(int[][] squares) {
        int N = squares.length;
        int M = squares[0].length;
        int[][] start = {{1, 0, 0}, {2, 0, M/2}, {3, N/2, 0}, {4, N/2, M/2}};
        int[][] newSquares = new int[N][M];
        for(int[] position : start) {
            for (int i = position[1]; i < position[1] + N/2; i++) {
                for (int j = position[2]; j < position[2] + M/2; j++) {
                    if (position[0] == 1) {
                        newSquares[i][j] = squares[i][j + M / 2];
                    } else if (position[0] == 2) {
                        newSquares[i][j] = squares[i + N / 2][j];
                    } else if (position[0] == 3) {
                        newSquares[i][j] = squares[i - N / 2][j];
                    } else if (position[0] == 4) {
                        newSquares[i][j] = squares[i][j - M / 2];
                    }
                }
            }
        }
        return newSquares;
    }
}