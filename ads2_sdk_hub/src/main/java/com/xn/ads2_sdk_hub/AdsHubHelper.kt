package com.xn.ads2_sdk_hub

import android.content.Context
import android.util.Log
import com.s987j.interweb.ads.InterWebAd
import com.s987j.interweb.ads.InterWebAdsSdk
import com.s987j.interweb.ads.callback.AdLoadCallback
import com.s987j.interweb.ads.callback.AdShowCallback
import com.s987j.interweb.ads.callback.InitCallback
import com.s987j.interweb.ads.config.InterWebAdsConfig
import com.s987j.interweb.ads.model.AdError
import com.xn.ads2_sdk_hub.configbean.init.BaseInitBean
import com.xn.ads2_sdk_hub.configbean.init.InterWebInitBean
import com.xn.ads2_sdk_hub.configbean.load.BaseLoadBean
import com.xn.ads2_sdk_hub.configbean.load.InterWebLoadBean
import com.xn.ads2_sdk_hub.configbean.showBean.BaseShowBean
import com.xn.ads2_sdk_hub.configbean.showBean.InterWebShowBean

object AdsHubHelper {
    private const val TAG = "AdsHub-Helper"
    private var interWebAd: InterWebAd? = null
    fun init(context: Context, initBaen: BaseInitBean) {

        if (initBaen is InterWebInitBean) {
            val config = InterWebAdsConfig(
                appId = initBaen.appId,       // 从平台获取的应用 ID
                appToken = initBaen.appToken  // 从平台获取的应用 Token
            )

            InterWebAdsSdk.init(context, config, object : InitCallback {
                override fun onSuccess() {
                    // SDK 初始化成功，可以开始加载广告
                    Log.i(TAG, "InterWeb 初始化成功")
                }
                override fun onError(code: Int, message: String) {
                    // 初始化失败，检查 appId / appToken 是否正确
                    Log.e(TAG, "InterWeb 初始化失败: code=$code, message=$message")
                }
            })
        }



    }

    fun loadAd(loadBean: BaseLoadBean) {
        if (loadBean is InterWebLoadBean) {
            InterWebAdsSdk.loadAd(
                unitId = loadBean.unitId,   // 广告位 ID，从平台获取
                advBundle = loadBean.advBundle, // 广告主的应用包名
                cou = loadBean.cou,               // 用户所在国家（可选，见 1.1 配置表）
                callback = object : AdLoadCallback {
                    override fun onAdLoaded(ad: InterWebAd) {
                        // 广告加载成功，保存对象，等待展示
                        interWebAd = ad
                    }
                    override fun onAdLoadFailed(error: AdError) {
                        // 广告加载失败，可查看错误码或稍后重试
                        Log.e(TAG, "InterWeb 加载失败: code=${error.code}, msg=${error.message}")
                    }
                }
            )
        }

    }

    fun showAd(showBean: BaseShowBean) {
        if (showBean is InterWebShowBean) {
            if (interWebAd?.isReady() == true) {
                interWebAd?.show(showBean.activity, object : AdShowCallback {
                    override fun onAdShown() {
                        // 广告已成功展示给用户
                        Log.e(TAG, "InterWeb 展示成功")

                    }
                    override fun onAdShowFailed(error: AdError) {
                        // 展示失败，可重新加载
                        Log.e(TAG, "InterWeb 展示失败: code=${error.code}, msg=${error.message}")
                    }
                    override fun onAdClosed() {
                        // 用户关闭了广告，在此处销毁对象并重新加载
                        interWebAd?.destroy()
                        interWebAd = null
                        // 可在此处提前加载下一条广告
                    }
                })
            } else {
                Log.e(TAG, "InterWeb ad is null or is not ready")
            }
        }

    }

    fun destory() {
        interWebAd?.destroy()
        interWebAd = null
    }

    fun release() {
        InterWebAdsSdk.release()
    }
}