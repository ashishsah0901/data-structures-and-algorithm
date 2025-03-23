class Solution {
    public int longestStrChain(String[] words) {
        int n = words.length, maxI = 0;
        int dp[] = new int[n];
        Arrays.sort(words, (s, t) -> s.length() - t.length());
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (words[i].length() - words[j].length() > 1) {
                    break;
                }
                if (helper(words[j], words[i]) && 1 + dp[j] > dp[i]) {
                    dp[i] = 1 + dp[j];
                }
            }
            if (dp[i] > dp[maxI]) {
                maxI = i;
            }
        }
        return 1 + dp[maxI];
    }

    boolean helper(String s1, String s2) {
        int i = s1.length() - 1, j = s2.length() - 1;
        if (i == j)
            return false;
        while (i >= 0 && j >= 0) {
            if (s1.charAt(i) != s2.charAt(j)) {
                j--;
            } else {
                i--;
                j--;
            }
        }
        return i == j || j == 0;
    }
}