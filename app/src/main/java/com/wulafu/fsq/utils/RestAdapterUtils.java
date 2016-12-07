package com.wulafu.fsq.utils;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.wulafu.fsq.BuildConfig;
import com.wulafu.fsq.config.IApiConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * RestAdapter工场类
 * Created by yuanwai on 16/2/22.
 */
public class RestAdapterUtils implements IApiConfig {



    public static final String API_BASE_URL = "https://api.douban.com/v2/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)

                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(initHttpChient()).build();
        return retrofit.create(serviceClass);
    }

    private static void initRetrofit() {
        if (mHttpRetrofit == null) {
            mHttpRetrofit =
                    new Retrofit.Builder()
                            .baseUrl(API_BASE_URL)
                            .client(initHttpChient())
                            .addConverterFactory(BaiDaiGsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
        }
    }


  /**
   * 文件缓存最大值
   */
  private static final int CACHESIZE = 10 * 1024 * 1024;
  /**
   * Timeout时间
   */
  private static final int TIMEOUTSCEOND = 30;
  /**
   * http的Retrofit
   */
  private static Retrofit mHttpRetrofit;
  /**
   * https证书工厂
   */
  private static SSLSocketFactory mSSLSocketFactory;

 // private static SignApi mSignApi;

  public static String yongleSign;

  /**
   * http接口操作模型
   */
  public static <T> T getRestAPI(String endpoint, Class<T> service, Context ctx) {
   /* if (!"true".equals(BaiDaiApp.mContext.getString(R.string.nativeJson)) || !endpoint.startsWith(
        BASE_URL)) {*/
      ////无网络的处理
      //if (!NetworkUtils.isNetworkAvaliable()) {
      //  ToastUtil.showNormalShortToast(
      //      BaiDaiApp.mContext.getResources().getString(R.string.the_current_network));
      //  try {
      //    throw new Throwable("网络连接异常");
      //  } catch (Throwable throwable) {
      //    throwable.printStackTrace();
      //  }
      //}
      initRetrofit();

      return mHttpRetrofit.create(service);
   /* } else {
      DynamicRestAdapter adapter = new DynamicRestAdapter();
      return adapter.create(service);
    }*/
  }

  /**
   * 获取http的访问对象
   */
  public static <T> T getRestAPI(String endpoint, Class<T> service) {
    return getRestAPI(endpoint, service, null);
  }
/*
  *//**
   * https接口操作模型
   *//*
  public static <T> T getHttpsRestAPI(Class<T> service) {
    return getHttpsRetrofit().create(service);
  }*/

  /**
   * 为okhttp设置https协议
   *//*
  private static OkHttpClient initHttpsChient() {
    if (mSSLSocketFactory == null) {
      try {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null, null);
        Certificate cnCertificate = getCertificate("cacert_cn.cer");
        Certificate comCertificate = getCertificate("cacert_com.cer");
        keyStore.setCertificateEntry("cnca", cnCertificate);
        keyStore.setCertificateEntry("comca", comCertificate);
        TrustManager[] tm = new X509TrustManager[] { new BDX509TrustManager(keyStore) };
        sslContext.init(null, tm, new java.security.SecureRandom());
        mSSLSocketFactory = sslContext.getSocketFactory();
      } catch (Exception e) {
        //LogUtils.LOGE("设置HTTPS出错", e);
      }
    }
    return new OkHttpClient.Builder().cache(getHttpCache(WApp.mContext.getCacheDir()))
        .sslSocketFactory(mSSLSocketFactory)
        .build();
  }*/

  /**
   * HTTPS的Clinet
   */

 private static OkHttpClient initHttpChient() {
    Interceptor logInterceptor;
    if (BuildConfig.DEBUG) {
      //只显示基础信息
      logInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    } else {
      logInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE);
    }
    return new OkHttpClient().newBuilder()

        .connectTimeout(TIMEOUTSCEOND, TimeUnit.SECONDS)
        .addNetworkInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();//获取请求
                    //这里就是说判读我们的网络条件，要是有网络的话我么就直接获取网络上面的数据，要是没有网络的话我么就去缓存里面取数据
                    if(!NetworkUtils.isNetworkAvaliable()){
                        request = request.newBuilder()
                                //这个的话内容有点多啊，大家记住这么写就是只从缓存取，想要了解这个东西我等下在
                                // 给大家写连接吧。大家可以去看下，获取大家去找拦截器资料的时候就可以看到这个方面的东西反正也就是缓存策略。
                                .cacheControl(CacheControl.FORCE_CACHE)
                                .build();
                        Log.d("CacheInterceptor","no network");
                    }
                    Response originalResponse = chain.proceed(request);
                    if(NetworkUtils.isNetworkAvaliable()){
                        //这里大家看点开源码看看.header .removeHeader做了什么操作很简答，就是的加字段和减字段的。
                        String cacheControl = request.cacheControl().toString();
                        return originalResponse.newBuilder()
                                //这里设置的为0就是说不进行缓存，我们也可以设置缓存时间
                                .header("Cache-Control", "public, max-age=" + 0)
                                .removeHeader("Pragma")
                                .build();
                    }else{
                        int maxTime = 4*24*60*60;
                        return originalResponse.newBuilder()
                                //这里的设置的是我们的没有网络的缓存时间，想设置多少就是多少。
                                .header("Cache-Control", "public, only-if-cached, max-stale="+maxTime)
                                .removeHeader("Pragma")
                                .build();
                    }
                }

            })
            .addInterceptor(logInterceptor)
        .build();
  }
    public static String getSingStr(String url) {
        HttpURLConnection conn = null;
        String mSignStr = "";
        try {
            conn = (HttpURLConnection) new URL(BASE_URL + IApiConfig.GET_SIGN_TIME).openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            if (Integer.parseInt(Build.VERSION.SDK) < Build.VERSION_CODES.FROYO) {
                System.setProperty("http.keepAlive", "false");
            }
            int code = conn.getResponseCode();
            if (code == 200) {
                InputStream inputStream = conn.getInputStream();
                //下面对获取到的输入流进行读取
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                String time = response.toString();

            } else {

            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return url + mSignStr;
    }
  /**
   * http的Retrofit
   */

  /*
  private static Retrofit getHttpsRetrofit() {
    return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(initHttpsChient())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();
  }
*/
  /**
   * 获取网络缓存路径
   */
  private static Cache getHttpCache(File directory) {
    return new Cache(directory, CACHESIZE);
  }

  /**
   * 生成证书
   *
   * @throws CertificateException
   * @throws IOException
   *//*
  private static Certificate getCertificate(String name) throws CertificateException, IOException {
    CertificateFactory cf = CertificateFactory.getInstance("X.509");
    InputStream certInput = HttpManager.class.getResourceAsStream(name);

    Certificate certificate;
    try {
      certificate = cf.generateCertificate(certInput);
    } finally {
      if (certInput != null) {
        certInput.close();
      }
    }

    return certificate;
  }*/

  /**
   * 证书管理类
   *//*
  static class BDX509TrustManager implements X509TrustManager {
    protected ArrayList<X509TrustManager> x509TrustManagers = new ArrayList();

    protected BDX509TrustManager(KeyStore... additionalkeyStores) {
      ArrayList factories = new ArrayList();

      TrustManagerFactory tmf;
      int var6;
      try {
        tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init((KeyStore) null);
        factories.add(tmf);
        KeyStore[] var7 = additionalkeyStores;
        var6 = additionalkeyStores.length;

        for (int tm = 0; tm < var6; ++tm) {
          KeyStore keyStore = var7[tm];
          TrustManagerFactory additionalCerts =
              TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
          additionalCerts.init(keyStore);
          factories.add(additionalCerts);
        }
      } catch (Exception var9) {
        throw new RuntimeException(var9);
      }

      Iterator var10 = factories.iterator();

      while (var10.hasNext()) {
        tmf = (TrustManagerFactory) var10.next();
        TrustManager[] var13;
        int var12 = (var13 = tmf.getTrustManagers()).length;
        for (var6 = 0; var6 < var12; ++var6) {
          TrustManager var11 = var13[var6];
          if (var11 instanceof X509TrustManager) {
            this.x509TrustManagers.add((X509TrustManager) var11);
          }
        }
      }

      if (this.x509TrustManagers.size() == 0) {
        throw new RuntimeException("Couldn\'t find any X509TrustManagers");
      }
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType)
        throws CertificateException {
      X509TrustManager defaultX509TrustManager = this.x509TrustManagers.get(0);
      defaultX509TrustManager.checkClientTrusted(chain, authType);
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType)
        throws CertificateException {
      Iterator var4 = this.x509TrustManagers.iterator();

      while (var4.hasNext()) {
        X509TrustManager tm = (X509TrustManager) var4.next();

        try {
          tm.checkServerTrusted(chain, authType);
          return;
        } catch (CertificateException var6) {
          ;
        }
      }

      throw new CertificateException();
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
      //                            return new X509Certificate[0];
      ArrayList list = new ArrayList();
      Iterator var3 = this.x509TrustManagers.iterator();

      while (var3.hasNext()) {
        X509TrustManager tm = (X509TrustManager) var3.next();
        list.addAll(Arrays.asList(tm.getAcceptedIssuers()));
      }

      return (X509Certificate[]) list.toArray(new X509Certificate[list.size()]);
    }
  }
*/
 /* public static String getSingStr(String url) {
    HttpURLConnection conn = null;
    String mSignStr = "";
    try {
      conn = (HttpURLConnection) new URL(BASE_URL + IApiConfig.GET_SIGN_TIME).openConnection();
      conn.setRequestMethod("POST");
      conn.setDoOutput(true);
      conn.setDoInput(true);
      conn.setUseCaches(false);
      if (Integer.parseInt(Build.VERSION.SDK) < Build.VERSION_CODES.FROYO) {
        System.setProperty("http.keepAlive", "false");
      }
      int code = conn.getResponseCode();
      if (code == 200) {
        InputStream inputStream = conn.getInputStream();
        //下面对获取到的输入流进行读取
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
          response.append(line);
        }
        String time = response.toString();
        SignBean mSignBean = new Gson().fromJson(time, SignBean.class);
        LogUtils.LOGE("签名数据" + AESOperator.getInstance()
            .encrypt(AESOperator.key + mSignBean.getData().getTime()));
        if (url.contains("?")) {
          mSignStr = "&sign=" + AESOperator.getInstance()
              .encrypt(AESOperator.key + mSignBean.getData().getTime()) + "&version="
              + DeviceUtils.getVersionName(BaiDaiApp.mContext) + "&clientType=2";
          yongleSign = mSignStr;
        } else {
          mSignStr = "?sign=" + AESOperator.getInstance()
              .encrypt(AESOperator.key + mSignBean.getData().getTime()) + "&version="
              + DeviceUtils.getVersionName(BaiDaiApp.mContext) + "&clientType=2";
        }
      } else {
        LogUtils.LOGD("status code: " + code);
      }
    } catch (Exception e) {
      System.out.print(e.getMessage());
    } finally {
      if (conn != null) {
        conn.disconnect();
      }
    }
    return url + mSignStr;
  }*/
}
