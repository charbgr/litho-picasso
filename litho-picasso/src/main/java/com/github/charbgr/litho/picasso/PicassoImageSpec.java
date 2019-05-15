package com.github.charbgr.litho.picasso;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.Diff;
import com.facebook.litho.Size;
import com.facebook.litho.annotations.MountSpec;
import com.facebook.litho.annotations.OnCreateMountContent;
import com.facebook.litho.annotations.OnMeasure;
import com.facebook.litho.annotations.OnMount;
import com.facebook.litho.annotations.OnUnmount;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;
import com.facebook.litho.annotations.ShouldUpdate;
import com.facebook.litho.utils.MeasureUtils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;
import com.squareup.picasso.Transformation;
import java.io.File;
import java.util.List;

import static com.facebook.litho.annotations.ResType.DRAWABLE;
import static com.facebook.litho.annotations.ResType.INT;

@MountSpec
public class PicassoImageSpec {

  private static final int DEFAULT_INT_VALUE = 0;
  private static final int DEFAULT_FLOAT_VALUE = -1;

  private static final int DEFAULT_PIVOT_VALUE = 0;

  @PropDefault
  protected static final int resizeTargetWidth = DEFAULT_INT_VALUE;

  @PropDefault
  protected static final int resizeTargetHeight = DEFAULT_INT_VALUE;

  @PropDefault
  protected static final int resizeTargetWidthResId = DEFAULT_INT_VALUE;

  @PropDefault
  protected static final int resizeTargetHeightResId = DEFAULT_INT_VALUE;

  @PropDefault
  protected static final int pivotX = DEFAULT_PIVOT_VALUE;

  @PropDefault
  protected static final int pivotY = DEFAULT_PIVOT_VALUE;

  @OnMeasure
  static void onMeasureLayout(ComponentContext c, ComponentLayout layout, int widthSpec,
      int heightSpec, Size size) {
    MeasureUtils.measureWithEqualDimens(widthSpec, heightSpec, size);
  }

  @OnCreateMountContent
  static ImageView onCreateMountContent(ComponentContext c) {
    ImageView imageView = new ImageView(c.getBaseContext());
    return imageView;
  }

  @OnMount
  static void onMount(ComponentContext c, ImageView imageView,
      @Prop(optional = true) String imageUrl, @Prop(optional = true) File file,
      @Prop(optional = true) Uri uri, @Prop(optional = true) Integer resourceId,
      @Prop(optional = true) Picasso picasso,
      @Prop(optional = true, resType = DRAWABLE) Drawable errorDrawable,
      @Prop(optional = true, resType = DRAWABLE) Drawable placeholderImage,
      @Prop(optional = true) boolean noPlaceholder,
      @Prop(optional = true) NetworkPolicy networkPolicy,
      @Prop(optional = true) MemoryPolicy memoryPolicy, @Prop(optional = true) Bitmap.Config config,
      @Prop(optional = true) boolean fit, @Prop(optional = true) boolean centerCrop,
      @Prop(optional = true) boolean centerInside, @Prop(optional = true) boolean noFade,
      @Prop(optional = true) boolean onlyScaleDown,
      @Prop(optional = true) Picasso.Priority priority,
      @Prop(optional = true) int resizeTargetWidth, @Prop(optional = true) int resizeTargetHeight,
      @Prop(optional = true, resType = INT) int resizeTargetWidthResId,
      @Prop(optional = true, resType = INT) int resizeTargetHeightResId,
      @Prop(optional = true) float rotateDegrees, @Prop(optional = true) int pivotX,
      @Prop(optional = true) int pivotY, @Prop(optional = true) String stableKey,
      @Prop(optional = true) Object tag, @Prop(optional = true) Transformation transformation,
      @Prop(optional = true) List<? extends Transformation> transformations,
      @Prop(optional = true) Target target, @Prop(optional = true) Callback callback) {

    if (imageUrl == null && file == null && uri == null && resourceId == null) {
      throw new IllegalArgumentException(
          "You must provide at least one of String, File, Uri or ResourceId");
    }

    if (picasso == null) {
      picasso = Picasso.with(c.getBaseContext());
    }

    RequestCreator request;

    if (imageUrl != null) {
      request = picasso.load(imageUrl);
    } else if (file != null) {
      request = picasso.load(file);
    } else if (uri != null) {
      request = picasso.load(uri);
    } else {
      request = picasso.load(resourceId);
    }

    if (request == null) {
      throw new IllegalStateException("Could not instantiate RequestCreator");
    }

    if (networkPolicy != null) {
      request.networkPolicy(networkPolicy);
    }

    if (memoryPolicy != null) {
      request.memoryPolicy(memoryPolicy);
    }

    if (centerCrop) {
      request.centerCrop();
    }

    if (centerInside) {
      request.centerInside();
    }

    if (config != null) {
      request.config(config);
    }

    if (errorDrawable != null) {
      request.error(errorDrawable);
    }

    if (noPlaceholder) {
      request.noPlaceholder();
    } else {
      if (placeholderImage != null) {
        request.placeholder(placeholderImage);
      }
    }

    if (fit) {
      request.fit();
    }

    if (noFade) {
      request.noFade();
    }

    if (onlyScaleDown) {
      request.onlyScaleDown();
    }

    if (priority != null) {
      request.priority(priority);
    }

    if (resizeTargetWidth != DEFAULT_INT_VALUE && resizeTargetHeight != DEFAULT_INT_VALUE) {
      request.resize(resizeTargetWidth, resizeTargetHeight);
    }

    if (resizeTargetWidthResId != DEFAULT_INT_VALUE
        && resizeTargetHeightResId != DEFAULT_INT_VALUE) {
      request.resizeDimen(resizeTargetWidthResId, resizeTargetHeightResId);
    }

    if (rotateDegrees != DEFAULT_FLOAT_VALUE) {
      if (pivotX != DEFAULT_PIVOT_VALUE && pivotY != DEFAULT_PIVOT_VALUE) {
        request.rotate(rotateDegrees, pivotX, pivotY);
      } else {
        request.rotate(rotateDegrees);
      }
    }

    if (stableKey != null) {
      request.stableKey(stableKey);
    }

    if (tag != null) {
      request.tag(tag);
    }

    if (transformation != null) {
      request.transform(transformation);
    }

    if (transformations != null) {
      request.transform(transformations);
    }

    if (target != null) {
      request.into(target);
    } else {
      if (callback != null) {
        request.into(imageView, callback);
      } else {
        request.into(imageView);
      }
    }
  }

