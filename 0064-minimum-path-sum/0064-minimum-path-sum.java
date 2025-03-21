class Solution {
    public int minPathSum(int[][] grid) {
        int[] currentMinPathSum = new int[grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i == 0 && j == 0) {
                    currentMinPathSum[j] = grid[i][j];
                } else if (i == 0) {
                    currentMinPathSum[j] = currentMinPathSum[j - 1] + grid[i][j];
                } else if (j == 0) {
                    currentMinPathSum[j] = currentMinPathSum[j] + grid[i][j];
                } else {
                    currentMinPathSum[j] = Math.min(currentMinPathSum[j - 1], currentMinPathSum[j]) + grid[i][j];
                }
            }
        }
        return currentMinPathSum[grid[0].length-1];
    }
}