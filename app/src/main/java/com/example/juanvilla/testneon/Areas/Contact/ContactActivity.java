package com.example.juanvilla.testneon.Areas.Contact;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.api.Helpers.ApiServices;
import com.example.api.Models.Contact.ContactInfo;
import com.example.juanvilla.testneon.Areas.Contact.Adapters.ContactAdapter;
import com.example.juanvilla.testneon.Areas.Contact.Beans.ContactManagerBean;
import com.example.juanvilla.testneon.Areas.Contact.Interfaces.CloseInterface;
import com.example.juanvilla.testneon.Areas.Splash.Beans.InitialConfigBean;
import com.example.juanvilla.testneon.R;
import com.example.juanvilla.testneon.Utils.BaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.Map;

@EActivity(R.layout.activity_contact)
public class ContactActivity extends BaseActivity implements CloseInterface {

  @ViewById(R.id.contactList)
  RecyclerView contactList;

  @ViewById(R.id.toolbar)
  Toolbar toolbar;

  @Bean
  InitialConfigBean initialConfigBean;

  @Bean
  ContactManagerBean contactManagerBean;

  @AfterViews
  public void init(){
    setSupportActionBar(toolbar);
    setTitle(getResources().getString(R.string.title_activity_contact));
    ContactAdapter contactAdapter = new ContactAdapter(initialConfigBean.getContacts().getResults() , this);
    contactList.setLayoutManager((new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)));
    contactList.setAdapter(contactAdapter);
  }

  @Override
  public void onBackPressed() {
    finish();
  }

  @Override
  public void onClose(String id, String amount) {
    contactManagerBean.getSwipeLayout().setEnabled(true);
    contactManagerBean.getSwipeLayout().setRefreshing(true);
    sendTranfer(id,amount);
  }

  @Override
  public void onCallFunction(ContactInfo contactInfo) {
    contactManagerBean.setTransferFragment(contactInfo);
  }

  @Background
  public void sendTranfer(String id, String amount) {

    Map<String, Object> moneyMap = new HashMap<>();
    moneyMap.put("Id",Integer.valueOf(id));
    moneyMap.put("ClienteId",Integer.valueOf(id));
    moneyMap.put("Token",initialConfigBean.getToken());
    moneyMap.put("Valor",Double.valueOf(amount));

    contactManagerBean.validateTransfer(ApiServices.getTestNeonInterface().sendMoney(moneyMap));
  }
}
