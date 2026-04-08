-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

-keepclasseswithmembernames class * {
    native <methods>;
}

-optimizationpasses 5
-dontusemixedcaseclassnames
-verbose

-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable
