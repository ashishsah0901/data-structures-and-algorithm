class Solution {
    private static boolean hasCycleDfs(ArrayList<Integer> graph[], boolean visited[], int root, boolean rec[]) {
        visited[root] = true;
        rec[root] = true;
        for (int i = 0; i < graph[root].size(); i++) {
            int neighbour = graph[root].get(i);
            if (rec[neighbour]) {
                return true;
            } else if (!visited[neighbour] && hasCycleDfs(graph, visited, neighbour, rec)) {
                return true;
            }
        }
        rec[root] = false;
        return false;
    }

    public boolean canFinish(int n, int[][] prerequisites) {
        ArrayList<Integer> graph[] = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < prerequisites.length; i++) {
            graph[prerequisites[i][0]].add(prerequisites[i][1]);
        }
        boolean visited[] = new boolean[n];
        boolean rec[] = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (hasCycleDfs(graph, visited, i, rec)) {
                    return false;
                }
            }
        }
        return true;
    }
}