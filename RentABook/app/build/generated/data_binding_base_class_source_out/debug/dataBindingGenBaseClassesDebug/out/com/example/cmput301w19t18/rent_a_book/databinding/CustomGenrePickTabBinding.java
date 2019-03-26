package com.example.cmput301w19t18.rent_a_book.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.cmput301w19t18.rent_a_book.GenreViewModel;

public abstract class CustomGenrePickTabBinding extends ViewDataBinding {
  @NonNull
  public final ViewPager custPager;

  @NonNull
  public final TabLayout custTabLayout;

  @NonNull
  public final TextView genreText;

  @NonNull
  public final TextView genresSelected;

  @Bindable
  protected GenreViewModel mViewModel;

  protected CustomGenrePickTabBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, ViewPager custPager, TabLayout custTabLayout, TextView genreText,
      TextView genresSelected) {
    super(_bindingComponent, _root, _localFieldCount);
    this.custPager = custPager;
    this.custTabLayout = custTabLayout;
    this.genreText = genreText;
    this.genresSelected = genresSelected;
  }

  public abstract void setViewModel(@Nullable GenreViewModel viewModel);

  @Nullable
  public GenreViewModel getViewModel() {
    return mViewModel;
  }

  @NonNull
  public static CustomGenrePickTabBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static CustomGenrePickTabBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<CustomGenrePickTabBinding>inflate(inflater, com.example.cmput301w19t18.rent_a_book.R.layout.custom_genre_pick_tab, root, attachToRoot, component);
  }

  @NonNull
  public static CustomGenrePickTabBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static CustomGenrePickTabBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<CustomGenrePickTabBinding>inflate(inflater, com.example.cmput301w19t18.rent_a_book.R.layout.custom_genre_pick_tab, null, false, component);
  }

  public static CustomGenrePickTabBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static CustomGenrePickTabBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (CustomGenrePickTabBinding)bind(component, view, com.example.cmput301w19t18.rent_a_book.R.layout.custom_genre_pick_tab);
  }
}
