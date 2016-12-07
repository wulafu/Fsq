package com.wulafu.fsq;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wulafu.fsq.utils.CommentAPI;
import com.wulafu.fsq.utils.RestAdapterUtils;
import com.wulafu.fsq.utils.User;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // http://v.juhe.cn/weather/index
                RestAdapterUtils.getRestAPI("",CommentAPI.class).getSearchBooks("小王子", "", 0, 3)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<User>() {
                            @Override
                            public void onCompleted() {
                            }
                            @Override
                            public void onError(Throwable e) {
                                Log.d("wulafu", "onError: -------------"+e.getMessage());
                            }
                            @Override
                            public void onNext(User user) {
                                Log.d("wulafu", "onNext: -------------"+user.getBooks().get(0).getAlt_title());
                                //Toast.makeText(getApplicationContext(),user.getBooks().get(0).getTitle(),Toast.LENGTH_LONG).show();
                            }
                        });

            }
        });


    }}