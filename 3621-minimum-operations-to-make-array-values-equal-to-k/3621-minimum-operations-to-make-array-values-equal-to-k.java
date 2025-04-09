class Solution {
    public int minOperations(int[] nums, int k) {
        int n = nums.length;
        for (int a: nums) {
            if (a < k) {
                return -1;
            }
        }
        Arrays.sort(nums);
        int[] temp = new int[n];
        for (int i = 0; i < n; i++) {
            temp[i] = nums[n-1-i];
        }
        int op = 0;
        int max = temp[0];
        for (int num : temp) {
            if (num > k) {
                if (num < max) {
                    max = num;
                    op++;
                }
            } else {
                break;
            }
        }
        return max==k?op:op+1;
    }
}