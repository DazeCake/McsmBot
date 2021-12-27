package moe.dazecake

import moe.dazecake.utils.ConfigValidator
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.BotOnlineEvent
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
        logger.info { "读取配置中..." }
        McsmBotConfig.reload()
        McsmBotCommand.register()
//      "服务器连接验证"
        ConfigValidator().verify(McsmBotConfig)
        GlobalEventChannel.filter {
            it is BotOnlineEvent && it.bot.id == McsmBotConfig.bot
        }.subscribeOnce<BotOnlineEvent> {

        }

    }
}