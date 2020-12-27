package cn.itcast.web.shiro;

import cn.itcast.utils.Encrypt;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class CustomCredentialsMatcher extends SimpleCredentialsMatcher{

    @Override //密码比较器方法
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken uptoken=(UsernamePasswordToken)token;
        // 1 获取用户输入的密码
        String email = uptoken.getUsername();
        String password = new String(uptoken.getPassword());
        //1.1 将用户输入的密码进行加密
                //MD5加密(加盐)
        String MD5password = Encrypt.md5(password, email);

        // 2 获取数据库的密码
        String dataPassword =(String)info.getCredentials();
        // 3 比较

        return MD5password.equals(dataPassword);
    }
}
