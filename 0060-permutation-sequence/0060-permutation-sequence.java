class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> list = new LinkedList<>();
        int base = 1;
        k--;
        for (int i = 0; i < n; i++) {
            list.add(i + 1);
            base *= i + 1;
        }
        StringBuilder sbr = new StringBuilder();
        while (n > 0) {
            base /= n;
            int pos = k / base;
            sbr.append(list.get(pos));
            list.remove(pos);
            k %= base;
            n--;
        }
        return sbr.toString();
    }
}