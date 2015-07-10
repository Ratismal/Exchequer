package io.github.ratismal.exchequer.util;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageUtil {

    public static void sendMessageToAllPlayers(String message) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(getMessage(message));
        }
    }

    public static void sendMessage(CommandSender sender, String message) {
        if (null != sender) {
            sender.sendMessage(getMessage(message));
        }
    }

    public static void sendConsoleMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(getMessage(message));
    }

    public static String getMessage(String message){
        return new StringBuilder().append("\2476[\2474Exchequer\2476]\2473 ").append(message).toString();
    }
}
