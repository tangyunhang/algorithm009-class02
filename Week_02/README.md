学习笔记
  一、哈希表
    哈希表（Hash table），也叫散列表，是根据键值对直接进行访问的数据结构。
    具体实现为把键映射到表中一个位置来访问记录，以加快查找速度，映射函数称为哈希函数或散列函数。
    如果不同的键经过哈希函数的计算得到的值相同，就产生哈希碰撞。
    解决方法：
      1.利用链表在已有数据后面插入新数据 -“链地址法”。
      2.当冲突时多次使用哈希函数，算出一个候补地址（数组上的位置）-“开放地址法”。
    如果链表过长，会影响时间复杂度。如果哈希函数设计得好，则平均时间复杂度是O(1)。
        注意：数组空间太小容易发生冲突，太大造成内存浪费，因此，给数组设置合适的空间非常重要；
    哈希表的时间复杂度：查找、插入、删除的平均时间复杂度都是O(1)，最坏情况下的时间复杂度都是O(n)。
    哈希表的空间复杂度是O(n)。
  二、映射
    Map：key-value对，key不重复。
    常用的具体类有HashMap和TreeMap。
    HashMap的key无序，各项操作的时间复杂度为O(1)。
    TreeMap的key有序，各项操作的时间复杂度为O(log n)。
  三、集合
    Set：不重复元素的集合
      常用的具体类有HashSet和TreeSet。
      HashSet无序，各项操作的时间复杂度为O(1)。
      TreeSet有序，各项操作的时间复杂度为O(log n)。
      其中hashSet底层其使用到还是HashMap
 关于HashMap的小总结
 put方法：
  public V put(K key, V value) {
    return putVal(hash(key), key, value, false, true);
}
当调用put()，首先会根据key生成一个 hash值，原理如下：
static final int hash(Object key) {
    int h;
    //key 是 null 直接返回 0
    //key 不是null，先计算key对应的hashCode，赋值给 h
    //并将 h 与 h >>> 16 做异或（相同为0 不同为1）运算 ，作为hash值返回
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
拿到了hash值后，调用 putVal()，将对象table赋值给tab，并以tab是否为空作为是否第一次调用此方法的判断，是则resize()并给tab，n赋值；
获取tab的第i个元素：根据 (n - 1) & hash 算法 ，计算出i找到，如果为空，调用newNode() ，赋值给tab第i个；
如果不为空，可能存在2种情况：hash值重复了，也就是put过程中，发现之前已经有了此key对应的value，则暂时e = p；
至于另外一种情况就是位置冲突了，即根据(n - 1) & hash算法发生了碰撞，再次分情况讨论；
1.以链表的形式存入；
2.如果碰撞导致链表过长(大于等于TREEIFY_THRESHOLD)，就把链表转换成红黑树；
最后，如果e不为空，将e添加到table中（e.value 被赋值为 putVal()中的参数 value）；
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
               boolean evict) {
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    //hashmap对象中 tabel属性为空--->第一次put---->resize()
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;
    //发现tab[i] 没有值，直接存入即可
    if ((p = tab[i = (n - 1) & hash]) == null)
        tab[i] = newNode(hash, key, value, null);
    else {
        //tab[i]取到值了，莫慌，先定义下方2个变量
        Node<K,V> e; K k;
        //如果是 key 重复了  很简单，直接e = p
        if (p.hash == hash &&
            ((k = p.key) == key || (key != null && key.equals(k))))
            e = p;
        // 该链为树
        else if (p instanceof TreeNode)
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        // 该链为链表
        else {
            for (int binCount = 0; ; ++binCount) {
                if ((e = p.next) == null) {
                    p.next = newNode(hash, key, value, null);
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash);
                    break;
                }
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
            }
        }
        //几种情况都处理，可以添加元素 了
        if (e != null) { // existing mapping for key
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            afterNodeAccess(e);
            return oldValue;
        }
    }
    ++modCount;
    if (++size > threshold)
        resize();
    afterNodeInsertion(evict);
    return null;
}
