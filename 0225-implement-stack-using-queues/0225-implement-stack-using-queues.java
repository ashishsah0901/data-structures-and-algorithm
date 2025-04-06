class MyStack {
    private Queue <Integer> queue1;
    private int size =0;
    private Queue <Integer> queue2;

    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void push(int x) {
        queue1.add(x);
    }

    public int pop() {
         while (queue1.size() > 1) {
            queue2.offer(queue1.poll());
        }
        int topElement = queue1.poll();
        queue1 = queue2;
        queue2 = new LinkedList<>();
        return topElement;
    }

    public int top() {
        while (queue1.size() > 1) {
            queue2.offer(queue1.poll());
        }
        int topElement = queue1.peek();
        queue2.offer(queue1.poll());
        queue1 = queue2;
        queue2 = new LinkedList<>();
        return topElement;
    }

    public boolean empty() {
        if(queue1.isEmpty()){
            return true;
        }
        return false;
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */