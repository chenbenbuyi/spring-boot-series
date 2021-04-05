package cnblogs.chenbenbuyi.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Sex implements IEnum {

    FEMALE(0, "女"),
    MALE(1, "男");

    Sex(int code, String desc) {
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

}