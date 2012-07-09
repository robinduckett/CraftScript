function onEnable() {
    console.info("Hello World!");
}

function onDisable() {
    var rnd = Math.random() * 10;
    if (rnd < 5.5) {
        console.info("Where did you go?");
    } else {
        console.info("I don't hate you.");
    }
}

function onPlayerLogin(event, player) {
    console.info("Player: " + player.getName() + "!!!!");
    player.sendMessage("Welcome aboard " + player.getName() + "!");
}

function onPlayerCommand(event, player) {
    console.info("Player: " + player.getName());
    player.sendMessage("Hello " + player.getName());
}