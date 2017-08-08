package com.example.api.Helpers;

import android.content.Context;

import com.example.api.Error.RestErrorHandler;
import com.example.api.Interfaces.ContactInterface;
import com.example.api.Interfaces.ContactInterface_;
import com.example.api.Interfaces.TestNeonInterface;
import com.example.api.Interfaces.TestNeonInterface_;

/**
 * Created by juan.villa on 06/08/17.
 */

public class ApiServices {

  private static TestNeonInterface testNeonInterface = null;

  protected static ContactInterface contactInterface = null;

  /**
   * Initialize services.
   *
   * @param context      the context
   * @param errorHandler the error handler
   */
  public static void initializeServices(Context context, RestErrorHandler errorHandler) {
    testNeonInterface = new TestNeonInterface_(context);
    testNeonInterface.setRestErrorHandler(errorHandler);

    contactInterface = new ContactInterface_(context);
    contactInterface.setRestErrorHandler(errorHandler);
  }

  public static TestNeonInterface getTestNeonInterface() {
    return testNeonInterface;
  }

  public static ContactInterface getContactInterface() {
    return contactInterface;
  }
}
