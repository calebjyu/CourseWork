package linear;

import java.util.ArrayList;

public class Sorter {
	
	public static void insertionSort(ArrayList<Integer> arrL){//O(n^2) best case O(n)
		for(int i=1;i<arrL.size();i++){
			int j=i-1;
			int x=i;
			while(j>=0&&arrL.get(j)>arrL.get(x)){
				int temp = arrL.get(x);
				arrL.set(x,arrL.get(j));
				arrL.set(j,temp);
				j--;
				x--;
			}
		}
	}
	public static void insertionSort(int[] arr){//O(n^2) best case O(n)
		for(int i=1;i<arr.length;i++){
			int j=i-1;
			int x=i;
			while(j>=0&&arr[j]>arr[x]){
				int temp = arr[x];
				arr[x]=arr[j];
				arr[j]=temp;
				j--;
				x--;
			}
		}
	}
	
	public static <T extends Comparable<T>> Node<T> mergeSort(Node<T> list){
		if(list==null||list.next==null){
			return list;
		}
		Node<T> list2 = split(list);
		list=mergeSort(list);
		list2=mergeSort(list2);
		return merge(list,list2);
	}
	private static <T extends Comparable<T>> Node<T> split(Node<T> list) {//returns reference to second linked list
		if(list==null) return null;
		int count = 0;
		for(Node<T> ptr = list;ptr!=null;ptr=ptr.next){
			count++;
		}
		int mid = count/2;
		Node<T> list2 = list;
		for(int i=0;i<mid;i++){
			list2=list2.next;
		}
		Node<T> temp=list2;
		list2.next=null;
		return temp.next;
	}
	private static <T extends Comparable<T>> Node<T> merge(Node<T> l1,Node<T> l2) {
		if(l1==null&&l2==null){
			return null;
		}
		if(l1==null){
			return l2;
		}
		if(l2==null){
			return l1;
		}
		if(l1.data.compareTo(l2.data)<=0){
			l1.next=merge(l1.next,l2);
			return l1;
		}else{
			l2.next=merge(l1,l2.next);
			return l2;
		}
	}
}
