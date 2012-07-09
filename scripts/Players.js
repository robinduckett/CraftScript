var Player = Backbone.Model.extend({
    initialize: function() {
        this.on("change:player", this.changePlayer, this);
    },

    changePlayer: function() {
        this.set('name', this.get('player').getName());
    }
});

var PlayerCollection = Backbone.Collection.extend({
   model: Player
})

var Players = Backbone.Model.extend({
    initialize: function() {
        this.set('players', new PlayerCollection());

        this.get('players').on('add', function() {
            plugin.getServer().broadcastMessage('Player added');
        });

        Minecraft.on("player:join", this.login, this);
        Minecraft.on("player:quit", this.quit, this);
        Minecraft.on("player:command", this.cmd, this);
        Minecraft.on("player:respawn", this.respawn, this);
        Minecraft.on("plugin:disable", this.disable, this);
    },

    join: function(event, player) {
        var players = this.get('players');
        var player = new Player();

        player.set('player', player);
        players.add(player);
    },

    quit: function(event, player) {
        var players = this.get('players');

        var player = players.find(function(p) {
            return p.get('name') === player.getName();
        });

        players.remove(player);
    },

    cmd: function(event, player) {
        var players = this.get('players');

        if (event.getMessage() == "/plist") {
            player.sendMessage(JSON.stringify(players));

            event.setCancelled(true);
        }
    },

    respawn: function(event, player) {
        player.getServer().broadcastMessage("Dun dun dun. Another one bites the dust, ah.");
    },

    disable: function(event, player) {
        Minecraft.off("player:login", this.login, this);
        Minecraft.off("player:command", this.cmd, this);
        Minecraft.off("player:respawn", this.respawn, this);
        Minecraft.off("player:disable", this.disable, this);
    }
});

var players = new Players();