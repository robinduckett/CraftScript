function MinecraftEvent() {

}
MinecraftEvent.prototype = Backbone.Events;
var Minecraft = new MinecraftEvent();

function onEnable() {
    Minecraft.trigger("plugin:enable");
}

function onDisable() {
    Minecraft.trigger("plugin:disable");
}

function onPlayerLogin(event, player) {
    Minecraft.trigger("player:login", event, player);
}

function onPlayerCommand(event, player) {
    Minecraft.trigger("player:command", event, player);
}

function onPlayerJoin(event, player) {
    Minecraft.trigger("player:join", event, player);
}

function onPlayerQuit(event, player) {
    Minecraft.trigger("player:quit", event, player);
}

function onPlayerRespawn(event, player) {
    Minecraft.trigger("player:respawn", event, player);
}