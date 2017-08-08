package com.example.juanvilla.testneon.Utils;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.api.Error.Error;
import com.example.api.Error.RestErrorEvent;
import com.example.api.Helpers.BusProvider;
import com.example.juanvilla.testneon.Areas.Historic.HistoricActivity;
import com.example.juanvilla.testneon.Areas.Main.MainActivity;
import com.example.juanvilla.testneon.R;
import com.squareup.otto.Subscribe;
/**
 * Created by juan.villa on 06/08/17.
 */

public class BaseActivity extends AppCompatActivity {

  Snackbar snackbar;
  private final Object busCallback = new Object() {
    @Subscribe
    public void handleRestErrorEvent(RestErrorEvent event) {
      onError(new Error(event.getErrorCode(), event.getMessage()));
    }
  };

  @Override
  protected void onResume() {
    super.onResume();
    BusProvider.getInstance().register(busCallback);
  }

  @Override
  protected void onPause() {
    super.onPause();
    try {
      BusProvider.getInstance().unregister(busCallback);
    } catch (Exception e) {
      Log.e("log", "busCallback is not registered!");
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);

    if(!(this instanceof MainActivity)) {
      final Drawable bakcIcon = getResources().getDrawable(R.drawable.ic_left_black);
      bakcIcon.setColorFilter(Color.parseColor("#FFFFFFFF"), PorterDuff.Mode.SRC_ATOP);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setHomeAsUpIndicator(bakcIcon);
    }

    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if(!(this instanceof MainActivity)) {
      supportFinishAfterTransition();
    }
    return super.onOptionsItemSelected(item);
  }

  public void onError(Error e) {
    showSnackbar(getResources().getString(R.string.unknown_error_msg));
  }

  public void showSnackbar(final String msg) {

    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        if (snackbar == null || !snackbar.isShownOrQueued()) {
          try {
            View parentView = BaseActivity.this.findViewById(android.R.id.content).getRootView();
            if(getParent() instanceof HistoricActivity){

              snackbar = Snackbar.make(parentView, msg, Snackbar.LENGTH_INDEFINITE);
            }else {
              snackbar = Snackbar.make(parentView, msg, Snackbar.LENGTH_LONG);
            }

            snackbar.setAction("OK", new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                snackbar.dismiss();
              }
            });

            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(getResources().getColor(R.color.colorGray));
            snackbarView.setPadding(0, 60, 0, 60);

            snackbar.setActionTextColor(getResources().getColor(R.color.lightGray));
            snackbar.show();


          } catch (Exception e) {
            Log.e("log", "Couldn't show snackBar: " + e);
          }
        }
      }
    });
  }
}
