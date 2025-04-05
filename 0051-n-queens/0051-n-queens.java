class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        char board[][] = new char[n][n];
        for (char[] arr: board) {
            Arrays.fill(arr, '.');
        }
        get(n, board, 0, ans);
        return ans;
    }
    private void get(int n, char board[][], int row, List<List<String>> ans) {
        if (row == n) {
            List<String> curr = new ArrayList<>();
            for(char[] currRow: board) {
                curr.add(new String(currRow));
            }
            ans.add(curr);
            return;
        }
        for (int idx = 0; idx < n; idx++) {
            if (isValid(board, row, idx)) {
                board[row][idx] = 'Q';
                get(n, board, row + 1, ans);
                board[row][idx] = '.';
            }
        }
    }
    private boolean isValid(char board[][], int i, int j) {
        for (int idx = 0; idx < i; idx++) {
            if (board[idx][j] == 'Q') {
                return false;
            } 
        }
        int newI = i - 1, newJ = j - 1;
        while (newI >= 0 && newJ >= 0) {
            if (board[newI--][newJ--] == 'Q') {
                return false;
            }
        }
        newI = i - 1;
        newJ = j + 1;
        while (newI >= 0 && newJ < board[0].length) {
            if (board[newI--][newJ++] == 'Q') {
                return false;
            }
        }
        return true;
    }
}