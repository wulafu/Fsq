/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wulafu.fsq.utils;


import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import de.greenrobot.event.EventBus;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Converter;

/**
 * 创建者: wwd
 * 创建日期:16/6/28
 * 类的功能描述:相应body
 */
final class BaiDaiResponseBody<T> implements Converter<ResponseBody, T> {
  private final Gson gson;
  private final Type type;

  BaiDaiResponseBody(Gson gson, Type type) {
    this.gson = gson;
    this.type = type;
  }

  /**
   * 根据后台定义的状态码,处理不同的逻辑
   *
   * @throws IOException
   */
  @Override
  public T convert(ResponseBody value) throws IOException {
    BufferedSource bufferedSource = Okio.buffer(value.source());
    String response = bufferedSource.readUtf8();
    try {
     // LogUtils.LOGD("response>>" + response);
      //ResultResponse 只解析result字段
      HttpBaseBean resultResponse = gson.fromJson(response, HttpBaseBean.class);
      //根据后台状态码处理结果
      switch (resultResponse.getCode()) {
        case BaiDaiHttpResponseCode.BAIDAI_CODE_SUCCESS:
          break;
        //token过期
        case BaiDaiHttpResponseCode.BAIDAI_CODE_TOKEN_INVALID:
          //存储过期的token
          SharedPreferenceHelper.setInvalidUserToken(SharedPreferenceHelper.getUserToken());
          EventBus.getDefault().post(resultResponse);
          break;
        default:
          break;
      }
    } finally {
      bufferedSource.close();
      return gson.fromJson(response, type);
    }
  }
}
