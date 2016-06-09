package com.thoughtworks.conference;

import android.app.Activity;
import android.os.Bundle;


public class TestFragmentContainerActivity extends Activity{
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.test_fragment_container);
  }
}
