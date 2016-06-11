package com.thoughtworks.conference.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.thoughtworks.conference.model.Category;
import com.thoughtworks.conference.model.Conference;

import static com.thoughtworks.conference.view.TrackFragment.SESSION_LIST_EXTRA_KEY;

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
    final TrackFragment trackFragment = new TrackFragment();
    Bundle bundle = new Bundle();
    bundle.putParcelableArrayList(SESSION_LIST_EXTRA_KEY, conference.filterByCategory(categories[position]));
    trackFragment.setArguments(bundle);
    return trackFragment;
  }

  @Override
  public int getCount() {
    return categories.length;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return categories[position].name();
  }
}