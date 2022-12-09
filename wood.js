const mineflayer = require('mineflayer');
//const { Block } = require('prismarine-block');
const pathfinder = require('mineflayer-pathfinder').pathfinder
const Movements = require('mineflayer-pathfinder').Movements
const { GoalNear } = require('mineflayer-pathfinder').goals

const vec3 = require('vec3');


const portNum = 46041;  //DON'T FORGET TO CHANGE THE PORT NUMBER

const bot = mineflayer.createBot({
    host: 'localhost',
    port: portNum,
    username: 'tree_bot'
});

bot.loadPlugin(pathfinder)

   
bot.once('spawn', function() {
    console.log('spawned');
    const defaultMove = new Movements(bot);
    //find the nearest 4 logs
    let blocks = [];
    let block = [];
    console.log('bot position: ' + bot.entity.position);
    bot.findBlocks({
        point: bot.entity.position,
        matching: require('minecraft-data')(bot.version).blocksByName.oak_log.id,
        maxDistance: 400,
        count: 4
    }).forEach((e) => {console.log(e); blocks.push(e.x); blocks.push(e.y); blocks.push(e.z); block.push(e)});


    console.log('loading the array: ' + blocks);

    //move to the first block of wood that we found
    bot.pathfinder.setMovements(defaultMove);
    let goal = new GoalNear(blocks[0], blocks[1], blocks[2], 1);
    bot.pathfinder.setGoal(goal);
    var b = new vec3(blocks[0], blocks[1], blocks[2]);
    bot.lookAt(b);
    
    //once it reached the logs it starts to break them
    bot.on('goal_reached', () => {
        async function dig(posx, posy, posz, i) {
            console.log('breaking log at position ' + posx + ', ' + posy + ', ' + posz);
            let target;
            if (blocks.length != 0) {
                //if the bot is not already digging it starts digging the next block
                if (!bot.targetDigBlock) {
                    var pos = new vec3(posx, posy, posz);
                    let block_at = bot.blockAt(pos);
                    if (bot.entity.position.y > posy) { //if the player is above the log 
                        target = bot.blockAt(block_at.position.offset(0, -1, 0));
                    } else {
                        target = bot.blockAt(block_at.position.offset(0, 0, 0));
                    }

                    if (target && bot.canDigBlock(target)) {
                        try {
                            await bot.dig(target);
                            return;
                        } catch (err) {
                            console.log('error while trying to break a log: ' + err);
                        }
                    } else {
                        console.log('cannot dig')
                    }
                } 
            }
        }
        let i = 0;
        if (blocks.length == 12) {
            while (i < blocks.length) {
                let j = i + 1;
                let k = i + 2;
                dig(blocks[i], blocks[j], blocks[k], i);
                i = i + 3;
            } 
        }
        //bot.stopDigging();
        console.log('bot has finished digging! it now has at least 4 wood logs');
    })

    //after the goal was reached and after all the l
    // bot.on('diggingCompleted', () => {
    //     bot.quit();
    // })
    


    // async function craft (bot) {
    //     const mcData = require('minecraft-data')(bot.version)
    //     const plankRecipe = bot.recipesFor(mcData.itemsByName.oak_planks.id ?? mcData.itemsByName.planks.id)[0]
    //     await bot.craft(plankRecipe, 1, null)
    //     const stickRecipe = bot.recipesFor(mcData.itemsByName.sticks.id)[0]
    //     await bot.craft(stickRecipe, 1, null)
    //     //bot.chat('Crafting Sticks finished')
    //     console.log('successfully created a crafting table');
    // }
    // craft(bot);
    bot.inventory

})



function lookAtNearestPlayer () {
    const playerFilter = (entity) => entity.type === 'player'
    const playerEntity = bot.nearestEntity(playerFilter);
    if (!playerEntity) return;
    const pos = playerEntity.position.offset(0, playerEntity.height, 0);
    bot.lookAt(pos);
}

// bot.on('physicTick', lookAtNearestPlayer);

bot.on('playerCollect', (collector, collected) => {
    if (collector.type === 'player') {
      const item = collected.getDroppedItem()
      bot.chat(`${collector.username !== bot.username ? ("I'm so jealous. " + collector.username) : 'I '} collected ${item.count} ${item.displayName}`)
    }
});

