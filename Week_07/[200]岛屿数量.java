//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1: 
//
// 输入:
//11110
//11010
//11000
//00000
//输出: 1
// 
//
// 示例 2: 
//
// 输入:
//11000
//11000
//00100
//00011
//输出: 3
//解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //定义:岛屿数量
    private int numLands;
    //行数,列数
    private int rows;
    private int cols;

    /**
     * 方法一：采用DFS，
     * 1 1 1 0 0
     * 1 1 0 0 0
     * 0 0 0 1 0
     * 0 0 0 0 1
     */
    public int numIslands(char[][] grid) {
        //行数,列数
        rows = grid.length;
        cols = grid[0].length;
        if(rows==0){
            return 0;
        }
        for (int i=0;i<rows;++i){
            for(int j=0;j<cols;++j){
                //若节点为0则跳过
                if(grid[i][j]=='0'){
                    continue;
                }
                //若节点为1，则遇到岛屿，将记录岛屿数加1，并将当前节点标记为0，进入dfs深度搜索，
                //查看相邻节点，是否为1，若为1则将其标记为0
                if(grid[i][j]=='1'){
                    numLands++;
                    //当前节点标记为0
                    //进入dfs深度搜索
                    dfs (grid,i,j);
                }
            }
        }
        return numLands;
    }

    private void dfs(char[][] grid ,int i,int j){
        //结束条件,遇到节点为0，或坐标越界
        if(i>=rows || j>=cols|| i<0 || j<0|| grid[i][j]=='0'){
            return;
        }
        if(grid[i][j]=='1'){
            //当前节点标记为0
            grid[i][j]='0';
            //迭代他的上下左右节点
            dfs (grid,i-1,j);
            dfs (grid,i+1,j);
            dfs (grid,i,j-1);
            dfs (grid,i,j+1);
        }
        return;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
