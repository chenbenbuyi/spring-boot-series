### Spring boot 以及结合Maven 的一些多环境配置

## Spring boot 自带的多环境配置
> 通过命名不同的配置文件，通常为application-name.yml 来区分不同环境的配置文件
> 启动时添加激活不同配置的启动参数,如java -jar xxx.jar --spring.profiles.active=test或者在主配置文件（application.yml）中配置spring.profiles.active=test
> 在命令运行中，两个连续的减号 -- 就是对 主配置文件中的属性进行赋值的意思，可通过配置禁用


## Maven 自带的多环境配置（适用于所有的maven项目）

>在主配置文件夹如application.yml中定义激活变量如 spring.profiles.active=@profile.active@
 其中，profile.active 变量在maven的pom.xml 中根据设置赋值。在maven打包的时候指定要打包的配置环境，如-P test，传入test的就是profile.active变量的值。
 配置之后，可以在Idea右侧的Maven选项可中看到Profiles，能够看到默认的选择。
 此外，还可以配置一些激活条件，如jdk版本，操作系统等。详见本模块中的pom.xml中的配置
 
## 资源过滤
> 主要是针对打包的问题，在配置了多环境不同的配置文件后，如何过滤掉不同环境下不需要的配置文件。