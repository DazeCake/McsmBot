package api

import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import moe.dazecake.McsmBot
import moe.dazecake.McsmBotConfig
import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.utils.warning
import okhttp3.*
import java.io.IOException

object McsmRequests {
    private val config = McsmBotConfig

    fun getStatus(group: Group) {

        val request = Request.Builder()
            .url("${config.McsmIP}api/status/${config.ServerName}")
            .build()

        OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                McsmBot.logger.warning { "出现网络问题,请求失败!" }
            }

            override fun onResponse(call: Call, response: Response) {

                val res = Gson().fromJson(response.body?.string(), hashMapOf<String, String>().javaClass)
                runBlocking {
                    println(res.toString())
                    group.sendMessage(
                        """
                        名称: ${res["id"]}
                        版本: ${res["version"]}
                        在线人数: ${res["current_players"]}/${res["max_players"]}
                        状态: ${if (res["status"] == "true") "在线" else "离线"}
                    """.trimIndent()
                    )
                }

            }
        })
    }

    fun getStatus(group: Group,serverName:String) {

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
                    println(res.toString())
                    group.sendMessage(
                        """
                        名称: ${res["id"]}
                        版本: ${res["version"]}
                        在线人数: ${res["current_players"]}/${res["max_players"]}
                        状态: ${if (res["status"] == "true") "在线" else "离线"}
                    """.trimIndent()
                    )
                }

            }
        })
    }

}