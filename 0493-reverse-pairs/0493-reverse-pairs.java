class Solution {
    public int reversePairs(int[] nums) {
        int n = nums.length;
        return mergeSort(nums, 0, n - 1);
    }
    private int mergeSort(int nums[], int l, int r) {
        int count = 0;
        if (l < r) {
            int mid = (l + r) / 2;
            count += mergeSort(nums, l, mid);
            count += mergeSort(nums, mid + 1, r);
            count += merge(nums, l, mid, r);
        }
        return count;
    }
    private int merge(int nums[], int l, int mid, int r) {
        List<Integer> temp = new ArrayList<>();
        int low = l, high = mid + 1, count = 0;
        while (low <= mid && high <= r) {
            if ((long) nums[low] > 2 * (long) nums[high]) {
                count += mid - low + 1;
                high++;
            } else {
                low++;
            }
        }
        low = l;
        high = mid + 1;
        while (low <= mid && high <= r) {
            if (nums[low] <= nums[high]) {
                temp.add(nums[low++]);
            } else {
                temp.add(nums[high++]);
            }
        }
        while (low <= mid) {
            temp.add(nums[low++]);
        }
        while (high <= r) {
            temp.add(nums[high++]);
        }
        for (int i = 0; i < temp.size(); i++) {
            nums[i + l] = temp.get(i);
        }
        return count;
    }
}