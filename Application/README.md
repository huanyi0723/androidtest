接触android已经有一段时间了，一直以为android程序的入口是配置文件中指定的Activity，最近看一个开源项目，
发现里面实现了android 的Application类，才知道，android程序的真正入口是Application类的onCreate方法。
只不过大多数开发者无需重写该类，它的继承关系如下图:java.lang.Object
   ? android.content.Context
     ? android.content.ContextWrapper
       ? android.app.Application

android.app.Application类包含了4个公开的方法
   void  onConfigurationChanged(Configuration newConfig)
   void  onCreate()  //这里才是真正的入口点。
   void  onLowMemory()
   void  onTerminate()

下面是测试代码：
使用application需要两个步骤：1.复写Application类，2.在配置文件中配置
示例代码如下：
MyApp类：

package app.app;
import android.app.Application;
import android.content.res.Configuration;
public class MyApp extends Application {
@Override
public void onConfigurationChanged(Configuration newConfig) {
  super.onConfigurationChanged(newConfig);
}
@Override
public void onCreate() {
  super.onCreate();
  System.out.println("MyApp is called");
}
@Override
public void onLowMemory() {
  super.onLowMemory();
}
@Override
public void onTerminate() {
  super.onTerminate();
}
}



配置文件：
<application android:icon="@drawable/icon" android:label="@string/app_name" android:name="app.app.MyApp" >
  ......
</application>

Activity类:
public void onCreate(Bundle savedInstanceState) {

  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  System.out.println("MainActivity is called");
    
}

结果：MyApp is called       MainActivity is called    

注释1：在Android中，Application只是一个松散的表征概念，没有多少实质上的表征[和J2me的Midlet有明显区别]。Application类，代表应用程序上下文状态，是一个极度弱化的概念。Application只是一个空间范畴的概念，Application就是Activity，Service之类的组件上下文描述。Application并不是Android的核心概念，而Activity才是Android的核心概念
注释2：MyApplication类的作用是为了放一些全局的和一些上下文都要用到变量和方法。

