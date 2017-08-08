package com.example.juanvilla.testneon.Areas.Splash.Beans;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.api.Helpers.ApiServices;
import com.example.api.Models.Contact.ResponseContact;
import com.example.juanvilla.testneon.Areas.Main.MainActivity_;
import com.example.juanvilla.testneon.R;
import com.google.gson.Gson;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by juan.villa on 06/08/17.
 */
@EBean
public class InitialConfigBean {

  @RootContext
  Context context;

  SharedPreferences appSharedPrefs;
  SharedPreferences.Editor prefsEditor;
  Gson gson;

  @AfterInject
  public void init(){
    appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    prefsEditor = appSharedPrefs.edit();
    gson = new Gson();
  }

  public boolean hasContacts(){
    String json = appSharedPrefs.getString("Contacts", "");
    return json.equals("")? false:true;
  }

  public void setInitialContacts(ResponseContact response) {
    if(response!=null) {
      String json = gson.toJson(response);
      prefsEditor.putString("Contacts", json);
      prefsEditor.commit();
      generateToken();
    }
  }

  public void setToken(String token) {
    if(token!=null) {
      token = token.replaceAll("\"", "");
      prefsEditor.putString("Token", token);
      prefsEditor.commit();
    }
    MainActivity_.intent(context).flags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK).start();
  }

  public String getToken(){
    return appSharedPrefs.getString("Token", "");
  }

  public ResponseContact getContacts(){
    return gson.fromJson(appSharedPrefs.getString("Contacts", ""), ResponseContact.class);
  }

  @Background
  public void generateToken(){
    setToken(ApiServices.getTestNeonInterface().getToken(context.getResources().getString(R.string.name), context.getResources().getString(R.string.email)));
  }

}
