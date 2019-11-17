package com.lemon.shiro.factory;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * 重写DefaultWebSubjectFactory.createSubject
 *
 * @author sjp
 * @date 2019/4/30
 **/
public class StatelessDefaultSubjectFactory extends DefaultWebSubjectFactory {

	@Override
    public Subject createSubject(SubjectContext context) {
		// 不创建session
		context.setSessionCreationEnabled(false);
		return super.createSubject(context);
	}

}