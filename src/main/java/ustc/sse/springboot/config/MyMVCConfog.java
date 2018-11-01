package ustc.sse.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ustc.sse.springboot.component.LoginHandlerInterceptor;
import ustc.sse.springboot.component.MyLocaleResolver;

/**
 * @author LRK
 * @project_name spring-boot-03-web-restfulcrud
 * @package_name ustc.sse.springboot.config
 * @date 2018/10/31 11:04
 * @description God Bless, No Bug !
 */
@Configuration
public class MyMVCConfog implements WebMvcConfigurer{
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 浏览器发送 /ustc 请求会来到 success页面
        registry.addViewController("/ustc").setViewName("success");
    }

    @Bean // 将组件注册在容器中才会起作用
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer configurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }
            // 注册拦截器,也可以写在外面
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                // SpringBoot1.5 已经做好了静态资源映射,不需要另外处理excludePathPatterns,可以直接访问
                // 但是SpringBoot2.0 之后静态资源默认也会拦截,需要额外放行
                registry.addInterceptor(new LoginHandlerInterceptor())
                        .addPathPatterns("/**").excludePathPatterns("/index.html","/","/user/login",
                        "/static/asserts/**","/webjars/**");
            }
        };
        return configurer;
    }
    @Bean  // 在容器里添加自定义的LocaleResolver
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
