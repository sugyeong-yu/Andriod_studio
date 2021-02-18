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

## 1. 버튼누르면 log print , toast message 하는 event 생성 및 연결
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

## 2. 버튼을 눌렀을때, textbox에 입력한 문자열을 가져와서 textview의 문자를 바꿔줌
```
 button.setOnClickListener(new View.OnClickListener() { // 1.
            @Override
            public void onClick(View v) {
                String str = editText.getText().toString(); // 2.
                str += "!!!"; // 3.

                textView.setText(str); // 4.
            }
        });
```
- 1. : 버튼이 눌렸을때(자동완성)
- 2. : editText 즉, textbox에서 getText()로 toString() 문자열로 받아옴.
- 3. : 받아온 문자열에 !!!를 붙여줌
- 4. : textView에 str을 setting.
- 결과
  - 초기화면
   
  ![image](https://user-images.githubusercontent.com/70633080/108345222-5d50dd80-7221-11eb-978f-8b3bf2b0d645.png) 
  ![image](https://user-images.githubusercontent.com/70633080/108345389-8b362200-7221-11eb-8e1b-e131bdfa644f.png)\
  - 버튼 누른뒤\
  ![image](https://user-images.githubusercontent.com/70633080/108345440-9c7f2e80-7221-11eb-82f2-2b752b7a25f6.png)

## 3. 체크박스가 바뀔 때 event
```
checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // 1.
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //checkbox.ischecked로 체크가 되어있으면 ~ 안되어있으면 ~
        boolean ret = checkBox.isChecked() // 2.
        if (ret){ // 3.

        }
        else { // 4.

        }

    }
});
```
- 1. : 체크박스가 눌렸을때 (자동완성)
- 2. : checkBox.isChecked() 로 체크박스가 눌렸는지 안눌렸는지 상태를 True or False로 받아옴
- 3. : True일때 이벤트
- 4. : False일때 이벤트

## 4. 버튼이 눌렸을때 image view띄우기
```
button.setOnClickListener(new View.OnClickListener() { // 1.
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.icon); // 2.
            }
        });
```
- 1. : 버튼이 눌렸을때 (자동완성)
- 2. : R이 자기자신 쓸수있게끔, icon image를 imageView에 띄움
- 결과\
![image](https://user-images.githubusercontent.com/70633080/108346566-ddc40e00-7222-11eb-9099-250e61b96a9f.png)
![image](https://user-images.githubusercontent.com/70633080/108346614-eae0fd00-7222-11eb-9809-ea779e2fb83b.png)

## 5. 화면을 여러개띄울때 ( activity를 main이외의 창에 다른 activity를 띄울때
- File -> New -> Activity 를 누르면 새로운 창이 뜸
```
button.setOnClickListener(new View.OnClickListener() {// 1. 이벤트 달기
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, layouttestActivity.class); // 2.
                startActivity(intent); // 3. 다른 액티비티를 화면에 띄움

            }
        });
```
- 1. : 버튼이 눌렸을때(자동완성)
- 2. : new Intent(Main~, new_activity.class)로 Main에서 new activity를 띄우겠다. 라는 Intent를 생성
- 이때, 새로만든 activity인 layouttestActivity에는 아래의 코드가 작성되어 있음.
```
public class layouttestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layouttest);
    }
}
```
- 3. : 이 새로만든 activity를 화면에 띄움
- 결과\
![image](https://user-images.githubusercontent.com/70633080/108347383-c76a8200-7223-11eb-8737-547c74eba369.png)
![image](https://user-images.githubusercontent.com/70633080/108347432-d3eeda80-7223-11eb-80eb-51cc15aa8406.png)
