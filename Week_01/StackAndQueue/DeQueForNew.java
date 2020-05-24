package StackAndQueue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * �� add first �� add last �����µ� API ��д Deque �Ĵ���
 * @author yunhang.tang
 *
 */
public class DeQueForNew {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//�ɴ��룺
			oldDeQue();
		//��api�������
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
