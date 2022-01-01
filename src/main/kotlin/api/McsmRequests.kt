package api

import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import moe.dazecake.McsmBot
import moe.dazecake.McsmBotConfig
import moe.dazecake.http.HttpRequests
import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.utils.info
import net.mamoe.mirai.utils.warning
import okhttp3.*
import java.io.IOException

object McsmRequests {
    private val config = McsmBotConfig
    private val httpRequests = HttpRequests()

    fun getStatus(group: Group, serverName: String = config.ServerName) {

        val request = Request.Builder()
            .url("${config.McsmIP}api/status/${serverName}")
            .build()

        OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                McsmBot.logger.warning { "出现网络问题,请求失败!" }
            }

            override fun onResponse(call: Call, response: Response) {

                val res = Gson().fromJson(response.body?.string(), hashMapOf<String, String>().javaClass)
                runBlocking {
                    McsmBot.logger.info { res.toString() }
                    group.sendMessage(
                        """
                        名称: ${res["id"]}
                        版本: ${res["version"]}
                        在线人数: ${res["current_players"]}/${res["max_players"]}
                        状态: ${if (res["status"].toString() == "true") "在线" else "离线"}
                    """.trimIndent()
                    )
                }

            }
        })
    }

    fun openServer(group: Group, serverName: String = config.ServerName) {
        httpRequests.get(
            group, "${config.McsmIP}api/start_server/${serverName}?apikey=${config.APIkey}",
            """
                成功开启服务器
            """.trimIndent(),
            """
                开启服务器失败
            """.trimIndent()
        )
    }

    fun closeServer(group: Group, serverName: String = config.ServerName) {
        httpRequests.get(
            group,"${config.McsmIP}api/stop_server/${serverName}?apikey=${config.APIkey}",
            """
                成功关闭服务器
            """.trimIndent(),
            """
                服务器未能成功关闭
            """.trimIndent()
        )
    }

    fun restartServer(group: Group, serverName: String = config.ServerName) {
        httpRequests.get(
            group,"${config.McsmIP}api/restart_server/${serverName}?apikey=${config.APIkey}",
            """
                成功重启服务器
            """.trimIndent(),
            """
                服务器未能成功重启
            """.trimIndent()
        )
    }
}