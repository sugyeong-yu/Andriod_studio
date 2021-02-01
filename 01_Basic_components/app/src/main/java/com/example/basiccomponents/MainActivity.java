package com.example.basiccomponents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    //이벤트달기
    //버튼을 자바소스에서 사용할 수 있게 받아오기 저장할변수선언
    private Button button;
    private TextView textView;
    private EditText editText;
    private  CheckBox checkBox;
    private  RadioGroup radioGroup;
    private RadioButton radioButton1;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button); // id로 부터 어떤 view를 찾겠다. 리소스 접근을 위해 R을 사용, 버튼으로 형변환
        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
        checkBox = (CheckBox)findViewById(R.id.checkBox);
        imageView = (ImageView)findViewById(R.id.imageView);

        // 1. 버튼누르면 로그출력, 토스트메세지 출력하는 이벤트 달ㅇ보기
//        button.setOnClickListener(new View.OnClickListener() {//이벤트 달기
//            @Override
//            public void onClick(View v){
//                Log.d("@@@@@@@@", "click됨");
//                Toast.makeText(MainActivity.this, "dfs", Toast.LENGTH_SHORT).show();
//
//            }
//        });

        // 2.버튼을 눌렀을때 textbox에 친 문자열을 가져와서 textview의 문자를 바꿔줌.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = editText.getText().toString();
                str += "!!!";

                textView.setText(str);
            }
        });

        // 3. 체크박스가 바뀌고 나서
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox.ischecked로 체크가 되어있으면 ~ 안되어있으면 ~
//                boolean ret = checkBox.isChecked()
//                if (ret){
//
//                }
//                else {
//
//                }

            }
        });

        // 4. 라디 오 int checkedid 는 어떤 라디오버튼이 눌려져잇는지를 받아올수있음
        // 아레는 그냥 버튼으로 한거.
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imageView.setImageResource(R.drawable.icon);
//            }
//        });

        // 5. 화면을 여러개,,? 액티비티를 메인말고 다른ㄱ액티비티도 해줘야함.  파일 -> 뉴 -> 액티비티
        // 버튼을 누르면 새로운 창이뜸.
        button.setOnClickListener(new View.OnClickListener() {//이벤트 달기
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, layouttestActivity.class);
                startActivity(intent); // 다른 액티비티를 화면에 띄움

            }
        });





    }
}