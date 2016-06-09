package com.thoughtworks.conference.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.thoughtworks.conference.model.Category;
import com.thoughtworks.conference.model.Conference;

public class ViewPagerAdapter extends FragmentPagerAdapter {
  private final Conference conference;
  private final Category[] categories;

  public ViewPagerAdapter(FragmentManager manager, Conference conference) {
    super(manager);
    this.conference = conference;
    categories = Category.values();
  }

  @Override
  public Fragment getItem(int position) {
    return null;
  }

  @Override
  public int getCount() {
    return categories.length;
  }
}