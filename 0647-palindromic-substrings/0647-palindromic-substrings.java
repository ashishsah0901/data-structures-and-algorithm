class Solution {
    public int countSubstrings(String str) {
        int n = str.length();
        int count = 0;
        boolean table[][] = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            table[i][i] = true;
            count++;
        }
        for (int i = 0; i < n - 1; ++i) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                table[i][i + 1] = true;
                count++;
            }
        }
        for (int k = 3; k <= n; ++k) {
            for (int i = 0; i < n - k + 1; ++i) {
                int j = i + k - 1;
                if (table[i + 1][j - 1]
                    && str.charAt(i) == str.charAt(j)) {
                    table[i][j] = true;
                    count++;
                }
            }
        }
        return count;
    }
}