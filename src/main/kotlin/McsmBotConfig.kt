package moe.dazecake

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object McsmBotConfig : AutoSavePluginConfig("McsmBotConfig") {
    @ValueDescription(
        """
        Bot
    """
    )
    val bot: Long by value()

    @ValueDescription(
        """
        面板IP地址(带端口)
    """
    )
    val McsmIP: String by value()

    @ValueDescription(
        """
        服务器名称
    """
    )
    val ServerName: String by value()

    @ValueDescription(
        """
        Mcsm的APIkey
    """
    )
    val APIkey: String by value()

    @ValueDescription(
        """
        所有者QQ 拥有全部命令执行权限
    """
    )
    val owner: List<Long> by value()

    @ValueDescription(
        """
        管理员QQ 部分命令权限
    """
    )
    val admin: List<Long> by value()

    @ValueDescription(
        """
        自定义命令列表
    """
    )
    val cmd: Map<String, Map<String, String>> by value(
        hashMapOf<String, Map<String, String>>(
            "owner" to hashMapOf("自定义命令" to "服务器命令"),
            "admin" to hashMapOf("自定义命令" to "服务器命令"),
            "user" to hashMapOf("在线人数" to "list")
        )
    )


}