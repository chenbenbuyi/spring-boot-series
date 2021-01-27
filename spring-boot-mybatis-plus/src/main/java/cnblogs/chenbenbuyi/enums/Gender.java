package cnblogs.chenbenbuyi.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 处理思路：
 * 根据Spring MVC 对枚举参数的处理，自定义参数处理规则覆盖默认的参数处理规则,即注入特定类型自定义转换器实现页面传值到后台枚举的自动转换
 */

public enum Gender implements IEnum {

    FEMALE(0, "女"),
    MALE(1, "男");

    Gender(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    @EnumValue
    private int code;

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

    /**
     *  处理@RequestBody请求类型的枚举值转换
     *  该注解用在对象的反序列化时，指定特定的构造函数或者工厂方法。在反序列化时，Jackson默认会调用对象的无参构造函数
     *  该注解只能用在静态方法或者构造方法
     */
    @JsonCreator
    public static Gender converter(String value){
        try{
            return Gender.valueOf(value);
        }catch (IllegalArgumentException e){
            for (Gender gender : Gender.values()) {
                try {
                    if (gender.code==Integer.parseInt(value)) {
                        return gender;
                    }
                }catch (NumberFormatException n) {
                    if (gender.desc.equals(value)) {
                        return gender;
                    }
                }
            }
            throw new IllegalArgumentException("No element matches: "+value);
        }
    }

}