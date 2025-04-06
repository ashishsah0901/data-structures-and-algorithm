class Solution {
    public int myAtoi(String s) {
        return count(s, 0, 0, 0);
    }
    private int count(String s, int i, int sign, int res) {
        if (i >= s.length()) {
            return res * sign;
        }
        char c = s.charAt(i);
        if (sign == 0) {
            if (c == ' ') return count(s, i + 1, 0, res);
            else if (c == '+') return count(s, i + 1, 1, res);
            else if (c == '-') return count(s, i + 1, -1, res);
            else if (Character.isDigit(c)) return count(s, i, 1, res);
            else return 0;
        }
        if (Character.isDigit(c)) {
            int val = c - '0';
            if (res > (Integer.MAX_VALUE - val) / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            } else {
                res = (res * 10) + val;
                return count(s, i + 1, sign, res);
            }
        }
        return res * sign;
    }
}