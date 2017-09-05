package cn.zy.ini;

import junit.framework.Assert;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/** * @author  ZY
 * @date 创建时间：2017年9月5日 下午4:07:09 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class ConfigurationCreateTest {
	@Test
	public void getSecurityManager(){
		Factory<org.apache.shiro.mgt.SecurityManager> factory =  
		         new IniSecurityManagerFactory("classpath:shiro-config.ini");  
		  
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();  
		  
		//将SecurityManager设置到SecurityUtils 方便全局使用  
		SecurityUtils.setSecurityManager(securityManager);  
		Subject subject = SecurityUtils.getSubject();  
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");  
		subject.login(token);  
		  
		Assert.assertTrue(subject.isAuthenticated());   
	}
}
