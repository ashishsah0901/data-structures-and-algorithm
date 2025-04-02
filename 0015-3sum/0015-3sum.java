class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            twoSum(nums, ans, i + 1, n - 1, -nums[i]);
        }
        return ans;
    }
    private void twoSum(int nums[], List<List<Integer>> ans, int start, int end, int target) {
        while (start < end) {
            if (nums[start] + nums[end] < target) {
                start++;
            } else if (nums[start] + nums[end] > target) {
                end--;
            } else {
                ans.add(Arrays.asList(new Integer[] {-target, nums[start], nums[end]}));
                start++;
                while (start < end && nums[start] == nums[end]) {
                    start++;
                }
            }
        }
    }
}