package ustc.sse.springboot.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author LRK
 * @project_name spring-boot-03-web-restfulcrud
 * @package_name ustc.sse.springboot.component
 * @date 2018/10/31 14:22
 * @description
 * God Bless, No Bug!
 *  链接里附带区域化信息
 */
public class MyLocaleResolver implements LocaleResolver {
    /**
     * 解析链接里的区域化信息
     * @param request
     * @return
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String info = request.getParameter("l");
        Locale locale = Locale.getDefault();
        if (!StringUtils.isEmpty(info)){
            String[] split = info.split("_");
            locale = new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
