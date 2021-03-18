package cn.itcast.web.controller;

import cn.itcast.domain.Module;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LoginController extends BaseController{

    @Autowired
    private UserService userService;
    /*
    * 登录校验
    *      成功 home/main显示该用户信息
    *      失败 login.jsp显示错误信息
    *
    * */
	/*@RequestMapping("/login")
	public String login(String email,String password) {
        //1 判断用户名(Email)和密码是否为空
                        // 没写：继续登录页面
        if(UtilFuns.isEmpty(email) || UtilFuns.isEmpty(password)){
            // 到登录页面
            return "forward:login.jsp";
        }
        //2 带着用户名去查询该用户
        User user=userService.findByEmail(email);
        //3 将输入的密码和该用户的密码匹配
        if(user==null || !user.getPassword().equals(password)){
            // 失败：到登录页面显示错误信息
            request.setAttribute("error","用户名或者密码错误");
            return "forward:login.jsp";
        }

        // 成功："home/main"
        List<Module> moduleList=userService.FindByUserModule(user);
        session.setAttribute("modules",moduleList);

        session.setAttribute("loginUser",user);
	    return "home/main";
	}*/


    /* shiro做登录认证 */
    @RequestMapping("/login")
    public String login(String email,String password) {
        try {
            //1 获取subject对象
            Subject subject = SecurityUtils.getSubject();
            //2 保存用户名和密码
            UsernamePasswordToken upTonken=new UsernamePasswordToken(email,password);
            //3 调用realm进行登录认证
            subject.login(upTonken);
            //4 拿着认证的对象获取该对象的权限
            //5 保存到session
            User user =(User)subject.getPrincipal();
            List<Module> moduleList=userService.FindByUserModule(user);
            session.setAttribute("modules",moduleList);
            session.setAttribute("loginUser",user);
        }catch (Exception e){
            // 失败：到登录页面显示错误信息
            request.setAttribute("error","用户名或者密码错误");
            return "login";
        }
        return "home/main";
    }





    //退出
    @RequestMapping(value = "/logout",name="用户登出")
    public String logout(){
        SecurityUtils.getSubject().logout();   //登出
        return "forward:login.jsp";
    }

    @RequestMapping("/home")
    public String home(){

	    return "home/home";
    }
}
