package StackAndQueue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 用 add first 或 add last 这套新的 API 改写 Deque 的代码
 * @author yunhang.tang
 *
 */
public class DeQueForNew {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//旧代码：
			oldDeQue();
		//新api代码调整
			newDeQue();
	}
	
	private static void oldDeQue(){
		Deque<String> deque = new LinkedList<String>();
		deque.push("a");
		deque.push("b");
		deque.push("c");
		System.out.println(deque);
		String str = deque.peek();
		System.out.println(str);
		System.out.println(deque);
		while (deque.size() > 0) {
		 System.out.println(deque.pop());
		}
		System.out.println(deque);
	}
	private static void newDeQue(){
		Deque<String> deque = new LinkedList<String>();
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

}
