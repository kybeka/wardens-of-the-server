/**
 * @file Basic bot functionality. Bot can mine resources,
 * craft items and use furnaces.
 * @author Alessandro Zanzi <zanzia@usi.ch>
 * 
 */

const mineflayer = require('mineflayer');
const toolPlugin = require('mineflayer-tool').plugin;
const { pathfinder, Movements, goals } = require('mineflayer-pathfinder');
const { GoalGetToBlock, GoalNear, GoalLookAtBlock } = goals;

const vec3 = require('vec3');

const portNum = 39899;  //DON'T FORGET TO CHANGE THE PORT NUMBER

const bot = mineflayer.createBot({
  host: 'localhost',
  port: portNum,
  username: 'tree_bot'
});

let mcData;
bot.loadPlugin(pathfinder);
bot.loadPlugin(toolPlugin);

/* Deprecated in favor of mineNode */
/* const collectBlocks = async (bot, name, count = 1) => {
  const b = mcData.blocksByName[name];
  if (!b) return;

  const blocks = bot.findBlocks({
    matching: b.id,
    maxDistance: 64,
    count
  });

  if (blocks.length === 0) {
    console.log('no blocks found')
    return
  }

  const targets = [];
  for (let i = 0; i < blocks.length; i++) {
    targets.push(bot.blockAt(blocks[i]));
  }

  try {
    await bot.collectBlock.collect(targets);
    // All blocks have been collected.
    console.log(`Collected ${count} ${name}`);
  } catch (err) {
    console.log(err);
  }
} */

/* const canCraft = (bot, r) => {
  for (let i = 0; i < r.delta.length - 1; ++i) {
    const item = bot.inventory.findInventoryItem(r.delta[i].id, null);
    if (!item) return false;
  }
  return true;
} */

/**
 * Makes bot craft items.
 * 
 * @param {Bot}    bot      the bot entity that will execute the action.
 * @param {String} itemName the name identifier of the item to craft.
 * @param {Number} count    the quantity of items to craft; defaults to 1.
 * @param {Block}  bench    the crafting table to use, if recipe requires one.
 */
const craftItem = async (bot, itemName, count = 1, bench = null) => {
  const item = mcData.itemsByName[itemName];
  if (!item) throw new Error('Unknown item type');

  const recipe = bot.recipesFor(item.id, null, count, bench)[0];
  if (!recipe) return;
  console.log("crafting " + itemName);

  try {
    await bot.craft(recipe, count, bench);
    console.log("crafting")
    const craftedItem = bot.inventory.findInventoryItem(item.id, null);
    await bot.waitForTicks(10);
    await bot.equip(craftedItem);
    console.log(`Crafted ${count} ${itemName}`);
  } catch (err) {
    console.log('Error crafting ' + itemName);
    console.log(err)
  }
}

/**
 * Makes the bot go near a crafting table if it exists within 64 blocks.
 * If a crafting table does not exist and it is not in the bot inventory,
 * then the bot crafts one and places it nearby.
 * 
 * @param  {Bot}   bot the bot entity that will execute the action.
 * @return {Block} the block representing the crafting table.
 */
const getCraftingTable = async bot => {
  let bench = bot.findBlock({
    matching: 167,
    maxDistance: 64
  });

  if (!bench) {
    /* No nearby bench. Check if already in inventory */
    bench = bot.inventory.findInventoryItem(257, null);
    if (!bench) {
      /* Not in inventory, craft bench */
      console.log('bench not found, crafting one!');
      await craftItem(bot, 'crafting_table');
      await bot.waitForTicks(10);
    }

    /* Randomly chose a nearby spot to place the bench */
    const bPos = bot.findBlocks({
      matching: 8,
      maxDistance: 15,
      count: 10,
      useExtraInfo: block => {
        /* On top of the block there must be nothing */
        let { position } = block;
        let check = bot.blockAt(vec3(position.x, position.y + 1, position.z));
        if (check.name == 'air') return true;
      }
    })[Math.floor(Math.random() * 10) + 1];
    const baseBlock = bot.blockAt(bPos);

    /* Place crafting table */
    bench = bot.inventory.findInventoryItem(257, null);
    await bot.equip(bench);
    await bot.placeBlock(baseBlock, vec3(0, 1, 0));
    console.log('bench placed!');

    const newBenchPos = vec3(baseBlock.position.x, baseBlock.position.y + 1, baseBlock.position.z);
    return bot.blockAt(newBenchPos);
  }

  /* Nearby bench found. Go to it */
  console.log('going to bench ' + bench.position);
  const { position } = bench;
  const goal = new GoalGetToBlock(position.x, position.y, position.z);
  await bot.pathfinder.goto(goal);

  return bench;
}

/**
 * Makes the bot go near a furnace if it exists within 64 blocks.
 * If a furnace does not exist and it is not in the bot inventory,
 * then the bot crafts one and places it nearby.
 * 
 * @param  {Bot}   bot the bot entity that will execute the action.
 * @return {Block} the block representing the furnace.
 */
