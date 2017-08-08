package com.example.juanvilla.testneon.Areas.Historic.Beans;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.api.Models.Transfers.UserData;
import com.example.juanvilla.testneon.Areas.Contact.Adapters.ContactAdapter;
import com.example.juanvilla.testneon.Areas.Historic.HistoricActivity;
import com.example.juanvilla.testneon.Areas.Splash.Beans.InitialConfigBean;
import com.example.juanvilla.testneon.R;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by juan.villa on 07/08/17.
 */
@EBean
public class HistoricManagerBean {

  @RootContext
  HistoricActivity activity;

  @Bean
  InitialConfigBean initialConfigBean;

  @ViewById(R.id.transferContactList)
  RecyclerView transferContactList;

  @ViewById(R.id.transferList)
  RecyclerView transferList;

  @UiThread
  public void validateTransference(ArrayList<UserData> response){

    if(response!=null) {
      if (response.size() > 0) {
        ContactAdapter transferContactAdapter = new ContactAdapter(initialConfigBean.getContacts().getResults(), activity);
        ContactAdapter transferAdapter = new ContactAdapter(initialConfigBean.getContacts().getResults(), activity);

        transferContactAdapter.setTransfers(setTransfersSum(response));
        transferAdapter.setTransfers(setTransfersSum(response));

        transferContactList.setLayoutManager((new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)));
        transferContactList.setAdapter(transferContactAdapter);

        transferList.setLayoutManager((new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)));
        transferAdapter.setType(1);
        transferList.setAdapter(transferAdapter);
      } else {
        activity.showSnackbar(activity.getResources().getString(R.string.no_historic));
      }
    }

  }

  public ArrayList<UserData> setTransfersSum(ArrayList<UserData> response){
    ArrayList<UserData> transferUsers = new ArrayList<>();
    MultiValueMap<String, Double>valor = new LinkedMultiValueMap<>();

    for (UserData userData:response){
      double val = 0;
      if (valor.getFirst(userData.getClienteId())!=null){
        val = Double.valueOf(valor.getFirst(userData.getClienteId()));
      }
      valor.set(userData.getClienteId(),val + Double.valueOf(userData.getValor()));
    }

    for (UserData userData:response){
      UserData u= new UserData()
          .withClienteId(userData.getClienteId())
          .withValor(String.valueOf(valor.getFirst(userData.getClienteId())));

      if(!transferUsers.contains(u)) {
        transferUsers.add(u);
      }
    }

    Collections.sort(transferUsers, new Comparator<UserData>() {
      @Override
      public int compare(UserData o1, UserData o2) {
        return Double.compare(Double.valueOf(o1.getValor()),Double.valueOf(o2.getValor()));
      }
    });

    Collections.reverse(transferUsers);

    return transferUsers;
  }



}
