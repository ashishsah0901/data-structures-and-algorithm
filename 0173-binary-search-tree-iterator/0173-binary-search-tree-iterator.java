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
class BSTIterator {

    List<Integer> ans = new ArrayList<>();
    int counter;

    public BSTIterator(TreeNode root) {
        create(root);
        counter = 0;
    }

    void create(TreeNode root) {
        if (root == null) {
            return;
        }
        create(root.left);
        ans.add(root.val);
        create(root.right);
    }

    public int next() {
        return ans.get(counter++);
    }

    public boolean hasNext() {
        return counter < ans.size();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */