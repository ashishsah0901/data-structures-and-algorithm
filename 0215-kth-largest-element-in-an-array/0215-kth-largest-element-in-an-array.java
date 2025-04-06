class Solution {
    public int findKthLargest(int[] nums, int k) {
        int cnt[] = new int[20001];
        for (int num: nums) {
            cnt[num + 10000]++;
        }
        for (int i = cnt.length - 1; i >= 0; i--) {
            if (cnt[i] > 0) {
                k -= cnt[i];
                if (k <= 0) {
                    return i - 10000;
                }
            }
        }
        return -1;
    }
}