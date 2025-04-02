class Solution {
    public int[] countBits(int n) {
        int[] answer = new int[n + 1];
        int mask = 1;
        for (int i = 0; i <= n; i++) {
            int num = i;
            int sum = 0;
            for (int j = 0; (j < 32 && num != 0); j++) {
                sum += num & mask;
                num >>= 1;
            }
            answer[i] = sum;
        }
        return answer;
    }
}