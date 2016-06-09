package com.thoughtworks.conference.presenter;

import com.thoughtworks.conference.model.Session;
import com.thoughtworks.conference.view.TrackView;
import com.thoughtworks.conference.viewmodel.SessionViewModel;

import java.util.ArrayList;
import java.util.List;

public class TrackPresenter {
  private final List<Session> sessions;
  private final TrackView trackView;

  public TrackPresenter(List<Session> sessions, TrackView trackView) {
    this.sessions = sessions;
    this.trackView = trackView;
  }

  public void presentSessions() {
    List<SessionViewModel> sessionViewModels = new ArrayList<>();

    for (Session session : sessions) {
      sessionViewModels.add(new SessionViewModel(session));
    }

    trackView.render(sessionViewModels);
  }
}
