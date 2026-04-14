package au.p1xel1ze;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import net.minecrell.terminalconsole.TerminalConsoleAppender;
import org.jline.terminal.Terminal;
import org.jline.utils.InfoCmp;

public class Cls extends JavaPlugin {
   public void onEnable() {
      getCommand("cls").setExecutor(this);
      this.log("[ClearScreen] §bClearScreen enabled");
   }

   public void onDisable() {
      this.log("[ClearScreen] §bClearScreen disabled");
   }

   private void log(String message) {
      Bukkit.getServer().getConsoleSender().sendMessage(message);
   }

   public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
      if (commandLabel.equalsIgnoreCase("cls")) {
         if (sender instanceof ConsoleCommandSender) {
            Terminal terminal = TerminalConsoleAppender.getTerminal();
            terminal.writer().write("\033[2J\033[H\033[3J");
            terminal.puts(InfoCmp.Capability.clear_screen);
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
