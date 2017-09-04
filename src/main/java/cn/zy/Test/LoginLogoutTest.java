package cn.zy.Test;

import junit.framework.Assert;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;


/** * @author  ZY
 * @date 创建时间：2017年9月4日 下午3:03:41 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class LoginLogoutTest {
	@Test
	public void testHelloworld(){
		//1.获取securityManager工厂,使用ini配置文件初始化securityManager,
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
		//2.得到securityManager实例,并绑定给SecurityUtils;
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		//3.得到subject及创建用户名/密码身份验证Token(验证凭证)
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
		
		try {
			//4.登陆,身份验证
			subject.login(token);
		} catch (AuthenticationException e) {
			//5.身份验证失败
			e.printStackTrace();
		}
		
		Assert.assertEquals(true, subject.isAuthenticated());//断言用户已经登陆
		
		//6.退出
		subject.logout();
	}
}

