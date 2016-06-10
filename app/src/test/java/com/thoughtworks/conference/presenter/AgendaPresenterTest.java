package com.thoughtworks.conference.presenter;

import com.thoughtworks.conference.apiclient.APIClient;
import com.thoughtworks.conference.apiclient.APIClientCallback;
import com.thoughtworks.conference.apiclient.NetworkException;
import com.thoughtworks.conference.model.Category;
import com.thoughtworks.conference.model.Conference;
import com.thoughtworks.conference.model.Session;
import com.thoughtworks.conference.view.AgendaView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static com.thoughtworks.conference.testdata.TestDataUtil.parseDate;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AgendaPresenterTest {

  public static final String CONFERENCE_ENDPOINT = "https://intense-fire-9666.firebaseio.com/";
  private APIClient mockApiClient;
  private AgendaPresenter agendaPresenter;
  private Conference conference;
  private AgendaView mockAgendaView;

  @Before
  public void setUp() {
    final Session session1 = new Session("Create_Session", "description1", parseDate("2016-06-26T03:30:00+05:30"),
        parseDate("2016-06-26T05:30:00+05:30"), Category.CREATE, "location1");
    final Session session2 = new Session("Belong_Session", "description2", parseDate("2016-06-26T04:00:00+05:30"),
        parseDate("2016-06-26T05:30:00+05:30"), Category.BELONG, "location2");
    conference = new Conference(session1, session2);
    mockApiClient = mock(APIClient.class);
    mockAgendaView = mock(AgendaView.class);
    agendaPresenter = new AgendaPresenter(mockApiClient, mockAgendaView);
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        final APIClientCallback<Conference> callback = (APIClientCallback) invocation.getArguments()[1];
        callback.onSuccess(conference);
        return null;
      }
    }).when(mockApiClient).get(eq(CONFERENCE_ENDPOINT), any(APIClientCallback.class));
  }

  @Test
  public void callApiClient() {
    agendaPresenter.presentConference();
    verify(mockApiClient, times(1)).get(eq(CONFERENCE_ENDPOINT), any(APIClientCallback.class));
  }

  @Test
  public void renderViewOnApiResponse(){
    agendaPresenter.presentConference();
    verify(mockAgendaView, times(1)).render(eq(conference));
  }
  @Test
  public void showProgressDialogAndRenderResponseAndHideDialogInOrder(){
    agendaPresenter.presentConference();
    InOrder inOrder = inOrder(mockAgendaView, mockApiClient);
    inOrder.verify(mockAgendaView).showProgressDialog();
    inOrder.verify(mockApiClient).get(eq(CONFERENCE_ENDPOINT), any(APIClientCallback.class));
    inOrder.verify(mockAgendaView).render(eq(conference));
    inOrder.verify(mockAgendaView).dismissProgressDialog();
  }
  @Test
  public void hideProgressDialogAndShowErrorDialogOnFailureOfApiCall(){
    final String errorMessage = "No network";
    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        final APIClientCallback<Conference> callback = (APIClientCallback) invocation.getArguments()[1];
        callback.onFailure(new NetworkException(errorMessage));
        return null;
      }
    }).when(mockApiClient).get(eq(CONFERENCE_ENDPOINT), any(APIClientCallback.class));

    agendaPresenter.presentConference();

    verify(mockAgendaView, times(1)).showErrorDialog(eq(errorMessage));
    verify(mockAgendaView, times(1)).dismissProgressDialog();
  }
}