import java.math.BigInteger;

class Solution {
    final long MOD = 1000000007;

    public int countNumbers(String l, String r, int b) {
        long up = count(r, b);
        long low = l.equals("0") ? 0 : count(decrement(l), b);
        long ans = up - low;
        ans %= MOD;
        if (ans < 0)
            ans += MOD;
        return (int) ans;
    }

    long count(String s, int base) {
        BigInteger num = new BigInteger(s);
        if (num.compareTo(BigInteger.ZERO) < 0)
            return 0;
        ArrayList<Integer> digits = new ArrayList<>();
        BigInteger bBI = BigInteger.valueOf(base);
        if (num.equals(BigInteger.ZERO))
            digits.add(0);
        while (num.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] dr = num.divideAndRemainder(bBI);
            digits.add(dr[1].intValue());
            num = dr[0];
        }
        Collections.reverse(digits);
        int n = digits.size();
        Long[][][][] memo = new Long[n + 1][base + 1][2][2];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= base; j++) {
                for (int k = 0; k < 2; k++) {
                    for (int l = 0; l < 2; l++) {
                        memo[i][j][k][l] = -1L;
                    }
                }
            }
        }
        return dfs(0, 0, 1, 0, digits, base, memo);
    }

    long dfs(int pos, int last, int tight, int started, List<Integer> digits, int base, Long[][][][] memo) {
        if (pos == digits.size())
            return 1;
        if (memo[pos][last][tight][started] != -1)
            return memo[pos][last][tight][started];
        long res = 0;
        int limit = (tight == 1 ? digits.get(pos) : base - 1);
        for (int d = 0; d <= limit; d++) {
            if (started == 1 && d < last)
                continue;
            int nt = (tight == 1 && d == limit) ? 1 : 0;
            int nstart = (started == 1 || d != 0) ? 1 : 0;
            if (started == 0 && d == 0) {
                res = (res + dfs(pos + 1, 0, nt, 0, digits, base, memo)) % MOD;
            } else {
                res = (res + dfs(pos + 1, d, nt, 1, digits, base, memo)) % MOD;
            }
        }
        memo[pos][last][tight][started] = res;
        return res;
    }

    String decrement(String s) {
        BigInteger num = new BigInteger(s);
        num = num.subtract(BigInteger.ONE);
        return num.toString();
    }
}