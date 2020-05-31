//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。 
//
// 示例: 
//
// 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//] 
//
// 说明： 
//
// 
// 所有输入均为小写字母。 
// 不考虑答案输出的顺序。 
// 
// Related Topics 哈希表 字符串

import java.util.Arrays;
import java.util.HashMap;

/**
 *方法1 ：哈希表，将所有字符串排序后比较 s O(NKlogK) k O（NK）
 */
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0 ){
            return new ArrayList();
        }
        HashMap<String ,List<String>> hashMap = new HashMap<String, List<String>>();
        for(String str : strs){
            char[] sc = str.toCharArray();
            Arrays.sort(sc);
            String nst = String.valueOf(sc);
            if (!hashMap.containsKey(nst)){
                hashMap.put(nst,new ArrayList());
            }
            hashMap.get(nst).add(str);
        }
        return new ArrayList(hashMap.values());

    }
}
//leetcode submit region end(Prohibit modification and deletion)
