class Solution {
    public int countSymmetricIntegers(int low, int high) {
        int res = 0;
        for (int i = low; i <= high; i++) {
            if (isSymmetric(i))
                res++;
        }
        return res;
    }

    public boolean isSymmetric(int num) {
        String s = Integer.toString(num);
        int n = s.length();
        if (n % 2 == 1)
            return false;
        int leftSum = 0;
        int rightSum = 0;
        for (int i = 0; i < n / 2; i++) {
            leftSum += s.charAt(i) - '0';
            rightSum += s.charAt(n - i - 1) - '0';
        }
        return leftSum == rightSum;
    }
}