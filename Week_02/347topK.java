/**
 * 方法一，hash表，以值为key,次数为value,维护k 大小的一个小顶堆，遍历map,将最大的k个元素放进去
 */

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        if (nums.length==0) return new int[0];
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i : nums){
            map.put(i,map.getOrDefault(i,0)+1);
        }
        PriorityQueue<Integer> par = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return map.get(a) - map.get(b);
            }
        });
        //遍历map
        /*for (Integer key : map.keySet()){
            if(par.size()<k){
                par.offer(key);
            }else if (map.get(key)>map.get(par.peek())){
                    par.poll();
                    par.offer(key);
            }
        }*/
        //遍历map
        for (Integer key : map.keySet()){
            par.offer(key);
            if(par.size()>k){
                par.poll();
            }
        }
        // 取出最小堆中的元素
        int[] res = new int[k];
        for (int i =0;i<k;++i){
            res[i]=par.poll();
        }
        return res;
    }
}