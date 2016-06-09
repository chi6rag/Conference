package com.thoughtworks.conference.view;

import com.thoughtworks.conference.viewmodel.SessionViewModel;

import java.util.List;

public interface TrackView {

  void render(List<SessionViewModel> sessionViewModels);
}
