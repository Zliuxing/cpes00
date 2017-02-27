package cpes01;


public class Test5 {

	public static void main(String[] args) throws Exception {
		// 访问权限：方法的提供者和方法的调用者之间的关系
		// 方法的调用者：cpes01.Test5
		// 方法的提供者：java.lang.Object ==> cpes01.AA
		AA aa = new AA();
		aa.clone();
	}
}
class AA {

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
}
