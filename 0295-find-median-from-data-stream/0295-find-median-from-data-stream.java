class MedianFinder {
    PriorityQueue<Integer> minHeap, maxHeap;

    public MedianFinder() {
        minHeap = new PriorityQueue<Integer>();
        maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
    }

    public void addNum(int num) {
        // Add in corresponding heap
        if (maxHeap.isEmpty() || num <= maxHeap.peek())
            maxHeap.offer(num);
        else
            minHeap.offer(num);
        // Check balance
        if (maxHeap.size() > minHeap.size() + 1)
            minHeap.offer(maxHeap.poll());
        else if (minHeap.size() > maxHeap.size() + 1)
            maxHeap.offer(minHeap.poll());
    }

    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            if (maxHeap.isEmpty())
                return 0.0;
            // Even length, take average of both peeks
            double leftMax = maxHeap.peek(), rightMin = minHeap.peek();
            return (leftMax + rightMin) / 2.0;
        }
        // Odd length
        if (maxHeap.size() > minHeap.size())
            return (double) maxHeap.peek();
        return (double) minHeap.peek();

    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */