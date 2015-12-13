package tutorials;
import java.util.*;
public class ArrayLists {
	public static void main(String args[]){
		String[] things = {"eggs","lasers","hats","pie"};
		List<String> list1 = new ArrayList<String>();
		
		for(String x: things)
			list1.add(x);
		
		String[] moreThings = {"hats", "lasers"};
		List<String> list2 = new ArrayList<String>();
		
		for(String y : moreThings)
			list2.add(y);
		
		for(int i = 0; i < list2.size();i++){
			System.out.printf("%s ", list2.get(i));
		}
	}
}
