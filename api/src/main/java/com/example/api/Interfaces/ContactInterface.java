package com.example.api.Interfaces;

import com.example.api.Models.Contact.ContactInfo;
import com.example.api.Models.Contact.ResponseContact;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.RestClientErrorHandling;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;

import lombok.experimental.NonFinal;

/**
 * Created by juan.villa on 06/08/17.
 */
@NonFinal
@Rest(rootUrl = "https://randomuser.me/api/", converters = { StringHttpMessageConverter.class,MappingJackson2HttpMessageConverter.class})
public interface ContactInterface extends RestClientErrorHandling {

  @Get("?results=15")
  ResponseContact getContacts();
}
