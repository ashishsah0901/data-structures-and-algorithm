class Solution {
    public String smallestPalindrome(String s) {
        int[] f = new int[26];
        for (char c : s.toCharArray()) {
            f[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        String mid = "";
        for (int i = 0; i < 26; i++) {
            if (f[i] % 2 == 1) {
                mid = "" + (char) (i + 'a');
            }
            for (int j = 0; j < f[i] / 2; j++) {
                sb.append((char) (i + 'a'));
            }
        }
        return sb.toString() + mid + sb.reverse().toString();
    }
}