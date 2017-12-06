package search;

public class BinarySearchClass {

	public static <T extends Comparable<T>> boolean BinarySearch(T[] list,T target){
		boolean found=false;
		int hi=list.length-1, lo=0;
		while(lo<=hi){
			int mid=(lo+hi)/2;
			int c = target.compareTo(list[mid]);
			if(c==0){//equals
				found=true;
			}else if(c<0){//less than
				hi=mid-1;
			}else if(c>0){//greater than
				lo=mid+1;
			}
		}
		return found;
	}
	
}
