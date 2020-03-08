# FlutterPlatformViewDemo

a flutter demo, just show a error and temporary workaround
https://github.com/flutter/flutter/issues/48063

java.lang.RuntimeException: Cannot execute operation because FlutterJNI is not attached to native.
        at io.flutter.embedding.engine.FlutterJNI.ensureAttachedToNative(FlutterJNI.java:227)
        at io.flutter.embedding.engine.FlutterJNI.markTextureFrameAvailable(FlutterJNI.java:554)


# Flutter doctor info:
Doctor summary (to see all details, run flutter doctor -v):
[✓] Flutter (Channel stable, v1.12.13+hotfix.8, on Mac OS X 10.14.6 18G3020, locale zh-Hans-CN)
[✓] Android toolchain - develop for Android devices (Android SDK version 29.0.3)
[✓] Android Studio (version 3.5)
[✓] VS Code (version 1.42.1)
[✓] Connected device (1 available)
