package cnblogs.chenbenbuyi.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.springframework.context.annotation.Bean;

/**
 *  Mybatis Plus 3.4.0版本之前 分页插件配置，3.4.0 之后已经标记为废弃，应该使用MybatisPlusInterceptor进行分页配置
 *  {@link MybatisPlusConfig}
 */
//@Configuration
//@MapperScan(basePackages = {"cnblogs.chenbenbuyi.mapper"})
public class MybatisPlusConfig_old {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }
}