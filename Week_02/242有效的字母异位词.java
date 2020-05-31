//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。 
//
// 示例 1: 
//
// 输入: s = "anagram", t = "nagaram"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s = "rat", t = "car"
//输出: false 
//
// 说明: 
//你可以假设字符串只包含小写字母。 
//
// 进阶: 
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
// Related Topics 排序 哈希表


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Arrays;
import java.util.HashMap;

/**
 * 方法1,sort，比较字符串是否相同,时间复杂度度O(nlogn) k O(1)
 * 方法2，通过哈希表，计算每个字符串中每个字符出现的次数 S ：O(n+m),K: O（n）
 * 方法3，用数组代替哈希表 O(n+m),K: O（1）
 */
/*class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()){
            return  false;
        }
        char[] st = s.toCharArray();
        char[] tt = t.toCharArray();
        Arrays.sort(st);
        Arrays.sort(tt);
        return Arrays.equals(st,tt);

    }
}*/
/*class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()){
            return  false;
        }
        Map<Character, Integer> charMap1 = new HashMap<>((int) (s.length() / 0.75F + 1.0F));
        for (char c : s.toCharArray())
            charMap1.put(c, charMap1.getOrDefault(c, 0) + 1);
        for (char c : t.toCharArray())
            charMap1.put(c, charMap1.getOrDefault(c, 0) - 1);
        for (char c : s.toCharArray()) {
            if (charMap1.get(c)!=0) {
                return false;
            }
        }
        return true;
    }
}*/

class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()){
            return  false;
        }
        int[] cur = new int[26];
        for (char c : s.toCharArray()){
             cur[c-'a']++;
        }
        for (char c : t.toCharArray()){
            cur[c -'a']--;
        }
        for (int d : cur){
            if (d !=0){
                return false;
            }
        }

        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
