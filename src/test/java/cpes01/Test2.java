package cpes01;

public class Test2 {

	public static void main(String[] args) {

        A a = new B();
		
        //test(a);
        // 多态 ， 动态绑定
        // 调用一个对象的成员方法时，JVM会查看当前对象的实际内存是什么，将内存和方法声明进行绑定
        // 调用内存中的方法就可以了。
        // 动态绑定和属性没有关系，在哪里声明在哪里使用
        System.out.println( a.sum() );
	}

	public static void test( A a ) {
		System.out.println( "aaaaa" );
	}
	
	public static void test( B b ) {
		System.out.println( "bbbbb" );
	}
}
class A {
	
	public int i = 10;
	
	public int sum() {
		return i + 10;
	}
}
class B extends A {
	public int i = 20;
	
//	public int sum() {
//		return i + 20;
//	}
}
