package cnblogs.chenbenbuyi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @date: 2021/2/23 10:36
 * @author: chen
 * @desc:
 */

@SpringBootApplication
public class LoggingApplication  implements CommandLineRunner {

    /**
     *  启动方法内捕捉异常是因为一开始改模块启动没任何反应，虚拟机 Process finished with exit code 1
     *  没有任何异常抛出，那说明没有捕获运行时异常，于是自己手动 catch,果然，返现是配置文件错误——日志文件大小的配置参数有误
     *  java.lang.IllegalArgumentException: String value [10M] is not in the expected format.
     *  原因其实不在于格式错误，而是自己没有添加logback日志配置文件
     */
    public static void main(String[] args) {
        try{
            SpringApplication.run(LoggingApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *  实现 CommandLineRunner 接口或者 ApplicationRunner 接口可以在服务启动完成后执行一些操作
     *  参见源码 {@link org.springframework.boot.SpringApplication#run(java.lang.String...)}
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("LoggingApplication 应用启动完成后执行。。。。");
    }
}
