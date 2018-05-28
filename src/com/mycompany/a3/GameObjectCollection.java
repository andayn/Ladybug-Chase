package com.mycompany.a3;

import java.util.ArrayList;

public class GameObjectCollection implements ICollection {
	private ArrayList<Object> collection;
	
	public GameObjectCollection() {
		collection = new ArrayList<Object>();
	}
	
	public Object get(int n) {
		return collection.get(n);
	}
	
	public void add(Object obj) {
		collection.add(obj);
	}
	
	public int size() {
		return collection.size();
	}
	
	public IIterator getIterator() {
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements IIterator {
		private int currentIndex;
		
		public ArrayListIterator() {
			currentIndex = -1;
		}
		
		public Object getNext() {
			currentIndex++;
			return collection.get(currentIndex);
		}
		
		public boolean hasNext() {
			if ((collection.size() <= 0) || (currentIndex == (collection.size() - 1))) {
				return false;
			} else {
				return true;
			}
			
 		}
	}
}
