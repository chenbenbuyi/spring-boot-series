##### 国际化
> 根据 MessageSourceAutoConfiguration 自动配置，spring boot 中由国际化接口 MessageSource的子类 ResourceBundleMessageSource（mvc中需自己配置，spring boot 已经添加到自动化配置）来管理国际化资源文件
基本步骤：
 ① 直接在 resources 目录下（通常会新建 i18n 目录明确是国际化资源）,添加国际化的配置资源，如xxx.properties，是为默认配置，用于没有配置国际化语言信息的默认显示。再新增特定的语言配置文件如xxx_zh_CN.properties,idea 就会默认识别并统一管理,然后在 Resource Bundle 上右键可以快捷的 新建语言配置文件，并且通过 Resource Bundle 可以方便的添加不同语言的键值对映射值
 ② 语言变量抽取，在 thymeleaf 模板使用 #{XX} 取值的方式对应国际化文件中的键，实现国际化取值。
 ③ 配置好语言文件基础名 spring.message.basename（参见MessageSourceAutoConfiguration）后访问页面，默认就会根据浏览器语言信息(比如谷歌浏览器以排在最顶层的语言为默认显示，注意观察请求头中 Accept-Language的变化)显示默认的国际化环境语言
 ④ 自定义解析器 LocaleResolver 用来切换语言 