const getFurnace = async bot => {
  let furnace = bot.findBlock({
    matching: 170,
    maxDistance: 64
  });

  if (!furnace) {
    /* No nearby bench. Check if already in inventory */
    furnace = bot.inventory.findInventoryItem(259, null);
    if (!furnace) {
      /* Not in inventory, craft furnace */
      console.log('furnace not found, crafting one!');
      let bench = await getCraftingTable(bot);
      await craftItem(bot, 'furnace', 1, bench);
      await bot.waitForTicks(10);
    }

    /* Randomly chose a nearby spot to place the furnace */
    const bPos = bot.findBlocks({
      matching: 8,
      maxDistance: 15,
      count: 10,
      useExtraInfo: block => {
        /* On top of the block there must be nothing */
        let { position } = block;
        let check = bot.blockAt(vec3(position.x, position.y + 1, position.z));
        if (check.name == 'air') return true;
      }
    })[Math.floor(Math.random() * 10) + 1];
    const baseBlock = bot.blockAt(bPos);

    /* Place furnace */
    furnace = bot.inventory.findInventoryItem(259, null);
    await bot.equip(furnace);
    await bot.placeBlock(baseBlock, vec3(0, 1, 0));
    console.log('furnace placed!');

    const newPos = vec3(baseBlock.position.x, baseBlock.position.y + 1, baseBlock.position.z);
    return bot.blockAt(newPos);
  }

  /* Nearby furnace found. Go to it */
  console.log('going to furnace' + furnace.position);
  const { position } = furnace;
  const goal = new GoalGetToBlock(position.x, position.y, position.z);
  await bot.pathfinder.goto(goal);

  return furnace;
}

/**
 * Makes the bot mine resources.
 * 
 * @param {Bot}    bot       the bot entity that will execute the action.
 * @param {String} blockName the name identifier of the block to mine.
 * @param {Number} maxCount  the number of blocks to mine; defaults to 1.
 */
const mineNode = async (bot, blockName, maxCount = 1) => {
  console.log('mining ' + blockName)
  const b = mcData.blocksByName[blockName];
  if (!b) throw new Error('Unknown block type');

  let minedCount = 0;
  while (minedCount < maxCount) {
    /* Prefer resources that are in sight of the bot */
    let block = bot.findBlock({
      matching: b.id,
      maxDistance: 64,
      useExtraInfo: block => {
        if (bot.canSeeBlock(block)) return true;
      }
    });

    /* If bot can't see resource, find nearest one */
    if (!block) {
      block = bot.findBlock({
        matching: b.id,
        maxDistance: 64
      });
    }

    /* No resource available, stop */
    if (!block) return;

    /* Go near block to mine */
    let { position } = block;
    const goal = new GoalGetToBlock(position.x, position.y, position.z);
    await bot.pathfinder.goto(goal);

    if (bot.canDigBlock(block)) {
      const cb = e => {
        if (e.position.distanceTo(block.position.offset(0.5, 0.5, 0.5)) <= 0.5) {
          let { position } = e;
          const goal = new GoalNear(position.x, position.y, position.z, 0)
          bot.pathfinder.setGoal(goal);
        }
      };

      bot.on('itemDrop', cb);
      try {
        /* Mine resource */
        await bot.tool.equipForBlock(block);
        await bot.dig(block);
        /* Wait for items to drop */
        await bot.waitForTicks(10);
        ++minedCount;
      } finally {
        bot.removeListener('itemDrop', cb);
      }
    }
  }
  console.log(`finished mining ${minedCount} ${blockName} nodes!`);
}

bot.once('spawn', async () => {
  mcData = require('minecraft-data')(bot.version);
  console.log('spawned');
  const movements = new Movements(bot);
  bot.pathfinder.setMovements(movements);

  /* 1. Collect wood */
  await mineNode(bot, 'oak_log', 4);

  /* 2. Make wooden pickaxe */
  await craftItem(bot, 'oak_planks', 4);
  await craftItem(bot, 'stick', 1);
  let bench = await getCraftingTable(bot);
  await craftItem(bot, 'wooden_pickaxe', 1, bench);

  /* 3. Upgrade to stone pickaxe */
  await mineNode(bot, 'stone', 3);
  bench = await getCraftingTable(bot);
  await craftItem(bot, 'stone_pickaxe', 1, bench);

  /* 4. Craft furnace */
  await mineNode(bot, 'stone', 9);
  await mineNode(bot, 'coal_ore', 4);
  await mineNode(bot, 'iron_ore', 4);
  const furnaceBlock = await getFurnace(bot);

  /* 5. Smelt iron ore */
  const furnace = await bot.openFurnace(furnaceBlock);
  await furnace.putFuel(720, null, 4);
  await furnace.putInput(727, null, 4);
});
