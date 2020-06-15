//您需要在二叉树的每一行中找到最大的值。 
//
// 示例： 
//
// 
//输入: 
//
//          1
//         / \
//        3   2
//       / \   \  
//      5   3   9 
//
//输出: [1, 3, 9]
// 
// Related Topics 树 深度优先搜索 广度优先搜索


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 方法一:广度优先搜索 S O(n)
 */
/*class Solution {
    public List<Integer> largestValues(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if(root == null) return res;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size= queue.size();
            int rowMax = Integer.MIN_VALUE;
            while (size -- != 0){
                TreeNode node = queue.poll();
                rowMax = Math.max(node.val,rowMax);
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
            res.add(rowMax);
        }
        return res;
    }
}*/
class Solution {
    ArrayList<Integer> res = new ArrayList<>();;
    public List<Integer> largestValues(TreeNode root) {
        if(root == null) return res;
        dfs(root,0);
        return res;
    }
    private void dfs(TreeNode root,int level){
        if(root == null){
            return;
        }
        if(res.size() == level){
            res.add(Integer.MIN_VALUE);
        }
        res.set(level,Math.max(res.get(level),root.val));
        dfs(root.left,level+1);
        dfs(root.right,level+1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
