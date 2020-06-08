//给定一个二叉树，找出其最大深度。 
//
// 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例： 
//给定二叉树 [3,9,20,null,null,15,7]， 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回它的最大深度 3 。 
// Related Topics 树 深度优先搜索


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

import javax.swing.tree.TreeNode;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Stack;

/**
 * 方法一：
 */
/*class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int leftH = maxDepth(root.left);
        int rightH = maxDepth(root.right);
        return Math.max(leftH,rightH)+1;
    }
}*/

/**
 *
 */
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int l = 0;
        while (!queue.isEmpty()){
               l++;
                //循环将当前一层的节点都取出来，保证当次循环结束，队列中只包含同一层节点
                int size= queue.size();
               for(int i = 0 ;i<size;++i){
                   TreeNode cur = queue.poll();
                   if(cur.left != null)    queue.offer(cur.left);
                   if(cur.right != null)   queue.offer(cur.right);
               }
        }
        return l;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
