Minecraft.on("player:login", function(playerLoginEvent, player) {
    player.getServer().broadcastMessage("Well hellooo " + player.getName());
});

Minecraft.on("player:command", function(playerCommandEvent, player) {
    player.getServer().broadcastMessage(player.getName() + " tried " + playerCommandEvent.getMessage());
    //playerCommandEvent.setCancelled(true);
});

Minecraft.on("player:respawn", function(event, player) {
    player.getServer().broadcastMessage(player.getName() + " just bit the dust ");
});