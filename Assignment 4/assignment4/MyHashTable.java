//id:260800991
//name:Antoine Hamasaki-Belanger

package assignment4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class MyHashTable<K, V> implements Iterable<HashPair<K, V>> {
	// num of entries to the table
	private int numEntries;
	// num of buckets
	private int numBuckets;
	// load factor needed to check for rehashing
	private static final double MAX_LOAD_FACTOR = 0.75;
	// ArrayList of buckets. Each bucket is a LinkedList of HashPair
	private ArrayList<LinkedList<HashPair<K,V>>> buckets;

	// constructor
	public MyHashTable(int initialCapacity) {
		//number of buckets should equal the capacity of Arraylist
		this.numBuckets = initialCapacity; 
		//no entries entered up to now
		this.numEntries = 0;
		//initialize the arraylist
		buckets = new ArrayList<LinkedList<HashPair<K,V>>>(this.numBuckets);
		//for each bucket, create a linked list
		for (int i = 0; i < this.numBuckets; i++) {
			LinkedList<HashPair<K,V>> linkedList = new LinkedList<HashPair<K,V>>();
			buckets.add(i, linkedList);
		}
	}

	public int size() {
		return this.numEntries;
	}

	public int numBuckets() {
		return this.numBuckets;
	}

	/**
	 * Returns the buckets variable. Usefull for testing purposes.
	 */
	public ArrayList<LinkedList<HashPair<K, V>>> getBuckets() {
		return this.buckets;
	}

	/**
	 * Given a key, return the bucket position for the key.
	 */
	public int hashFunction(K key) {
		int hashValue = Math.abs(key.hashCode()) % this.numBuckets;
		return hashValue;
	}

	/**
	 * Takes a key and a value as input and adds the corresponding HashPair to this
	 * HashTable. Expected average run time O(1)
	 */
	public V put(K key, V value) {
		HashPair<K,V> newPair = new HashPair<K,V>(key, value);
		int index = hashFunction(key);
		double load;

		//if linkedlist at index is empty, add the new pair
		if (buckets.get(index).size() == 0) {
			this.numEntries++;
			buckets.get(index).add(newPair);
			load = ((double) this.numEntries) / this.numBuckets;
			if (MAX_LOAD_FACTOR < load) 
					this.rehash();
			return null;
		} 
		
		//if linked list at index has pairs already
		else {
			//iterate through linked list to see if duplicate of key
			for (HashPair<K,V> node : this.buckets.get(index)) {
				//if duplicate of key, return old value
				if (node.getKey().equals(key)) {
					V oldValue = node.getValue();
					node.setValue(value); 
					return oldValue;
				}
			}
			
			//if key not is linkedlist already
			this.buckets.get(index).add(newPair);
			this.numEntries++;
			load = ((double) this.numEntries) / this.numBuckets;
			if (MAX_LOAD_FACTOR < load) 
				this.rehash();
			return null;
		}
	}

	/**
	 * Get the value corresponding to key. Expected average runtime = O(1)
	 */
	public V get(K key) {
		//find index of linkedlist
		int index = Math.abs(key.hashCode()) % this.numBuckets;
		
		//check if key is in linkedlist
		for (HashPair<K,V> node : buckets.get(index)) {
			if (node.getKey().equals(key)) 
				return node.getValue();		
		}

		//return null if key not found
		return null;
	}

	/**
	 * Remove the HashPair corresponding to key . Expected average runtime O(1)
	 */
	public V remove(K key) {
		//find index
		int index = Math.abs(key.hashCode()) % this.numBuckets;
		//index of linkedList
		int indexLinkedlist = 0;
		
		//iterate through linkedlist and search for key, if found delete it and return value
		for (HashPair<K,V> node : this.buckets.get(index)) {
			if (node.getKey().equals(key)) {
				V removedValue = node.getValue();
				this.buckets.get(index).remove(indexLinkedlist);
				return removedValue;
			}
			indexLinkedlist++;
		}
		return null;
	}

	// Method to double the size of the hashtable if load factor increases
	// beyond MAX_LOAD_FACTOR.
	// Made public for ease of testing.
	public void rehash() {
		//double capacity size
		this.numBuckets *= 2;
		//create a temporary arraylist with initial values
		ArrayList<LinkedList<HashPair<K,V>>> temp = this.buckets;
		//set buckets to new arraylist
		this.buckets = new ArrayList<LinkedList<HashPair<K,V>>>(this.numBuckets);

		//create a linkedlist for each bucket
		for (int i=0; i < this.numBuckets; i++) {
			LinkedList<HashPair<K,V>> list = new LinkedList<HashPair<K,V>>();
			this.buckets.add(i, list);
		}
		
		//add pairs for linkedlist of each bucket
		for (LinkedList<HashPair<K,V>> list : temp) {
			for (HashPair<K,V> node : list) {
				int index = hashFunction(node.getKey());
				this.buckets.get(index).add(node);
			}
		}
	}

	/**
	 * Return a list of all the keys present in this hashtable.
	 */
	public ArrayList<K> keys() {
		//create array of keys
		ArrayList<K> keys = new ArrayList<K>();

		//iterate through linkedlist of each bucket and add the keys to array of keys
		for (LinkedList<HashPair<K,V>> list : this.buckets) {
			for (HashPair<K,V> node : list) {
				keys.add(node.getKey());
			}
		}
		return keys;
	}

	/**
	 * Returns an ArrayList of unique values present in this hashtable. Expected
	 * average runtime is O(n)
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<V> values() {
		//create hashtable which will take values as keys and keys as values
		MyHashTable<K,V> hash = new MyHashTable<K,V>(this.numEntries);
		//create arraylist of values
		ArrayList<V> values = new ArrayList<V>();
		
		//adding values as keys using put method will refrain from having duplicates of values because of put method
		for (LinkedList<HashPair<K,V>> list : this.buckets) {
			for (HashPair<K,V> node : list) 
				hash.put((K) (node.getValue()), (V) (node.getKey()));
			
		}
		
		for (LinkedList<HashPair<K,V>> list : hash.buckets) {
			for (HashPair<K,V> node : list) 
				values.add((V) (node.getKey()));	
		}
		
		return values;
	}

	@Override
	public MyHashIterator iterator() {
		return new MyHashIterator();
	}

	private class MyHashIterator implements Iterator<HashPair<K,V>> {
		private LinkedList<HashPair<K,V>> entries = new LinkedList<HashPair<K,V>>();
        private HashPair<K,V> firstPair;
        private int index;

        private MyHashIterator() {
            for(LinkedList<HashPair<K,V>> list: buckets) {
                if(!(list.isEmpty() == true)) {
                    for(HashPair<K,V> pair: list) 
                        entries.addLast(pair);   
                }
            }
            firstPair = entries.getFirst();
            index = 0;
        }

        @Override
        public boolean hasNext() {
            if(this.entries.size() > index) 
                return true;
            
            return false;
        }

        @Override
        public HashPair<K,V> next() {
            index++;
            if(hasNext() == true) 
                return this.entries.get(index);
            
            return null;
        }

    }
}
