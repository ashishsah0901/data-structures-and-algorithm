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
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return helper(root, depth(root));
    }
    private int depth(TreeNode n) {                                     // compute depth of the tree.
        return n == null ? 0 : 1 + Math.max(depth(n.left), depth(n.right));
    }
    private TreeNode helper(TreeNode n, int d) {
        if (n == null) return null;                                     // base case 1: not deepest leaf.
        if (d == 1) return n;                                           // base case 2: deepest leaf.
        TreeNode L = helper(n.left, d - 1), R = helper(n.right, d - 1); // general case: recurse to children.
        if (L != null && R != null) return n;                           // both children have the deepest leaves.
        return L == null ? R : L;                                       // only 1 child has the deepest leaf.
    }
}