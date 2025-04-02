class Solution {
    public int maxArea(int[] height) {
        int max = 0, n = height.length;
        int i = 0, j = n - 1;
        while (i < j) {
            int h1 = height[i];
            int h2 = height[j];
            int ans = Math.min(h1, h2) * Math.abs(i - j);
            max = Math.max(ans, max);
            if (h1 < h2) {
                i++;
            } else {
                j--;
            }
        }
        return max;
    }
}