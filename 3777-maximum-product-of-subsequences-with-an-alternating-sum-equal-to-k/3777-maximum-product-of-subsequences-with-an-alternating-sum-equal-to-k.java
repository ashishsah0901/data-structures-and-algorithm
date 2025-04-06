class Solution {
    // Helper class to represent a state with alternating sum and next pick parity.
    // Parity: 0 means the next chosen element will be added,
    //         1 means it will be subtracted.
    static class Pair {
        int alt, parity;

        Pair(int alt, int parity) {
            this.alt = alt;
            this.parity = parity;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Pair))
                return false;
            Pair other = (Pair) o;
            return this.alt == other.alt && this.parity == other.parity;
        }

        @Override
        public int hashCode() {
            return Objects.hash(alt, parity);
        }
    }

    public int maxProduct(int[] nums, int k, int limit) {
        // --- Phase 1: DP for nonzero subsequences ---
        // We track states for subsequences that have never used zero.
        // Map: state (alt, parity) -> set of possible products (all > 0).
        Map<Pair, Set<Integer>> dpNonZero = new HashMap<>();
        // Initially, no nonempty subsequence exists.

        for (int x : nums) {
            // Build a new DP mapping for this iteration.
            Map<Pair, Set<Integer>> newDP = new HashMap<>();
            // Option to skip the current element: copy existing states.
            for (Map.Entry<Pair, Set<Integer>> entry : dpNonZero.entrySet()) {
                Pair state = entry.getKey();
                newDP.computeIfAbsent(state, key -> new HashSet<>()).addAll(entry.getValue());
            }
            // Option 1: start a new subsequence (only if x != 0)
            if (x != 0 && x <= limit) {
                // For a new subsequence, the first element is at index 0 (even) so its contribution is +x.
                Pair startState = new Pair(x, 1); // next element will be subtracted.
                newDP.computeIfAbsent(startState, key -> new HashSet<>()).add(x);
            }
            // Option 2: extend each existing subsequence (only if x != 0, to remain in nonzero branch)
            if (x != 0) {
                for (Map.Entry<Pair, Set<Integer>> entry : dpNonZero.entrySet()) {
                    Pair state = entry.getKey();
                    for (int prod : entry.getValue()) {
                        long newProd = (long) prod * x;
                        if (newProd <= limit) {
                            int newAlt = (state.parity == 0) ? state.alt + x : state.alt - x;
                            int newParity = 1 - state.parity;
                            Pair newState = new Pair(newAlt, newParity);
                            newDP.computeIfAbsent(newState, key -> new HashSet<>()).add((int) newProd);
                        }
                    }
                }
            }
            dpNonZero = newDP;
        }

        int ansNonZero = -1;
        // Among states in dpNonZero, check those with alternating sum equal to k.
        for (Map.Entry<Pair, Set<Integer>> entry : dpNonZero.entrySet()) {
            if (entry.getKey().alt == k) {
                for (int prod : entry.getValue()) {
                    ansNonZero = Math.max(ansNonZero, prod);
                }
            }
        }

        // --- Phase 2: DP for subsequences that include at least one zero ---
        // In these subsequences the final product becomes 0.
        // We'll maintain two sets:
        //   dpNotUsed: states from subsequences that have not yet used a zero.
        //   dpUsed: states from subsequences that have used at least one zero.
        Set<Pair> dpNotUsed = new HashSet<>();
        Set<Pair> dpUsed = new HashSet<>();

        // Process each number in order.
        for (int x : nums) {
            // New sets for the next iteration.
            Set<Pair> newNotUsed = new HashSet<>(dpNotUsed); // skipping x in not-used branch
            Set<Pair> newUsed = new HashSet<>(dpUsed); // skipping x in used branch

            // Starting new subsequence:
            if (x == 0) {
                // If x is zero, start a new subsequence that immediately has used zero.
                newUsed.add(new Pair(0, 1));
            } else {
                // If x is nonzero, start a new subsequence in the not-used branch.
                newNotUsed.add(new Pair(x, 1));
            }

            // Extend sequences from dpNotUsed.
            for (Pair state : dpNotUsed) {
                // If we take x:
                if (x == 0) {
                    // Taking zero converts the subsequence to used.
                    int newAlt = (state.parity == 0) ? state.alt + 0 : state.alt - 0; // remains state.alt
                    int newParity = 1 - state.parity;
                    newUsed.add(new Pair(newAlt, newParity));
                } else {
                    // x nonzero: remain in not-used.
                    int newAlt = (state.parity == 0) ? state.alt + x : state.alt - x;
                    int newParity = 1 - state.parity;
                    newNotUsed.add(new Pair(newAlt, newParity));
                }
            }
            // Extend sequences from dpUsed (already used zero, remain in used branch).
            for (Pair state : dpUsed) {
                int newAlt = (state.parity == 0) ? state.alt + x : state.alt - x;
                int newParity = 1 - state.parity;
                newUsed.add(new Pair(newAlt, newParity));
            }

            dpNotUsed = newNotUsed;
            dpUsed = newUsed;
        }

        boolean canZero = false;
        for (Pair state : dpUsed) {
            if (state.alt == k) {
                canZero = true;
                break;
            }
        }
        int ansZero = canZero ? 0 : -1;

        // Final answer is the best among nonzero subsequences and those with zero.
        int finalAns = Math.max(ansNonZero, ansZero);
        return finalAns;
    }
}