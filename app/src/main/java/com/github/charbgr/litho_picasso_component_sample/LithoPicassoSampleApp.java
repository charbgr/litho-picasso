package com.github.charbgr.litho_picasso_component_sample;

import android.app.Application;
import com.facebook.soloader.SoLoader;

public class LithoPicassoSampleApp extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    SoLoader.init(this, false);
  }
}
