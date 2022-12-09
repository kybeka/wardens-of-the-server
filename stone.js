/**
 * collect stone (if wooden pickaxe breaks create a stone one)
 * find iron ore (6 ore)
 * @author Sapana Tamang
 */

const mineflayer = require('mineflayer');
// const inventoryViewer = require('mineflayer-web-inventory');
const vec3 = mineflayer.vec3;
const { pathfinder, Movements, goals: { GoalNear } } = require('mineflayer-pathfinder');


const portNum = "36537";

const bot = mineflayer.createBot({
    host: 'localhost',
    port: portNum,
    username: 'search_stone',
    version: "1.19.2",
    viewDistance: "tiny"
});

const collectblock = require('mineflayer-collectblock');
const { Item } = require('prismarine-item');
const { resourceUsage } = require('process');

bot.loadPlugin(collectblock.plugin);
let mcData;

/**
 * Bot picks up stone thrown by the player.
 */
async function collectStone() {
    const stone = bot.findBlock({
        matching: mcData.blocksByName.stone,
        maxDistance: 70
    });

    if (stone) {
        // if we find one, collect it
        try {
            await bot.collectBlock.collect(stone);
            collectStone();
        } catch (err) {
            console.log(err);
        }
    }
}


function digIron() {
    //minefor
    // huntfor
    // collec iron_ingot, diamond
    // source 
}

bot.once('spawn', () => {
    mcData = require('minecraft-data')(bot.version);
    collectStone();
    followPlayer();
    locateCobbleStone();
});

bot.on('chat', (username, message) => {
    if (username === bot.username) return;
    bot.chat(message);
    console.log(`${username} said ${message}`)
});

bot.on('kicked', console.log);
bot.on('error', console.log);

bot.loadPlugin(pathfinder);

/**
 * Follows the player.
 * @returns 
 */
function followPlayer() {
    const player = bot.players[''];

    if (!player || !player.entity) return;

    console.log(player);

    const mcData = require('minecraft-data')(bot.version);
    console.log("mcData: " + mcData);
    const movements = new Movements(bot, mcData);
    movements.scafoldingBlocks = [];

    console.log(movements);

    bot.pathfinder.setMovements(movements);

    // const goal = new GoalFollow(player.entity, 1);
    bot.pathfinder.setGoal(player.entity, true);
}

/**
 * Search Cobble stone
 */
function searchCobbleStone() {

    const mcData = require('minecraft-data')(bot.version);
    let cobblestone = mcData.blocksByName.cobblestone.id;
    if (bot.canSeeBlock(cobblestone)) {
        bot.canDigBlock(cobblestone);
    }

    if (cobblestone == 2) {
        bot.stopDigging();
    } else {
        searchCobbleStone();
    }


}

function sayItems(items = null) {
    if (!items) {
        items = bot.inventory.items();
        if (bot.registry.isNewerOrEqualTo('1.9') && bot.inventory.slots[45]) {
            items.push(bot.inventory.slots[45]);
        }
    }
    const output = items.map(itemToString).join(', ');
    if (output) {
        bot.chat(output);
    } else {
        bot.chat('empty');
    }
}

function locateCobbleStone() {

    const mcData = require('minecraft-data')(bot.version);
    const movements = new Movements(bot);
    // movements.scafoldingBlocks = [];
    const blocks =[];
    const block=[];
    bot.pathfinder.setMovements(movements);

    const cobblestone = bot.findBlocks({
        // Cobblestone: mineable/block stone_crafting_materials stone_tool_materials block.minecraft.cobblestone
        // bot.blockInSight(maxSteps, vectorLength);
        point: bot.entity.position,
        matching: mcData.blocksByName.cobblestone.id,
        maxDistance: 32,
        count: 11
    }).forEach((e)=> {
        console.log(e);
        blocks.push(e.x);
        blocks.push(e.y);
        blocks.push(e.z);
        block.push(e);
    })

    if (!cobblestone) {
        bot.chat("I can't see any cobblestone");
        return;
    }

    bot.pathfinder.setMovements(movements);


    // const x = cobblestone.position.x;
    // const y = cobblestone.position.y;
    // const z = cobblestone.position.z;

    const goal = new GoalNear(blocks[0], blocks[1], blocks[2], 1);     // not sure if it is GoalNear or GoalBlock
    bot.pathfinder.setGoal(goal);
    var b = new vec3(blocks[0], blocks[1], blocks[2]);
    bot.lookAt(b);

}

function locateStick() {
    const mcData = require('minecraft-data')(bot.version);

    const stick_ = bot.mcData.itemsByName(stick).id;
    console.log(stick);

    // bot.heldItem
    // bot.usingHeldItem
    const Item = require('prismarine-item')('1.8');
    const stick = new Item(320, 1);
    console.log(stick);

    const notchItem = Item.toNotch(stick);
    console.log(notchItem);
    console.log(Item.fromNotch(notchItem));
}

/** craft item */
async function craftItem(name, amount) {
    amount = parseInt(amount, 10);
    const item = bot.registry.itemsByName[name];
    const craftingTableID = bot.registry.blocksByName.crafting_table.id;

    const craftingTable = bot.findBlock({
        matching: craftingTableID
    });

    if (item) {
        const recipe = bot.recipesFor(item.id, null, 1, craftingTable)[0];
        if (recipe) {
            bot.chat(`I can make ${name}`);
            try {
                await bot.craft(recipe, amount, craftingTable);
                bot.chat(`did the recipe for ${name} ${amound} times`);
            } catch (err) {
                bot.chat(`error making ${name}`);
            }
        } else {
            bot.chat(`I cannot make ${make}`);
        }
    } else {
        bot.chat(`unknown item: ${name}`);
    }
}

function makeStonePickaxe() {

    let cobblestone = "";
    if (cobblestone == 3 && stick == 2) {
        craftItem('stone_pickaxe', 1);

    }


}

function itemToString(item) {
    if (item) {
        return `${item.name} x ${item.count}`;
    } else {
        return `(nothing)`;
    }
}
/** Returns item by name */
function itemByName(name) {
    const items = bot.inventory.items();
    if (bot.registry.isNewerOrEqualTo('1.9') && bot.inventory.slots[45]) {
        items.push(bot.inventory.slots[45])
        return items.filter(item => item.name === name)[0];

    }
}

const dig = () => {
    const mcData = require('minecraft-data')(bot.version);

    if(bot.targetDigBlock) {
        setTimeout(dig, 1);
        return
    } else {
        var target = mcData.blocksByName.cobblestone.id;
        if ((target !== undefined) && (target.position.distanceTo(bot.entity.position.offset(0,1,0)) > 4.3)) {
            target = undefined;
        } else if (target !== undefined && bot.canDigBlock(target)) {
            bot.dig(target, () => setTimeout(dig, 1))
        } else {
            setTimeout(dig, 1);
            return;
        }

    }


}