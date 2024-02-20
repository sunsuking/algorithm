import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int FRONT = 2;
    private static final int BACK = 3;
    private static final int LEFT = 4;
    private static final int RIGHT = 5;
    private static final int START = 0;
    private static final int LAST = 3;
    private static final int NUMBER = 4;
    private static char[][][] CUBE = {{
            {'w', 'w', 'w'},
            {'w', 'w', 'w'},
            {'w', 'w', 'w'}
    }, {
            {'y', 'y', 'y'},
            {'y', 'y', 'y'},
            {'y', 'y', 'y'}
    }, {
            {'r', 'r', 'r'},
            {'r', 'r', 'r'},
            {'r', 'r', 'r'}
    }, {
            {'o', 'o', 'o'},
            {'o', 'o', 'o'},
            {'o', 'o', 'o'},
    }, {
            {'g', 'g', 'g'},
            {'g', 'g', 'g'},
            {'g', 'g', 'g'},
    }, {
            {'b', 'b', 'b'},
            {'b', 'b', 'b'},
            {'b', 'b', 'b'},
    }};
    private static char[][][] cube;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder();
        StringTokenizer st;
        for (int testCase = 0; testCase < T; testCase++) {
            cube = cloneCube();
            int count = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < count; i++) {
                String next = st.nextToken();
                rotate(next.charAt(0), next.charAt(1) == '+');
            }
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    builder.append(cube[UP][x][y]);
                }
                builder.append("\n");
            }

