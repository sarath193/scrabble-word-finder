
/*
 * HashTable Implementation using separate chaining.
 * 
 * 
 * To check rehashing in this hashtable make the initial size 100000.
 */
import java.util.LinkedList;

public class MyHashTable<T> {

	private static int totalWords = 0;
	private static int bucketSize = 0;
	private static int currentSize = 0;
	private static int maxChainLength = 0;
	private LinkedList<T>[] theList;

	private final double LAMBDA = 0.7;

	private int prime;

	@SuppressWarnings("unchecked")
	public MyHashTable() {
		bucketSize = nextPrime(200000);
		theList = new LinkedList[bucketSize];
		prime = nextPrime(bucketSize * 5);
	}

	public void add(T str) {
		if (LAMBDA < (float) totalWords / bucketSize) {
			rehash();
		}
		totalWords++;
		int hashValue = hashcode(str.toString());
		LinkedList<T> temp;
		if (theList[hashValue] == null) {
			temp = new LinkedList<>();
			temp.add(str);
			theList[hashValue] = temp;
			currentSize++;
		} else {
			temp = theList[hashValue];
			temp.add(str);
			if (maxChainLength < temp.size())
				maxChainLength = temp.size();
			theList[hashValue] = temp;
		}
	}

	/*
	 * Rehashing is done if ratio of total number of entries in the hashtable to
	 * the total number of buckets exceed the lambda ratio.
	 */
	@SuppressWarnings("unchecked")
	private void rehash() {
		bucketSize = nextPrime(bucketSize * 2);
		LinkedList<T>[] theTempList = theList;
		theList = new LinkedList[bucketSize];
		for (LinkedList<T> list : theTempList) {
			if (list == null)
				continue;
		}
	}

	public boolean contains(T s) {
		int hashValue = hashcode(s.toString());
		LinkedList<T> temp = theList[hashValue];
		if (temp == null)
			return false;
		return temp.contains(s);
	}

	public void remove(T s) {
		int hashValue = hashcode(s.toString());
		LinkedList<T> temp = theList[hashValue];
		if (temp == null) {
			System.out.println(s + " not found !");
		} else {
			if (!temp.contains(s))
				System.out.println(s + " not found !");
			else {
				temp.remove(s);
				if (temp.size() == 0) {
					currentSize--;
					totalWords--;
				}
				System.out.println(s + " removed successfully!");
			}

		}
	}
	/*
	 * Gives the next prime number
	 */

	private int nextPrime(int num) {
		if ((num & 1) == 0)
			num += 1;
		while (!isPrime(num)) {
			num += 2;
		}
		return num;
	}

	private boolean isPrime(int num) {
		if (num == 1)
			return false;
		if (num == 2)
			return true;
		for (int i = 3; i * i <= num; i += 2) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

	private int hashcode(String str) {
		long hash = str.hashCode();
		hash = hash % prime;
		for (int i = 0; i < str.length(); i++) {
			hash = hash * 31 + 17 * (int) str.charAt(i);
		}
		if (hash < 0)
			hash *= -1;
		return (int) (hash % bucketSize);

	}

	public int getBucketSize() {
		return bucketSize;
	}

	public int getFilledBuckets() {
		return currentSize;
	}

	public int getMaxLength() {
		return maxChainLength;
	}

	public int getTotalWords() {
		return totalWords;
	}

}
