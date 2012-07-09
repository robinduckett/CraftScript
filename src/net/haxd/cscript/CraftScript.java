package net.haxd.cscript;

import org.bukkit.plugin.java.JavaPlugin;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.EcmaError;
import org.mozilla.javascript.ScriptableObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Robin Duckett &lt;rduckett@specificmedia.com&gt;
 */

public class CraftScript extends JavaPlugin {
    public Context engine;
    public ScriptableObject scope;

    private void printErrorMessage(Exception e) {
        getLogger().info("Error: " + e.getMessage());
    }

    public void onEnable() {
        engine = Context.enter();
        scope = engine.initStandardObjects();

        try {
            InputStream load = this.getClassLoader().getResourceAsStream("loadScripts.js");
            InputStream underscore = this.getClassLoader().getResourceAsStream("loadScripts.js");
            InputStream backbone = this.getClassLoader().getResourceAsStream("loadScripts.js");

            scope.put("console", scope, getLogger());

            engine.evaluateReader(scope, new InputStreamReader(underscore), "underscore.js", 0, null);
            engine.evaluateReader(scope, new InputStreamReader(backbone), "backbone.js", 0, null);
            engine.evaluateReader(scope, new InputStreamReader(load), "loadScripts.js", 0, null);
        } catch (IOException e) {
            printErrorMessage(e);
        } catch (EcmaError e) {
            printErrorMessage(e);
        }

        EventListener eventListener = new EventListener(this);
        getServer().getPluginManager().registerEvents(eventListener, this);

        eventListener.callFunction("onEnable", null);
    }

    public void onDisable() {
        engine.evaluateString(scope, "if (typeof onDisable !== \"undefined\") onDisable();", "<cmd>", 0, null);
        Context.exit();
        getLogger().info("CScript Disabled");
    }
}
