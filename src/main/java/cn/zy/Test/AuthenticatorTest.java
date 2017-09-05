package cn.zy.Test;

import junit.framework.Assert;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/** * @author  ZY
 * @date 创建时间：2017年9月4日 下午5:11:06 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class AuthenticatorTest {
	private void login(String configFile) {  
	    //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager  
	    Factory<org.apache.shiro.mgt.SecurityManager> factory =  
	            new IniSecurityManagerFactory(configFile);  
	  
	    //2、得到SecurityManager实例 并绑定给SecurityUtils  
	    org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();  
	    SecurityUtils.setSecurityManager(securityManager);  
	  
	    //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）  
	    Subject subject = SecurityUtils.getSubject();  
	    UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");  
	  
	    subject.login(token);  
	}  
	@Test  
	public void testAllSuccessfulStrategyWithSuccess() {  
	    login("classpath:shiro-authenticator-all-success.ini");  
	    Subject subject = SecurityUtils.getSubject();  
	  
	    //得到一个身份集合，其包含了Realm验证成功的身份信息  
	    PrincipalCollection principalCollection = subject.getPrincipals();  
	    Assert.assertEquals(2, principalCollection.asList().size());  
	} 
	
}
