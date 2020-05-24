#### **分析 Queue 和 PriorityQueue 的源码**
**Queue:**
[8.0 Queue API 文档](https://docs.oracle.com/javase/8/docs/api/java/util/Queue.html)
是继承了Collection的一个接口，并在此基础上提供额外的插入、提取和检查操作，且队列不允许空元素。
1.每种操作都有两种形式：1抛出异常，2返回null或false
2.第二种，操作是专为容量受限的使用而设计的
|  | 抛出异常 |返回特殊值  |
| --- | --- | --- |
| 插入 | add(e) | offer(e) |
| 移除 |remove())  |poll()  |
| 检查| element() |  peek()|
2.方法详解
 
* boolean add(E e)：添加元素，如果成功返回true,**如果因为容量限制导致失败**，抛出IllegalStateException异常
* rboolean offer(E e)：添加元素，如果成功返回true,如果因为容量限制导致失败，返回false
* E remove()：返回被删除的元素，删除队列头的元素，并且删除队列头的元素，与peek 不同的是,如果队列为空，抛出NoSuchElementException
* E poll()：删除队列头的元素，并返回被删除的元素，如果队列为空，返回null
* E element()：取出队列头的元素（不删除），与peek 不同的是，如果队列为空，抛出 NoSuchElementException
* E peek()：取出队列头的元素（不删除），如果队列为空，返回null


**PriorityQueue:（优先级队列）**
[8.0 PriorityQueue API 文档](https://docs.oracle.com/javase/8/docs/api/java/util/PriorityQueue.html)

 是一个基于堆排序实现的，带有优先级的队列，而这个优先级是指自然顺序或者是自定义的优先级（通过比较器），每次从队列里获取**队头元素**的时候，它都会给我们优先级最大（最小）的元素。
#### 1.属性：
```
//默认容量
private static final int DEFAULT_INITIAL_CAPACITY = 11;

//底层的存储结构，也是通过数组来存储的
transient Object[] queue; // non-private to simplify nested class access

//队列中的元素个数
private int size = 0;

//比较器，默认为null
private final Comparator<? super E> comparator;

//queue的最大容量
private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
```
  从这些属性中可见：
  1.底层也是通过数组来实现，且数组默认大小为11；
  2.它还有一个比较器，默认情况下比较器为 null。当比较器为空时，它会按照元素的自然顺序进行排序，如果元素无法比较，则会抛出异常。  
####   2.方法：
 1.构造函数
    
```
//无参的构造方法，默认容量大小为11，比较器为null
public PriorityQueue() {
    this(DEFAULT_INITIAL_CAPACITY, null);
}

//指定初始容量大小，比较器为null
public PriorityQueue(int initialCapacity) {
    this(initialCapacity, null);
}

//指定比较器，初始容量大小为11
public PriorityQueue(Comparator<? super E> comparator) {
    this(DEFAULT_INITIAL_CAPACITY, comparator);
}

//指定初始容量大小和比较器
public PriorityQueue(int initialCapacity, Comparator<? super E> comparator) {
    // Note: This restriction of at least one is not actually needed,
    // but continues for 1.5 compatibility
    if (initialCapacity < 1)
        throw new IllegalArgumentException();
    this.queue = new Object[initialCapacity];
    this.comparator = comparator;
}

//通过指定的集合来初始化优先队列
public PriorityQueue(Collection<? extends E> c) {
    if (c instanceof SortedSet<?>) {
        SortedSet<? extends E> ss = (SortedSet<? extends E>) c;
        this.comparator = (Comparator<? super E>) ss.comparator();
        initElementsFromCollection(ss);
    }
    else if (c instanceof PriorityQueue<?>) {
        PriorityQueue<? extends E> pq = (PriorityQueue<? extends E>) c;
        this.comparator = (Comparator<? super E>) pq.comparator();
        initFromPriorityQueue(pq);
    }
    else {
        this.comparator = null;
        initFromCollection(c);
    }
}

//通过一个优先队列来初始化
public PriorityQueue(PriorityQueue<? extends E> c) {
    this.comparator = (Comparator<? super E>) c.comparator();
    initFromPriorityQueue(c);
}

//通过一个SortedSet来初始化优先队列
public PriorityQueue(SortedSet<? extends E> c) {
    this.comparator = (Comparator<? super E>) c.comparator();
    initElementsFromCollection(c);
}
```

2、扩容
```
 private void grow(int minCapacity) {
        int oldCapacity = queue.length;
        // Double size if small; else grow by 50%
        int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                                         (oldCapacity + 2) :
                                         (oldCapacity >> 1));
        // overflow-conscious code
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        queue = Arrays.copyOf(queue, newCapacity);
    }
```
1.从扩容方法中可以看出，当 queue 的长度小于 64 时，它每次扩容都是比原来大一倍且再多扩充两个存储单位；而当 queue 的长度大于等于 64 之后，它每次扩容都是比原来多扩50%的空间,如果超范围，就按照指定大小进行扩容

3.获取堆顶元素
```
// 直接数组返回// 也可以使用 element()，是 abstractQueue 的方法，调用的也是 peek() 
public E peek() {
        return (size == 0) ? null : (E) queue[0];
    }
```
4.获取元素下标
```
// 因为使用的是最堆的数据结构，所以只能遍历，效率较低
private int indexOf(Object o) {
        if (o != null) {
            for (int i = 0; i < size; i++)
                if (o.equals(queue[i]))
                    return i;
        }
        return -1;
    }
```
5.添加元素
涉及堆，之后，在写分析。。。

6.删除元素
涉及堆，之后，在写分析。。。
