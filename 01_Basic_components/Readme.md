# 기본 기능사용 및 ui설계
# 1. AndroidManifest.xml
```
<activity android:name=".layouttestActivity"></activity>
<activity android:name=".ConstraintLayoutActivity" />
<activity android:name=".FrameLayoutActivity" />
<activity android:name=".LinearLayoutActivity" />
<activity android:name=".MainActivity">
```
- 새로운 activity 를 추가할 수 있다.
  - File -> New -> Activity -> grid선택 ex) Empty~ 
  - 이 파일에 새로운 activity가 자동으로 추가됨.
  - app\src\main\res\layout 에 새로운 activity.xml파일이 생긴다. 
  - 이를 MainActivity.java파일에서 연결만 해주면 새로운 창 (activity)를 사용할 수 있다.

# 2. activity_main.xml
- ScrollView : scroll 내릴 수 있는 기능
  - < ScrollView  ~~~~ > : 괄호 안에있는 기능들에 한해서만 스크롤이 가능하다.
- LinearLayout : layout을 쌓는다는 것. 
  - 역시 <LinearLayout ~~~ > 괄호 안에 있는 기능들에 한해서만 쌓을 수 있다.
- TextView : text출력관련 기능
- EditText : text를 입력받을 수 있음 (문자열 입력 칸)
- Button : 버튼 (이벤트와 연결하여 버튼 눌렀을때, 이벤트 발생가능)
- ToggleButtion : 눌렀을때 버튼 색상 변경 또는 텍스트 변경과 같이 버튼의 상태에 따라 변화가 발생한다.
- CheckBox : 체크박스 
- RadioGroup : 라디오박스를 grouping, 같은 그룹에 있는 라디오박스들은 중복하여 선택될 수 없다.
  - <RadioGroup ~~~ > : 괄호안에 있는 라디오버튼에 대해서 같은 group으로 묶음
- RadioButton : 라디오박스이다.
  - id가 각 라디오박스마다 다름
- ImageView : image를 띄울 수 있음. PyQt에서는 라벨에 image를 띄웠지만 여기서는 따로 띄울 수 있다.
> layout dir에 있는 나머지 activity들은 추가된 activity이다. \
> event와 activity를 연결 시, 어떤 event발생시 해당 activity 창이 새롭게 열릴 수 있다.

# 3. MainActivity.java
```
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

private Button button;
private TextView textView;
private EditText editText;
private  CheckBox checkBox;
private  RadioGroup radioGroup;
private RadioButton radioButton1;
private ImageView imageView;
```
- activity_main.xml에서 만든 기능들을 java파일에서 사용할 수 있도록 불러온다.
- 불러온 소스들을 저장할 변수를 선언

```
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    button = (Button)findViewById(R.id.button); // 
    textView = (TextView) findViewById(R.id.textView);
    editText = (EditText) findViewById(R.id.editText);
    checkBox = (CheckBox)findViewById(R.id.checkBox);
    imageView = (ImageView)findViewById(R.id.imageView);
```
- findViewById : id로 부터 어떤 view를 찾겠다. id를 이용해 activity에서 만든 소스들을 변수에 저장한다.
- R. : 리소스 접근을 위해 R을 사용
- findView는 view를 반환하기 때문에 각 형태에 맞는 형변환을 해주어야한다. ex) (Buttion) , (TextView)

1. 버튼누르면 log print , toast message 하는 event 생성 및 연결
```
button.setOnClickListener(new View.OnClickListener() {// 1.
    @Override
    public void onClick(View v){
        Log.d("@@@@@@@@", "click됨"); // 2.
        Toast.makeText(MainActivity.this, "dfs", Toast.LENGTH_SHORT).show(); // 3.
    }
});
```
- 1.: 버튼이 눌렸을 때 (자동완성)
- 2.: 아래 Log_cat 에서 ("@@@@@@@@","click됨")이라는 log가 출력됨.
- 3.: Toast message로 "dfs"라는 메세지가 출력됨.
- 결과\
![image](https://user-images.githubusercontent.com/70633080/107037719-d3574c80-67fe-11eb-9feb-db432ba8d866.png)
![image](https://user-images.githubusercontent.com/70633080/107037781-ea963a00-67fe-11eb-9d12-f0aa920ce368.png)
