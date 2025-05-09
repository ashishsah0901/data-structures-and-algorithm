class Node {
    Node links[];
    Node() {
        links = new Node[2];
    }
    boolean containsKey(int bit) {
        return links[bit] != null;
    }
    void put(int bit, Node node) {
        links[bit] = node;
    }
    Node get(int bit) {
        return links[bit];
    }
}
class Trie {
    Node root;
    Trie() {
        root = new Node();
    }
    void insert(int num) {
        Node node = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (!node.containsKey(bit)) {
                node.put(bit, new Node());
            }
            node = node.get(bit);
        }
    }
    int getMax(int num) {
        Node node = root;
        int maxNum = 0;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (node.containsKey(1 - bit)) {
                maxNum |= (1 << i);
                node = node.get(1 - bit);
            } else {
                node = node.get(bit);
            }
        }
        return maxNum;
    }
}
class Solution {
    public int findMaximumXOR(int[] nums) {
        Trie trie = new Trie();
        for (int it : nums) {
            trie.insert(it);
        }
        int maxi = 0;
        for (int it : nums) {
            maxi = Math.max(maxi, trie.getMax(it));
        }
        return maxi;
    }
}