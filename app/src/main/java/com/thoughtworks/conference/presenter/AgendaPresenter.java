package com.thoughtworks.conference.presenter;

import com.thoughtworks.conference.apiclient.APIClient;

public class AgendaPresenter {
  private final APIClient apiClient;
  public static final String CONFERENCE_ENDPOINT = "https://intense-fire-9666.firebaseio.com/";

  public AgendaPresenter(APIClient apiClient) {
    this.apiClient = apiClient;
  }

  public void fetchEvents() {
    apiClient.get(CONFERENCE_ENDPOINT, null);
  }
}
