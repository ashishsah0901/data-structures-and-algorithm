class Solution {
    public int subsetXORSum(int[] nums) {
        return backtrack(nums, 0, 0);
    }
    
    private final int backtrack(int[] nums, int start, int running) {
        int result = running;
        for(int i = start; i < nums.length; i++) {
            running ^= nums[i];
            result += backtrack(nums, i + 1, running);
            running ^= nums[i];
        }
        return result;
    }
}