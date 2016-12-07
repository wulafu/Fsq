package com.wulafu.fsq.utils;

/**
 * 创建者: wwd
 * 创建日期:16/7/27
 * 类的功能描述:定义百代服务器返回的响应码,后续根据后台添加对应状态码
 */
public interface BaiDaiHttpResponseCode {
  /**
   * 服务器返回正确的数据
   */
  int BAIDAI_CODE_SUCCESS = 200;
  /**
   * token失效
   */
  int BAIDAI_CODE_TOKEN_INVALID = 401;
  /**
   * 条件不满足
   */
  int BAIDAI_CODE_403 = 403;
}
