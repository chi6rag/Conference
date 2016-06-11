package com.thoughtworks.conference.view;

public interface DetailView {
  void updateView();
  void showToast(String string);
  void showConflictPopup(String currentSession, String addedSession);
}
