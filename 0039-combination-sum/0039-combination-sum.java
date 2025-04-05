class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        solve(candidates, target, 0, 0, new ArrayList<>(), ans);
        return ans;
    }
    private void solve(int arr[], int t, int sum, int idx, List<Integer> curr, List<List<Integer>> ans) {
        if (sum == t) {
            ans.add(new ArrayList<>(curr));
            return;
        }
        if (sum > t || idx >= arr.length) {
            return;
        }
        curr.add(arr[idx]);
        solve(arr, t, sum + arr[idx], idx, curr, ans);
        curr.remove(curr.size() - 1);
        solve(arr, t, sum, idx + 1, curr, ans);
    }
}