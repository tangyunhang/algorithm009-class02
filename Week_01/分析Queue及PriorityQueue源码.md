1.用 add first 或 add last 这套新的 API 改写 Deque 的代码
旧代码：

Deque<String> deque = new LinkedList<String>();

deque.push("a");
deque.push("b");
deque.push("c");
System.out.println(deque);

String str = deque.peek();
System.out.println(str);
System.out.println(deque);

while (deque.size()>0){
    System.out.println(deque.pop());
}
System.out.println(deque);

改写：
Deque<String> deque = new LinkedList<String>();
<<<<<<< HEAD:Week_01/分析Queue及PriorityQueue源码.md
		deque.addFirst("a");
		deque.addFirst("b");
		deque.addFirst("c");
		System.out.println(deque);
		System.out.println(deque);
		String str = deque.peekFirst();
		System.out.println(str);
		System.out.println(deque);
		while (deque.size() > 0) {
		 System.out.println(deque.pollFirst());
		}
		System.out.println(deque);
	}
2.分析 Queue 和 PriorityQueue 的源码
Queue是继承了Collection的一个接口，并在此基础上提供额外的插入、提取和检查操作。
抛出异常	返回特殊值
插入	add(e)		offer(e)
移除	remove()	poll()
检查	element()	peek()
每种操作失败时都有两种类型的返回值：
1.抛出异常
2.返回一个 特定 的值：null 或者 false，取决于操作的类型
=======
deque.addFirst("a");
deque.addFirst("b");
deque.addFirst("c");
System.out.println(deque);
System.out.println(deque);
String str = deque.peekFirst();
System.out.println(str);
System.out.println(deque);
while (deque.size() > 0) {
 System.out.println(deque.pollFirst());
}
System.out.println(deque);
}
2.分析 Queue 和 PriorityQueue 的源码
>>>>>>> 661ed21423f49e5a5c1cbf4fb6b66a305fbf7423:Week_01/改写Deque代码及分析PriorityQueue源码.md
