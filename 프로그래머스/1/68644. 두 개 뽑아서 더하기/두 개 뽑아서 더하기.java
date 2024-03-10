import java.util.*;

class Solution {
    private static int[] array, nums;
    private static Set<Integer> set;
    
    public int[] solution(int[] numbers) {
        nums = numbers;
        array = new int[2];
        set = new HashSet<>();
        combination(0, 0);
        int index = 0;
        int[] answer = new int[set.size()];
        for (int num : set) {
            answer[index++] = num;
        }
        Arrays.sort(answer);
        return answer;
    }
    
    private static void combination(int index, int start) {
        if (index == 2) {
            set.add(array[0] + array[1]);    
            return;
        }
        
        for (int i = start; i < nums.length; i++) {
            array[index] = nums[i];
            combination(index + 1, i + 1);
        }
    }
}