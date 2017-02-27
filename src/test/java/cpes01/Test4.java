package cpes01;

import java.util.ArrayList;
import java.util.List;

import com.atguigu.cpes.bean.User;

public class Test4 {

	public static void main(String[] args) throws Exception {
		
		List<User> users = new ArrayList<User>();
		
		User user = new User();
		user.setId(200);
		user.setUsername("zhangsan");
		
		User user1 = new User();
		user1.setId(201);
		user1.setUsername("lisi");
		
		User user2 = new User();
		user2.setId(202);
		user2.setUsername("wangwu");
		
		users.add(user1);
		users.add(user2);
		users.add(user);
		
	
		//test(users, 200);
		// 包装类进行数据比对时，会将常用（byte）的数据进行数据缓存,进行比对时，结果相同，但是超出范围，比对结果不相同
		// 包装类型的数据比对一定要使用equals方法
		
		Integer i = 127;
		Integer j = 127;
		
		System.out.println( i == j );
	}
	
	public static void test( List<User> users, Integer id ) {
		
		for ( User user : users ) {
			System.out.println(user.getId() == id );
			if ( user.getId() == id ) {
				System.out.println( user.getUsername() );
			}
		}
	}

}
