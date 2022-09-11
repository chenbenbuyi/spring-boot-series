package cnblogs.chenbenbuyi.controller;

import cnblogs.chenbenbuyi.config.CorsConfig;
import cnblogs.chenbenbuyi.config.ResponseAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author chen
 * 跨域问题本质是浏览器的行为，它的初衷是为了保证用户的访问安全，防止恶意网站窃取数据”，所以，实际解决中只需告诉浏览器这是一个安全的请求，“我是自己人”就行了，那怎么告诉浏览器这是一个正常的请求呢？
 * 只需要在返回头中设置“Access-Control-Allow-Origin”参数即可解决跨域问题，此参数就是用来表示允许跨域访问的原始域名的，当设置为“*”时，表示允许所有站点跨域访问
 * @Description
 * @Date 2022/9/11 13:24
 * 使用 @CrossOrigin 注解实现跨域；{@link CrossController}
 * 通过配置文件实现跨域；{@link CorsConfig#addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry)}
 * 通过 CorsFilter 对象实现跨域；{@link CorsConfig#corsFilter()}
 * 通过 Response 对象实现跨域 {@link CrossController#test2(javax.servlet.http.HttpServletResponse)}
 * 通过实现 ResponseBodyAdvice 实现跨域。参考 {@link ResponseAdvice}
 */

@RestController
@CrossOrigin(origins = "*")
public class CrossController {


    @RequestMapping("/cross")
    public String test() {
        return "通过控制器注解实现局部跨域";
    }

    /**
     * 此方式是解决跨域问题最原始的方式，但它可以支持任意的 Spring Boot 版本（早期的 Spring Boot 版本也是支持的）。但此方式也是局部跨域，它应用的范围最小，设置的是方法级别的跨域
     * 此种方式就不需要@CrossOrigin注解
     */
    @RequestMapping("/cross2")
    public String test2(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return "通过控制器注解实现局部跨域";
    }
}
