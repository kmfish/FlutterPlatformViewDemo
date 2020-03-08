# FlutterPlatformViewDemo
a flutter demo, just reproduce  error and give a temporary workaround.
https://github.com/flutter/flutter/issues/48063

```
 java.lang.RuntimeException: Cannot execute operation because FlutterJNI is not attached to native.
        at io.flutter.embedding.engine.FlutterJNI.ensureAttachedToNative(FlutterJNI.java:227)
        at io.flutter.embedding.engine.FlutterJNI.markTextureFrameAvailable(FlutterJNI.java:554)
        at io.flutter.embedding.engine.renderer.FlutterRenderer.markTextureFrameAvailable(FlutterRenderer.java:274)
```

# Flutter doctor info:
Doctor summary (to see all details, run flutter doctor -v):

[✓] Flutter (Channel stable, v1.12.13+hotfix.8, on Mac OS X 10.14.6 18G3020, locale zh-Hans-CN)

[✓] Android toolchain - develop for Android devices (Android SDK version 29.0.3)

[✓] Android Studio (version 3.5)

[✓] VS Code (version 1.42.1)

[✓] Connected device (1 available)


# crash analyze
crash stack is in FlutterRenderer:
```java
    private SurfaceTexture.OnFrameAvailableListener onFrameListener = new SurfaceTexture.OnFrameAvailableListener() {
      @Override
      public void onFrameAvailable(@NonNull SurfaceTexture texture) {
        if (released) {
          // Even though we make sure to unregister the callback before releasing, as of Android O
          // SurfaceTexture has a data race when accessing the callback, so the callback may
          // still be called by a stale reference after released==true and mNativeView==null.
          return;
        }
        markTextureFrameAvailable(id);
      }
    };
```

it indicated the 'released' flag is still true after view destroyed.  FlutterRenderer.release be called in VirtualDisplayController.dispose().

In VirtualDisplayController:
```java
    public void dispose() {
        PlatformView view = presentation.getView();
        // Fix rare crash on HuaWei device described in: https://github.com/flutter/engine/pull/9192
        presentation.cancel();
        presentation.detachState();
        view.dispose();
        virtualDisplay.release();
        textureEntry.release();   // here is essential
    }
```

VirtualDisplayController.dispose() be called in PlatformViewsController.

In PlatformViewsController:
```java
public void onFlutterViewDestroyed() {
    flushAllViews();
}
   
private void flushAllViews() {
    for (VirtualDisplayController controller : vdControllers.values()) {
         controller.dispose();
    }
     vdControllers.clear();
    }    
 
```

PlatformViewsController.onFlutterViewDestroyed() be called in FlutterPluginRegistry, not in FlutterEnginePluginRegistry.

so I compared between FlutterEnginePluginRegistry & FlutterPluginRegistry:

In FlutterEnginePluginRegistry:
```java
  @Override
  public void detachFromActivity() {
    if (isAttachedToActivity()) {
      Log.v(TAG, "Detaching from an Activity: " + activity);
      for (ActivityAware activityAware : activityAwarePlugins.values()) {
        activityAware.onDetachedFromActivity();
      }

      // Deactivate PlatformViewsController.
      flutterEngine.getPlatformViewsController().detach();  // here just call PlatformViewsController.detach(), miss onFlutterViewDestroyed()

      activity = null;
      activityPluginBinding = null;
    } else {
      Log.e(TAG, "Attempted to detach plugins from an Activity when no Activity was attached.");
    }
  }
```

In FlutterPluginRegistry:
```java
    public void detach() {
        mPlatformViewsController.detach();
        mPlatformViewsController.onFlutterViewDestroyed();   // here is essential
        mFlutterView = null;
        mActivity = null;
    }
```

So far we can make a conclusion is that FlutterEnginePluginRegistry's detachFromActivity() is missing a method call(mPlatformViewsController.onFlutterViewDestroyed()). 

# three test cases
- PlatformView + FlutterFragment + FlutterEngine, when view destroy, crashed
- PlatformView + FlutterActivity + FlutterEngine, when view destroy, crashed
- PlatformView + io.flutter.app.FlutterActivity + FlutterNativeView, when view destroy, it's ok (this case may be deprecated.[Flutter offical: Shift FlutterNativeView to FlutterEngine](https://github.com/flutter/flutter/issues/21785))


These test cases can verify our conclusions above.

# temporary workaround
In custom FlutterFragment or FlutterActvity's onDestroy(): 
```kotlin
    override fun onDestroy() {
        // fix flutter bug: https://github.com/flutter/flutter/issues/48063
        flutterEngine?.platformViewsController?.onFlutterViewDestroyed()  // add this line
        super.onDestroy()
    }
```



