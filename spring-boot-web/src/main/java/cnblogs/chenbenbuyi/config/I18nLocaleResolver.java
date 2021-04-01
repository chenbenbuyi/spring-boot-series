package cnblogs.chenbenbuyi.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author chen
 * @date 2021/4/1 20:42
 * @Description 国际化解析器
 * 默认的语言解析 见 {@link WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter#localeResolver()}
 */
@Component("localeResolver")
public class I18nLocaleResolver implements LocaleResolver {
    /**
     * Locale 是表示语言和国家/地区信息的本地化类，它是创建国际化应用的基础
     * 通过 LocaleResolver 可以获取该区域信息对象
     * 在 Spring Boot 环境中，默认配置了该对象 {@link WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter#localeResolver()}
     *  根据该方法，默认环境下就是使用的 {@link AcceptHeaderLocaleResolver} 来解析获取区域信息——即从请求头中获取请求区域信息来完成的国际化显示
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String lang = request.getParameter("lang");
        // 获取当前默认的语言区域本地环境
        Locale aDefault = Locale.getDefault();
        if (!StringUtils.isEmpty(lang)) {
            // 分割出语言代码和国家代码
            String[] split = lang.split("_");
            return new Locale(split[0], split[1]);
        }
        return aDefault;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
