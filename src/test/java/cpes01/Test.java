package cpes01;

public class Test {

	public static void main(String[] args) throws Exception {
		
		//int i = cal();
		//System.out.println(i);
		
		//int i = 0;
		
		//i = i++; // _a = i++; ==> _a = 0; i+1 = 1
		// i = _a = 0;
		//System.out.println(i);
		
		Thread t = new Thread();
		
		t.start();

		t.sleep(1000);// 让当前正在运行的线程休眠, main线程休眠
		
	}
	
	public static int cal() {
		int i = 0;
		// _rtnVal
		try {
			return i++; // _rtnVal = i++; return _rtnVal;
		} finally {
			return ++i; // _rtnVal = ++i; return _rtnVal = 2;
		}
	}

}
