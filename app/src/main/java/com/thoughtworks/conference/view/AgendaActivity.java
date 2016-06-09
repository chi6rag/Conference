package com.thoughtworks.conference.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.thoughtworks.conference.R;
import com.thoughtworks.conference.apiclient.APIClient;
import com.thoughtworks.conference.model.Conference;

public class AgendaActivity extends AppCompatActivity implements AgendaView {

  private ProgressDialog progressDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_agenda);
  }

  @NonNull
  protected APIClient getApiClient() {
    return new APIClient(this);
  }

  @Override
  public void render(Conference conference) {
  }

  @Override
  public void showProgressDialog() {
    progressDialog = new ProgressDialog(this);
    progressDialog.setMessage(getResources().getString(R.string.loading));
    progressDialog.setCancelable(false);
    progressDialog.show();
  }

  @Override
  public void showErrorDialog(String eq) {

  }

  @Override
  public void dismissProgressDialog() {
    progressDialog.dismiss();
  }
}