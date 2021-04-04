package cnblogs.chenbenbuyi.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chen
 * @date 2021/4/2 21:55
 */

@Configuration
public class DruidConfig {

    /**
     *  后台监控配置
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        // 现在要进行druid监控的配置处理操作
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String,String> initParameters = new HashMap<>();
        // 白名单,多个用逗号分割， 如果allow没有配置或者为空，则允许所有访问
        initParameters.put("allow", "127.0.0.1,192.168.111.119");
        // 黑名单,多个用逗号分割 (共同存在时，deny优先于allow)
        initParameters.put("deny", "192.168.1.100");
        // 控制台管理用户名和 密码  loginUsername 和 loginPassword 为固定参数名
        initParameters.put("loginUsername", "admin");
        initParameters.put("loginPassword", "123456");
        // 是否可以重置数据源，禁用HTML页面上的“Reset All”功能
        initParameters.put("resetEnable", "false");
        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean ;
    }

    /**
     *  过滤器配置
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean() ;
        filterRegistrationBean.setFilter(new WebStatFilter());
        //所有请求进行监控处理
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.css,/druid/*");
        return filterRegistrationBean ;
    }

    @Bean
    // 主要为了绑定配置文件中的配置
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }
}
