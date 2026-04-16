package au.p1xel1ze.clearscreen;

import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.command.SimpleCommand;
import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import org.slf4j.Logger;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.ConsoleCommandSource;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

@Plugin(name = "ClearScreen", id = "clearscreen", description = "Clears the server console", version = "1.0.1", authors = {"P1xel1ze"})
public class Velocity {
    private final ProxyServer server;
    private final Logger logger;
    @Inject
    public Velocity(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;
    }
    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        server.getCommandManager().register(
            server.getCommandManager().metaBuilder("cls").plugin(this).build(),
            new ClsCommand(logger)
        );
    }
    // The command class itself
    static public class ClsCommand implements SimpleCommand {
        private final Logger logger;
            public ClsCommand(Logger logger) {
            this.logger = logger;
        }
        @Override
        public void execute(Invocation invocation) {
            CommandSource source = invocation.source();
            if (source instanceof ConsoleCommandSource) {
                logger.info("\033[2J\033[H\033[3J");
            } else {
                source.sendMessage(Component.text("You can only execute this command from the console", NamedTextColor.RED));
            }
        }
    }
}
