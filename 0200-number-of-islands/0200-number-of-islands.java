class Solution {
    int[][] dirs = {{0,1},{1,0},{-1,0},{0,-1}};
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int ans = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]=='1'){
                    ans++;
                    dfs(grid,i,j,n,m);
                }
            }
        }
        return ans;
    }
    void dfs(char[][] grid,int i,int j,int n,int m){
        grid[i][j] = "-1".charAt(0);
        for(int[] dir: dirs){
            int x = i + dir[0];
            int y = j + dir[1];
            if(x>=0 && x<n && y>=0 && y<m && grid[x][y]=='1'){
                dfs(grid,x,y,n,m);
            }
        }
    }
}