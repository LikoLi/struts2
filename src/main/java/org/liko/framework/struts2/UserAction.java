package org.liko.framework.struts2;

import com.opensymphony.xwork2.Action;

public class UserAction implements Action{
	public String execute() throws Exception {
		return LOGIN;
	}
}
