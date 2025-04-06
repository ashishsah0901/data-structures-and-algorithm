class Router {
    private static class Packet {
        int s;
        int d;
        int t;

        Packet(int s, int d, int t) {
            this.s = s;
            this.d = d;
            this.t = t;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Packet))
                return false;
            Packet that = (Packet) o;
            return s == that.s &&
                    d == that.d &&
                    t == that.t;
        }

        @Override
        public int hashCode() {
            return Objects.hash(s, d, t);
        }
    }

    private static class TimeList {
        ArrayList<Integer> list;
        int idx;

        TimeList() {
            list = new ArrayList<>();
            idx = 0;
        }

        void add(int t) {
            list.add(t);
        }

        void removeFirst(int t) {
            if (idx < list.size() && list.get(idx) == t) {
                idx++;
            } else {
                int pos = Collections.binarySearch(list.subList(idx, list.size()), t);
                if (pos >= 0) {
                    list.remove(idx + pos);
                }
            }
        }

        int countInRange(int low, int high) {
            int left = lowerBound(list, low, idx, list.size());
            int right = upperBound(list, high, idx, list.size());
            return right - left;
        }

        private int lowerBound(List<Integer> list, int value, int lo, int hi) {
            int l = lo, r = hi;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (list.get(mid) < value) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            return l;
        }

        private int upperBound(List<Integer> list, int value, int lo, int hi) {
            int l = lo, r = hi;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (list.get(mid) <= value) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            return l;
        }
    }

    private int limit;
    private Deque<Packet> q;
    private Set<Packet> set;
    private Map<Integer, TimeList> map;
    public Router(int limit) {
        this.limit = limit;
        this.q = new ArrayDeque<>();
        this.set = new HashSet<>();
        this.map = new HashMap<>();
    }

    public boolean addPacket(int s, int d, int t) {
        Packet curr = new Packet(s, d, t);
        if (set.contains(curr)) {
            return false;
        }

        if (q.size() == limit) {
            Packet prev = q.pollFirst();
            set.remove(prev);
            TimeList tl = map.get(prev.d);
            if (tl != null) {
                tl.removeFirst(prev.t);
                if (tl.idx >= tl.list.size()) {
                    map.remove(prev.d);
                }
            }
        }

        q.offerLast(curr);
        set.add(curr);
        map.putIfAbsent(d, new TimeList());
        map.get(d).add(t);
        return true;
    }

    public int[] forwardPacket() {
        if (q.isEmpty()) {
            return new int[0];
        }

        Packet curr = q.pollFirst();
        set.remove(curr);
        TimeList tl = map.get(curr.d);
        if (tl != null) {
            tl.removeFirst(curr.t);
            if (tl.idx >= tl.list.size()) {
                map.remove(curr.d);
            }
        }
        return new int[] { curr.s, curr.d, curr.t };
    }

    public int getCount(int d, int startTime, int endTime) {
        TimeList tl = map.get(d);
        if (tl == null) {
            return 0;
        }
        return tl.countInRange(startTime, endTime);
    }
}

/**
 * Your Router object will be instantiated and called as such:
 * Router obj = new Router(limit);
 * boolean param_1 = obj.addPacket(s,d,t);
 * int[] param_2 = obj.forwardPacket();
 * int param_3 = obj.getCount(d,startTime,endTime);
 */