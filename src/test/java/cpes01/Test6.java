package cpes01;


public class Test6 {

	public static void main(String[] args) throws Exception {
		
		String name = "123.png";
		name = name.substring(name.lastIndexOf(".")+1);
		System.out.println( name );
		D d = new E();
		
		Class[] cs = E.class.getInterfaces();
		System.out.println( cs.length );
	}
}
interface D {
	
}
class C implements D {

}
class E extends C {
	
}
