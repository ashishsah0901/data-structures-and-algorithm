class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int dp[] = new int[n];
        int hash[] = new int[n];
        int max = 0, index = -1;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            hash[i] = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0 && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    hash[i] = j;
                }
            }
            if (dp[i] > max) {
                max = dp[i];
                index = i;
            }
        }
        ArrayList<Integer> fList = new ArrayList<>();
        while(index != -1) {
            fList.add(nums[index]);
            index = hash[index];
        }
        return fList;
    }
}