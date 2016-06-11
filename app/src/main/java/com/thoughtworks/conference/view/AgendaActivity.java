package com.thoughtworks.conference.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.thoughtworks.conference.R;
import com.thoughtworks.conference.apiclient.APIClient;
import com.thoughtworks.conference.model.Conference;
import com.thoughtworks.conference.presenter.AgendaPresenter;

public class AgendaActivity extends AppCompatActivity implements AgendaView {

  private ProgressDialog progressDialog;
  private AgendaPresenter agendaPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_agenda);
    agendaPresenter = new AgendaPresenter(getApiClient(), this);
    agendaPresenter.presentConference();
  }

  @NonNull
  protected APIClient getApiClient() {
    return new APIClient(this);
  }

  @Override
  public void render(Conference conference) {
    setupTabs(conference);
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

  private void setupTabs(Conference conference) {
    TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
    ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), conference);
    viewPager.setAdapter(adapter);
    tabLayout.setupWithViewPager(viewPager);
  }

}