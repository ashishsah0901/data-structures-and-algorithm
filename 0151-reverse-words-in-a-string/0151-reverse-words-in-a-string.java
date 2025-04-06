class Solution {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        String[] splitted = s.split("\\s+");
        for(int i = splitted.length - 1; i >= 0; i--) {
            sb.append(splitted[i]);
            sb.append(' ');
        }
        return sb.toString().trim();
    }
}