package au.p1xel1ze.clearscreen;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import net.minecrell.terminalconsole.TerminalConsoleAppender;
import org.jline.terminal.Terminal;
import org.jline.utils.InfoCmp;

public class BukkitSpigot extends JavaPlugin {
   public void onEnable() {
      getCommand("cls").setExecutor(this);
      this.log("ClearScreen enabled");
      this.log("Run `cls` to clear the console");
   }

   public void onDisable() {
      this.log("ClearScreen disabled");
   }

   private void log(String message) {
      Bukkit.getServer().getConsoleSender().sendMessage("[ClearScreen] §b" + message);
   }

   public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
      if (commandLabel.equalsIgnoreCase("cls")) {
         if (sender instanceof ConsoleCommandSender) {
            Terminal terminal = TerminalConsoleAppender.getTerminal();
            terminal.writer().write("\033[2J\033[H\033[3J");
            terminal.puts(InfoCmp.Capability.clear_screen);
            terminal.writer().write("\n> ");
            terminal.flush();
            return true;
         } else {
            sender.sendMessage("§4You can only execute this command from the console");
            return true;
         }
      }
      return false;
   }
}
