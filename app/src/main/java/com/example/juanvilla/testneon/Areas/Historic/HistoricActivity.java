package com.example.juanvilla.testneon.Areas.Historic;

import android.support.v7.widget.Toolbar;

import com.example.api.Helpers.ApiServices;
import com.example.juanvilla.testneon.Areas.Historic.Beans.HistoricManagerBean;
import com.example.juanvilla.testneon.Areas.Splash.Beans.InitialConfigBean;
import com.example.juanvilla.testneon.R;
import com.example.juanvilla.testneon.Utils.BaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_historic)
public class HistoricActivity extends BaseActivity {

  @ViewById(R.id.toolbar)
  Toolbar toolbar;

  @Bean
  InitialConfigBean initialConfigBean;

  @Bean
  HistoricManagerBean historicManagerBean;

  @AfterViews
  public void init() {
    setSupportActionBar(toolbar);
    setTitle(getResources().getString(R.string.title_activity_historic));
    getTransfers();
  }

  @Background
  public void getTransfers(){
    historicManagerBean.validateTransference(ApiServices.getTestNeonInterface().getTransfer(initialConfigBean.getToken()));
  }

}
