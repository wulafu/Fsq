package com.wulafu.fsq.utils;

import android.text.TextUtils;

import com.wulafu.fsq.R;
import com.wulafu.fsq.application.WApp;

import java.util.Date;

import static com.wulafu.fsq.utils.SharedPreferenceUtil.getString;
import static com.wulafu.fsq.utils.SharedPreferenceUtil.putString;

/**
 * Created by xmren on 9/9/2015.
 * SharedPerence工具类
 */
public class SharedPreferenceHelper {
  /**
   * 是否记住密码
   */
  private static final String REMEMBER_PASSWORD = "remember_password";

  /**
   * 是否是第一次打开APP
   */
  private static String IS_FIRST_OPEN = "is_first_open";
  /**
   * 记住账户
   */
  private static String REMEMBER_ACCOUNT = "rememberAccount";
  /**
   * 是否自动登录
   */
  private static String ISAUTOLOGIN = "IsAutoLogin";
  /**
   * 密码的加密密码
   */
  private static String PASSWORD_CODE = "baidai";
  /**
   * 用户的token
   */
  private static String USER_TOKEN = "userToken";
  /**
   * 用于存储失效过期的token
   */
  private static String INVALID_USER_TOKEN = "invalidUserToken";
  /**
   * 用户的头像
   */
  private static String USER_PHOTOURL = "user_info_icon";
  /**
   * 用户的昵称
   */
  private static String USER_NICKNAME = "nickname";
  /**
   * 用户生日
   */
  private static String USER_BIRTHDAY = "user_birthday";
  /**
   * 用户邮箱
   */
  private static String USER_EMAIL = "user_email";
  /**
   * 用户标签
   */
  private static String USER_TAG = "user_tag";
  /**
   * 用户性别
   */
  private static String USER_GENDER = "user_gender";
  /**
   * 用户保存的手机号
   */
  private static String USER_MOBILE = "user_mobile";
  /**
   * 用户的账号
   */
  private static String USER_ACCOUNT = "user_account";
  /**
   * 用户的居住城市
   */
  private static String USER_CITY = "user_city";
  /**
   * 用户的居住城市
   */
  private static String USER_CITYID = "user_cityid";
  /**
   * 用户的MemberId
   */
  private static String USER_MEMBERID = "user_memberid";
  /**
   * 城市ID
   */
  private static String CITY_ID = "cityId";
  /**
   * 因首页增加美食，住宿，景点，购物对应的城市选择，先进行选择判断，
   * 从目的地进入对应模块，把此值改为0，用
   *
   * @see #CITY_ID
   * 选择的默认地址
   */
  private static String HOME_SELECT_ID = "homeCityID";
  /**
   * 城市名称
   */
  private static String CITY_NAME = "cityName";
  /**
   * 用户的居住城市
   */
  private static String USER_THIRD = "user_third";
  /**
   * 用户的居住城市
   */
  private static String USER_LABLE = "user_lable";
  /**
   * 城市所属省会
   */
  private static final String PROVINCE_NAME = "provinceName";
  /**
   * 天气id
   */
  private static final String WEATHER_CODE = "weatherCode";
  /**
   * 定位的经度
   */
  private static final String LOCATION_LONGITUDE_KEY = "LOCATION_LONGITUDE_KEY";
  /**
   * 定位过的维度
   */
  private static final String LOCATION_LATITUDE_KEY = "LOCATION_LATITUDE_KEY";

  /**
   * 定位的经度
   */
  private static final String LOCATION_LONGITUDE_KEY_FOR_MAP = "LOCATION_LONGITUDE_KEY_FOR_MAP";
  /**
   * 定位过的维度
   */
  private static final String LOCATION_LATITUDE_KEY_FOR_MAP = "LOCATION_LATITUDE_KEY_FOR_MAP";
  /**
   * 城市ID
   */
  private static String USER_ADDRESS = "address";
  /**
   * 订单号
   */
  private static String ORDER_NO = "order_no";

  /**
   * 景点，住宿，美食，购物，详情界面的的经度
   */
  private static final String MODULE_LOCATION_LONGITUDE_KEY = "module_location_longitude_key";
  /**
   * 景点，住宿，美食，购物，详情界面的的纬度
   */
  private static final String MODULE_LOCATION_LATITUDE_KEY = "module_location_latitude_key";
  /**
   * 城市ID
   */
  private static String DEVICE_TOKEN = "device_token";

