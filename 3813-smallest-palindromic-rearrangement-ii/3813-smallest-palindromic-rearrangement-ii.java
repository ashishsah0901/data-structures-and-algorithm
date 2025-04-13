import java.math.BigInteger;

class Solution {
    public String smallestPalindrome(String s, int k) {
        int n = s.length(), o = 0, ci = -1;
        int[] a = new int[26];
        for (int i = 0; i < n; i++) {
            a[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if ((a[i]&1) > 0) {
                o++;
                ci = i;
            }
        }
        if (o > 1) {
            return "";
        }
        int[] h = new int[26];
        int m = 0;
        for (int i = 0; i < 26; i++) {
            h[i] = a[i] >> 1;
            m += h[i];
        }
        BigInteger[] f = new BigInteger[m + 1];
        f[0] = BigInteger.ONE;
        for (int i = 1; i <= m; i++) {
            f[i] = f[i - 1].multiply(BigInteger.valueOf(i));
        }
        BigInteger T = f[m];
        for (int i = 0; i < 26; i++) {
            T = T.divide(f[h[i]]);
        }
        if (T.compareTo(BigInteger.valueOf(k)) < 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int r = m;
        for (int pos = 0; pos < m; pos++) {
            for (int i = 0; i < 26; i++) {
                if (h[i] == 0) {
                    continue;
                }
                BigInteger cand = T.multiply(BigInteger.valueOf(h[i])).divide(BigInteger.valueOf(r));
                if (cand.compareTo(BigInteger.valueOf(k)) < 0) {
                    k -= cand.intValue();
                } else {
                    sb.append((char) ('a' + i));
                    T = cand;
                    h[i]--;
                    r--;
                    break;
                }
            }
        }
        String first = sb.toString();
        String cent = ((n&1) == 1 && ci != -1) ? "" + (char) ('a' + ci) : "";
        String rev = new StringBuilder(first).reverse().toString();
        return first + cent + rev;
    }
}