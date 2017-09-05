package cn.zy.ini;

import java.util.Arrays;

import junit.framework.Assert;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;

import com.alibaba.druid.pool.DruidDataSource;

/** * @author  ZY
 * @date 创建时间：2017年9月5日 下午3:58:01 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
/**
 * 1、对象名=全限定类名  相对于调用public无参构造器创建对象
2、对象名.属性名=值    相当于调用setter方法设置常量值
3、对象名.属性名=$对象引用    相当于调用setter方法设置对象引用
 * */
public class NonConfigurationCreateTest {
	public void test(){
		DefaultSecurityManager securityManager = new DefaultSecurityManager();  
		//设置authenticator  
		ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();  
		authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());  
		securityManager.setAuthenticator(authenticator);  
		  
		//设置authorizer  
		ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();  
		authorizer.setPermissionResolver(new WildcardPermissionResolver());  
		securityManager.setAuthorizer(authorizer);  
		  
		//设置Realm  
		DruidDataSource ds = new DruidDataSource();  
		ds.setDriverClassName("com.mysql.jdbc.Driver");  
		ds.setUrl("jdbc:mysql://localhost:3306/shiro");  
		ds.setUsername("root");  
		ds.setPassword("123987");  
		  
		JdbcRealm jdbcRealm = new JdbcRealm();  
		jdbcRealm.setDataSource(ds);  
		jdbcRealm.setPermissionsLookupEnabled(true);  
		securityManager.setRealms(Arrays.asList((Realm) jdbcRealm));  
		  
		//将SecurityManager设置到SecurityUtils 方便全局使用  
		SecurityUtils.setSecurityManager(securityManager);  
		  
		Subject subject = SecurityUtils.getSubject();  
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");  
		subject.login(token);  
		Assert.assertTrue(subject.isAuthenticated());  
	}
}
