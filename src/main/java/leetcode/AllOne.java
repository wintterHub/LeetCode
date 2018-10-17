package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//#432
public class AllOne {

	public static void main(String[] args) {
		AllOne allOne = new AllOne();
		allOne.inc("one");
		allOne.inc("one");
		allOne.inc("two");
		allOne.inc("three");
		allOne.inc("one");
		allOne.inc("three");
		allOne.inc("three");

		allOne.dec("one");
		allOne.dec("one");
		allOne.dec("three");
		allOne.dec("two");

		System.out.println(allOne.getMaxKey());
		System.out.println(allOne.getMinKey());

	}

	// 定义一个类似于“桶”的数据结构，把value相同的放入一个桶中保存，保存这些key的集合
	private class Bucket {
		int count;
		Set<String> keySet;
		Bucket pre;
		Bucket next;

		public Bucket(int count) {
			this.count = count;
			this.keySet = new HashSet<String>();
		}
	}

	Bucket head;
	Bucket tail;

	Set<String> pri_keySet = new HashSet<>();
	Map<String, Bucket> pri_keyBucketMap = new HashMap<>();

	public AllOne() {
		head = new Bucket(Integer.MIN_VALUE);
		tail = new Bucket(Integer.MAX_VALUE);
		head.next = tail;
		tail.pre = head;
	}

	public void inc(String key) {
		if (pri_keySet.contains(key)) {
			Bucket bucket = pri_keyBucketMap.get(key);
			bucket.keySet.remove(key);
			if (bucket.keySet.size() <= 0) {
				bucket.next.pre = bucket.pre;
				bucket.pre.next = tail.next == bucket ? tail : bucket.next;
			}
		} else {
			if (head.next.count == 1) {
				insert2Bucket(head.next, key);
			} else {
				Bucket bucket = new Bucket(1);
				bucket.next = head.next;
				head.next = bucket;
				insert2Bucket(bucket, key);
			}
		}
	}

	public void dec(String key) {

	}

	public String getMaxKey() {
		return tail.next != null ? tail.next.keySet.iterator().next() : "";
	}

	public String getMinKey() {
		return head.next != tail ? head.next.keySet.iterator().next() : "";
	}

	public void insert2Bucket(Bucket bucket, String key) {
		bucket.keySet.add(key);
		pri_keySet.add(key);
		pri_keyBucketMap.put(key, bucket);
	}

}
