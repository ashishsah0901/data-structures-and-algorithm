class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char temp[] = s.toCharArray();
            Arrays.sort(temp);
            String sortedStr = new String(temp);
            if (!map.containsKey(sortedStr)) {
                map.put(sortedStr, new ArrayList<>());
            }
            map.get(sortedStr).add(s);
        }
        List<List<String>> ans = new ArrayList<>();
        for (List<String> values : map.values()) {
            ans.add(values);
        }
        return ans;
    }
}