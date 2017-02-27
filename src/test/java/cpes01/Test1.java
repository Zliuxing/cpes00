package cpes01;

public class Test1 {

	public static void main(String[] args) {
		
		//byte b = 127;
		//b++;
		//b = (byte)(b+1);
		//b = 127 + 1;
		
		// 127 ==> 0111 1111 + 1
		//     ==> 1000 0000 
		
		// 11111111 = -1
		
		// 负数的二进制的表现方式为：负数的绝对值按位取反加1
		
		// 00000001
		// 11111110 + 1
		// 11111111
		
		//System.out.println( b );
		
		byte b = 10;
		
		sum(b);
	}
	
//	public static void sum( byte b ) {
//		System.out.println( "byte...." );
//	}
//	public static void sum( short s ) {
//		System.out.println( "short...." );
//	}
	public static void sum( int i ) {
		System.out.println( "int...." );
	}
	public static void sum( char c ) {
		System.out.println( "char...." );
	}
}
