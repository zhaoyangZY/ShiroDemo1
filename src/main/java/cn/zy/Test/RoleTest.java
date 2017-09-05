package cn.zy.Test;

import java.util.Arrays;

import junit.framework.Assert;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/** * @author  ZY
 * @date 创建时间：2017年9月5日 下午2:12:41 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class RoleTest {
	private void login(String configFile,String name,String pwd) {  
	    //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager  
	    Factory<org.apache.shiro.mgt.SecurityManager> factory =  
	            new IniSecurityManagerFactory(configFile);  
	  
	    //2、得到SecurityManager实例 并绑定给SecurityUtils  
	    org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();  
	    SecurityUtils.setSecurityManager(securityManager);  
	  
	    //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）  
	    Subject subject = SecurityUtils.getSubject();  
	    UsernamePasswordToken token = new UsernamePasswordToken(name, pwd);  
	  
	    subject.login(token);  
	}  
	private Subject subject(){
		 Subject subject = SecurityUtils.getSubject(); 
		 return subject;
	}
	@Test  
	public void testHasRole() {  
	    login("classpath:shiro-role.ini", "zhang", "123");  
	    //判断拥有角色：role1  
	    Assert.assertTrue(subject().hasRole("role1"));  
	    //判断拥有角色：role1 and role2  
	    Assert.assertTrue(subject().hasAllRoles(Arrays.asList("role1", "role2")));  
	    //判断拥有角色：role1 and role2 and !role3  
	    boolean[] result = subject().hasRoles(Arrays.asList("role1", "role2", "role3"));  
	    Assert.assertEquals(true, result[0]);  
	    Assert.assertEquals(true, result[1]);  
	    Assert.assertEquals(false, result[2]);  
	}  
	@Test
	public void testIsPermitted() {  
	    login("classpath:shiro-permission.ini", "zhang", "123");  
	    //判断拥有权限：user:create  
	    Assert.assertTrue(subject().isPermitted("user:create"));  
	    //判断拥有权限：user:update and user:delete  
	    Assert.assertTrue(subject().isPermittedAll("user:update", "user:delete"));  
	    //判断没有权限：user:view  
	    Assert.assertFalse(subject().isPermitted("user:view"));  
	} 
	@Test(expected = UnauthorizedException.class)  
	public void testCheckPermission () {  
	    login("classpath:shiro-permission.ini", "zhang", "123");  
	    //断言拥有权限：user:create  
	    subject().checkPermission("user:create");  
	    //断言拥有权限：user:delete and user:update  
	    subject().checkPermissions("user:delete", "user:update");  
	    //断言拥有权限：user:view 失败抛出异常  
	    subject().checkPermissions("user:view");  
	}   
	@Test  
	public void testIsPermitted1() {  
	        login("classpath:shiro-authorizer.ini", "zhang", "123");  
	        //判断拥有权限：user:create  
	        Assert.assertTrue(subject().isPermitted("user1:update"));  
	        Assert.assertTrue(subject().isPermitted("user2:update"));  
	        //通过二进制位的方式表示权限  
	        Assert.assertTrue(subject().isPermitted("+user1+2"));//新增权限  
	        Assert.assertTrue(subject().isPermitted("+user1+8"));//查看权限  
	        Assert.assertTrue(subject().isPermitted("+user2+10"));//新增及查看  
	  
	        Assert.assertFalse(subject().isPermitted("+user1+4"));//没有删除权限  
	  
	        Assert.assertTrue(subject().isPermitted("menu:view"));//通过MyRolePermissionResolver解析得到的权限  
	}  
}
