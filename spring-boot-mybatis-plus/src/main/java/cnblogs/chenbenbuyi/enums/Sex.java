package cnblogs.chenbenbuyi.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * MP 对枚举常量值的数据库字段映射配置
 * 通用需求：例如性别、单位等固定常量，项目中为防止输入错误，会定义枚举的形式，这就需要配置持久层的数据实体和枚举值的映射关系
 * 两种方式：
 * ①  @EnumValue 注解，告诉MP 数据实体枚举字段真正的映射值 但是这种方式前端输入也必须是枚举实例的字面量，如 FEMALE 或 MALE，输入1或0无法转换成功.
 * ②  实现 MP 提供的 {@link com.baomidou.mybatisplus.annotation.IEnum} 泛型接口，重写其 getValue 方法，指定返回值
 *
 * 数据库存入的是枚举值如 0,1 之类简单数据，但页面展示数据需要经过格式化映射，
 * 譬如 0 代表女，1 代码男，如果页面单独根据定义的code定义一套格式化代码，后期状态值有修改，势必影响到前后端的同步修改，
 * 这种情况下，除了针对数据库的存储数据 0 或 1 ，还需要响应到前端的数据是经过格式化的，否则枚举的描述信息就毫无意义。
 * 幸运的是，由于我们前后端交互基本都是采用json格式，而Spring boot 默认json处理框架 jackson的 @JsonValue 注解很容易帮助我们将期望的响应格式信息响应到前端
 * 使用方式也很简单：直接在枚举显示字段上的标注该注解即可
 * 参考：https://www.cnblogs.com/yumiaoxia/p/10414212.html
 * 参考：https://blog.csdn.net/alinyua/article/details/86383254?utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~all~baidu_landing_v2~default-5-86383254.nonecase&utm_term=springboot%20%E6%8E%A5%E6%94%B6%E6%9E%9A%E4%B8%BE&spm=1000.2123.3001.4430
 */

public enum Sex implements IEnum{

    FEMALE(0, "女"),
    MALE(1, "男");

    Sex(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * @EnumValue 当实体类的属性是普通枚举，且是其中一个字段，使用该注解来标注枚举类里的属性的对应字段
     * ps:没有 @EnumValue 完全并不影响 Spring MVC 接受页面枚举字面量的值，但是影响数据库和枚举字段映射
     * {@link EnumValue} 参见其注释
     */
    @EnumValue
    private int code;

    /**
     * jackson 的 @JsonValue 注解控制枚举用哪个字段作为值进行序列化,可标注在字段和getter方法上，一个类只能有一个
     * 推荐标注在getter方法上。因为标注在字段上，swagger参数列表只显示字面值，但如果实际不能使用字面值传值，会给接口调用人员造成误解
     */
//    @JsonValue
    private String desc;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    @JsonValue
    public String getDesc() {
        return desc;
    }

}