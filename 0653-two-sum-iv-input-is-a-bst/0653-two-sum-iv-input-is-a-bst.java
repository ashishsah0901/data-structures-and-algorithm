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
    TreeNode node = null;
    boolean is = false;

    public boolean findTarget(TreeNode root, int k) {
        node = root;
        is = false;
        inorder(root, k);
        return is;
    }

    void inorder(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        if (is) {
            return;
        }
        inorder(root.left, k);
        if (k - root.val != root.val) {
            check(node, k - root.val);
        }
        if (is) {
            return;
        }
        inorder(root.right, k);
    }

    void check(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        if (is) {
            return;
        }
        if (root.val > k) {
            check(root.left, k);
        } else if (root.val < k) {
            check(root.right, k);
        } else {
            is = true;
            return;
        }
    }
}