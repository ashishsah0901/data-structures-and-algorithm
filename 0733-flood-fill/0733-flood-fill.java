class Solution {
    void solve(int[][] image, int n, int m, int i, int j, int color, int org, int[][] vis) {
        if (i >= n || j >= m)
            return;
        image[i][j] = color;
        vis[i][j] = 1;
        if (i > 0 && image[i - 1][j] == org && vis[i - 1][j] == 0) {
            solve(image, n, m, i - 1, j, color, org, vis);
        }
        if (j > 0 && image[i][j - 1] == org && vis[i][j - 1] == 0) {
            solve(image, n, m, i, j - 1, color, org, vis);
        }
        if (i < n - 1 && image[i + 1][j] == org && vis[i + 1][j] == 0) {
            solve(image, n, m, i + 1, j, color, org, vis);
        }
        if (j < m - 1 && image[i][j + 1] == org && vis[i][j + 1] == 0) {
            solve(image, n, m, i, j + 1, color, org, vis);
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int n = image.length;
        int m = image[0].length;
        int[][] vis = new int[n][m];
        int org = image[sr][sc];
        solve(image, n, m, sr, sc, color, org, vis);
        return image;
    }
}