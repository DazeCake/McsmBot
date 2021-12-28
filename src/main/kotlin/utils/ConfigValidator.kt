package moe.dazecake.utils

import com.google.gson.Gson
import moe.dazecake.McsmBot
import moe.dazecake.McsmBotConfig
import net.mamoe.mirai.utils.error
import net.mamoe.mirai.utils.info
import net.mamoe.mirai.utils.warning
import okhttp3.*
import java.io.IOException

class ConfigValidator {
    fun verify(config: McsmBotConfig) {
        if (config.bot != 0L ||
            config.McsmIP != "" ||
            config.APIkey != "" ||
            config.ServerName != ""
        ) {
            val request = Request.Builder()
                .url("${config.McsmIP}api/start_server/${config.ServerName}?apikey=${config.APIkey}")
                .build()
            OkHttpClient().newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    McsmBot.logger.error { "出现网络问题,服务器连接检查失败!" }
                }

                override fun onResponse(call: Call, response: Response) {
                    val res = Gson().fromJson(response.body?.string(), hashMapOf<String, String>().javaClass)
                    if (res["status"] == "200") {
                        McsmBot.logger.info { "服务器连接检查成功!" }
                        McsmBot.logger.info { "配置读取完成,插件已加载!" }
                    } else if (res["status"] == "500" || res["error"] == "服务器已经运行,无法再继续运行") {
                        McsmBot.logger.info { "服务器连接检查成功!" }
                        McsmBot.logger.info { "配置读取完成,插件已加载!" }
                    } else {
                        McsmBot.logger.error { "服务器连接检查失败,请修正配置文件或检查面板连接参数!" }
                        McsmBot.logger.error { res.toString() }
                        McsmBot.onDisable()
                    }
                }
            })
        } else {
            McsmBot.logger.error { "配置文件出错,请修正配置文件或检查面板连接参数!" }
        }
    }
}