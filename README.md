# Android_studio
Android studio tool을 이용해 APP을 개발해본다.
- Android 앱개발을 위한 공식통합개발환경(IDE)
- Intellij IDEA기반 (pycharm과 동일)
- 장점
    - 유연한 gradle 기반 빌드시스템
    - 에뮬레이터 : 핸드폰을 pc에서 구동가능함
    - 모든 android기기 지원
    - C++ 및 NDK지원
    - 빠른 빌드
- Build 과정\
![image](https://user-images.githubusercontent.com/70633080/106437059-31f79000-64b8-11eb-99e3-7cb3b9ea5552.png)

- Download\
<https://developer.android.com/studio?gclid=CjwKCAiA9bmABhBbEiwASb35V3tPg0Tce1ZH7Q0QovJosMuivfdHRxBrphqqnZFFfcPd-T48OSyeaBoCRBAQAvD_BwE&gclsrc=aw.ds>

- 새프로젝트 생성\
![image](https://user-images.githubusercontent.com/70633080/106437256-6ff4b400-64b8-11eb-9ec1-5b9bd96704db.png)\
![image](https://user-images.githubusercontent.com/70633080/106437291-7aaf4900-64b8-11eb-9894-f0984ec765c1.png)
- 원하는 형태의 ui를 선택가능하다.\
![image](https://user-images.githubusercontent.com/70633080/106437370-8bf85580-64b8-11eb-89e9-5c9051fb7562.png)
- 프로젝트이름설정, 경로설정, 개발언어설정, 지원할 최소 android version설정
  - android 5.0(Lolipop)으로 설정하여 진행하였다.
 
 - Android device 연결하기
    - USB driver 설치 <https://www.lge.co.kr/lgekor/download-center/downloadCenterList.do> 
    - Device 개발자모드 설정
    - USB 디버깅 허용누르기\
 ![image](https://user-images.githubusercontent.com/70633080/106442661-e1376580-64be-11eb-8a21-49ff4706c322.png)
 ![image](https://user-images.githubusercontent.com/70633080/106437538-b9dd9a00-64b8-11eb-9a72-f0dbdb7f0b83.png)\
 ![image](https://user-images.githubusercontent.com/70633080/106442936-42f7cf80-64bf-11eb-87fc-afad0f09d8ae.png)

# Project 구조
- 중요한 파일
1. MainActivity.java\
: 프로그램의 실제 동작을 명시하는 파일 (=.py)
2. Activity_main.xml\
: 프로그램의 위젯배치 등 디자인요소(ui)를 명시하는 파일
3. AndroidManifest.xml\
: 앱의 필수적인 정보를 명시하는 파일

- Android 4대 구성요소
1. Activity\
: 사용자와 상호작용하는 단일화면 (모든 앱은 하나이상의 액티비티를 포함한다.)
2. Service\
: background에서 처리해야하는 작업을 수행하는 요소 ( 앱을 종료하고도 노래가 계속해서 재생되는 것과 같은 기능을 service라함)
3. Broadcast receiver\
: OS로부터 발생하는 각종 event와 정보를 받아 처리하는 요소
4. Content provider\
: 데이터를 관리하고 다른앱의 데이터를 제공하는데 사용되는 요소

# 기능
- activity추가
    - File -> New -> Activity -> 원하는 grid ( ex) Empty ) 
    - app\src\main\res\layout 에 새로운 activity.xml파일이 생긴다. 
    - 이를 MainActivity.java파일에서 연결만 해주면 새로운 창 (activity)를 사용할 수 있다.
  