  /**
   * 连续签到天数
   */
  private static String CONTINUE_SIGN_NUM = "continueSignNum";
  /**
   * 总签到天数
   */
  private static String SIGN_TOTAL_NUM = "signTotalNum";
  /**
   * 用户积分数
   */
  private static String INTEGRAL = "integral";
  /**
   * 是否已签到
   */
  private static String ISSIGNED = "isSigned";
  /**
   * debug模式切换环境地址
   */
  private static String ENV_URL = "env_url";

  /**
   * 用户类型
   */
  private static String USERTYPE = "userType";

  /**
   * 景点列表页的筛选id
   */
  private static String SELECT_ID = "select_id";
  /**
   * 美食列表页的筛选id
   */
  private static String FOOD_SELECT_ID = "food_select_id";
  /**
   * 酒店列表页的筛选id
   */
  private static String HOTEL_SELECT_ID = "hotel_select_id";
  /**
   * 酒店列表页的筛选id
   */
  private static String SHOP_SELECT_ID = "shop_select_id";

  /**
   * 达人标签
   */
  private static String EXPERT_TAG = "expert_tag";
  /**
   * 达人头像
   */
  private static String EXPERT_ICON = "expert_icon";




  /**
   * 获取用户头像
   */
  public static String getUserPhotoUrl() {
    return getString(USER_PHOTOURL);
  }

  /**
   * 获取用户昵称
   */
  public static String getUserNickName() {
    return getString(USER_NICKNAME, "未知用户");
  }

  /**
   * 是否是第一次使用APP
   */
  public static boolean getIsFirstUseApp() {
    return SharedPreferenceUtil.getBoolean(IS_FIRST_OPEN, true);//
  }

  /**
   * 设置是否是第一次进入APP
   */
  public static void setIsFirstUserApp(boolean b) {
    SharedPreferenceUtil.putBoolean(IS_FIRST_OPEN, b);
  }

  /**
   * 是否需要记住账户
   */
  public static boolean isRememberAccount() {
    return SharedPreferenceUtil.getBoolean(REMEMBER_ACCOUNT, true);
  }

  public static void setRememberAccout(boolean b) {
    SharedPreferenceUtil.putBoolean(REMEMBER_ACCOUNT, b);
  }

  /**
   * 获取用户的绑定手机号
   */
  public static String getUserMobile() {
    return getString(USER_MOBILE, null);
  }

  /**
   * 获取用户的账户
   */
  public static String getUserAccount() {
    return getString(USER_ACCOUNT, null);
  }

  /**
   * 获取用户的生日
   */
  public static String getUserBirthday() {
    return getString(USER_BIRTHDAY, null);
  }

  /**
   * 获取用户的邮箱
   */
  public static String getUserEmail() {
    return getString(USER_EMAIL, null);
  }

  /**
   * 获取用户的标签
   */
  public static String getUserTag() {
    return getString(USER_TAG, null);
  }

  /**
   * 获取用户的性别
   */
  public static int getUserGender() {
    return SharedPreferenceUtil.getInt(USER_GENDER, -1);
  }

  /**
   * 保存用户账户
   */
  public static void saveUserAccount(String account) {
    putString(USER_ACCOUNT, account);
  }

  /**
   * 保存用户所在城市
   */
  public static void saveUserCity(String city) {
    putString(USER_CITY, city);
  }

  /**
   * 获取用户的所在城市
   */
  public static String getUserCity() {
    return getString(USER_CITY, null);
  }

  /**
   * 保存用户所在城市id
   */
  public static void saveUserCityId(String cityId) {
    putString(USER_CITYID, cityId);
  }

  /**
   * 获取用户的所在城市id
   */
  public static String getUserCityId() {
    return getString(USER_CITYID, null);
  }

  /**
   * 保存用户密码
   */
  public static void saveUserPassWord(String passWord) {
    try {
      putString(PASSWORD_CODE, passWord);
      return;
    } catch (Exception e) {
      putString(PASSWORD_CODE, passWord);
    }
  }

  /**
   * 保存用户生日
   */
  public static void saveUserBirthday(Date birthday) {
    putString(USER_BIRTHDAY, birthday.toString());
  }

