package com.example.juanvilla.testneon.Areas.Contact.Interfaces;

import com.example.api.Models.Contact.ContactInfo;

/**
 * Created by juan.villa on 07/08/17.
 */

public interface CloseInterface {
  void onClose(String name, String amount);
  void onCallFunction(ContactInfo contactInfo);
}
