package com.thoughtworks.conference.view;

import com.thoughtworks.conference.model.Conference;

public interface AgendaView {
  void render(Conference conference);
  void dismissProgressDialog();
  void showProgressDialog();
  void showErrorDialog(String eq);
}
