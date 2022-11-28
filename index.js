const mineflayer = require('mineflayer');

const bot = mineflayer.createBot({
    host: 'localhost',
    port: 43549, //DON'T FORGET TO CHANGE THE PORT
    username: 'looking_bot'
});

function lookAtNearestPlayer () {
    const playerFilter = (entity) => entity.type === 'player'
    const playerEntity = bot.nearestEntity(playerFilter);

    if (!playerEntity) return

    const pos = playerEntity.position.offset(0, playerEntity.height, 0);
    bot.lookAt(pos);
}

bot.on('physicTick', lookAtNearestPlayer);

//____________________________________________________//

const { pathfinder } = require('mineflayer-pathfinder');


const bot2 = mineflayer.createBot({
    host: 'localhost',
    port: 43549,
    username: 'pathfinding_bot'
});


