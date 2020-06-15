//一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。 
//
// 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。 
//
// 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。 
//
// 与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。 
//
// 现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变
//化次数。如果无法实现目标变化，请返回 -1。 
//
// 注意: 
//
// 
// 起始基因序列默认是合法的，但是它并不一定会出现在基因库中。 
// 所有的目标基因序列必须是合法的。 
// 假定起始基因序列与目标基因序列是不一样的。 
// 
//
// 示例 1: 
//
// 
//start: "AACCGGTT"
//end:   "AACCGGTA"
//bank: ["AACCGGTA"]
//
//返回值: 1
// 
//
// 示例 2: 
//
// 
//start: "AACCGGTT"
//end:   "AAACGGTA"
//bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
//
//返回值: 2
// 
//
// 示例 3: 
//
// 
//start: "AAAAACCC"
//end:   "AACCCCCC"
//bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
//
//返回值: 3
// 
//


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * //start: "AACCGGTT"
 * //end:   "AAACGGTA"
 * //bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
 */
/*class Solution {
    public int minMutation(String start, String end, String[] bank) {
        HashSet set = new HashSet(Arrays.asList(bank));
        //1.判断边界(1如果开始值，目标值为空，返回-1；若目标值不在基因库返回-1)
        if(null == start || "".equals(start)||null == end || "".equals(end))return -1;
        if(!set.contains(end)) return -1;
        //2定义可变元素，及初始化步骤
        char[] eml = new char[]{'A','C','G','T'};
        int step = 0;
        if (set.contains(start)) set.remove(start);
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()){// 每层变化一个位置
            ++step;//记录变换次数(进入下一层)
            int size = queue.size();//当前层，所有可能
            for (int d=0;d<size;++d){
                char[] temStr = queue.poll().toCharArray();//将起始数据从队列中取出，转为字符数组
                //循环遍历替换起始数据中的每一个元素（多种情况），查看是否为目标数据或在基因库中
                for (int i = 0 ; i< temStr.length ;++i){
                    char oleTemS = temStr[i];
                    for(int j =0;j<4;++j){
                        temStr[i] = eml[j];
                        String newStr = new String(temStr);
                        if(end.equals(newStr)){
                            //如果替换后结果为目标值则返回变换次数
                            return step;
                        }else if(set.contains(newStr)){
                            //若替换后的数据在基因库中，则说明当前为变化数据，将当前变化数据加入队列，并从基因库中去除
                            queue.offer(newStr);
                            set.remove(newStr);
                        }else {
                            temStr[i] = oleTemS;
                        }
                    }
                }
            }
        }
        return -1;
    }
}*/
/*class Solution {
    public int minMutation(String start, String end, String[] bank) {
        if("".equals(start)||null == start || "".equals(end)||null==end) return -1;
        HashSet<String> set = new HashSet<>(Arrays.asList(bank));
        if(!set.contains(end)) return -1;
        if(set.contains(start)) set.remove(start);
        char[] car = new char[]{'A','C','G','T'};
        Stack<String> queue = new Stack<>();
        queue.push(start);
        int step = 0;
        while (!queue.isEmpty()){
            step++;
            int size = queue.size();
            while (size!=0){
                 size--;
                 char[] str = queue.pop().toCharArray();
                 for (int i = 0;i<str.length;++i){
                     char ole = str[i];
                     for (int j=0;j<4;++j){
                         str[i]=car[j];
                         String newStr = new String(str);
                         if(end.equals(newStr))return step;
                         if (set.contains(newStr)){
                             queue.push(newStr);
                             set.remove(newStr);
                         }
                     }
                     str[i]=ole;
                 }
            }
        }
        return -1;
    }
}*/
class Solution {
    int minStep = Integer.MAX_VALUE;
    char[] eml = new char[]{'A','C','G','T'};
    public int minMutation(String start, String end, String[] bank) {
        HashSet set = new HashSet(Arrays.asList(bank));
        //1.判断边界(1如果开始值，目标值为空，返回-1；若目标值不在基因库返回-1)
        if(null == start || "".equals(start)||null == end || "".equals(end))return -1;
        if(!set.contains(end)) return -1;
        if (set.contains(start)) set.remove(start);
        help(start,end,0,set);
        return minStep == Integer.MAX_VALUE ? -1 : minStep;
    }

    private void help(String start, String end,int step ,HashSet set){
        //1.结束递归条件(当变换后的字符串等于目标数组，结束迭代)
        if(start.equals(end)){
            minStep = Math.min(step,minStep);
            return;
        }
        //将字符串转化为数组，方便替换
        char[] stch = start.toCharArray();
        for (int i=0;i<stch.length;++i){
             char old = stch[i];//保留原始字段，在当前字符替换为所有可能校验后还原（只替换一位元素）
            for(int j =0 ;j<eml.length;++j){
                stch[i] = eml[j];
                String newStart = new String(stch);
                if(set.contains(newStart)){//校验替换后字符串是否在基因库中，若存在则进入下一层
                    set.remove(newStart);
                    help(newStart,end,step+1,set);
                }
            }
            stch[i] = old;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
