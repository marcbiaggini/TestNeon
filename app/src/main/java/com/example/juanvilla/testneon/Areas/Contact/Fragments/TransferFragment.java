package com.example.juanvilla.testneon.Areas.Contact.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.api.Models.Contact.ContactInfo;
import com.example.juanvilla.testneon.Areas.Contact.Interfaces.CloseInterface;
import com.example.juanvilla.testneon.R;
import com.example.juanvilla.testneon.Utils.RoundedImageView;
import com.squareup.picasso.Picasso;


/**
 * Created by juan.villa on 06/08/2016.
 */

public class TransferFragment extends android.support.v4.app.DialogFragment implements View.OnClickListener {

  public Dialog dialog;
  CloseInterface mListener;
  RoundedImageView profileImage;
  EditText edtMoney;
  TextView txtName, txtPhone;
  ContactInfo contactInfo;
  String idUser;

  Button btnEnviar;

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    View view = inflater.inflate(R.layout.fragment_transfer, null);

    profileImage = (RoundedImageView)view.findViewById(R.id.imgProfile);
    setProfileImage(contactInfo.getImageUrl());

    txtName = (TextView) view.findViewById(R.id.txtName);
    setTxtName(contactInfo.getFullName());

    txtPhone = (TextView) view.findViewById(R.id.txtPhone);
    setTxtPhone(contactInfo.getPhone());

    btnEnviar = (Button) view.findViewById(R.id.btnEnviarDinheiro);
    btnEnviar.setOnClickListener(this);

    edtMoney = (EditText) view.findViewById(R.id.edtMoney);

    builder.setView(view);

    idUser = contactInfo.getClienteId();

    dialog = builder.create();
    dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, view.getHeight());
    dialog.getWindow().getAttributes().windowAnimations = R.style.MyAnimation_Window;
    dialog.show();
    return dialog;
  }

  @Override
  public void onStart() {
    super.onStart();
    Dialog dialog = getDialog();
    if (dialog != null) {
      int width = WindowManager.LayoutParams.MATCH_PARENT;
      int height = WindowManager.LayoutParams.WRAP_CONTENT;
      dialog.getWindow().setLayout(width, height);
    }
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
  }

  @Override
  public void onAttach(Context activity) {
    mListener = (CloseInterface) activity;
    super.onAttach(activity);
  }

  @Override
  public void onDetach() {
    mListener = null;
    super.onDetach();
  }


  @Override
  public void onClick(View view) {
    if (!edtMoney.getText().toString().isEmpty()) {
      mListener.onClose(idUser, edtMoney.getText().toString());
      dismiss();
    }
  }

  public void setContactInfo(ContactInfo contactInfo) {
    this.contactInfo = contactInfo;
  }

  public void setProfileImage(String url) {
    Picasso.with(getContext()).load(url).placeholder(R.drawable.neon).into(profileImage);
  }

  public void setTxtName(String name) {
    this.txtName.setText(name);
  }

  public void setTxtPhone(String phone) {
    this.txtPhone.setText(phone);
  }
}
