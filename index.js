const mineflayer = require('mineflayer');
const portNum = 33007;  //DON'T FORGET TO CHANGE THE PORT NUMBER

const bot = mineflayer.createBot({
    host: 'localhost',
    port: portNum,
    username: 'looking_bot'
});

function lookAtNearestPlayer () {
    const playerFilter = (entity) => entity.type === 'player'
    const playerEntity = bot.nearestEntity(playerFilter);
    if (!playerEntity) return;
    const pos = playerEntity.position.offset(0, playerEntity.height, 0);
    bot.lookAt(pos);
}

bot.on('physicTick', lookAtNearestPlayer);

// async function digResource() {
//     let blockPosition = bot.entity.position.offset(0,-1,0);
//     let block = bot.blockAt(blockPosition);
//     await bot.dig(block);
//     bot.chat("Resource dug!");
// }

// bot.on("chat", (username,text) => {
//     if (username === bot.username) return;
//     if (text === "dig") digResource();
//     // else if (text === "rail") placeRail();
//     else bot.chat("I don't understand!");
// });

//____________________________________________________//

// const { pathfinder } = require('mineflayer-pathfinder');


// const bot2 = mineflayer.createBot({
//     host: 'localhost',
//     port: portNum,
//     username: 'pathfinding_bot'
// });


