class Solution {
    public int findDuplicate(int[] nums) {
        int dup = -1, n = nums.length;
        for (int i = 0; i < n; i++) {
            int curr = Math.abs(nums[i]);
            if (nums[curr] < 0) {
                dup = curr;
                break;
            }
            nums[curr] *= -1;
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] < 0) {
                nums[i] *= -1;
            }
        }
        return dup;
    }
}