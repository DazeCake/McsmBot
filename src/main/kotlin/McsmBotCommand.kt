package moe.dazecake

import api.McsmRequests
import net.mamoe.mirai.console.command.CompositeCommand
import net.mamoe.mirai.console.command.MemberCommandSender

object McsmBotCommand : CompositeCommand(
    McsmBot,"mcsm",
    description = "McsmBot指令"
) {
    @SubCommand
    fun MemberCommandSender.status() {
        McsmRequests.getStatus(group)
    }

    @SubCommand
    fun MemberCommandSender.status(serverName: String) {
        McsmRequests.getStatus(group,serverName)
    }
}