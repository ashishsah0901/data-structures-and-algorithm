class MedianFinder {

    PriorityQueue<Integer> p1;
    PriorityQueue<Integer> p2;
    boolean even;

    public MedianFinder() {
        p1 = new PriorityQueue<>();
        p2 = new PriorityQueue<>(Collections.reverseOrder());
        even = true;
    }

    public void addNum(int num) {
        if (even) {
            p1.offer(num);
            p2.offer(p1.poll());
        } else {
            p2.offer(num);
            p1.offer(p2.poll());
        }
        even = !even;
    }

    public double findMedian() {
        if (even) {
            return (p2.peek() + p1.peek()) / 2.0;
        } else {
            return p2.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */