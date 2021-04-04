### 错误踩坑
> 1 .网络传输的对象必须实现序列化接口
> 2 .java.lang.IllegalStateException: zookeeper not connected  
> 基本建议是增大连接的超时时间或版本本身bug建议降低到较低的版本，但自己测试发现，windows上开启的zookeeper注册中心能测试成功，linux利用docker镜像启动的始终连接不上，防火墙确认已关闭，尚不清楚原因