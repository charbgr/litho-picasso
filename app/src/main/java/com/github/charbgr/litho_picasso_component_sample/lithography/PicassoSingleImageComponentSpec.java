package com.github.charbgr.litho_picasso_component_sample.lithography;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;
import com.github.charbgr.litho.picasso.PicassoImage;

@LayoutSpec
public class PicassoSingleImageComponentSpec {

  @PropDefault
  protected static final boolean fit = true;

  @OnCreateLayout
  static Component onCreateLayout(ComponentContext c, @Prop String image,
      @Prop(optional = true) boolean fit) {
    return PicassoImage.create(c).imageUrl(image).fit(fit).centerCrop(true).build();
  }
}
