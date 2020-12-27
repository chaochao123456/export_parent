package cn.itcast.web.shiro;

import cn.itcast.domain.Module;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;

public class AuthRealm extends AuthorizingRealm{

    @Autowired
    private UserService userService;

    /*
    * 参数principalCollection：安全数据的集合（user）
    * 返回值AuthorizationInfo: 用户的所有权限
    * */
    @Override //授权  执行实机：访问到了权限过滤器设置的资源时
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //1 获取认证的安全数据user
        User user =(User)principalCollection.getPrimaryPrincipal();
        //2 查询该user具备的权限
        List<Module> modules = userService.FindByUserModule(user);
        //3 将list的数据循环遍历封装权限名称给set集合
        HashSet<String> set = new HashSet<>();
        for (Module module : modules) {
            set.add(module.getName());
        }
        System.out.println(set);
        //4 将权限封装给AuthorizationInfo
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(set);
        //5 返回
        return info;
    }

    @Override // 认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken uptoken=(UsernamePasswordToken)authenticationToken;
        // 1 获取到页面书写的用户名和密码
        String email = uptoken.getUsername();
        String password =new String(uptoken.getPassword());
        // 2 根据用户名去数据库查询该用户
        User user = userService.findByEmail(email);
        if(user!=null){
            // 3 将书写的密码和数据库查询的用户名比较（密码比较器）
                    // 参数1: user对象
                    // 参数2: 数据库中的密码
                    // 参数3: 随便写 当前realm的名字
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
            return info; // 自动调用密码比较器
        }

        return null; //抛异常
    }
}
