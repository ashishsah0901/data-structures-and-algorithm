class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        gen(nums, 0, new ArrayList<>(), ans);
        return ans;
    }
    private void gen(int arr[], int i, List<Integer> curr, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(curr));
        for (int j = i; j < arr.length; j++) {
            if (i != j && arr[j] == arr[j - 1]) {
                continue;
            }
            curr.add(arr[j]);
            gen(arr, j + 1, curr, ans);
            curr.remove(curr.size() - 1);
        }
    }
}