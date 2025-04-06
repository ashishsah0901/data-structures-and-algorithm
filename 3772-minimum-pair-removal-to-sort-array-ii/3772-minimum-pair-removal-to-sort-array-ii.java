class Solution {
    private class Node implements Comparable<Node> {
        long value;
        int pos;
        Node left, right;
        boolean removed;
        Node(long value, int pos) {
            this.value = value;
            this.pos = pos;
            this.left = null;
            this.right = null;
            this.removed = false;
        }

        Node (Node left, Node right) {
            this.left = left;
            this.right = right;
            this.value = left.value + right.value;
            this.pos = left.pos;
        }

        @Override
        public int compareTo(Node other) {
            if (this.value != other.value) {
                return Long.compare(this.value, other.value);
            }
            return Integer.compare(this.pos, other.pos);
        }
    }

    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        if (n <= 1)
            return 0;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(nums[i], i);
            if (i > 0) {
                nodes[i - 1].right = nodes[i];
                nodes[i].left = nodes[i - 1];
            }
        }
        Node head = nodes[0];

        int violations = 0;
        Node cur = head;
        while (cur != null && cur.right != null) {
            if (cur.value > cur.right.value) {
                violations++;
            }
            cur = cur.right;
        }

        if (violations == 0)
            return 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        cur = head;
        while (cur != null && cur.right != null) {
            pq.offer(new Node(cur, cur.right));
            cur = cur.right;
        }

        int operations = 0;

        while (violations > 0 && !pq.isEmpty()) {
            Node candidate = pq.poll();
            // Check if candidate is still valid: both nodes must not have been removed and left.right must equal right.
            if (candidate.left.removed || candidate.right.removed || candidate.left.right != candidate.right) {
                continue;
            }
            Node leftNeighbor = candidate.left.left;
            Node rightNeighbor = candidate.right.right;

            // Create a new node whose value is the sum.
            long newValue = candidate.left.value + candidate.right.value;
            Node newNode = new Node(newValue, candidate.left.pos); // new node takes left's pos so that it remains to the left in order.

            // Link the new node into the list: its left pointer becomes leftNeighbor and its right pointer becomes rightNeighbor.
            newNode.left = leftNeighbor;
            newNode.right = rightNeighbor;
            if (leftNeighbor != null) {
                leftNeighbor.right = newNode;
            } else {
                // newNode is new head.
                head = newNode;
            }
            if (rightNeighbor != null) {
                rightNeighbor.left = newNode;
            }

            // Before we merge, update the violations count:
            // The old boundaries that disappear are:
            // (leftNeighbor, candidate.left), (candidate.left, candidate.right), (candidate.right, rightNeighbor)
            int removedViolations = 0;
            if (leftNeighbor != null && (leftNeighbor.value > candidate.left.value)) {
                removedViolations++;
            }
            if (candidate.left.value > candidate.right.value) {
                removedViolations++;
            }
            if (rightNeighbor != null && (candidate.right.value > rightNeighbor.value)) {
                removedViolations++;
            }
            violations -= removedViolations;

            // Now the new boundaries after merge are:
            // (leftNeighbor, newNode) and (newNode, rightNeighbor).
            if (leftNeighbor != null && (leftNeighbor.value > newNode.value)) {
                violations++;
            }
            if (rightNeighbor != null && (newNode.value > rightNeighbor.value)) {
                violations++;
            }

            // Mark the merged nodes as removed.
            candidate.left.removed = true;
            candidate.right.removed = true;

            // Add new candidate adjacent pairs formed by the new node.
            if (newNode.left != null) {
                pq.offer(new Node(newNode.left, newNode));
            }
            if (newNode.right != null) {
                pq.offer(new Node(newNode, newNode.right));
            }

            // Increment operation count.
            operations++;
        }

        return operations;
    }
}