//给定一个二叉树，返回它的中序 遍历。 
//
// 示例: 
//
// 输入: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//输出: [1,3,2] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 哈希表


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
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 中序：左根右
 * 方法一 ，递归,s O（n）  k 最坏，成联表O(n),平均O(logn)
 * 方法二，使用栈
 */
/*class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        helper(root,list);
        return list;

    }

    private void helper (TreeNode root,List<Integer> list){
        if(root != null){
            if(root.left != null){
                helper(root.left ,list);
            }
            list.add(root.val);
            if(root.right != null){
                helper(root.right ,list);
            }
        }

    }
}*/

/**
 *        6
 *      /  \
 *    4     5
 *   /\     \
 *  3  6    7
 *  6,4,5,3,6,-1,7
 *使用，栈 s O(n) k O(n)
 * 思路：从根节点开始，左孩子一个个压入栈，等到节点的左孩子为null,那节点为最左子节点
 * 把栈顶节点取出来放入数组，并将该节点的右孩子
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root !=null|| !stack.isEmpty()){
            while (root != null ){
                stack.push(root);
                root = root.left;
                //当不在存在左孩子节点，跳出循环
            }
            root = stack.pop();//弹出栈顶元素
            list.add(root.val);
            root = root.right;//寻找右孩子节点
        }
        return list;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
