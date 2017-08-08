package com.example.api.Models.Contact;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Wither;

/**
 * Created by juan.villa on 06/08/17.
 */

@Wither
@Value
@NoArgsConstructor(force = true)
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactInfo {
  @JsonProperty("name")
  private Name name;
  @JsonProperty("phone")
  private String phone;
  @JsonProperty("picture")
  private Picture picture;
  private String clienteId;
  private String Valor;
  private String fullName;
  private String imageUrl;
}
