package com.fun.collect.ds.linkList;

import com.fun.collect.ds.entity.ListNode;
import com.fun.collect.ds.entity.RandomListNode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author wanwan 2022/4/20
 */
public class LinkListSolution {

	/**
	 * 1、从头到尾打印链表
	 *
	 * 说明：我们都知道链表无法逆序访问，那肯定无法直接遍历链表得到从尾到头的逆序结果。
	 * 但是我们都知道递归是到达底层后才会往上回溯，因此我们可以考虑递归遍历链表
	 */
	public static void printListFromTailToHead(ListNode head) {
		if (head != null) {
			printListFromTailToHead(head.next);
			System.out.println(head.next);
		}
	}

	/**
	 * 2、合并两个递增链表
	 */
	public static ListNode<Integer> mergeList(ListNode<Integer> list1, ListNode<Integer> list2) {
		if (list1 == null || list2 == null) {
			return list1 == null ? list2 : list1;
		}
		if (list1.data <= list2.data) {
			list1.next = mergeList(list1.next, list2);
			return list1;
		}
		else {
			list2.next = mergeList(list1, list2.next);
			return list2;
		}
	}

	/**
	 * 3、找出链表环的入口节点
	 * 方法1：hash法，记录第一次重复的结点
	 */
	public static <T> T entryNodeOfLoop(ListNode<T> pHead) {
		HashSet<T> set = new HashSet<>();
		while (pHead != null) {
			if (set.contains(pHead.data)) {
				return pHead.data;
			}
			set.add(pHead.data);
			pHead = pHead.next;
		}
		return null;
	}

	/**
	 * 4、找出链表环的入口节点
	 * 方法2：快慢指针
	 */
	public static ListNode<?> findKthToTail(ListNode<?> pHead, int k) {
		ListNode<?> slow = pHead;
		ListNode<?> fast = pHead;
		while (k > 0) {
			if (fast == null) {
				return null;
			}
			fast = fast.next;
			k--;
		}
		while (fast != null) {
			slow = slow.next;
			fast = fast.next;
		}
		return slow;
	}

	/**
	 * 复杂链表的复制
	 */
	public static RandomListNode clone(RandomListNode pHead) {
		if (pHead == null) {
			return null;
		}
		RandomListNode cur = pHead;
		// 1.clone并插入到原链表
		while (cur != null) {
			RandomListNode clone = new RandomListNode();
			clone.data = cur.data;
			clone.next = cur.next;
			cur.next = clone;
			cur = clone.next;
		}
		cur = pHead;
		// 2.将原链表的随机指针copy到克隆链表
		while (cur != null) {
			RandomListNode copy = cur.next;
			copy.randomPoint = cur.randomPoint;
			cur = cur.next.next;
		}
		RandomListNode clone = pHead.next;
		cur = pHead.next;
		// 3.分开原链表和克隆链表
		while (cur != null) {
			if (cur.next != null) {
				pHead.next = pHead.next.next;
				cur.next = cur.next.next;
				pHead = pHead.next;
				cur = cur.next;
			} else {
				pHead.next = null;
				cur = null;
			}
		}
		return clone;
	}

	/**
	 * 删除链表中重复的节点（链表增序）
	 * 	方法1：直接比较删除
	 */
	public static ListNode<Integer> deleteDuplication(ListNode<Integer> pHead) {
		if (pHead == null) {
			return null;
		}
		//添加表头
		ListNode<Integer> newHead = new ListNode<>(0, pHead);
		ListNode<Integer> cur = newHead;
		while (cur.next != null && cur.next.next != null) {
			if (cur.next.data.equals(cur.next.next.data)) {
				Integer tmp = cur.next.data;
				while (cur.next != null && cur.next.data.equals(tmp)) {
					cur.next = cur.next.next;
				}
			}
			else {
				cur = cur.next;
			}
		}
		return newHead.next;
	}

	/**
	 * 删除链表中重复的节点（链表增序）
	 * 	方法2：哈希表法
	 */
	public static ListNode<Integer> deleteDuplication2(ListNode<Integer> pHead) {
		if (pHead == null) {
			return null;
		}
		//创建包含头结点的链表
		ListNode<Integer> newHead = new ListNode<>(0, pHead);
		ListNode<Integer> cur = newHead;

		HashMap<Integer,Integer> map = new HashMap<>(10);
		//计数
		while (pHead != null) {
			if (map.containsKey(pHead.data)) {
				map.put(pHead.data, map.get(pHead.data) + 1);
			}
			else {
				map.put(pHead.data, 1);
			}
			pHead = pHead.next;
		}
		//对比删除
		while (cur.next != null) {
			ListNode<Integer> tmp = cur.next;
			if (map.get(cur.next.data) > 1) {
				//0 1 1 1 2 3 3  ->> 0 2 3 3 ->> 0 2 5
				Integer count = map.get(cur.next.data);
				for (int i = 0; i < count; i++) {
					tmp = tmp.next;
				}
				cur.next = tmp;
			}
			else {
				cur = cur.next;
			}
		}
		return newHead.next;
	}


	public static void main(String[] args) {
		ListNode n1 = new ListNode(1, null);
		ListNode n2 = new ListNode(2, null);
		ListNode n3 = new ListNode(3, null);
		ListNode n4 = new ListNode(3, null);
		ListNode n51 = new ListNode(4, null);
		ListNode n52 = new ListNode(4, null);
		ListNode n53 = new ListNode(5, null);

		n1.next = n2; n2.next = n3; n3.next = n4; n4.next = n51; n51.next = n52; n52.next = n53;

//		ListNode<?> tail = findKthToTail(n1, 2);
//		deleteDuplication(n1);
		deleteDuplication2(n1);

		RandomListNode r1 = new RandomListNode("1", null, null);
		RandomListNode r2 = new RandomListNode("2", null, null);
		RandomListNode r3 = new RandomListNode("3", null, null);
		RandomListNode r4 = new RandomListNode("4", null, null);
		RandomListNode r5 = new RandomListNode("5", null, null);

		r1.next = r2; r2.next = r3; r3.next = r4; r4.next = r5;
		r1.randomPoint = r3; r2.randomPoint = r5; r3.randomPoint = null; r4.randomPoint = r2; r5.randomPoint = null;
		RandomListNode clone = clone(r1);
	}
}
