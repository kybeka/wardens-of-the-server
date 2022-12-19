/**
 * Testing stone bot implementation archived
 * collect stone (if wooden pickaxe breaks create a stone one)
 * find iron ore (6 ore)
 * @author Sapana Tamang
 */

const mineflayer = require('mineflayer');
const vec3 = require('vec3');
const { pathfinder, Movements, goals: { GoalNear } } = require('mineflayer-pathfinder');


const portNum = "39209";

const bot = mineflayer.createBot({
    host: 'localhost',
    port: portNum,
    username: 'search_stone',
    version: "1.19.2",
    viewDistance: "tiny"
});

const collectblock = require('mineflayer-collectblock');

bot.loadPlugin(collectblock.plugin);

bot.loadPlugin(pathfinder);
bot.once('spawn', () => {
    const defaultMove = new Movements(bot);
    let blocks = [];
    let block = [];
    const mcData = require('minecraft-data')(bot.version);
    bot.findBlocks({
        point: bot.entity.position,
        matching: mcData.blocksByName.cobblestone.id,
        maxDistance: 400,
        count: 11        
    }).forEach((e)=> {
        console.log(e);
        blocks.push(e.x);
        blocks.push(e.y);
        blocks.push(e.z);
        block.push(e);
    });

    bot.pathfinder.setMovements(defaultMove);
    let goal = new GoalNear(blocks[0], blocks[1], blocks[2], 1);
    bot.pathfinder.setGoal(goal);
    var b = new vec3(blocks[0], blocks[1], blocks[2]);
    bot.lookAt(b);


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
        // 11 *3
        if (blocks.length == 33) {
            while (i < blocks.length) {
                let j = i + 1;
                let k = i + 2;
                dig(blocks[i], blocks[j], blocks[k], i);
                i = i + 3;
            } 
        }
    })

});

