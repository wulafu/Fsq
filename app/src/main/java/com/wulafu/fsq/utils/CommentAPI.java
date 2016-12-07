package com.wulafu.fsq.utils;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 评论详情页面所有的API
 * Created by fengbei on 16/4/15.
 * 请求参数暂定
 * <p/>
 * modify by yujin on 2016/5/5
 */
public interface CommentAPI {


    @GET("book/search")
    Observable<User> getSearchBooks(@Query("q") String name,
                                    @Query("tag") String tag, @Query("start") int start,
                                    @Query("count") int count);
}
