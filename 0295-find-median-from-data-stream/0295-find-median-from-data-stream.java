class MedianFinder {

    PriorityQueue<Integer> min;
    PriorityQueue<Integer> max;

    public MedianFinder() {
        min = new PriorityQueue<>();
        max = new PriorityQueue<>(Collections.reverseOrder());
    }

    public void addNum(int num) {
        if (min.size() == 0 && max.size() == 0)
            max.add(num);
        else if (max.size() > min.size()) {
            if (max.peek() > num) {
                min.offer(max.poll());
                max.offer(num);
            } else {
                min.offer(num);
            }
        } else {
            if (num <= max.peek()) {
                max.offer(num);
            } else {
                min.offer(num);
                max.offer(min.poll());
            }
        }
    }

    public double findMedian() {
        if (max.size() > min.size()) {
            return max.peek();
        } else {
            return (max.peek() + min.peek()) / 2.0;
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */