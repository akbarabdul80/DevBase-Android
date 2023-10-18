# Glide

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
 <init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
  *** rewind();
}

# Uncomment for DexGuard only
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule

# SpotDialog
-keep class dmax.dialog.** {
    *;
}

## Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

## RxJava
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
# OkHttp
-dontwarn okhttp3.**
-keep class okhttp3.** { *; }
-dontwarn okio.**

# Base Recycler view
-keep class com.chad.library.adapter.** {
*;
}
-keep public class * extends com.chad.library.adapter.base.BaseQuickAdapter
-keep public class * extends com.chad.library.adapter.base.viewholder.BaseViewHolder
-keepclassmembers  class **$** extends com.chad.library.adapter.base.viewholder.BaseViewHolder {
     <init>(...);
}
-keepattributes InnerClasses

-keep class androidx.** {*;}
-keep public class * extends androidx.**
-keep interface androidx.** {*;}
-dontwarn androidx.**

# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Keep annotation default values (e.g., retrofit2.http.Field.encoded).
-keepattributes AnnotationDefault

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*

# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

# Keep inherited services.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface * extends <1>

# With R8 full mode generic signatures are stripped for classes that are not
# kept. Suspend functions are wrapped in continuations where the type argument
# is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

# R8 full mode strips generic signatures from return types if not kept.
-if interface * { @retrofit2.http.* public *** *(...); }
-keep,allowoptimization,allowshrinking,allowobfuscation class <3>

# ProGuard rules for androidx.core:core-ktx
-dontwarn androidx.**
-keep class androidx.** { *; }

# ProGuard rules for androidx.appcompat:appcompat
-dontwarn androidx.appcompat.**
-keep class androidx.appcompat.** { *; }

# ProGuard rules for com.google.android.material:material
-dontwarn com.google.android.material.**
-keep class com.google.android.material.** { *; }

# ProGuard rules for com.github.bumptech.glide:glide
-dontwarn com.bumptech.glide.**
-keep class com.bumptech.glide.** { *; }

# ProGuard rules for com.github.d-max:spots-dialog
-dontwarn com.github.dmax.**
-keep class com.github.dmax.** { *; }

# ProGuard rules for com.github.CymChad:BaseRecyclerViewAdapterHelper
-dontwarn com.github.CymChad.**
-keep class com.github.CymChad.** { *; }

# ProGuard rules for com.github.andrefrsousa:SuperBottomSheet
-dontwarn com.github.andrefrsousa.**
-keep class com.github.andrefrsousa.** { *; }

# ProGuard rules for com.squareup.retrofit2:retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }

# ProGuard rules for com.squareup.retrofit2:converter-gson
-dontwarn com.google.gson.**
-keep class com.google.gson.** { *; }

# ProGuard rules for com.squareup.okhttp3:logging-interceptor
-dontwarn okhttp3.**
-keep class okhttp3.** { *; }

# ProGuard rules for com.squareup.retrofit2:converter-scalars
-dontwarn retrofit2.converter.scalars.**
-keep class retrofit2.converter.scalars.** { *; }

# ProGuard rules for io.reactivex.rxjava2:rxandroid
-dontwarn io.reactivex.**
-keep class io.reactivex.** { *; }

# ProGuard rules for io.reactivex.rxjava2:rxjava
-dontwarn io.reactivex.**
-keep class io.reactivex.** { *; }

# ProGuard rules for com.jakewharton.rxbinding3:rxbinding
-dontwarn com.jakewharton.rxbinding3.**
-keep class com.jakewharton.rxbinding3.** { *; }

# ProGuard rules for com.squareup.retrofit2:adapter-rxjava2
-dontwarn retrofit2q.adapter.rxjava2.**
-keep class retrofit2.adapter.rxjava2.** { *; }