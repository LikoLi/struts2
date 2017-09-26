package org.liko.framework.struts2;

public class HelloAction {
	/*
	 * 1. 每次访问servelt时候, 都会执行service方法
	 * 	- 写类继承HttpServlet, 重写类里面的方法
	 * 	- 在web.xml中配置servlet访问路径
	 * 
	 * 2. 每次访问action, 每次访问action时候, 默认执行execute方法
	 *  - 配置action的访问路径
	 */
	
	public String execute() {
		return "ok";
	}
}
