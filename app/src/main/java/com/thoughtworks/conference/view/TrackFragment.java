package com.thoughtworks.conference.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.thoughtworks.conference.R;
import com.thoughtworks.conference.model.Session;
import com.thoughtworks.conference.presenter.TrackPresenter;
import com.thoughtworks.conference.viewmodel.SessionViewModel;

import java.util.ArrayList;
import java.util.List;

public class TrackFragment extends Fragment implements TrackView {
  public static String SESSION_LIST_EXTRA_KEY = "thoughtworks.eventapp.view.sessionlist";
  private ArrayList<Session> sessions;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    sessions = getArguments().getParcelableArrayList(SESSION_LIST_EXTRA_KEY);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.track_layout, container, false);
    return view;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    TrackPresenter trackPresenter = new TrackPresenter(sessions, this);
    trackPresenter.presentSessions();
  }

  @Override
  public void render(List<SessionViewModel> sessionViewModels) {
    ListView listView = (ListView) getView().findViewById(R.id.list_view);
    listView.setAdapter(new AgendaAdapter(getActivity(), sessionViewModels));
  }
}
