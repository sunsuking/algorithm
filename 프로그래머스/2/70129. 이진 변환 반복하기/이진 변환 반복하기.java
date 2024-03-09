class Solution {
    private static int[] nums;
    public int[] solution(String s) {
        nums = new int[2];
        dfs(s);
        return nums;
    }
    
    private static void dfs(String s) {
        if (s.equals("1")) return;
        String remain = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '0') {
                nums[1]++;
            } else {
                remain += "1";
            }
        }
        String newS = "";
        int initNum = -1;
        for (int i = 31; i >= 0; i--) {
            if ((remain.length() & (1 << i)) == 1 << i) {
                if (initNum == -1) initNum = i;
                newS += "1";
            } else {
                newS += "0";
            }
        }
        nums[0]++;
        dfs(newS.substring(31 - initNum, 32));
    }
}