package ustc.sse.springboot.component;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LRK
 * @project_name spring-boot-03-web-restfulcrud
 * @package_name ustc.sse.springboot.component
 * @date 2018/10/31 15:33
 * @description God Bless, No Bug!
 *  登陆的拦截器,检查是否已登录(Session是否存在'loginUser')
 *  写完后还需要注册到容器中
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 登陆检查
        if (request.getSession().getAttribute("loginUser") != null) {
            // 已登录,放行
            return true;
        }else {
            // 未登录,转发到登录页面
            try {
                request.setAttribute("msg","没有访问权限,请先登录!");
                request.getRequestDispatcher("/index.html").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  false;
        }
    }
}
