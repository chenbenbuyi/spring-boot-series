package cnblogs.chenbenbuyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @date: 2021/2/26 11:20
 * @author: chen
 * @desc: 多环境配置模块启动类
 */

@SpringBootApplication
public class ProfileApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ProfileApplication.class);
        /**
         * 禁用命令行参数,也即无法通过 java -jar xxx.jar --spring.profiles.active=test的形式赋值参数
         * 实测在idea中 -D配置启动参数不受影响
         */
//        springApplication.setAddCommandLineProperties(false);
        springApplication.run(args);
    }
}