  @OnUnmount
  static void onUnmount(ComponentContext c, ImageView imageView) {
    Picasso.with(imageView.getContext()).cancelRequest(imageView);
  }

  @ShouldUpdate(onMount = true)
  static boolean shouldUpdate(
          @Prop(optional = true) Diff<String> imageUrl,
          @Prop(optional = true) Diff<File> file,
          @Prop(optional = true) Diff<Uri> uri,
          @Prop(optional = true) Diff<Integer> resourceId,
          @Prop(optional = true, resType = DRAWABLE) Diff<Drawable> errorDrawable,
          @Prop(optional = true, resType = DRAWABLE) Diff<Drawable> placeholderImage,
          @Prop(optional = true) Diff<Boolean> noPlaceholder,
          @Prop(optional = true) Diff<Bitmap.Config> config,
          @Prop(optional = true) Diff<Boolean> fit,
          @Prop(optional = true) Diff<Boolean> centerCrop,
          @Prop(optional = true) Diff<Boolean> centerInside,
          @Prop(optional = true) Diff<Boolean> noFade,
          @Prop(optional = true) Diff<Boolean> onlyScaleDown,
          @Prop(optional = true) Diff<Picasso.Priority> priority,
          @Prop(optional = true) Diff<Integer> resizeTargetWidth,
          @Prop(optional = true) Diff<Integer> resizeTargetHeight,
          @Prop(optional = true, resType = INT) Diff<Integer> resizeTargetWidthResId,
          @Prop(optional = true, resType = INT) Diff<Integer> resizeTargetHeightResId,
          @Prop(optional = true) Diff<Float> rotateDegrees,
          @Prop(optional = true) Diff<Integer> pivotX,
          @Prop(optional = true) Diff<Integer> pivotY,
          @Prop(optional = true) Diff<String> stableKey,
          @Prop(optional = true) Diff<Object> tag,
          @Prop(optional = true) Diff<Transformation> transformation,
          @Prop(optional = true) Diff<List<? extends Transformation>> transformations
  ) {
    return imageUrl.getNext() != imageUrl.getPrevious() ||
            file.getNext() != file.getPrevious() ||
            uri.getNext() != uri.getPrevious() ||
            resourceId.getNext() != resourceId.getPrevious() ||
            errorDrawable.getNext() != errorDrawable.getPrevious() ||
            placeholderImage.getNext() != placeholderImage.getPrevious() ||
            noPlaceholder.getNext() != noPlaceholder.getPrevious() ||
            config.getNext() != config.getPrevious() ||
            fit.getNext() != fit.getPrevious() ||
            centerCrop.getNext() != centerCrop.getPrevious() ||
            centerInside.getNext() != centerInside.getPrevious() ||
            noFade.getNext() != noFade.getPrevious() ||
            onlyScaleDown.getNext() != onlyScaleDown.getPrevious() ||
            priority.getNext() != priority.getPrevious() ||
            resizeTargetWidth.getNext() != resizeTargetWidth.getPrevious() ||
            resizeTargetHeight.getNext() != resizeTargetHeight.getPrevious() ||
            resizeTargetWidthResId.getNext() != resizeTargetWidthResId.getPrevious() ||
            resizeTargetHeightResId.getNext() != resizeTargetHeightResId.getPrevious() ||
            rotateDegrees.getNext() != rotateDegrees.getPrevious() ||
            pivotX.getNext() != pivotX.getPrevious() ||
            pivotY.getNext() != pivotY.getPrevious() ||
            stableKey.getNext() != stableKey.getPrevious() ||
            tag.getNext() != tag.getPrevious() ||
            transformation.getNext() != transformation.getPrevious() ||
            transformations.getNext() != transformations.getPrevious();
  }
}
