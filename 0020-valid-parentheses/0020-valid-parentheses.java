class Solution {
    public boolean isValid(String str) {
        Stack<Character> s = new Stack<>();
        int i = 0;
        while (i < str.length()) {
            char c = str.charAt(i);
            i++;
            if (c == ')') {
                if (s.isEmpty() || s.pop() != '(') {
                    return false;
                } else {
                    continue;
                }
            } else if (c == ']') {
                if (s.isEmpty() || s.pop() != '[') {
                    return false;
                } else {
                    continue;
                }
            } else if (c == '}') {
                if (s.isEmpty() || s.pop() != '{') {
                    return false;
                } else {
                    continue;
                }
            } else {
                s.push(c);
            }
        }
        return s.isEmpty();
    }
}