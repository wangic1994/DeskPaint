package c;

/**
 * Created by Administrator on 2017/5/26.
 */

public class JniClient {

     public native String getStr();

     public native int AddInt(int a, int b);

     static {
          System.loadLibrary("WangicClock");   //defaultConfig.ndk.moduleName
     }
}
