package test;

import java.util.ArrayList;

public class Subsets {

	ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set, int index) {
		ArrayList<ArrayList<Integer>> allsubsets;

		if (set.size() == index) {// Base case - add empty set

			allsubsets = new ArrayList<ArrayList<Integer>>();

			allsubsets.add(new ArrayList<Integer>()); // Empty set

		} else {

			allsubsets = getSubsets(set, index + 1);
			printAll(allsubsets);
			int item = set.get(index);
			System.out.println("index: "+index + ", item: "+ item);
			ArrayList<ArrayList<Integer>> moresubsets = new ArrayList<ArrayList<Integer>>();
			for (ArrayList<Integer> subset : allsubsets) {

				ArrayList<Integer> newsubset = new ArrayList<Integer>();

				newsubset.addAll(subset); //

				newsubset.add(item);

				moresubsets.add(newsubset);

			}

			allsubsets.addAll(moresubsets);

		}
		return allsubsets;
	}
	
	void printAll(ArrayList<ArrayList<Integer>> allsubsets) {
		for(int i=0; i<allsubsets.size(); i++ ) {
			
			ArrayList<Integer> subset= allsubsets.get(i);
			for(int j=0; j<subset.size(); j++ ) {
				System.out.print(subset.get(j)+ "");
				
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Subsets subsets= new Subsets();
		ArrayList<Integer> list= new ArrayList<Integer>() ;
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		ArrayList<ArrayList<Integer>> allsubsets= subsets.getSubsets(list,0);
	//	subsets.printAll(allsubsets);
	}

}
