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

        Object scopeGet = plugin.scope.get("on" + sevent, plugin.scope);
        Function func = (Function) scopeGet;
        func.call(plugin.engine, plugin.scope, plugin.scope, args);
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        this.processEvent("PlayerLogin", event);
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        this.processEvent("PlayerCommand", event);
    }
}
