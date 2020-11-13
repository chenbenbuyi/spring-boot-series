package cnblogs.chenbenbuyi.config;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Scanner;

/**
 * 利用 AutoGenerator 编写 MyBatis-Plus 代码生成器 支持Entity、Mapper、Mapper XML、Service、Controller 等各个模块的代码 快速生成
 */
public class MyBatisPlusCodeGenerator {

    private static String scanner(String input) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(("请输入" + input + "："));
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StrUtil.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + input + "！");
    }

    /**
     * 代码生成器
     */
    public static void main(String[] args) {
        String modePath = "/spring-boot-mybatis-plus";
        AutoGenerator mpg = new AutoGenerator();

        /**
         * 全局配置
         */
        GlobalConfig gc = new GlobalConfig();
        gc.setAuthor("博客园：陈本布衣");
        // 获取程序当前路径后再设置输出路径
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + modePath+"/src/main/java");
        // 是否覆盖同名文件  默认false  是否打开输出目录 默认true
        gc.setFileOverride(false).setOpen(false);
        // 实体命名方式 %s 为占位符
        gc.setEntityName("%sEntity");
        gc.setMapperName("%sMapper");
        // 会生成 XxMapper.xml
        gc.setXmlName("%sMapper");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        gc.setServiceName("I%sService");
        gc.setIdType(IdType.AUTO);
        mpg.setGlobalConfig(gc);

        /**
         * 数据源配置
         */
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
//        dsc.setUrl("jdbc:sqlite:D:\\db\\test.db"); sqlite 文件数据库
//        dsc.setDriverName("org.sqlite.JDBC");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUrl("jdbc:mysql://localhost:3306/hello-boot?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&serverTimezone=Asia/Shanghai");
        dsc.setSchemaName("public");
        dsc.setUsername("root");
        dsc.setPassword("");
        mpg.setDataSource(dsc);

        /**
         * 包路径配置 根据配置的包名路径，会自动在工程中生成相应的包
         */
        PackageConfig pc = new PackageConfig();
        pc.setParent("cnblogs.chenbenbuyi");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        /**
         * 配置模板
         *  通常情况下，并不需要自定义模板，因为 mybatis-plus-generator 的 templates 包中已经罗列了三种模板引擎支持的对应模板
         */
        // 据源码，如果不配置，默认用的是 VelocityTemplateEngine 配置
        TemplateConfig templateConfig = new TemplateConfig();
        mpg.setTemplate(templateConfig);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());


        /**
         * 数据库策略配置，通过该配置，可指定需要生成哪些表或者排除哪些表
         */
        StrategyConfig strategy = new StrategyConfig();
        // 设置数据库表映射到数据实体类命名格式-驼峰命名
        strategy.setNaming(NamingStrategy.no_change);
        // 数据库字段下划线转序代码驼峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setInclude(scanner("请输入表名，多表用英文逗号分割").split(","));
        // 驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        //表和前缀处理
        mpg.setStrategy(strategy);

        mpg.execute();
    }

}