  /**
   * 保存用户邮箱
   */
  public static void saveUserEmail(String email) {
    putString(USER_EMAIL, email);
  }

  /**
   * 保存用户Tag
   */
  public static void saveUserTag(String tag) {
    putString(USER_TAG, tag);
  }

  /**
   * 保存用户性别
   */
  public static void saveUserGender(int gender) {
    SharedPreferenceUtil.putInt(USER_GENDER, gender);
  }

  /**
   * 保存用户昵称
   */
  public static void saveUserName(String name) {
    putString(USER_NICKNAME, name);
  }

  /**
   * 保存用户地址
   */
  public static void saveUserAddressr(String address) {
    putString(USER_ADDRESS, address);
  }

  /**
   * 保存用户头像
   */
  public static void saveUserIcon(String icon) {
    putString(USER_PHOTOURL, icon);
  }
  /**
   * 保存达人用户头像
   */
  public static void saveExpertUserIcon(String icon) {
    putString(EXPERT_ICON, icon);
  }

  /**
   * 获取达人用户头像
   */
  public static String getExpertUserIcon() {

    return getString(EXPERT_ICON, null);
  }
  /**
   * 保存用户手机号码
   */
  public static void saveUserMobile(String mobile) {
    putString(USER_MOBILE, mobile);
  }

  /**
   * 获取用户收货地址
   */
  public static void getUserAddressr(String address) {
    getString(USER_ADDRESS, null);
  }

  /**
   * 获取用户收货地址
   */
  public static void saveUserMemeberId(String address) {
    putString(USER_MEMBERID, null);
  }

  /**
   * 获取用户密码
   */
  public static String getPassword() {
    String string = getString(PASSWORD_CODE, "");
    return string;
  }

  /**
   * 获取用户的MemeberID
   */
  public static String getUserMemberId() {
    return getString(USER_MEMBERID, null);
  }

  /**
   * 是否是自动登录
   */
  public static boolean getIsAutoLogin() {
    return SharedPreferenceUtil.getBoolean(ISAUTOLOGIN, true);
  }

  public static void getIsAutoLogin(boolean isAutoLogin) {
    SharedPreferenceUtil.putBoolean(ISAUTOLOGIN, isAutoLogin);
  }

  /**
   * 获取用户Token
   */
  public static String getUserToken() {
    return getString(USER_TOKEN);
  }

  /**
   * 获取用户Token
   */
  public static void setUserToken(String token) {
    putString(USER_TOKEN, token);
  }

  /**
   * 获取失效的用户Token
   */
  public static String getInvalidUserToken() {
    return getString(INVALID_USER_TOKEN);
  }

  /**
   * 获取失效的用户Token
   */
  public static void setInvalidUserToken(String token) {
    putString(INVALID_USER_TOKEN, token);
  }

  /**
   * 注销用户Token
   */
  public static void loginOut() {
    SharedPreferenceUtil.getSharedPreferences()
        .edit()
        .remove(USER_TOKEN)
        .remove(USER_ACCOUNT)
        .remove(USER_BIRTHDAY)
        .remove(USER_MEMBERID)
        .remove(USER_ADDRESS)
        .remove(USER_CITY)
        .remove(USER_CITYID)
        .remove(USER_EMAIL)
        .remove(USER_NICKNAME)
        .remove(USER_GENDER)
        .remove(USER_MOBILE)
        .remove(USER_PHOTOURL)
        .remove(USER_TAG)
        .remove(PASSWORD_CODE)
        .commit();
    //        SharedPreferenceUtil.putString(USER_TOKEN,null);
  }



  /**
   * 获取城市ID
   */
  public static int getCityId() {
    return SharedPreferenceUtil.getInt(CITY_ID, -1);
  }

  /**
   * 存储首页选择的城市ID
   */
  public static void setHomeCityId(int cityId) {
    SharedPreferenceUtil.putInt(HOME_SELECT_ID, cityId);
  }

  /**
   * 获取首页选择的城市ID
   */
  public static int getHomeCityId() {
    return SharedPreferenceUtil.getInt(HOME_SELECT_ID, -1);
  }

  /**
   * 存储城市名称
   */
  public static void setCityName(String cityName) {
    putString(CITY_NAME, cityName);
  }

  /**
   * 获取城市名称
   */
  public static String getCityName() {
    return getString(CITY_NAME);
  }

