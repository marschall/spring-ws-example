package com.github.marschall.springwsexample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

  @Bean(name = "countries")
  public Wsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) {
    return new SimpleWsdl11Definition(new ClassPathResource("hr.wsdl", getClass()));
  }

  @Bean
  public XsdSchema countriesSchema() {
    return new SimpleXsdSchema(new ClassPathResource("countries.xsd", getClass()));
  }

  @Bean
  public HolidayEndpoint holidayEndpoint() {
    return new HolidayEndpoint();
  }
}