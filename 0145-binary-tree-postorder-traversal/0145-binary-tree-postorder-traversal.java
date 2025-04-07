/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        while (true) {
            if (root != null) {
                ans.add(root.val);
                st.push(root);
                root = root.right;
            } else {
                if (st.isEmpty()) {
                    break;
                }
                root = st.pop();
                root = root.left;
            }
        }
        return ans.reversed();
    }
}