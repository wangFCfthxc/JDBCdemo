package demo;

public class test {

	public static void main(String[] args) {
		int a = 3; // 011(2進制)
		int b = 4; // 100
		a = a & 2; // 011 and 010 = 010 
		System.out.println(a);
		a = a | b; // 011 or 100 = 111
		System.out.println(a);


	}

}
