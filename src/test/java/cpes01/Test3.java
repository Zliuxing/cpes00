package cpes01;

import java.util.ArrayList;

import com.atguigu.cpes.bean.User;

public class Test3 {

	public static void main(String[] args) {
		ArrayList<User> list = new ArrayList<User>();
		// ArrayList浅复制
		User user = new User();
		user.setUsername("zhangsan");
		
		list.add(user);
		
		// 拷贝
		ArrayList<User> list1 = (ArrayList<User>)list.clone();
		
		User user1 = list1.get(0);
		user1.setUsername("lisi");
		
		System.out.println( user.getUsername() );
		System.out.println( user1.getUsername() );
	}

}
