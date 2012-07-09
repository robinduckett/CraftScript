CraftScript
===========

Bukkit Server Mod for Javascript Integration via Rhino

## Installation
Download Rhino and copy js.jar in `plugins/CraftScript/lib`

Put your scripts in `plugins/CraftScript/scripts` and they will autoload

## API

The JS API is built on top of Backbone and works through Backbone events. Backbone is included in the scope and you
should be able to use it for your own scripts.

### Minecraft object

The `Minecraft` object is a prototype of Backbone.Events and currently the following events are supported:

* `plugin:enable` - triggered when the plugin is enabled
* `plugin:disable` - triggered when the plugin is disabled
* `player:login` - triggered when the player has logged in
* `player:command` - triggered when the player sends a command
* `player:join` - triggered when the player joins the world
* `player:quit` - triggered when the player quits the world
* `player:respawn` - triggered when the player respawns

### Backbone

There is limited Backbone integration and the first script loaded is the Players.js script which maps the player events
to a Backbone model called Players.

### Credits

* Robin Duckett [<rduckett@specificmedia.com>](mailto:rduckett@specificmedia.com)
