class Solution {
    private static class Node {
        long value; // use long so that sums don’t overflow
        int pos; // "position" used for tie–breaking (the leftmost node is smaller)
        Node left, right;
        boolean removed; // mark if this node was removed

        Node(long value, int pos) {
            this.value = value;
            this.pos = pos;
            this.left = null;
            this.right = null;
            this.removed = false;
        }
    }

    // Candidate adjacent–pair for merging.
    // It stores a pointer to left node and right node, along with the sum and the left node’s pos.
    private static class PairCandidate implements Comparable<PairCandidate> {
        long sum; // sum = left.value + right.value
        int pos; // left node position (to break ties)
        Node left, right;

        PairCandidate(Node left, Node right) {
            this.left = left;
            this.right = right;
            this.sum = left.value + right.value;
            this.pos = left.pos;
        }

        // Compare first by sum, then by left node’s pos.
        @Override
        public int compareTo(PairCandidate other) {
            if (this.sum != other.sum) {
                return Long.compare(this.sum, other.sum);
            }
            return Integer.compare(this.pos, other.pos);
        }
    }

    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;

        // If only one element, array is trivially non-decreasing.
        if (n <= 1)
            return 0;

        // Create doubly linked list nodes corresponding to the array
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(nums[i], i);
            if (i > 0) {
                nodes[i - 1].right = nodes[i];
                nodes[i].left = nodes[i - 1];
            }
        }
        Node head = nodes[0];

        // Count the number of adjacent violations (left > right)
        int violations = 0;
        Node cur = head;
        while (cur != null && cur.right != null) {
            if (cur.value > cur.right.value) {
                violations++;
            }
            cur = cur.right;
        }

        // If there are no violations, array is already non-decreasing.
        if (violations == 0)
            return 0;

        // PriorityQueue for candidate merges.
        PriorityQueue<PairCandidate> pq = new PriorityQueue<>();
        cur = head;
        while (cur != null && cur.right != null) {
            pq.offer(new PairCandidate(cur, cur.right));
            cur = cur.right;
        }

        int operations = 0;

        // We simulate until the list becomes non-decreasing (violations==0)
        while (violations > 0) {
            // Poll the candidate with the smallest sum (and leftmost in case of tie)
            PairCandidate candidate = pq.poll();
            if (candidate == null) {
                // In theory, we should always have a candidate if there is more than one element.
                break;
            }
            // Check if candidate is still valid: both nodes must not have been removed and left.right must equal right.
            if (candidate.left.removed || candidate.right.removed || candidate.left.right != candidate.right) {
                continue; // skip obsolete candidate
            }

            // For the valid candidate, do the merge.
            // Let L be candidate.left.left and R be candidate.right.right.
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
                pq.offer(new PairCandidate(newNode.left, newNode));
            }
            if (newNode.right != null) {
                pq.offer(new PairCandidate(newNode, newNode.right));
            }

            // Increment operation count.
            operations++;
        }

        return operations;
    }
}