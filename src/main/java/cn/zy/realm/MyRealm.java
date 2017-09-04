package cn.zy.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

/** * @author  ZY
 * @date 创建时间：2017年9月4日 下午3:34:18 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class MyRealm implements Realm{

	@Override
	public String getName() {
		return "myRealm";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		//仅支持UsernamePasswordToken类型的token
		return token instanceof UsernamePasswordToken;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		String username = (String)token.getPrincipal();//得到用户名
		String password = new String((char[])token.getCredentials());//得到密码
		if(!"zhang".equals(username)){
			throw new UnknownAccountException();//用户名错误
		}
		if(!"123".equals(password)){
			throw new IncorrectCredentialsException();//密码错误
		}
		 //如果身份认证验证成功，返回一个AuthenticationInfo实现；  
        return new SimpleAuthenticationInfo(username, password, getName());  
	}
	
}
