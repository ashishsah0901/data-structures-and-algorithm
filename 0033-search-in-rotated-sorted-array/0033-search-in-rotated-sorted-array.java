class Solution {
    public int search(int[] nums, int target) {
        /*
        4 5 6 7 0 1 2
        ^           ^
        |           |
        */
        int n = nums.length, low = 0, high = n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[low] == target) {
                return low;
            } else if (nums[high] == target) {
                return high;
            }
            if (nums[low] < nums[mid]) {
                if (target < nums[mid] && target > nums[low]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (target > nums[mid] && target < nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
}