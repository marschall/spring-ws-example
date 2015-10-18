package com.github.marschall.springwsexample;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.ws.transport.http.support.AbstractMessageDispatcherServletInitializer;

public class SpringMessageDispatcherServletInitializer
        extends AbstractMessageDispatcherServletInitializer {

  @Override
  protected WebApplicationContext createServletApplicationContext() {
    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    context.register(WebServiceConfig.class);
    return context;
  }

  @Override
  protected WebApplicationContext createRootApplicationContext() {
    return null;
  }

}
