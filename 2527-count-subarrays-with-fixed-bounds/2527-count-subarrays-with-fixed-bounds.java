class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long count = 0;
        int leftBound = -1, lastmin = -1, lastmax = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < minK || nums[i] > maxK) {
                leftBound = i;
            }
            if (nums[i] == minK) {
                lastmin = i;
            }
            if (nums[i] == maxK) {
                lastmax = i;
            }
            int start = Math.min(lastmin, lastmax);
            if (start > leftBound) {
                count += start - leftBound;
            }
        }
        return count;
    }
}