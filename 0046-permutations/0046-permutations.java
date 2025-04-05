class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        generate(ans,curr,nums);
        return ans;
    }
    void generate(List<List<Integer>> ans, List<Integer> curr, int nums[]){
        if(curr.size()==nums.length){
            ans.add(new ArrayList<>(curr));
            return;
        }
        for(int j=0;j<nums.length;j++){
            if(curr.contains(nums[j])) continue;
            curr.add(nums[j]);
            generate(ans,curr,nums);
            curr.remove(curr.size()-1);
        }
    }
}