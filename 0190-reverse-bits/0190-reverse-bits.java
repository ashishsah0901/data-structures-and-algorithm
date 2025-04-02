public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        int count = 0;
        while (n != 0 && count < 32) {
            if ((n & 1) == 1) {
                res = (res | (1 << (31 - count)));
            }
            n = n >> 1;
            count++;
        }
        return res;
    }
}