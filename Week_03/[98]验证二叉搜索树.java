//给定一个二叉树，判断其是否是一个有效的二叉搜索树。 
//
// 假设一个二叉搜索树具有如下特征： 
//
// 
// 节点的左子树只包含小于当前节点的数。 
// 节点的右子树只包含大于当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 示例 1: 
//
// 输入:
//    2
//   / \
//  1   3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//    5
//   / \
//  1   6
//     / \
//    3   7
//输出: false
//解释: 输入为: [5,1,4,null,null,3,6]。
//     根节点的值为 5 ，但是其右子节点值为 4 。
// 
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

/**
 * 方法一 递归
 * 思路：1.节点的左子树只包含小于当前节点的数,节点的右子树只包含大于当前节点的数
 * 设置一个上界，和一个下界，左节点比较是否小于上界；右节点是否大于下界
 */
/*class Solution {
    public boolean isValidBST(TreeNode root) {
       // return root != null;
         return isValid(root,null,null);
    }

    private boolean isValid(TreeNode root,Integer max,Integer min){
        if(root == null){
            return true;
        }
        //检验左子节点是否大于上界
        if(max != null && root.val>=  max)return false;
        //检验右子节点是否小于上界
        if(min != null && root.val<= min)return false;
        //检验左子树
        if(!isValid(root.left,root.val,min)) return false;
        //检验右子树
        if(!isValid(root.right,max,root.val)) return false;

        return true;
    }
}*/

import java.util.LinkedList;

/**
 * 方法二 ，因为二叉搜索树特性，中序遍历，wei升序,建辅助栈
 * [5,1,4,null,null,3,6]
 */
/*class Solution {
    public boolean isValidBST(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        int lastVal = Double.MIN_EXPONENT;
        stack.push(root);
        while (!stack.isEmpty() || root != null){
                //先将所有左节点放入栈中
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            //如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (root.val<lastVal) return false;
            lastVal = root.val;
            root = root.right;
        }
        return true;
    }
}*/
/*class Solution {
    int per = Double.MIN_EXPONENT;
    public boolean isValidBST(TreeNode root) {
        return isValid(root);
    }
    private boolean isValid(TreeNode root){
            if(root == null){
                return true;
            }
            if(!isValid(root.left)) return false;
            if(root.val<= per) return false;
            per = root.val;
            return isValid(root.right);
    }
}*/

/*class Solution {
    int per = Double.MIN_EXPONENT;
    public boolean isValidBST(TreeNode root) {
        return isValid(root);
    }
    private boolean isValid(TreeNode root){
        if(root == null){
            return true;
        }
        if(!isValid(root.left)) return false;
        if(root.val<= per) return false;
        per = root.val;
        return isValid(root.right);
    }
}*/

//leetcode submit region end(Prohibit modification and deletion)
