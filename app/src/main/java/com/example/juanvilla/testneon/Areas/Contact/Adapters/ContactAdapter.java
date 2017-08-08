package com.example.juanvilla.testneon.Areas.Contact.Adapters;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.api.Models.Contact.ContactInfo;
import com.example.api.Models.Transfers.UserData;
import com.example.juanvilla.testneon.Areas.Contact.ContactActivity;
import com.example.juanvilla.testneon.Areas.Historic.HistoricActivity;
import com.example.juanvilla.testneon.R;
import com.example.juanvilla.testneon.Utils.ResizeAnimation;
import com.example.juanvilla.testneon.Utils.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by juan.villa on 06/08/17.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

  ArrayList<ContactInfo> contacts;
  ArrayList<UserData> transfers;
  AppCompatActivity ctx;
  int type = 0;

  public ContactAdapter(ArrayList<ContactInfo> contacts, AppCompatActivity ctx) {
    this.contacts = contacts;
    this.ctx = ctx;
  }

  @Override
  public ContactAdapter.ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (type==0) {
      return new ContactViewHolder(LayoutInflater.from(ctx.getBaseContext()).inflate(R.layout.item_contact, parent, false));
    }else {
      return new ContactViewHolder(LayoutInflater.from(ctx.getBaseContext()).inflate(R.layout.item_value, parent, false));
    }
  }

  @Override
  public void onBindViewHolder(ContactAdapter.ContactViewHolder holder, int position) {
    if(ctx instanceof HistoricActivity){

      Integer index = Integer.valueOf(transfers.get(position).getClienteId());

      if(type==0) {

        holder.txtMoney.setVisibility(View.VISIBLE);
        holder.txtMoney.setText("R$ "+transfers.get(position).getValor());

        holder.txtName.setText(contacts.get(index).getName().getFirst() + " " + contacts.get(index).getName().getLast());
        holder.txtPhone.setText(contacts.get(index).getPhone());
        Picasso.with(ctx).load(contacts.get(index).getPicture().getLarge()).placeholder(R.drawable.neon).into(holder.imgProfile);

      }else {
        holder.txtMoney.setText("R$ "+transfers.get(position).getValor());

        int height = Double.valueOf(transfers.get(position).getValor()).intValue() / 8;

        if((height)<=400){
          if((height)<=40){
            holder.amountBar.startAnimation(animate(holder.amountBar,50,0));
          }else if((height)<=200){
            holder.amountBar.startAnimation(animate(holder.amountBar,150,0));
          } else {
            holder.amountBar.startAnimation(animate(holder.amountBar,height,0));
          }
        }else if((height)>400){
          if((height)<=500) {
            holder.amountBar.startAnimation(animate(holder.amountBar, 210, 0));
          }else if ((height)>500){
            holder.amountBar.startAnimation(animate(holder.amountBar, 410, 0));
          }
        }

        Picasso.with(ctx).load(contacts.get(index).getPicture().getLarge()).placeholder(R.drawable.neon).into(holder.imgProfile);
      }

    }else {
      holder.txtName.setText(contacts.get(position).getName().getFirst()+" "+contacts.get(position).getName().getLast());
      holder.txtPhone.setText(contacts.get(position).getPhone());

      Picasso.with(ctx).load(contacts.get(position).getPicture().getLarge()).placeholder(R.drawable.neon).into(holder.imgProfile);
      holder.img_url= contacts.get(position).getPicture().getLarge();
      holder.position = String.valueOf(position);
    }
  }

  public ResizeAnimation animate(View view, int targetHeight, int startHeight){
    ResizeAnimation resizeAnimation = new ResizeAnimation(view, targetHeight, startHeight);
    resizeAnimation.setDuration(3000);
    return resizeAnimation;
  }
  public void setTransfers(ArrayList<UserData> transfers){
    this.transfers = transfers;
  }

  public void setType(int type) {
    this.type = type;
  }

  @Override
  public int getItemCount() {
    return ctx instanceof HistoricActivity? transfers.size():contacts.size();
  }


  public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView txtName, txtPhone, txtMoney;
    String img_url;
    RoundedImageView imgProfile;
    String position;
    RelativeLayout amountBar;


    public ContactViewHolder(View itemView) {
      super(itemView);
      itemView.setOnClickListener(this);
      txtName = (TextView) itemView.findViewById(R.id.txtName);
      txtPhone = (TextView) itemView.findViewById(R.id.txtPhone);
      txtMoney = (TextView) itemView.findViewById(R.id.txtMoney);
      imgProfile = (RoundedImageView) itemView.findViewById(R.id.imgProfile);
      amountBar = (RelativeLayout) itemView.findViewById(R.id.amountBar);
    }

    @Override
    public void onClick(View view) {
      if(ctx instanceof ContactActivity) {
        ContactInfo selectedContact = new ContactInfo().withClienteId(position).withFullName(txtName.getText().toString()).withPhone(txtPhone.getText().toString()).withImageUrl(img_url);
        ((ContactActivity) ctx).onCallFunction(selectedContact);
      }
    }
  }
}
