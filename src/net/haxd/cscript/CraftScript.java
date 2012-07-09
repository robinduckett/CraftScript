package net.haxd.cscript;

import org.bukkit.plugin.java.JavaPlugin;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.EcmaError;
import org.mozilla.javascript.ScriptableObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Robin Duckett &lt;rduckett@specificmedia.com&gt;
 */

public class CraftScript extends JavaPlugin {
    public Context engine;
    public ScriptableObject scope;
    public ScriptLoader scriptLoader;

    private void printErrorMessage(Exception e) {
        getLogger().info("Error: " + e.getMessage());
    }

    public void onEnable() {
        engine = Context.enter();
        scope = engine.initStandardObjects();

        try {
            InputStream load = this.getClassLoader().getResourceAsStream("loadScripts.js");
            InputStream underscore = this.getClassLoader().getResourceAsStream("underscore.js");
            InputStream backbone = this.getClassLoader().getResourceAsStream("backbone.js");

            scope.put("console", scope, getLogger());
            scope.put("plugin", scope, this);

            engine.evaluateReader(scope, new InputStreamReader(underscore), "underscore.js", 0, null);
            engine.evaluateReader(scope, new InputStreamReader(backbone), "backbone.js", 0, null);
            engine.evaluateReader(scope, new InputStreamReader(load), "loadScripts.js", 0, null);

            scriptLoader = new ScriptLoader(this);
            scriptLoader.getDir(new File("./plugins/CraftScript/scripts"));
        } catch (IOException e) {
            printErrorMessage(e);
        } catch (EcmaError e) {
            printErrorMessage(e);
        }

        EventListener eventListener = new EventListener(this);
        getServer().getPluginManager().registerEvents(eventListener, this);

        scriptLoader.callFunction("onEnable", null);
    }

    public void onDisable() {
        scriptLoader.callFunction("onDisable", null);
        Context.exit();
    }
}
