package com.example.juanvilla.testneon.Areas.Contact.Beans;

import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;

import com.example.api.Models.Contact.ContactInfo;
import com.example.juanvilla.testneon.Areas.Contact.ContactActivity;
import com.example.juanvilla.testneon.Areas.Contact.Fragments.TransferFragment;
import com.example.juanvilla.testneon.R;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

/**
 * Created by juan.villa on 07/08/17.
 */

@EBean
public class ContactManagerBean implements SwipeRefreshLayout.OnRefreshListener{

  @RootContext
  ContactActivity contactActivity;

  @ViewById(R.id.swipe_container)
  SwipeRefreshLayout swipeLayout;

  TransferFragment transferFragment;
  FragmentManager fm ;

  @AfterInject
  public void init() {
    transferFragment = new TransferFragment();
    fm = contactActivity.getSupportFragmentManager();
  }

  @AfterViews
  public void initViews(){
    swipeLayout.setRefreshing(false);
    swipeLayout.setEnabled(false);
    swipeLayout.setOnRefreshListener(this);
  }

  @UiThread
  public void validateTransfer(Boolean response) {
    swipeLayout.setRefreshing(false);
    swipeLayout.setEnabled(false);
    if(response!=null){
      if(response){
        contactActivity.showSnackbar(contactActivity.getResources().getString(R.string.transfer_success));
      }else {
        contactActivity.showSnackbar(contactActivity.getResources().getString(R.string.transfer_failure));
      }
    }
  }

  public void setTransferFragment(ContactInfo contactInfo) {
    transferFragment.setContactInfo(contactInfo);
    if (transferFragment.getDialog() == null) {
      transferFragment.show(fm, "fragment_transfer_dialog");
    } else {
      transferFragment.getDialog().show();
    }
  }

  @Override
  public void onRefresh() {
    swipeLayout.setRefreshing(false);
  }

  public SwipeRefreshLayout getSwipeLayout() {
    return swipeLayout;
  }
}
