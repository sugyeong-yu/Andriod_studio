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
  
#
