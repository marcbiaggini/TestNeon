package com.example.juanvilla.testneon.Areas.Main;

import android.content.Intent;

import com.example.juanvilla.testneon.Areas.Contact.ContactActivity_;
import com.example.juanvilla.testneon.Areas.Historic.HistoricActivity_;
import com.example.juanvilla.testneon.R;
import com.example.juanvilla.testneon.Utils.BaseActivity;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

  @Click(R.id.btnEnviarDinheiro)
  public void gotoEnviarActivity(){
    ContactActivity_.intent(this).flags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK).start();
  }

  @Click(R.id.btnHistorico)
  public void gotoHistorico(){
    HistoricActivity_.intent(this).flags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK).start();
  }
  @Override
  public void onBackPressed() {

  }
}
