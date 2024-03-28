import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] array =new int[9][9];
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();

        for(int x = 0; x < 9; x++) {
            char[] chars = br.readLine().toCharArray();
            for (int y = 0; y < 9; y++) {
                array[x][y] = chars[y] - '0';
            }
        }
        dfs(0);

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                builder.append(array[x][y]);
            }
            builder.append("\n");
        }
        System.out.print(builder.toString());
    }
    private static void dfs(int d) {
        if(d == 81) {
            flag = true;
            return;
        }

        int row = d / 9;
        int col = d % 9;

        if(array[row][col]!=0)
            dfs(d + 1);
        else {
            for(int i = 1; i < 10; i++) {
                if(!isValid(row,col,i)) continue;
                array[row][col] = i;
                dfs(d+1);

                if(flag) return;
                array[row][col]=0;
            }
        }
    }
    private static boolean isValid(int r, int c, int n) {
        for(int i = 0; i < 9; i++) {
            if(array[i][c] == n || array[r][i] == n) return false;
        }

        int sr = r / 3 * 3;
        int sc = c - (c % 3);
        for(int row = sr; row < sr + 3; row++) {
            for(int col = sc; col < sc + 3; col++) {
                if(array[row][col]==n) return false;
            }
        }
        return true;
    }

}