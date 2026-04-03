package com.xn.ads2_sdk_hub.configbean.load


/***
 * @param unitId = "YOUR_UNIT_ID",   // 广告位 ID，从平台获取
 * @param advBundle = "com.xxx.yyy", // 广告主的应用包名
 * @param cou = "IDN",               // 用户所在国家（可选，见 1.1 配置表）
 */
class InterWebLoadBean(var unitId: String, var advBundle: String, var cou: String): BaseLoadBean() {
}