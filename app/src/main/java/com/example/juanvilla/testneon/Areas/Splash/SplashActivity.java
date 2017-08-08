package com.example.juanvilla.testneon.Areas.Splash;

import android.content.Intent;

import com.example.api.Helpers.ApiServices;
import com.example.juanvilla.testneon.Areas.Main.MainActivity_;
import com.example.juanvilla.testneon.Areas.Splash.Beans.InitialConfigBean;
import com.example.juanvilla.testneon.R;
import com.example.juanvilla.testneon.Utils.BaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;


@EActivity(R.layout.activity_splash)
public class SplashActivity extends BaseActivity {

  @Bean
  InitialConfigBean initialConfigBean;

  @AfterViews
  public void init(){
    if(!initialConfigBean.hasContacts()){
      getContact();
    }else {
      MainActivity_.intent(getBaseContext()).flags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK).start();
    }
  }

  @Background
  public void getContact(){
    initialConfigBean.setInitialContacts(ApiServices.getContactInterface().getContacts());
  }

}
