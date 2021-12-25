package moe.dazecake

import net.mamoe.mirai.console.extension.PluginComponentStorage
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info

object McsmBot : KotlinPlugin(
    JvmPluginDescription(
        id = "moe.dazecake.McsmBot",
        name = "McsmBot",
        version = "0.0.1",
    ) {
        author("DazeCake")
        info("""适用于Mcsmanager的QQ机器人，基于Mirai驱动""")
    }
) {
    override fun onEnable() {
        logger.info("[McsmBot]: 读取配置中...")
        McsmBotConfig.reload()
        logger.info { "[McsmBot]: 配置读取完成,插件已加载!" }
    }
}