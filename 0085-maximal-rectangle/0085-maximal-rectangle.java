class Solution {
    public int maximalRectangle(char[][] mat) {
        int ans = 0;
        int curr[] = new int[mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == '0') {
                    curr[j] = 0;
                } else {
                    curr[j] += mat[i][j] - '0';
                }
            }
            ans = Math.max(ans, solve(curr));
        }
        return ans;
    }
    private int solve(int arr[]) {
        int n = arr.length, ans = 0;
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i <= n; i++) {
            int curr = i < n ? arr[i] : 0;
            while (!st.isEmpty() && curr < arr[st.peek()]) {
                int prev = st.pop();
                int width = st.isEmpty() ? i : i - st.peek() - 1;
                ans = Math.max(ans, arr[prev] * width);
            }
            st.push(i);
        }
        return ans;
    }
}