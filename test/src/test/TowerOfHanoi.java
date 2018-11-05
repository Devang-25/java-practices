package test;

public class TowerOfHanoi {
	
	 void towerOfhanoi(int n, int src, int dest, int aux) {
		 if(n== 1) {
			 System.out.println(src + " - "+ dest);
			 return;
		 }
		 towerOfhanoi(n-1, src, aux, dest);
		 towerOfhanoi(n-1, aux, dest, src);
	 }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TowerOfHanoi t= new TowerOfHanoi();
	//	t.towerOfhanoi(3, 1,2,3);
		System.out.println("ab".equals("ab"));
	}

}