  /**
   * 存储城市所属省名称
   */
  public static void setProviceName(String cityName) {
    putString(PROVINCE_NAME, cityName);
  }

  /**
   * 获取城市所属省名称
   */
  public static String getProviceName() {
    return getString(PROVINCE_NAME);
  }

  /**
   * 存储选中的天气id
   */
  public static void setWeatherCode(String weatherCode) {
    putString(WEATHER_CODE, weatherCode);
  }

  /**
   * 获取天气id
   */
  public static String getWeatherCode() {
    return getString(WEATHER_CODE);
  }


  /**
   * 存储订单号，当前交易的订单号
   *
   * @param orderNo 订单号
   */
  public static void setOrderNO(String orderNo) {
    putString(ORDER_NO, orderNo);
  }

  /**
   * 获取订单号
   */
  public static String getOrderNo() {
    return getString(ORDER_NO);
  }

  /**
   * 保存经度
   *
   * @param moduleLongitudeKey 经度
   */
  public static void setModuleLongitudeKey(String moduleLongitudeKey) {
    putString(MODULE_LOCATION_LONGITUDE_KEY, moduleLongitudeKey);
  }

  /**
   * 获取经度
   */
  public static String getModuleLongitudeKey() {
    return getString(MODULE_LOCATION_LONGITUDE_KEY);
  }

  /**
   * 保存纬度
   *
   * @param moduleLatitudekey 纬度
   */
  public static void setModuleLatitudeKey(String moduleLatitudekey) {
    putString(MODULE_LOCATION_LATITUDE_KEY, moduleLatitudekey);
  }

  /**
   * 获取纬度
   */
  public static String getModuleLatitudeKey() {
    return getString(MODULE_LOCATION_LATITUDE_KEY);
  }

  /**
   * 保存设备token
   */
  public static void setDeviceToken(String deviceToken) {
    putString(DEVICE_TOKEN, deviceToken);
  }

  /**
   * 获取设备token(信鸽推送)
   */
  public static String getDeviceToken() {
    return getString(DEVICE_TOKEN);
  }

  /**
   * 保存用户积分
   */
  public static void setIntegral(String integral) {
    putString(INTEGRAL, integral);
  }

  /**
   * 保存连续签到
   */
  public static void setContinueSignNum(String continueSignNum) {
    putString(CONTINUE_SIGN_NUM, continueSignNum);
  }

  /**
   * 保存地址
   */
  public static void setEnvUrl(String url) {
    putString(ENV_URL, url);
  }

  /**
   * 获取地址
   */
  public static String getEnvUrl() {
    String mEnvUrl = getString(ENV_URL);
    if (TextUtils.isEmpty(mEnvUrl)) {
      mEnvUrl = WApp.mContext.getString(R.string.abc_action_bar_home_description);
    }
    return mEnvUrl;
  }

  /**
   * 列表页保存筛选标签
   */
  public static void setSelectId(int id) {
    SharedPreferenceUtil.putInt(SELECT_ID, id);
  }
  /**
   * 获取获取筛选id
   */
  public static int getSelectId() {
    return SharedPreferenceUtil.getInt(SELECT_ID, -1);
  }
  /**
   * 美食列表页保存筛选标签
   */
  public static void setFoodSelectId(int id) {
    SharedPreferenceUtil.putInt(FOOD_SELECT_ID, id);
  }
  /**
   * 美食获取获取筛选id
   */
  public static int getFoodSelectId() {
    return SharedPreferenceUtil.getInt(FOOD_SELECT_ID, -1);
  }
  /**
   * 住宿列表页保存筛选标签
   */
  public static void setHotelSelectId(int id) {
    SharedPreferenceUtil.putInt(HOTEL_SELECT_ID, id);
  }
  /**
   * 住宿获取获取筛选id
   */
  public static int getHotelSelectId() {
    return SharedPreferenceUtil.getInt(HOTEL_SELECT_ID, -1);
  }
  /**
   * 购物列表页保存筛选标签
   */
  public static void setShopSelectId(int id) {
    SharedPreferenceUtil.putInt(SHOP_SELECT_ID, id);
  }
  /**
   * 购物获取获取筛选id
   */
  public static int getShopSelectId() {
    return SharedPreferenceUtil.getInt(SHOP_SELECT_ID, -1);
  }
}
