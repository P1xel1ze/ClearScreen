package au.p1xel1ze.clearscreen;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.CommandSender;

import org.jline.terminal.Terminal;
import org.jline.utils.InfoCmp;
import org.jline.terminal.TerminalBuilder;
import java.io.IOException;

public class Bungee extends Plugin {
    @Override
    public void onEnable() {
      getProxy().getPluginManager().registerCommand(this, new ClsCommand());
      this.log("§bClearScreen enabled");
      this.log("§bRun `cls` to clear the console");
   }

    @Override
    public void onDisable() {
        this.log("§bClearScreen disabled");
    }

    private void log(String message) {
        getLogger().info(message);
    }

    static class ClsCommand extends Command {
        ClsCommand() {
            super("cls");
        }
        public void execute(CommandSender sender, String[] args) {
            try {
                Terminal terminal = TerminalBuilder.builder().build();
                terminal.writer().write("\033[2J\033[H\033[3J");
                terminal.puts(InfoCmp.Capability.clear_screen);
                terminal.flush();
            } catch(IOException e) {}
        }
    }
}