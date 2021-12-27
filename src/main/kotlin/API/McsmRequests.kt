package moe.dazecake.API

import moe.dazecake.McsmBot
import moe.dazecake.McsmBotConfig
import net.mamoe.mirai.utils.info
import net.mamoe.mirai.utils.warning
import okhttp3.*
import java.io.IOException

object McsmRequests {
    private val McsmIP by McsmBotConfig::McsmIP
    private val serverName by McsmBotConfig::ServerName

    fun getStatus() {
        McsmBot.logger.info{ McsmIP}
        val request = Request.Builder()
            .url("${McsmIP}api/status/${serverName}")
            .build()
        OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                McsmBot.logger.warning { "出现网络问题,请求失败!" }
            }

            override fun onResponse(call: Call, response: Response) {
//                val res = Gson().fromJson(response.body?.string(), hashMapOf<String, String>().javaClass)
                McsmBot.logger.warning { response.body?.string() }
            }
        })
    }
}