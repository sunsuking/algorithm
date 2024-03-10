class Solution {
    public int solution(int n) {
        String s = "";
        int count = 1;
        while(n > 0) {
            int num = (int) Math.pow(3, count);
            if (n % num != 0) {
                int remain = (n % num) / (int) Math.pow(3, count - 1);
                s = s + remain; 
            } else {
                s = s + "0";
            }
            count++;
            n -= n % num;
        }
        int answer = 0;
        for (int i = 0; i < s.length(); i++) {
            answer += (s.charAt(s.length() - i - 1) - '0') * (int) Math.pow(3, i);
        }
        return answer;
    }
}