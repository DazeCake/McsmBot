package moe.dazecake.http

import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import moe.dazecake.McsmBot
import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.utils.info
import net.mamoe.mirai.utils.warning
import okhttp3.*
import java.io.IOException

class HttpRequests() {
    fun get(group: Group,url:String,successMsg:String,failMsg:String){

        val request = Request.Builder()
            .url(url)
            .build()

        OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                McsmBot.logger.warning { "出现网络问题,请求失败!" }
            }

            override fun onResponse(call: Call, response: Response) {

                val res = Gson().fromJson(response.body?.string(), hashMapOf<String, String>().javaClass)
                runBlocking {
                    McsmBot.logger.info { res["status"].toString() }
                    if (res["status"].toString() == "200.0") {
                        group.sendMessage(successMsg)
                    } else {
                        group.sendMessage(failMsg)
                    }
                }

            }
        })
    }
}