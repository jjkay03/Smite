package com.jjkay03.smite

import com.jjkay03.smite.commands.SmiteCommand
import org.bukkit.Bukkit
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.plugin.java.JavaPlugin

class Smite : JavaPlugin() {
    companion object {
        lateinit var instance: Smite
    }

    // Plugin startup logic
    override fun onEnable() {
        instance = this
        val console: ConsoleCommandSender = Bukkit.getConsoleSender()

        // Plugin welcome message
        console.sendMessage("")
        console.sendMessage("§b    _____ __  __ _____ _______ ______  ")
        console.sendMessage("§b  / ____|  \\/  |_   _|__   __|  ____| ")
        console.sendMessage("§b | (___ | \\  / | | |    | |  | |__    ")
        console.sendMessage("§b  \\___ \\| |\\/| | | |    | |  |  __| ")
        console.sendMessage("§b  ____) | |  | |_| |_   | |  | |____  ")
        console.sendMessage("§b |_____/|_|  |_|_____|  |_|  |______| ")
        console.sendMessage("")

        // Display plugin version
        logger.info("Plugin version: ${description.version}")

        // Get Commands
        getCommand("smite")?.setExecutor(SmiteCommand())
        getCommand("smite")?.tabCompleter = SmiteCommand()

    }

    // Plugin shutdown logic
    override fun onDisable() {
    }
}
