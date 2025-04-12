class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int M = 2048;
        long[] f = new long[M];
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            set.add(x);
        }
        for (int x : set) {
            f[x] = 1;
        }
        func(f, false);
        for (int i = 0; i < M; i++) {
            long t = f[i];
            f[i] = t * t * t;
        }
        func(f, true);
        int cnt = 0;
        for (long x : f) {
            if (x != 0) {
                cnt++;
            }
        }
        return cnt;
    }

    void func(long[] a, boolean inv) {
        int n = a.length;
        for (int len = 1; len < n; len <<= 1) {
            for (int i = 0; i < n; i += (len << 1)) {
                for (int j = 0; j < len; j++) {
                    long u = a[i + j];
                    long v = a[i + j + len];
                    a[i + j] = u + v;
                    a[i + j + len] = u - v;
                }
            }
        }
        if (inv) {
            for (int i = 0; i < n; i++) {
                a[i] /= n;
            }
        }
    }
}