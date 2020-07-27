//给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
// 
//
// 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回 0。 
// 所有单词具有相同的长度。 
// 所有单词只由小写字母组成。 
// 字典中不存在重复的单词。 
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。 
// 
//
// 示例 1: 
//
// 输入:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//输出: 5
//
//解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//     返回它的长度 5。
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: 0
//
//解释: endWord "cog" 不在字典中，所以无法进行转换。 
// Related Topics 广度优先搜索


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.*;

/**
 * 方法一：暴力法,使用队列
 */
/*class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordset = new HashSet<>(wordList);
        if(!wordset.contains(endWord)){
            return 0;
        }
        Deque<String> queue = new LinkedList<>();
        Set<String> viv = new HashSet<>();//合格已匹配
        char[] letter = new char[26];
        for(int i = 1;i<=26;i++){
            letter[i-1]=(char)(96+i);
        }
        queue.offer(beginWord);
        int step = 1;
        while (!queue.isEmpty()){
            ++step;
            int size = queue.size();
            while ( size-- != 0){
                char[] str = queue.poll().toCharArray();
                for (int i= 0 ;i<str.length;++i){
                    char old = str[i];
                    for(char le : letter){
                        str[i] = le;
                        String newStr = new String(str);
                        if(newStr.equals(endWord)){
                            return step;
                        }
                        if(!viv.contains(newStr) && wordset.contains(newStr)){
                            queue.offer(newStr);
                            viv.add(newStr);
                        }
                    }
                    str[i]=old;
                }
            }
        }
        return 0;
    }
}*/
/**
 * 方法二：暴力法,DFS,回溯
 */
/*class Solution {
      String eWork;
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordset = new HashSet<>(wordList);
        if(!wordset.contains(endWord)){
            return 0;
        }
        eWork = endWord;
        Set<String> viv = new HashSet<>();//合格已匹配
        char[] letter = new char[26];
        for(int i = 1;i<=26;i++){
            letter[i-1]=(char)(96+i);
        }

        return helper(beginWord,wordset,viv,letter,0);
    }
    private int helper(String beginWord,Set<String> wordset,Set<String> viv,
                       char[] letter,int level){
        if(beginWord.equals(eWork)){
            return level+1;
        }
        char[] bg = beginWord.toCharArray();
        for(int i=0;i<bg.length;++i){
            char old = bg[i];
            for (char c : letter){
                bg[i] = c;
                String newWord = String.valueOf(bg);
                if(!viv.contains(newWord) && wordset.contains(newWord)){
                    helper(newWord,wordset,viv,letter,level+1);
                }
            }
            bg[i]=old;
        }
        return level;
    }
}*/

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //将字典转化为set,提升查询速度
        Set<String> wordset = new HashSet<>(wordList);
        if(!wordset.contains(endWord)){
            return 0;
        }
        //先将可能需要匹配的字符，遍历成一个集合
        char[] letter = new char[26];
        for(int i = 1;i<=26;i++){
            letter[i-1]=(char)(96+i);
        }
        // 从两端BFS遍历要用的队列
         Deque<String> bgQue = new LinkedList<>();
         Deque<String> edQue = new LinkedList<>();
        // 两端已经遍历过的节点
         Set<String> bgSet = new HashSet<>();
         Set<String> edSet = new HashSet<>();
         bgQue.offer(beginWord);
         edQue.offer(endWord);
         bgSet.add(beginWord);
         edSet.add(endWord);
         int step=0;
         while (!bgQue.isEmpty()&&!edQue.isEmpty()){
             step++;
                if(bgQue.size()>edQue.size()){
                    //若尾端队列数小于前端，则交换位置，用尾端作为前端进行遍历
                    Deque<String> temp = edQue;
                        edQue =bgQue;
                        bgQue = temp;
                    Set<String> temp2 = edSet;
                        edSet =bgSet;
                        bgSet =temp2;
                }
                //(交换后，bg为当前端，ed为另外端，来进行遍历处理)
                int size = bgQue.size();
                while (size-- !=0){
                    char[] str = bgQue.poll().toCharArray();
                    for(int i= 0; i<str.length;++i){
                        // 保存第j位的原始字符
                        char old = str[i];
                        for(char le : letter){
                            str[i] = le;
                            String newStr = String.valueOf(str);
                            // 两端遍历相遇，结束遍历，返回step
                            if(edSet.contains(newStr)){
                                return step+1;
                            }
                            // 如果单词不在当前端已访问列表中，将其添加到队列，并标记为已访问
                            if(!bgSet.contains(newStr) && wordset.contains(newStr)){
                                bgQue.offer(newStr);
                                bgSet.add(newStr);
                            }
                        }
                        // 恢复第j位的原始字符
                        str[i] = old;
                    }
                }
         }
        return 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
