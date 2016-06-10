package com.thoughtworks.conference.presenter;

import com.thoughtworks.conference.apiclient.APIClient;
import com.thoughtworks.conference.apiclient.APIClientCallback;
import com.thoughtworks.conference.model.Conference;
import com.thoughtworks.conference.view.AgendaView;

public class AgendaPresenter {
  private final APIClient apiClient;
  private final AgendaView agendaView;
  public static final String CONFERENCE_ENDPOINT = "https://intense-fire-9666.firebaseio.com/";

  public AgendaPresenter(APIClient apiClient, AgendaView agendaView) {
    this.apiClient = apiClient;
    this.agendaView = agendaView;
  }

  public void fetchEvents() {
    apiClient.get(CONFERENCE_ENDPOINT, new APIClientCallback<Conference>() {
      @Override
      public void onSuccess(Conference conference) {
        agendaView.render(conference);
      }

      @Override
      public void onFailure(Exception e) {
      }

      @Override
      public Class<Conference> getClassOfType() {
        return null;
      }
    });
  }
}
