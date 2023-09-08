package com.loca.common.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextManager implements ApplicationContextAware {
	private static final ApplicationContextManager instance = new ApplicationContextManager();
	private ApplicationContext applicationContext = null;

	public static ApplicationContextManager getInstance() {
		return instance;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		instance.applicationContext = applicationContext;
	}

	public <T> T getBean(Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}

	public Object getBean(String className) {
		return applicationContext.getBean(className);
	}

	@SuppressWarnings("unchecked")
	public <T> T getBean(String className, Class<T> clazz) {
		return (T) applicationContext.getBean(className);
	}
}
