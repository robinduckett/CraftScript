package net.haxd.cscript;

import org.mozilla.javascript.EcmaError;
import org.mozilla.javascript.Function;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Robin Duckett &lt;rduckett@specificmedia.com&gt;
 */
public class ScriptLoader {
    private CraftScript plugin;

    public ScriptLoader(CraftScript plugin) {
        this.plugin = plugin;
    }

    public void getDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();

            for (String child : children) {
                getDir(new File(dir, child));
            }
        } else {
            try {
                plugin.engine.evaluateReader(plugin.scope, new FileReader(dir), dir.toString(), 0, null);
            } catch (IOException e) {
                plugin.getLogger().info("Unable to load script: " + dir.toString());
            }

        }
    }

    public void callFunction(String function, Object args[]) {
        Object lambda = plugin.scope.get(function, plugin.scope);
        Function jsfunction = (Function) lambda;
        try {
        jsfunction.call(plugin.engine, plugin.scope, plugin.scope, args);
        } catch (EcmaError e) {
            plugin.getServer().broadcastMessage("Error: " + e.getMessage());
        }
    }
}
