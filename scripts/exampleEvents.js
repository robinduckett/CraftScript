Minecraft.on("playerLogin", function(playerLoginEvent, player) {
    player.getServer().broadcastMessage("Well hellooo " + player.getName());
});

Minecraft.on("playerCommand", function(playerCommandEvent, player) {
    player.getServer().broadcastMessage(player.getName() + " tried " + playerCommandEvent.getMessage());
    //playerCommandEvent.setCancelled(true);
});