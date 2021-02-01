# 기본 기능 및 역할 및 연습
# 1. AndroidManifest.xml
- 경로 : app\src\main\AndroidManifest.xml
```
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round" <!-- 1 -->
        android:supportsRtl="true"
        android:theme="@style/Theme.Test">
        <activity android:name=".MainActivity"> <!-- 2 -->
            <intent-filter> <!-- 3 -->
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```
- 1 : 안드로이드에서 제공하는 기본아이콘이다. 앱 아이콘으로 이를 사용하겠다는 것.\
![image](https://user-images.githubusercontent.com/70633080/106446682-e519b680-64c3-11eb-8020-bc37191a7b9a.png)
- 2 : application안에 화면을 연결. 화면의 이름이 MainActivity 
- 3 : intent-filter 안에 포함된 activity들을 앱 실행시 가장 먼저 사용하겠다는 뜻.

# 2. activity_main.xml
```
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/hello"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        android:background="#000000"
        android:textColor="#ffffff"
        android:layout_weight="1"
        android:layout_margin="10dp"
        android:layout_marginBottom="10dp"
        android:padding="10dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <TextView
        android:id="@+id/textView"
        android:layout_width="77dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:text="TextView" />

</LinearLayout>
```
1. LinearLayout\
: label과 같은 역할을 한다.
- xmlns~~ : 자동완성으로 써도되고 안써도됨.
- layout_width : activity의 어느정도의 가로면적을 차지할 것인지.
  - match_parent : 부모를 다 차지하겠다. = activity화면을 가득 채우겠다.
  - warp_content : contents에 딱 맞게하겠다. 
  - 0하면 자동완성뜨는 것들 (dp,in,mm 등) : 핸드폰 내에서 몇픽셀을 차지하는지 등 절대크기를 지정할 수 있음 ex) 10dp 
    - dp를 가장 많이 쓰며 in은 인치안에 몇픽셀, mm는 mm안에 몇픽셀을 의미한다.
- layout_height : activity의 어느정도의 세로면적을 차지할 것인지.
  - 속성은 width와 동일하다.
- context : 어떤 java class와 연결될 것인가. (자동 설정됨)
- orientation : 수평(horizion), 수직(vertical) 방향결정 가능

2. TextView\
: 문자열을 나타내는 박스
- layout_width, layout_height : 차지할 너비와 높이정보 (속성은 위와 동일)
- layout_weight : 차지하는 비율설정가능
- text : 어떤 문자열로 셋팅 할 것인지. ( design으로 선택 시 확인이 가능하다.)
- background : 배경색바꾸기 ex) #000000 > 검정
- textcolor : 글자색바꾸기 ex) #ffffff > 흰색
- layout_margin : layout과 textview의 거리를 띄울 수 있다.
- layout_marginBottom, Top, Left, Right : 아래, 위, 왼, 오 각각 띄울 수 있다. ( layout_marging과 함께사용이 불가능함)
- padding : 위젯 내부에있는 컨텐츠를 띄울 수 있다. ( 내 위젯으로부터 컨텐츠의 거리를 벌림)
- id : 이벤트 발생을 위해서는 위젯만의 고유한 id를 가져야 한다. ex) @+id/id이름
  - 원래있던 id불러올경우 : @+빼기

# 3. MainActivity.java
```
public class MainActivity extends AppCompatActivity { // 1

    @Override // 2
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // 3

        setContentView(R.layout.activity_main); // 4
    }
}
```
- 1 : 안드로이드의 기본제공되는 activity를 상속받는 class이다.
- 2 : 생명주기를 추가하려면 @Override 하면된다.
- 3 : 생명주기를 참고하면 onCreate이라는 기본제공함수가 있다. (자동완성으로 사용)
- 4 : R은 구성요소를 사용할 수 있도록 해주는 것이다. 이는 layout폴더에 activity_main파일을 불러와 이 activity에 해당 구성요소를 셋팅하겠다는 의미이다.
