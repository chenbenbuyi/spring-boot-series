package cnblogs.chenbenbuyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date: 2021/3/22 14:36
 * @author: chen
 * @desc: 测试docker打包部署模块
 */

@RestController
@SpringBootApplication
public class DockerApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DockerApplication.class);
        springApplication.run(args);
    }


    @GetMapping("/docker")
    public String index(){
        return "I am in docker";
    }
}
