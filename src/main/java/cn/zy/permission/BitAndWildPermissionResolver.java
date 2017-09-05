package cn.zy.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/** * @author  ZY
 * @date 创建时间：2017年9月5日 下午3:23:22 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class BitAndWildPermissionResolver implements PermissionResolver {  
    @Override  
    public Permission resolvePermission(String permissionString) {  
        if(permissionString.startsWith("+")) {  
            return new BitPermission(permissionString);  
        }  
        return new WildcardPermission(permissionString);  
    }  
}
