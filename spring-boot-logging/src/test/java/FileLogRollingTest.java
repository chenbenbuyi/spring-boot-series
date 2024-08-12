import cn.hutool.core.date.DateTime;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.LineHandler;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author csxian
 * @Description 日志文件滚动查询
 * @Date 2022/12/21 16:58
 */
public class FileLogRollingTest {

    public static void main(String[] args) throws Exception {
        System.out.println(readLines(0, 1));
    }


    public static String readLines(int start, int end) {
        String path = "C:\\Users\\admin\\Desktop\\log.txt";
        AtomicInteger atomicInteger = new AtomicInteger(1);
        FileUtil.readLines(new File(path), Charset.defaultCharset(), new LineHandler() {
            @Override
            public void handle(String line) {
                atomicInteger.getAndIncrement();
                System.out.println("第"+atomicInteger.get()+"行："+line);

            }
        });
        return "";
    }
}