//            System.out.println(builder);
//            System.exit(0);
        }
        System.out.println(builder);
    }

    private static void leftPlus() {
        int[] target = new int[]{FRONT, DOWN, BACK, UP};
        char[] end = new char[]{cube[target[LAST]][0][0], cube[target[LAST]][1][0], cube[target[LAST]][2][0]};
        for (int i = NUMBER - 1; i > 0; i--) {
            int now = target[i];
            int prev = target[i - 1];
            if (i == 3) {
                cube[now][2][0] = cube[prev][0][2];
                cube[now][1][0] = cube[prev][1][2];
                cube[now][0][0] = cube[prev][2][2];
            } else if (i == 2) {
                cube[now][2][2] = cube[prev][0][0];
                cube[now][1][2] = cube[prev][1][0];
                cube[now][0][2] = cube[prev][2][0];
            } else {
                cube[now][0][0] = cube[prev][0][0];
                cube[now][1][0] = cube[prev][1][0];
                cube[now][2][0] = cube[prev][2][0];
            }
        }
        cube[target[START]][0][0] = end[0];
        cube[target[START]][1][0] = end[1];
        cube[target[START]][2][0] = end[2];
        rotateOne(LEFT);
    }

    private static void downPlus() {
        char[] temp = new char[3];
        temp[0] = cube[2][2][0];
        temp[1] = cube[2][2][1];
        temp[2] = cube[2][2][2];

        // 4 -> 2
        for (int i = 0; i < 3; i++) cube[2][2][i] = cube[4][2][i];

        // 3 -> 4
        for (int i = 0; i < 3; i++) cube[4][2][i] = cube[3][2][i];

        // 5 -> 3
        for (int i = 0; i < 3; i++) cube[3][2][i] = cube[5][2][i];

        // 2 -> 5
        for (int i = 0; i < 3; i++) cube[5][2][i] = temp[i];

        rotateOne(DOWN);
    }

    private static void upPlus() {
        char[] temp = new char[3];
        temp[0] = cube[5][0][0];
        temp[1] = cube[5][0][1];
        temp[2] = cube[5][0][2];

        // 3(0 0, 0 1, 0 2) -> 5(0 0, 0 1, 0 2)
        for (int i = 0; i < 3; i++) cube[5][0][i] = cube[3][0][i];

        // 4(0 0, 0 1, 0 2) -> 3(0 0, 0 1, 0 2)
        for (int i = 0; i < 3; i++) cube[3][0][i] = cube[4][0][i];

        // 2(0 0, 0 1, 0 2) -> 4(0 0, 0 1, 0 2)
        for (int i = 0; i < 3; i++) cube[4][0][i] = cube[2][0][i];

        // 5 -> 2(0 0, 0 1, 0 2)
        for (int i = 0; i < 3; i++) cube[2][0][i] = temp[i];

        // 배열 90도 돌리기
        // [i, j] <- [j, N-1-i]
        // [N-1-j, i] <- [i, j]
        rotateOne(UP);
    }

    private static void rightPlus() {
        char[] temp = new char[3];
        temp[0] = cube[0][0][2];
        temp[1] = cube[0][1][2];
        temp[2] = cube[0][2][2];

        // 2(0 2, 1 2, 2 2) -> 0(0 2, 1 2, 2 2)
        for (int i = 0; i < 3; i++) cube[0][i][2] = cube[2][i][2];

        // 1(0 2, 1 2, 2 2) -> 2(0 2, 1 2, 2 2)
        for (int i = 0; i < 3; i++) cube[2][i][2] = cube[1][i][2];

        // 3(2 0, 1 0, 0 0) -> 1(0 2, 1 2, 2 2)
        for (int i = 0; i < 3; i++) cube[1][i][2] = cube[3][2 - i][0];

        // 0(0 2, 1 2, 2 2) -> 3(2 0, 1 0, 0 0)
        for (int i = 0; i < 3; i++) cube[3][2 - i][0] = temp[i];

        rotateOne(RIGHT);
    }

    private static void B() { // B 연산
        char[] temp = new char[3];
        temp[0] = cube[0][0][0];
        temp[1] = cube[0][0][1];
        temp[2] = cube[0][0][2];

        // 5(0 2, 1 2, 2 2) -> 0(0 0, 0 1, 0 2)
        for (int i = 0; i < 3; i++) cube[0][0][i] = cube[5][i][2];

        // 1(2 0, 2 1, 2 2) -> 5(2 2, 1 2, 0 2)
        for (int i = 0; i < 3; i++) cube[5][2 - i][2] = cube[1][2][i];

        // 4(0 0, 1 0, 2 0) -> 1(2 0, 2 1, 2 2)
        for (int i = 0; i < 3; i++) cube[1][2][i] = cube[4][i][0];

        // 0(0 2, 0 1, 0 0) -> 4(0 0, 1 0, 2 0)
        for (int i = 0; i < 3; i++) cube[4][i][0] = temp[2 - i];

        rotateOne(BACK);
    }

    static void F() { // F 연산
        char[] temp = new char[3];
        temp[0] = cube[0][2][0];
        temp[1] = cube[0][2][1];
        temp[2] = cube[0][2][2];

        // 4(2 2, 1 2, 0 2) -> 0(2 0, 2 1, 2 2)
        for (int i = 0; i < 3; i++) cube[0][2][i] = cube[4][2 - i][2];

        // 1(0 0, 0 1, 0 2) -> 4(0 2, 1 2, 2 2)
        for (int i = 0; i < 3; i++) cube[4][i][2] = cube[1][0][i];

        // 5(2 0, 1 0, 0 0) -> 1(0 0, 0 1, 0 2)
        for (int i = 0; i < 3; i++) cube[1][0][i] = cube[5][2 - i][0];

        // 0(2 0, 2 1, 2 2) -> 5(0 0, 1 0, 2 0)
        for (int i = 0; i < 3; i++) cube[5][i][0] = temp[i];

        rotateOne(FRONT);
    }

    private static void rotateOne(int index) {
        char[][] arrTemp = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arrTemp[i][j] = cube[index][i][j];
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cube[index][i][j] = arrTemp[3 - 1 - j][i];
            }
        }
    }

    private static void rotate(char command, boolean isRight) {
        int[] target;
        char[] end;
        switch (command) {
            case 'U':
                if (isRight) {
                    upPlus();
                } else {
                    upPlus();
                    upPlus();
                    upPlus();
                }
                break;
            case 'D':
                if (isRight) {
                    downPlus();
                } else {
                    downPlus();
                    downPlus();
                    downPlus();
                }
                break;
            case 'F':
                if (isRight) {
                    F();
                } else {
                    F();
                    F();
                    F();
                }

                break;
            case 'B':
                if (isRight) {
                    B();
                } else {
                    B();
                    B();
                    B();
                }

                break;
            case 'L':
                if (isRight) {
                    leftPlus();
                } else {
                    leftPlus();
                    leftPlus();
                    leftPlus();
                }
                break;
            case 'R':
                if (isRight) {
                    rightPlus();
                } else {
                    rightPlus();
                    rightPlus();
                    rightPlus();
                }
                break;
        }
    }

    private static char[][][] cloneCube() {
        char[][][] newCube = new char[6][3][3];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                newCube[i][j] = CUBE[i][j].clone();
            }
        }
        return newCube;
    }
}