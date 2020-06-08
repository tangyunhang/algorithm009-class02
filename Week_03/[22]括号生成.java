//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例： 
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
// 
// Related Topics 字符串 回溯算法


import java.util.ArrayList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    ArrayList<String> list;
    public List<String> generateParenthesis(int n) {
        list = new ArrayList<>();
        _generate(0,0,n,"");
        return list;
    }

    public void _generate(int l,int r,int n,String s){
        if(l== n && r==n){
            list.add(s);
            return;
        }

        if(l<n){
            _generate(l+1,r,n,s+"(");
        }
        if(r<l){
            _generate(l,r+1,n,s+")");
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
