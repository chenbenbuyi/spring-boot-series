package cnblogs.chenbenbuyi.enums;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @date: 2021/1/27 11:12
 * @author: chen
 * @desc: 枚举转换工厂类
 */
public class StringToEnumConverterFactory implements ConverterFactory<String, IEnum> {

    /**
     *  缓存枚举类型和转换器映射关系
     */
    private static final Map<Class, Converter> CONVERTER_MAP = new HashMap<>();

    @Override
    public <T extends IEnum> Converter<String, T> getConverter(Class<T> targetType) {
        Converter converter = CONVERTER_MAP.get(targetType);
        if (converter == null) {
            converter = new StringToEnumConverter<>(targetType);
            CONVERTER_MAP.put(targetType, converter);
        }
        return converter;
    }


    /**
     * 自定义的转换器实现自定义的转换逻辑,比如页面传枚举字面量、code值甚至其它字段如本示例中的 desc 都可以完美转换
     * 经测试，自定义转换器后支持普通Bean方式、@RequestParam 方式以及 @PathVariable 方式的 Rest api 路径方式传值、但是不支持@RequestBody 传值方式。
     *  @RequestBody 传值方式该转换器不起作用，可通过 @JsonCreator 注解进行手动转换 {@link Gender#converter}
     *  通过 自定义转换器和@JsonCreator 互补，基本能满足常见的几种页面传值方式
     *
     */
    class StringToEnumConverter<T extends IEnum> implements Converter<String, T> {
        private Map<String, T> enumMap = new HashMap<>();

        private StringToEnumConverter(Class<T> enumType) {
            T[] enums = enumType.getEnumConstants();
            for (T e : enums) {
                // 从 code 值建立映射
                enumMap.put(e.getCode() + "", e);
                // 从描述值建立映射
                enumMap.put(e.getDesc(), e);
                // 从枚举字面量建立映射
                enumMap.put(((Enum) e).name(), e);
            }
        }

        @Override
        public T convert(String source) {
            T result = enumMap.get(source);
            if (result == null) {
                throw new IllegalArgumentException("No element matches："+source);
            }
            return result;
        }
    }

}
