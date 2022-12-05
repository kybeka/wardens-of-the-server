const mineflayer = require('mineflayer');
const portNum = 37571;  //DON'T FORGET TO CHANGE THE PORT NUMBER

const bot = mineflayer.createBot({
    host: 'localhost',
    port: portNum,
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

// const { pathfinder } = require('mineflayer-pathfinder');


// const bot2 = mineflayer.createBot({
//     host: 'localhost',
//     port: portNum,
//     username: 'pathfinding_bot'
// });


