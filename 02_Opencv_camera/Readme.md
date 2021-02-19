# 오픈CV로 카메라사용해보기
## 준비
- Configure -> sdk manager -> SDK Tools\
![image](https://user-images.githubusercontent.com/70633080/108461614-5d52eb00-72be-11eb-968e-a915dd4cf322.png)\
![image](https://user-images.githubusercontent.com/70633080/108461713-90957a00-72be-11eb-8d7a-3bcfa7e262f8.png)
- 개발도구들을 관리할 수 있는 창이다. (어떤 api를 가지고 개발할 것인지를 선택)
- NDK, CMake를 선택 후 apply -> accpet -> 다운로드
- NDK : 안드로이드에서 native언어를 자바환경에서 사용하기 위해 구성해놓은 도구 모음.
- CMake : 지금까지 자바를 기반으로 개발을 하였기 때문에 C기반의 프로그램을 build 해줄 수 있는 도구가 필요하다. 따라서 C또는 C++을 컴파일,빌드할 수 있는 도구

- Open CV설치\
: 자바에서의 설치는 조금 복잡하다. (라이브러리 소스코드 다운 -> 적용) 과정을 거쳐야한다.
  - Android용 open cv lib 다운 <https://github.com/opencv/opencv/releases>
  - 4.5.0 버전의 android-sdk.zip다운
  - 새 프로젝트 -> native c++선택
  - 자바파일에서 static과 public~~ 파일 그리고 c++이라는 폴더가 생김
- Open CV 연결\
: open cv는 native 언어에 대한것과 java에 관한것 모두 연결을 해주어야 사용이 가능하다.
  - Import해주기 : File -> New -> Import Module
  - sdk폴더 선택
  
  ![image](https://user-images.githubusercontent.com/70633080/108473013-d0fdf380-72d0-11eb-8e08-96d8b1b1249f.png)
  - 모듈 종속성 설정:  File -> Project Structure -> Dependencies -> app -> + -> Module Dependency -> sdk체크 -> Ok
  
  ![image](https://user-images.githubusercontent.com/70633080/108473378-4bc70e80-72d1-11eb-987c-c42ce61ce0fc.png)
  - 경로지정 : Android -> app -> cpp -> CMakeLists.txt  또는 Project -> app -> src -> main -> cpp -> CMakeLists.txt
  
  ![image](https://user-images.githubusercontent.com/70633080/108473486-7022eb00-72d1-11eb-942d-55f8701cf77d.png)\
  ![image](https://user-images.githubusercontent.com/70633080/108474022-225ab280-72d2-11eb-9995-28a878e5b1d5.png)\
  ![image](https://user-images.githubusercontent.com/70633080/108474091-3d2d2700-72d2-11eb-8f54-8147e3b63304.png)

# 초기설정
## 1. AndroidManifest.xml
- Camera permission부여
```
 <uses-permission android:name="android.permission.CAMERA"/>
```
- 권한을 부여하여 사용자의 동의(ex) 카메라를 허용하시겠습니까?)없이도 사용가능 하도록함.  
- 그러나 버전23부터는 권한부여를 해도 사용자가 이를 허용하지 않으면 사용할 수 없음.
- 현재 실습에서 사용한 핸드폰은 21버전이기 때문에 동의없이 카메라 사용이 가능한것. 

## 2. Activity_main.xml
- Layout 구성
```
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <org.opencv.android.JavaCameraView  
        android:layout_width='match_parent'
        android:layout_height='match_parent'
        android:id="@+id/camera_view"
        />
</LinearLayout>
```
- org.opencv.android.JavaCameraView: 오픈cv에서 제공하는 뷰를 가져와서 사용 z카메라에서 이미지를 받아와서 띄워주는 뷰 (자동완성 안됨)

## 3. MainActivity.java
- Native library불러오기
```
static { // 1.
        System.loadLibrary("native-lib"); // 2.
        System.loadLibrary("opencv_java4"); // 3. 라이브러리에대한 정보를 담고있는 파일을 불러오는것. sdk\native\libs\armabi~폴더안에있음 lib란 글자는 자동으로 붙음
    }
```
  - 1.: import된 lib들을 불러오는 과정이다. 
  - 2.: native-lib을 사용해 native언어를 다룰 것이므로 이를 불러옴
  - 3.: 라이브러리에 대한 정보를 담고있는 파일을 불러온다. (sdk\native\libs\armabi~폴더안에있음 lib란 글자는 자동으로 붙음)

- Camera View 셋팅
```
import org.opencv.android.JavaCameraView;
import org.opencv.android.CameraBridgeViewBase;

private JavaCameraView camera_view; // 1.

@Override
protected void onCreate(Bundle savedInstanceState) { // 2.
    super.onCreate(savedInstanceState);

    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    setContentView(R.layout.activity_main);

    camera_view = (JavaCameraView)findViewById(R.id.camera_view); // 3.
    camera_view.setCameraIndex(0); // 4. 0이 후면 1이 전면
    camera_view.setCvCameraViewListener(listner); // 5.
    camera_view.setCameraPermissionGranted();
    camera_view.enableView();


}
```
- 1.: 카메라뷰 객체생성 , ```private CameraBridgeBase camera_view``` 로도 사용가능 
- 2.: 자동완성 
- 3.: activity_main에서의 camera_view의 ID를 받아옴
- 4.: 0이면 후면, 1이면 전면
- 5.: listner를 넣어주어야한다. 이는 아래코드에서 나옴

- Camera View listner 정의
```
CameraBridgeViewBase.CvCameraViewListener2 listner = new CameraBridgeViewBase.CvCameraViewListener2() {
        @Override
        public void onCameraViewStarted(int width, int height) {

        }

        @Override
        public void onCameraViewStopped() {

        }

        @Override
        public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
            return inputFrame.rgba();
        }
};
```


- 추가로 Permission check\
![image](https://user-images.githubusercontent.com/70633080/108480349-30143600-72da-11eb-97b3-5cd61293880f.png)
  -  Android Marshmallow(23) 버전 이상부터는 별도의 permission체크가 필요하다.
  -  본 실습에서는 이 코드를 사용하지 않아도됨.

- NDK를 이용한 영상처리 추가
```
CameraBridgeViewBase.CvCameraViewListener2 listner = new CameraBridgeViewBase.CvCameraViewListener2() {
        @Override
        public void onCameraViewStarted(int width, int height) {

        }

        @Override
        public void onCameraViewStopped() {

        }

        @Override
        public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
            Mat mat = inputFrame.rgba(); // 1.
            Mat ret = new Mat(); // 2.
            function(mat.getNativeObjAddr(), ret.getNativeObjAddr()); // 3. 
            // 4. 

            return ret;

        }
};
```
  - 카메라에 프레임이 들어왔을때, event
  - input Frame을 받아와 rgb로 변환하여 출력하는 코드이다.
  - 1.: java의 opencv 에있는 map ?? 으로 이미지를 받아옴 rgb로
  - 2.: 따로 output주소가 필요하기때문에 복사해서 새로운 변수를 만듬
  - 3.: 따로 return을 하지않더라도 주소값을 넘겨줬으므로 수정된 것을 참조해서 사용할 수 있음
  - 4.: 이제 cpp에서 영상처리 코드만 넣어주면됨

  - Native method 작성 ( native-lib.cpp 파일의 코드)
  ```
  #include <jni.h>
  #include <string>
  #include "opencv2/opencv.hpp"
  using namespace cv;
  
  extern "C" JNIEXPORT jstring JNICALL
  Java_com_example_practic2_MainActivity_stringFromJNI(
          JNIEnv* env,
          jobject /* this */) {
      std::string hello = "Hello from C++"; // 1.
      // 2.
      return env->NewStringUTF(hello.c_str());
  }

  extern "C"
  JNIEXPORT void JNICALL
  Java_com_example_practic2_MainActivity_function(JNIEnv *env, jobject thiz, jlong input_addr,
                                                  jlong output_addr) {

      Mat &input =*(Mat *)input_addr; // 3.
      Mat &result = *(Mat *)output_addr;

      cvtColor(input, result, COLOR_BGR2GRAY);

      // TODO: implement function()
  }
  ```
- 1.: 자바환경으로부터 호출되었으므로 호출된 자바환경에 대해알아야 처리가 가능하다.
- 2.: 전달받은 환경에 대해서 처리가능한 형식으로 반환한다.
- 3.:  input_addr은 숫자의 형태로 주소값이 저장되어있음 
  
# Application 
