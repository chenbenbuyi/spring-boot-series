package cnblogs.chenbenbuyi.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @date: 2020/9/30 10:18
 * @author: chen
 * @desc: Mybatis plus 公共字段（常见如Id,创建、修改时间等）自动填充 这样，可以提取公共模型来统一定义，子类实体按需继承
 */

@Component
public class MybatisAutoFillHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        // 声明自动填充字段的逻辑。
        this.strictInsertFill(metaObject,"createTime", LocalDateTime.class,LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 声明自动填充字段的逻辑。
        this.strictUpdateFill(metaObject,"updateTime", LocalDateTime.class,LocalDateTime.now());
    }
}
