package com.jjkay03.smite.commands

import org.bukkit.Bukkit
import org.bukkit.Server
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

class SmiteCommand : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("§cOnly players can use this command!")
            return true
        }

        val player = sender

        if (args.size == 0) {
            player.world.strikeLightning(player.getTargetBlock(null, 600).location)
            return true
        }

        var power = 5
        if (args.size > 1) {
            try {
                power = args[1].toInt()
            } catch (ignored: NumberFormatException) {
            }
        }
        val finalPower = power

        val onlinePlayers = getOnlinePlayers(Bukkit.getServer())

        if (args[0].equals("@a", ignoreCase = true)) {
            for (targetPlayer in onlinePlayers) {
                sender.sendMessage("§b⚡ Smiting §r${targetPlayer.name}")
                val strike = targetPlayer.world.strikeLightningEffect(targetPlayer.location)
                if (!targetPlayer.isInvulnerable) {
                    targetPlayer.damage(finalPower.toDouble())
                }
            }
        } else {
            val targetPlayer = Bukkit.getPlayer(args[0])
            if (targetPlayer != null) {
                sender.sendMessage("§b⚡ Smiting §r${targetPlayer.name}")
                val strike = targetPlayer.world.strikeLightningEffect(targetPlayer.location)
                if (!targetPlayer.isInvulnerable) {
                    targetPlayer.damage(finalPower.toDouble())
                }
            }
        }

        return true
    }

    private fun getOnlinePlayers(server: Server): List<Player> {
        return ArrayList(server.onlinePlayers)
    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<String>): List<String> {
        return when (args.size) {
            1 -> {
                val playerNames = getOnlinePlayers(Bukkit.getServer()).map { it.name }
                playerNames + "@a"
            }
            2 -> listOf("5")
            else -> emptyList()
        }
    }
}
