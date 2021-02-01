package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity { // 안드로이드의 기본제공되는 액티비티를 상속받는 클래스이다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // 생명주기참고. 생명주기마다 자동함.

        setContentView(R.layout.activity_main);//R은 구성요소를 사용할 수 있도록해주는것. layout폴더에 activity_main파일을 불러와서 이 activity에 여기있는 구성요소를 셋팅하겠다.
    }

//    @Override
//    protected void onStart(){
//        super.onStart();
//    }
//
//    @Override
//    protected void onStart(){
//        super.onPause();
//    }
}