class Solution {
    public int minimumPairRemoval(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        int ans = 0;
        while (!check(list, list.size())) {
            int pos = 0;
            int min = list.get(0) + list.get(1);
            for (int i = 1; i < list.size() - 1; i++) {
                int sum = list.get(i) + list.get(i + 1);
                if (sum < min) {
                    min = sum;
                    pos = i;
                }
            }
            list.set(pos, min);
            list.remove(pos + 1);
            ans++;
        }
        return ans;
    }

    private boolean check(List<Integer> list, int n) {
        for (int i = 1; i < n; i++) {
            if (list.get(i) < list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }
}