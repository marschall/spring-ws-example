package com.github.marschall.springwsexample;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import com.mycompany.hr.schemas.HolidayRequest;

@Endpoint
public class HolidayEndpoint {

  private static final String NAMESPACE_URI = "http://mycompany.com/hr/schemas";

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "HolidayRequest")
  public void handleHolidayRequest(@RequestPayload HolidayRequest holidayRequest) throws Exception {
  }

}