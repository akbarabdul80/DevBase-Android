<h1 align="center">Zero Dev Base Android</h1></br>
<p align="center"> 
🏗 Development base library designed to expedite and streamline the Android app development process. This library offers various helper functions and components that you can use to accelerate your development.
</p>
</br>

## Download
Go to the [Releases](https://github.com/skydoves/TransformationLayout/releases) to download latest version.

## Key Features

- **ViewBinding** : Say goodbye to the hassle of manually initializing setContentView – our library simplifies the process by providing seamless support for fragments, activities, and even bottom sheets.
- **ViewExt** : Unlock a treasure trove of extension functions for effortless Android development. From handling currency, dates, and ImageView, to supercharging EditText – our library offers an array of versatile tools to enhance your app-building experience.
- **RxJava** : Streamline your API response handling in ViewModel using our RxJava extensions. We've designed these features to effortlessly manage observables, making your asynchronous data management more intuitive and efficient.


## Requirements

- Min API Level: 21

## Including in your project
[![JitPack](https://jitpack.io/v/akbarabdul80/DevBase-Android.svg)](https://jitpack.io/#akbarabdul80/DevBase-Android)

### Gradle
Add the dependency below to your **module**'s `build.gradle` file:

```gradle
dependencies {
    implementation("com.github.skydoves:transformationlayout:1.1.3")
}
```

## How to Use

### Setup
Add the dependency below to your **module**'s `build.gradle` file:
```gradle
android {
  ..
  buildFeatures {
      viewBinding true
  }
}
```

### ViewBinding
In ViewBinding we provide [SpotsDialog](https://github.com/dybarsky/spots-dialog) and empty function for clean your code you just override this function

```
private fun onViewReady() {
    initUI()
    initData()
    initAction()
    initObserver()
}

protected open fun initData() {
    // Intended to be empty
}

protected open fun initUI() {
    // Intended to be empty
}

protected open fun initAction() {
    // Intended to be empty
}

protected open fun initObserver() {
    // Intended to be empty
}
```

#### ViewBinding Activity
You can use `BaseActivity` class with generic type `ViewBinding`
```activity
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun initUI() { // Optional
        super.initUI()
    }

    override fun initData() { // Optional
        super.initData()
    }

    override fun initAction() { // Optional
        super.initAction()
    }

    override fun initObserver() { // Optional
        super.initObserver()
    }
}
```

#### ViewBinding Fragment
You can use `BaseFragment` class with generic type `ViewBinding`
```fragemnt
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun initUI() { // Optional
        super.initUI()
    }

    override fun initData() { // Optional
        super.initData()
    }

    override fun initAction() { // Optional
        super.initAction()
    }

    override fun initObserver() { // Optional
        super.initObserver()
    }
}
```

#### ViewBinding Fragment
You can use `BaseBottomSheetFragment` class with generic type `ViewBinding`
```fragemnt
class BottomPaymentFragment : BaseBottomSheetFragment<FragmentBottomPayment>() {
    override fun initUI() { // Optional
        super.initUI()
    }

    override fun initData() { // Optional
        super.initData()
    }

    override fun initAction() { // Optional
        super.initAction()
    }

    override fun initObserver() { // Optional
        super.initObserver()
    }
}
```

### RXJava Retrofit
### Response Base
You can use default response base with this `DevResponse`

**Data Class (You don't need to create this data class)**
```
data class DevResponse<T>(
    @SerializedName("sukses") val success: Int?,
    @SerializedName("pesan") val message: String?,
    @SerializedName("received") val data: T?
)
```

**API Endpoint**
```
@FormUrlEncoded
@POST("api/")
fun login(
    @Field("username") username: String,
    @Field("password") password: String
): Single<DevResponse<List<DataUser>>>
```

**RxJava**
```
// for init state
fun Single<*>.observe(state: MutableLiveData<DevStateDynamic>){}

// for observer
inline fun <reified T> MutableLiveData<DevStateDynamic>.observer()
```


### Make generic Response Base
You can custom your response base with your `SerializedName` GSON, just create new data class like below

**Data Class**
```
data class DevResponseDynamicExample<T>(
    @SerializedName("success")
    override val success: Int?,
    @SerializedName("message")
    override val message: String?,
    @SerializedName("data")
    override val data: T?
) : DevResponseDynamicInterface<T>
```

**API Endpoint**
```
@FormUrlEncoded
@POST("api/")
fun login(
    @Field("username") username: String,
    @Field("password") password: String
): Single<DevResponseDynamicExample<List<DataUser>>>
```

**RxJava**
```
// for init state
fun Single<*>.observeDynamic(state: MutableLiveData<DevStateDynamic>){}

// for observer
inline fun <reified T> MutableLiveData<DevStateDynamic>.observerDynamic()
```
