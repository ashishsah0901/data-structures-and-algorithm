class Solution {

    class Edge {
        int to;
        int weight;
        public Edge(int t, int w) {
            this.to = t;
            this.weight = w;
        }
    }

    class BIT {
        int n;
        long[] arr;
        public BIT(int n) {
            this.n = n;
            arr = new long[n + 1];
        }
        void update(int i, long delta) {
            for (; i <= n; i += i & -i) {
                arr[i] += delta;
            }
        }
        void rangeUpdate(int l, int r, long delta) {
            update(l, delta);
            update(r + 1, -delta);
        }
        long query(int i) {
            long sum = 0;
            for (; i > 0; i -= i & -i) {
                sum += arr[i];
            }
            return sum;
        }
    }

    int[] tin, tout;
    long[] initDist;
    int timer = 0;
    int[] edgeWeightFromParent;
    ArrayList<Edge>[] tree;

    void dfs(int node, int parent, long dist) {
        timer++;
        tin[node] = timer;
        initDist[node] = dist;
        for (Edge e : tree[node]) {
            if (e.to == parent) {
                continue;
            }
            edgeWeightFromParent[e.to] = e.weight;
            dfs(e.to, node, dist + e.weight);
        }
        tout[node] = timer;
    }

    public int[] treeQueries(int n, int[][] edges, int[][] queries) {
        tin = new int[n + 1];
        tout = new int[n + 1];
        initDist = new long[n + 1];
        edgeWeightFromParent = new int[n + 1];
        tree = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            tree[u].add(new Edge(v, w));
            tree[v].add(new Edge(u, w));
        }
        timer = 0;
        dfs(1, 0, 0);
        BIT bit = new BIT(n);
        ArrayList<Integer> ansList = new ArrayList<>();
        for (int[] query : queries) {
            if (query[0] == 1) {
                int u = query[1], v = query[2], newWeight = query[3];
                int child;
                if (tin[u] < tin[v]) {
                    child = v;
                } else {
                    child = u;
                }
                int delta = newWeight - edgeWeightFromParent[child];
                bit.rangeUpdate(tin[child], tout[child], delta);
                edgeWeightFromParent[child] = newWeight;
            } else if (query[0] == 2) {
                int x = query[1];
                long updatedDist = initDist[x] + bit.query(tin[x]);
                ansList.add((int) updatedDist);
            }
        }
        int[] res = new int[ansList.size()];
        for (int i = 0; i < ansList.size(); i++) {
            res[i] = ansList.get(i);
        }
        return res;
    }
}