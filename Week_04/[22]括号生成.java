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
/*class Solution {
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
}*/

/**
 * 方法一：递归(深度优先)
 */
/*class Solution {
    ArrayList<String> list = new ArrayList<>(); ;
    public List<String> generateParenthesis(int n) {
        _generate(0,0,n ,"");
        return list;
    }
  private void _generate(int left,int right,int n,String s){
        //1.递归结束条件
        if(left==n && right == n){//当左右括号，都达到括号对数
            list.add(s);
            return;
        }
        //2。处理逻辑

        //3.进入一层
          if(left<n){//左括号括号对数
            _generate(left+1,right,n,s+"(");
          }
          if(right<left){//右括号小于左   括号
              _generate(left,right+1,n,s+")");
          }
        //4.清理当前层
  }
}*/

/**
 * 方法二 ，回溯法
 */
class Solution {
    ArrayList<String> list;
    Integer len;
    public List<String> generateParenthesis(int n) {
            list = new ArrayList<>();
            len = 2*n;
            back("",n,0);
            return list;
    }
    private void back(String s ,int l , int r){
        //1.满足条件
        if(s.length()==len){
            list.add(s);
            return;
        }
        if(l>0){
            back(s+"(" ,l-1 , r+1);//左括号使用一个，对应的右括号可使用量增加一个
        }
        if(r>0){
            back(s+")" ,l , r-1);
        }
        return;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
