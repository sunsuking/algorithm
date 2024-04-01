import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int[] nums = new int[num];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < num; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        int[] solution = binarySearch(nums);

        System.out.println(nums[solution[0]] + " " + nums[solution[1]]);
    }

    private static int[] binarySearch(int[] array) {
        int start = 0;
        int end = array.length - 1;
        int min = Integer.MAX_VALUE;

        int indexStart = 0;
        int indexEnd = 0;

        while (start <= end) {
            if (array[start] + array[end] == 0) {
                indexStart = start;
                indexEnd = end;
                return new int[]{indexStart, indexEnd};
            } else if (array[start] + array[end] > 0) {
                if (array[start] + array[end] < min) {
                    min = array[start] + array[end];
                    indexStart = start;
                    indexEnd = end;
                }
                end--;
            } else if (array[start] + array[end] < 0) {
                if (Math.abs(array[start] + array[end]) < min) {
                    min = Math.abs(array[start] + array[end]);
                    indexStart = start;
                    indexEnd = end;
                }
                start++;
            }

            if (start == end) {
                end--;
            }
        }
        return new int[]{indexStart, indexEnd};
    }
}