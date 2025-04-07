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
    class Pair{
        TreeNode node;
        int depth;
        Pair(TreeNode node, int depth){
            this.node = node;
            this.depth = depth;
        }
    }
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        int ans = 0;
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));
        while(!q.isEmpty()) {
            int size = q.size();
            int first = q.peek().depth, last = first;
            for(int i = 0; i < size; i++) {
                last = q.peek().depth;
                TreeNode node = q.poll().node;
                if(node.left != null) {
                    q.offer(new Pair(node.left, last * 2 + 1));
                }
                if(node.right != null) {
                    q.offer(new Pair(node.right, last * 2 + 2));
                }
            }
            ans = Math.max(ans, last - first + 1);
        }
        return ans;
    }
}