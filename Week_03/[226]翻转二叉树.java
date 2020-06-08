//翻转一棵二叉树。 
//
// 示例： 
//
// 输入： 
//
//      4
//   /   \
//  2     7
// / \   / \
//1   3 6   9 
//
// 输出： 
//
//      4
//   /   \
//  7     2
// / \   / \
//9   6 3   1 
//
// 备注: 
//这个问题是受到 Max Howell 的 原问题 启发的 ： 
//
// 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。 
// Related Topics 树


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

/**
 * //      4
 * //   /   \
 * //  2     7
 * // / \   / \
 * //1   3 6   9
 *       4
 *     /   \
 *    7     2
 *   / \   / \
 *  6  9  1   3
 *
 * 方法一：递归
 */
/*class Solution {
    public TreeNode invertTree(TreeNode root) {
        //1结束递归
        if(root == null){
            return null;
        }
        //2.当前逻辑
        TreeNode  temp = root.left;
        root.left = root.right;
        root.right = temp;
        //3.进入下一层
        revares(root.left);
        revares(root.right);
        //处理清理当前成
        return root;
    }
}*/

import java.util.LinkedList;

/**
 * 迭代,通过队列
 *       4
 *     /   \
 *    7     2
 *   / \   / \
 *  6  9  1   3
 */
/*class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root==null) {
            return null;
        }
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode her = queue.poll();
            TreeNode temp = her.left;
            her.left = her.right;
            her.right = temp;
            if(her.left != null){
                queue.add(her.left);
            }
            if(her.right != null){
                queue.add(her.right);
            }
        }
        return root;
    }
}*/

class Solution {
    public TreeNode invertTree(TreeNode root) {
        //1结束递归
        if (root == null) {
            return null;
        }
        //2.当前逻辑
        //3.进入下一层
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        //处理清理当前成
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
