package moe.dazecake

import api.McsmRequests
import net.mamoe.mirai.console.command.CompositeCommand
import net.mamoe.mirai.console.command.MemberCommandSender

object McsmBotCommand : CompositeCommand(
    McsmBot, "mcsm",
    description = "McsmBot指令"
) {
    //获取指定服务器部分信息
    @SubCommand
    fun MemberCommandSender.status() {
        McsmRequests.getStatus(group)
    }

    @SubCommand
    fun MemberCommandSender.status(serverName: String) {
        McsmRequests.getStatus(group, serverName)
    }

    //开启服务器
    @SubCommand
    fun MemberCommandSender.openServer() {
        McsmRequests.openServer(group)
    }

    @SubCommand
    fun MemberCommandSender.openServer(serverName: String) {
        McsmRequests.openServer(group,serverName)
    }

    //关闭服务器
    @SubCommand
    fun MemberCommandSender.closeServer() {
        McsmRequests.closeServer(group)
    }

    fun MemberCommandSender.closeServer(serverName: String) {
        McsmRequests.closeServer(group,serverName)
    }



    //重启服务器
    @SubCommand
    fun MemberCommandSender.restartServer() {
        McsmRequests.restartServer(group)
    }

    @SubCommand
    fun MemberCommandSender.restartServer(serverName: String) {
        McsmRequests.restartServer(group,serverName)
    }

    //执行命令
//    @SubCommand
//    fun MemberCommandSender.sh(serverName: String) {
//
//    }

}