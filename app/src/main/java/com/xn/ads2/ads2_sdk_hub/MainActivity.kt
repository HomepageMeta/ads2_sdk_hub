package com.xn.ads2.ads2_sdk_hub

import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.xn.ads2.ads2_sdk_hub.ui.theme.Ads2_sdk_hubTheme
import com.xn.ads2_sdk_hub.AdsHubHelper
import com.xn.ads2_sdk_hub.configbean.init.InterWebInitBean
import com.xn.ads2_sdk_hub.configbean.load.InterWebLoadBean
import com.xn.ads2_sdk_hub.configbean.showBean.InterWebShowBean

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ads2_sdk_hubTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }


        AdsHubHelper.init(this@MainActivity, InterWebInitBean("UQ53HAHV", "9ixch9daqp548287"))

        Handler().postDelayed(
            {AdsHubHelper.loadAd(
                InterWebLoadBean(
                    "54a226b0-16c4-11f1-8591-c9000fa20814",
                    "com.lazada.android",
                    "CHN"
                )
            )},
            3000)

        Handler().postDelayed({AdsHubHelper.showAd(InterWebShowBean(this@MainActivity))}, 10000)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ads2_sdk_hubTheme {
        Greeting("Android")
    }
}