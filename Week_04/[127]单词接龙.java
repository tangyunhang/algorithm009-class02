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
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)){
            return 0;
        }
        //已经访问过的结点
        Set<String> visited = new HashSet<>();
        //当前要访问结点存进的队列
        Queue<String> queue =new LinkedList<>();
        queue.offer(beginWord);
        visited.add(beginWord);
        int count=0;
        while(queue.size()>0){
            int size=queue.size();
            ++count;
            //在当前要访问结点存进的队列里开始逐个检验，并且把结点的下一层放进队列里，BFS
            for(int i=0;i<size;i++){
                //每次都是最新要比较的单词 start
                String start=queue.poll();
                //因为有的单词已经遍历过，有的单词不满足转换会跳过，所以整个wordList遍历一次
                for(String s:wordList){
                    //已遍历过，跳过看下一个单词
                    if(visited.contains(s)){
                        continue;
                    }
                    //不能转换一个字母变成这个单词的，也跳过
                    if(!canConvert(start,s)){
                        continue;
                    }
                    //没遍历过，又能转换的，当是最后转换成endWord时
                    if(s.equals(endWord)){
                        return ++count;
                    }
                    //没遍历过，又能转换的，还没到最后的end的
                    //存进visited，放入队列之后搜索
                    visited.add(s);
                    queue.offer(s);
                }
            }
        }
        return 0;
    }
    public boolean canConvert(String s1,String s2){
        if(s1.length()!=s2.length()){
            return false;
        }
        int count=0;
        for(int i=0;i<s1.length();i++){
            if(s1.charAt(i)!=s2.charAt(i)){
                count++;
                if(count>1){
                    return false;
                }
            }
        }
        return true;
    }
}

