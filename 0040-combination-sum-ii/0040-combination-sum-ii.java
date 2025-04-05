class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> answer = new ArrayList<>();
        Arrays.sort(candidates);
        solve(0, candidates, target, new ArrayList<>(), answer);
        return answer;  
    }

    public void solve(int start, int[] candidates, int target, List<Integer> currList, List<List<Integer>> result){
        if(target == 0){
            result.add(new ArrayList<>(currList));
            return;
        }
        for(int i = start; i < candidates.length; i++){
            if(i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if(candidates[i]>target) {
                break;
            }
            currList.add(candidates[i]);
            solve(i + 1, candidates, target - candidates[i], currList, result);
            currList.remove(currList.size() - 1);
        }
    }
}