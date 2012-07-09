package net.haxd.cscript;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;

/**
 * @author Robin Duckett &lt;rduckett@specificmedia.com&gt;
 */
public class EventListener implements Listener {
    CraftScript plugin;

    public EventListener(CraftScript plugin) {
        this.plugin = plugin;
    }

    private void processEvent(String sevent, PlayerEvent event) {
        Object jsEvent = Context.javaToJS(event, plugin.scope);
        Object player = Context.javaToJS(event.getPlayer(), plugin.scope);
        Object args[] = { jsEvent, player };

        callFunction("on" + sevent, args);
    }

    public void callFunction(String function, Object args[]) {
        Object lambda = plugin.scope.get(function, plugin.scope);
        Function jsfunction = (Function) lambda;
        jsfunction.call(plugin.engine, plugin.scope, plugin.scope, args);
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onPlayerLogin(PlayerLoginEvent event) {
        this.processEvent("PlayerLogin", event);
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        this.processEvent("PlayerCommand", event);
    }
}
