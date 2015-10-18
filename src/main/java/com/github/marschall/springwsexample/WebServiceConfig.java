package com.github.marschall.springwsexample;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.SoapMessageFactory;
import org.springframework.ws.soap.axiom.AxiomSoapMessageFactory;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

  @Bean(name = "holiday")
  public Wsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) {
    return new SimpleWsdl11Definition(new ClassPathResource("hr.wsdl"));
  }

  @Bean(name = "hr")
  public XsdSchema countriesSchema() {
    return new SimpleXsdSchema(new ClassPathResource("hr.xsd"));
  }

  @Bean
  public HolidayEndpoint holidayEndpoint() {
    return new HolidayEndpoint();
  }

  @Bean
  public EndpointInterceptor validatingInterceptor() {
    PayloadValidatingInterceptor validatingInterceptor = new PayloadValidatingInterceptor();
    validatingInterceptor.setXsdSchema(this.countriesSchema());
    validatingInterceptor.setValidateRequest(true);
//    validatingInterceptor.setValidateResponse(true);
    return validatingInterceptor;
  }

  @Override
  public void addInterceptors(List<EndpointInterceptor> interceptors) {
    interceptors.add(this.validatingInterceptor());
  }

  @Bean
  public SoapMessageFactory messageFactory() {
//    SaajSoapMessageFactory messageFactory = new SaajSoapMessageFactory();
////    messageFactory.setSoapVersion(SoapVersion.SOAP_12);
//    return messageFactory;
    AxiomSoapMessageFactory messageFactory = new AxiomSoapMessageFactory();
//    messageFactory.setPayloadCaching(true);
    return messageFactory;
  }

}