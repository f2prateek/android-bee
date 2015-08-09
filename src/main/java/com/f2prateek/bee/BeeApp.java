package com.f2prateek.bee;

import android.app.Application;
import timber.log.Timber;

public class BeeApp extends Application {
  @Override public void onCreate() {
    super.onCreate();

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }
}
