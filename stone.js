/**
 * collect stone (if pickaxe wooden breaks create a stone one)
 * find iron ore (6 pieces)
 * goes to the surface
 * crafting grid needed
 * 
 * to make wooden pickaxe: 3 wood planks and 2 sticks
 * iron ore: stone pickaxe or better is required to mine
 * stone pickaxe: 2 sticks, 3 blackstone, 3 cobblestones
 */

// wooden pickaxe id: wooden_pickaxe item.minecraft.wooden_pickaxe
// iron pickaxe id: stone_pickaxe item.minecraft.iron_pickaxe

const mineflayer = require('mineflayer');

const portNum = "40853";

const bot = mineflayer.createBot({
    host: 'localhost',
    port: portNum,
    username: 'search_stone'
});
//
function lookAtNearestPlayer () {
    const playerFilter = (entity) => entity.type === 'player'
    const playerEntity = bot.nearestEntity(playerFilter);
    if (!playerEntity) return;
    const pos = playerEntity.position.offset(0, playerEntity.height, 0);
    bot.lookAt(pos);
}

//
bot.on('physicTick', lookAtNearestPlayer);

//
bot.on('playerCollect', (collector, collected) => {
    if (collector.type === 'player') {
      const item = collected.getDroppedItem()
      bot.chat(`${collector.username !== bot.username ? ("I'm so jealous. " + collector.username) : 'I '} collected ${item.count} ${item.displayName}`)
    }
});

let mcData;

// load collect block
bot.loadPlugin(require('mineflayer-collectblock').plugin);

async function collectStone() {
    const stone = bot.findBlock({
        matching: mcData.blocksByName.stone,
        maxDistance: 70
    });

    if(stone) {
        // if we find one, collect it.
        try {
            await bot.collectBlock.collect(stone)
            collectStone();
        } catch (err) {
            console.log(err);
        }
    }
}

bot.once('spawn', () => {
    mcData = require('minecraft-data')(bot.version)
    collectStone();
});