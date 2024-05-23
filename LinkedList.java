package project3;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
//import java.util.LinkedList.Node;
import java.util.Arrays;
@SuppressWarnings("unchecked")

public class LinkedList<E> implements Collection<E>, Iterable<E> {
	Node<E> head = null;
	Node<E> tail = null;
	int size = 0;
	
	/**
	 * @param o, an object to find the index of
	 * @return the index of o, or -1 if the object is not in the list
	 */
	public int indexOf(Object o) {
		int i;
		Node<E> node = head;
		for (i = 0; i < size; i++) {
			if (o == node.data) {
				return i;
			}
			node = node.next;
		}
		return -1;
	}
	
	/**
	 * @param index
	 * @throws IndexOutOfBoundsException if the index is outside of the range of the list
	 * @return the object at the wanted index 
	 */
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException ("Invalid index");
		}
		Node<E> node = head;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node.data;
	}
	
	/**
	 * @return a string of each object separated by , starting with [ and ending with ]
	 */
	public String toString() {
		Node<E> node = head;
		String linkedListString = "[";
		for (int i = 0; i < size - 1; i++) {
			linkedListString += node.data + ", ";
			node = node.next;
		}
		if (node != null) {
			linkedListString += node.data;
		}
		linkedListString += "]";
		return linkedListString;
	}
	
	/**
	 * Creates an array of a linked list by calling ToArray, then uses the sort function in Arrays, then copies the sorted array back into the list
	 */
	public void sort() {
		Object [] array = toArray();
		Arrays.sort(array);
		this.clear();
		for (Object o : array) {
			this.add((E) o);
		}
	}
	
	/**
	 * @return true if the element is added to the list, false otherwise
	 * Adds a given element e to the list
	 */
	@Override
	public boolean add(E e) {
		Node<E> node = new Node<E>();
        node.data = e;
        if (head == null) {
        	head = node;
        	tail = node;
        }
        else {
        	tail.next = node;
        	node.prev = tail;
        	tail = node;
        }
        size++;
        return true;
	}
	
	/**
	 * Removes all nodes from the list
	 * The list will now be empty
	 */
	@Override
	public void clear() {
		Node<E> node = head;
		for (int i = 0; i < size; i++) {
			Node<E> next = node.next;
			node.data = null;
			node.prev = null;
			node.next = null;
			node = null;
			node = next;
		}
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
	 * @param o, an object that will be checked to see if the list contains it
	 * @return true if the list contains object o, false otherwise
	 */
	@Override
	public boolean contains(Object o) {
		if (this.indexOf(o) != -1) {   // indexOf returns -1 if the object o is not found in the list
        	return true;
        }
        return false;
	}
	
	/**
	 * @param c, a collection of objects
	 * @return true if the list contains all of the object in Collection c, false otherwise
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c) {
			if (!this.contains(o)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param o, an object to be compared to 
	 * @return true if the object are equal, false otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		else if (head.data != o) {
			return false;
		}
		return true;
	}
	
	/**
	 * @return a unique integer value for each object
	 */
	@Override
	public int hashCode() {
		return Objects.hash(head, size, tail);
	}
	
	/**
	 * @return true if the list is empty, false otherwise
	 * A list is empty if the head value is null, meaning it does not point to a node
	 */
	@Override
	public boolean isEmpty() {
		if (head == null) {
			return true;
		}
		return false;
	}
	
	/**
	 * @return an iterator Iter() for the list
	 */
	@Override
	public Iterator<E> iterator() {
		return new Iter();
	}
	
	/**
	 * @param o, an object to be removed
	 * @return true if the object is successfully removed, false otherwise 
	 * An object is successfully removed if the list contains it
	 */
	@Override
	public boolean remove(Object o) {
		if (head == null) {        // if the list is empty, o cannot be removed
			return false;
		}
		if (!this.contains(o)) {   // checks if the list contains o
			return false;
		} 
		if (this.indexOf(o) == 0) {   // o is the first object in the list
			Node<E> node = head;
			head = node.next;
			node.next = null;
			node.data = null;
			node = null;
			this.size--;
			return true;
		}
		if (this.indexOf(o) == this.size() - 1) {   // o is the last object in the list
			Node<E> node = tail; 
			tail = node.prev;
			node.prev = null;
			node.data = null;
			node = null;
			this.size--;
			return true;
		}
		Node<E> node = head;
		for (int i = 0; i < size; i++) {
			if (node.data.equals(o)) {
				if (node.prev == null) {
					head = node.next;
				}
				if (node.next == null) {
					tail = node.prev;;
				}
				else {
					node.next.prev = node.prev;
					node.prev.next = node.next;
				}
				node.next = null;
				node.prev = null;
				node.data = null;
				node = null;
				this.size--;
				return true;
			}
			node = node.next;
		}
		return false;
	}
	
	/**
	 * @return the size of the list
	 * Size is increased/decreased when an object is added/removed
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * @return an array of Objects
	 * Creates a new array of Objects and fills each index with the object at the corresponding node
	 */
	@Override
	public Object[] toArray() {
		Node<E> node = head;
		Object a [] = new Object[size];
		for (int i = 0; i < size; i++){
			a[i] = node.data;
			node = node.next;
		}
		return a;
	}
	
	@Override
	public <T> T[] toArray(T[] a) {
		if (a.length < size)
            a = (T[]) Arrays.copyOf(a, size, a.getClass());
        Node<E> node = head;
        Object[] array = a;
        for (int i = 0; i < size; i++) {
            array[i] = node.data;
        	node = node.next;
        }
        if (a.length > size) {
            a[size] = null;
        }
        return a;
	}
	
	/*
	 * THE FOLLOWING THREE METHODS ARE REQURIED TO BE DECLARED BY THE COLLECTION INTERFACE 
	 * BUT DO NOT NEED TO BE IMPLEMENTED
	 */
	
	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static class Node<E> {    // Node class that declares next, prev (nodes) and data (element) to be used by the node
		Node<E> next;
		Node<E> prev;
		E data;
	}
	
	private class Iter implements Iterator<E> {    // iterator to be used by the list
		Node<E> node = head;      // starts at the first node
		int nextIndex = 1;        // next index after first node would be 1

		/**
		 * @return true if the list points to a node, false otherwise
		 * A node hasNext() if the next index is less than the size
		 */
		@Override
		public boolean hasNext() {
			return nextIndex < size;
		}

		/**
		 * @return the object of the node at the next index
		 */
		@Override
		public E next() {
			if (!this.hasNext()) {   // checks that the node has a next node
				return null;         // returns null if it does not have a next node
			}
			E answer = node.data;
			node = node.next;
			nextIndex++;
			return answer;
		}
		
	}
}
