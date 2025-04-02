class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n - 1 && nums[i] <= 0; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            twoSum(nums, ans, i + 1, n - 1, 0 - nums[i]);
        }
        return ans;
    }

    public void twoSum(int[] nums, List<List<Integer>> ans, int left, int right, int target) {
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                left++;
            } else if (nums[left] + nums[right] > target) {
                right--;
            } else {
                ans.add(
                        Arrays.asList(new Integer[] { -target, nums[left], nums[right] }));
                left++;
                while (left < right && nums[left] == nums[left - 1]) {
                    left++;
                }
            }
        }
    }
}