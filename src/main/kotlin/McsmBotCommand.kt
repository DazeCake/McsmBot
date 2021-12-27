package moe.dazecake

import moe.dazecake.API.McsmRequests
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CompositeCommand

object McsmBotCommand : CompositeCommand(
    McsmBot,"mcsm",
    description = "McsmBot指令"
) {
    @SubCommand
    suspend fun CommandSender.status(){
        McsmRequests.getStatus()
        sendMessage("查看本服务器状态")
    }

    @SubCommand
    suspend fun CommandSender.status(serverName: String) {
        sendMessage("查看${serverName}服务器状态")
    }
}