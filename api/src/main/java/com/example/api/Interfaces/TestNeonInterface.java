package com.example.api.Interfaces;

import org.androidannotations.rest.spring.annotations.Accept;
import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Field;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Header;
import org.androidannotations.rest.spring.annotations.Headers;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.MediaType;
import org.androidannotations.rest.spring.api.RestClientErrorHandling;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Map;

import com.example.api.Models.Transfers.UserData;
import lombok.experimental.NonFinal;

/**
 * Created by juan.villa on 06/08/17.
 */
@NonFinal
@Rest(rootUrl = "http://processoseletivoneon.azurewebsites.net", converters = { GsonHttpMessageConverter.class ,StringHttpMessageConverter.class,MappingJackson2HttpMessageConverter.class,FormHttpMessageConverter.class})
public interface TestNeonInterface extends RestClientErrorHandling {

  @Get("/GenerateToken?nome={nome}&email={email}")
  String getToken(@Path final String nome, @Path final String email);

  @Get("/GetTransfers?token={token}")
  ArrayList<UserData> getTransfer(@Path final String token);

  @Post("/SendMoney")
  @Accept(MediaType.APPLICATION_JSON)
  @Header(name = "Content-Type", value = "application/json; charset=utf-8")
  Boolean sendMoney(@Body Map<String, Object> formData);
}