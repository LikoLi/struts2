## Struts2 框架
1. Struts2 入门
	- Struts2 概述
	- Struts2入门案例
	- Struts2底层执行过程
	- Struts2相关配置
	- Struts2的action创建
	- Struts2的action访问

2. Struts2 数据操作

3. Struts2 值栈(页面能取到)
	

4. Struts2 拦截器

#### Struts2概述
- Struts2框架是应用JAVAEE三层结构中的WEB层框架  
- Struts2框架在Struts1和webwork基础之上发展的全新的框架  
- Struts2解决的问题: 维护请求
- Struts2版本: struts-2.3.24-all.jar
- Web层常用框架: struts2/SpringMVC

#### Struts2框架入门
1. 导入jar包
2. 创建action(HelloAction.java)
3. 配置action访问路径
	3.1. 创建struts2核心配置文件
		- 核心配置文件名称和位置是固定的
		- 位置必须在src下, 名称 struts.xml
	3.2.引入dtd约束
	3.3  配置访问路径(http://127.0.0.1:8080/struts2/hello.action)
		- 这一步的时候还不能访问, 需要配置过滤器
	3.4  配置过滤器
		```
			<filter>
			      <filter-name>struts2</filter-name>
			      <filter-class>org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter</filter-class>
			  </filter>
			  <filter-mapping>
			      <filter-name>struts2</filter-name>
			      <url-pattern>/*</url-pattern>
			  </filter-mapping>
		```

### Struts2执行过程
	- url请求
	- struts2过滤器
		- 获取请求路径,得到路径里面的请求值(hello)
		- 在src下面找到struts.xml, 使用dom4j解析, 得到xml文件中的内容
		- 使用(hello) 在xml文件中找action标签, 匹配name属性值是否一样
		- 使用匹配的action标签, 得到class属性的值, 使用反射调用对应的方法
		- 得到方法的返回值, 匹配值action标签里面匹配result标签里面的name属性, 匹配成功, 跳转到配置页面中去
		
	
