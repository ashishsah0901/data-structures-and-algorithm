class MedianFinder {

    PriorityQueue<Integer> p1;
    PriorityQueue<Integer> p2;

    public MedianFinder() {
        p1 = new PriorityQueue<>();
        p2 = new PriorityQueue<>(Collections.reverseOrder());
    }

    public void addNum(int num) {
        if (p2.size() == 0 || p2.peek() > num) {
            p2.offer(num);
        } else {
            p1.offer(num);
        }
        if (Math.abs(p1.size() - p2.size()) > 1) {
            balance(p1, p2);
        }
    }

    private void balance(PriorityQueue<Integer> p1,PriorityQueue<Integer>p2) {
        PriorityQueue<Integer> large = p1.size() > p2.size() ? p1 : p2;
        PriorityQueue<Integer> small = p1.size() > p2.size() ? p2 : p1;
        small.offer(large.poll());
    }

    public double findMedian() {
        PriorityQueue<Integer> large = p1.size() > p2.size() ? p1 : p2;
        PriorityQueue<Integer> small = p1.size() > p2.size() ? p2 : p1;
        if(large.size() == small.size()) {
            return (double)(large.peek() + small.peek()) / 2;
        } else {
            return large.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */