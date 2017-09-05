package cn.zy.permission;

import java.util.Arrays;
import java.util.Collection;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/** * @author  ZY
 * @date 创建时间：2017年9月5日 下午3:25:01 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class MyRolePermissionResolver implements RolePermissionResolver {  
    @Override  
    public Collection<Permission> resolvePermissionsInRole(String roleString) {  
        if("role1".equals(roleString)) {  
            return Arrays.asList((Permission)new WildcardPermission("menu:*"));  
        }  
        return null;  
    }  
}   