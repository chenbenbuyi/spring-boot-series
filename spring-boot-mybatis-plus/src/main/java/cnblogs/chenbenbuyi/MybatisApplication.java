package cnblogs.chenbenbuyi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @date: 2020/11/2 10:39
 * @author: chen
 * @desc:
 *  @Mapper的注解由 mybatis 提供的，标识这个类是一个数据访问层的bean，并交给spring容器管理。并且可以省去之前的xml映射文件
 *  添加此注解之后可不用在 Spring boot 启动类上添加  @MapperScan("one.mapper") 。但是，建议在启动类上添加，不然每个类都添加也麻烦
 *  注解 @Mapper ，Mybatis 会通过拦截器将注解的接口生成动态代理的类，并交给 Spring 管理。缺点就是每个接口都需要注解
 *  如果启动类上添加了@MapperScan 标注了包扫描路径，则Mapper接口可不用重复添加 @Mapper 注解
 */

@MapperScan("cnblogs.chenbenbuyi.mapper")
@SpringBootApplication
public class MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }
}
