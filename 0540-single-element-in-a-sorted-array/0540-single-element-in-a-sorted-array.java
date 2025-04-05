class Solution {
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length, low = 0, high = n - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] != nums[mid + 1] && nums[mid] != nums[mid - 1]) {
                return nums[mid];
            }
            if (mid % 2 == 0 && nums[mid] == nums[mid + 1]) {
                low = mid + 2;
            } else if (mid % 2 == 1 && nums[mid] == nums[mid - 1]) {
                low = mid + 1;
            } else if (mid % 2 == 0) {
                high = mid - 2;
            } else {
                high = mid - 1;
            }
        }
        return nums[low];
    }
}