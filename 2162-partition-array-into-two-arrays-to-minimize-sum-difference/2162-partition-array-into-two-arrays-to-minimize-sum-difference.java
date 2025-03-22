class Solution {
    public int minimumDifference(int[] arr) {
        int N = arr.length;
        int n = N/2;
        int total = 0;
        for(int a : arr) total += a;
        ArrayList<Integer>[] left_sum= new ArrayList[n+1]; 
        ArrayList<Integer>[] right_sum = new ArrayList[n+1];
        for(int i = 0; i <= n; i++){
            left_sum[i] = new ArrayList<>();
            right_sum[i] = new ArrayList<>();
        }
        for(int mask = 0; mask < (1<<n); mask++){
            int l_sum = 0, r_sum = 0, ct = 0;

            for(int i = 0; i < n; i++){
                if((mask & (1 << i) )!= 0){
                    ct++;
                    l_sum += arr[n-i-1];
                    r_sum += arr[i+n];
                }
            }
            left_sum[ct].add(l_sum);
            right_sum[ct].add(r_sum);
        }
        for(ArrayList<Integer> arrl : right_sum){
            Collections.sort(arrl);
        }
        int abs_diff = Integer.MAX_VALUE;
        for(int ct = 0; ct <= n; ct++){
            for(int sum : left_sum[ct]){
                int right_ct = n-ct;
                int r_sum = lower_bound(right_sum[right_ct], total/2 - sum);
                int total_sum = sum + r_sum;
                abs_diff = Math.min(abs_diff, Math.abs(total - 2*total_sum));
            }
        }
        return abs_diff;
    }
    public int lower_bound(ArrayList<Integer> arrl, int num){
        int n = arrl.size();
        int l = 0, r = n -1;
        while(l <= r){
            int mid = (l+r)/2;
            if(arrl.get(mid) == num) return num;
            if(arrl.get(mid) < num){
                l = mid+1;
            } else {
                r = mid-1;
            }
        }
        if(l != n) {
            return arrl.get(l);
        } else {
            return arrl.get(n-1);
        }
    }
}