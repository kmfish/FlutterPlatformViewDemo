#import "SharePlatformPlugin.h"
#if __has_include(<share_platform_plugin/share_platform_plugin-Swift.h>)
#import <share_platform_plugin/share_platform_plugin-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "share_platform_plugin-Swift.h"
#endif

@implementation SharePlatformPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftSharePlatformPlugin registerWithRegistrar:registrar];
}
@end
