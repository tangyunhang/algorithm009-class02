//给定一个二叉树，找出其最小深度。 
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例: 
//
// 给定二叉树 [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回它的最小深度 2. 
// Related Topics 树 深度优先搜索 广度优先搜索


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Deque;
import java.util.LinkedList;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        Deque<TreeNode> que= new LinkedList<>();
        que.offer(root);
        int l =0;
        while (!que.isEmpty() ){
                l++;
                int s = que.size();
                for (int i=0;i<s;++i ){
                TreeNode cr = que.poll();
                if (cr.left == null && cr.right == null){
                    return l;
                }
                if (cr.left != null){
                    que.offer(cr.left);
                }
                if (cr.right != null){
                    que.offer(cr.right);
                }

            }
        }
        return l;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
