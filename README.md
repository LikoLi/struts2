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

### Struts2源码分析(org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter)
1. 过滤器在服务器启动时候创建, 创建过滤器时候执行init方法
	1.1. 在init方法中主要加载配置文件
		- 包含自己创建的配置文件和struts2自带的配置文件(struts.xml, web.xml)

### Struts2 配置
#### Struts2 核心配置文件(struts.xml[名称位置是固定的])
1. 在配置文件中主要有三个标签(package, action, result)
1.1 package
	- 类似于代码包, 区别不同的action, 配置action必须首先写package标签, 在package里面才能配置action
1.1.1 package标签属性
	- name属性: 属性值与功能本身没有关系, 在一个配置文件中可以写多个package标签, name属性 值不能相同
	- extends属性: 属性值固定(struts-default), 写了这个属性之后, 在package里面配置的类具有了action功能.
	- namespace属性：namespace 属性值和action标签里面的name属性值构成访问路径
	
1.2 action(配置action访问路径)
	- name属性：namespace 属性值和action标签里面的name属性值构成访问路径,在package标签里面可以写多个action, 但是action的name属性值不能相同
	- class属性：action类的全路径
	- method属性：[比如在action里面默认执行execute方法, 但是在action里面还可以写其他方法], 让action里面多个方法执行, 使用method配置
	
1.3 result(根据action的返回值, 配置到返回不同的路径里面)
	- name属性: 和方法返回值一样
	- type属性: 配置如何到路径中(转发或重定向), type默认属性值是转发

#### 修改Struts2默认常量
1. 常用方式
	- 在struts.xml 中进行配置
	```
		<constant name="struts.i18n.encoding" value="UTF-8"></constant>
	```
	- 在src下面创建struts.propertis, 进行修改
	- 在web.xml 中配置
2. 常用常量
	- struts.i18n.encoding=UTF-8
	> 表单提交数据到action里面, 在action可以获取表单提交数据, 表单提交数据有中文, 会有乱码. 如果在action获取通过post方式提交的表单里面的中文, 中文乱码问题解决了, 不需要自己处理.

### 分模块开发
1.单独写配置文件, 把配置文件引入到核心配置文件里面
```
	<include file="org/liko/framework/struts2/hello.xml"></include>
```

### Action编写方式
1. action编写有三种方式
	- 创建普通类, 不继承任何类, 也不实现任何接口(HelloAction.java)
	- 创建类, 实现Action接口
	```
		public class UserAction implements Action{
			public String execute() throws Exception {
				return LOGIN;
			}
		}
	```
	- 创建类, 继承类ActionSupport(ActionSupport 实现了 Action)
	```
		public class PersonAction extends ActionSupport{
			@Override
			public String execute() throws Exception {
				return SUCCESS;
			}
		}
	```
### 访问Action的方法(重点)
	- 用action标签的method属性, 在这个属性里面写执行的action的方法
	- 使用通配符实现
	- 动态访问实现(不用)
