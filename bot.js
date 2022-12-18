/**
 * @file Basic bot functionality. Bot can mine resources,
 * craft items and use furnaces.
 * @author Alessandro Zanzi <zanzia@usi.ch>
 * 
 */

const mineflayer = require('mineflayer');
const toolPlugin = require('mineflayer-tool').plugin;
const { pathfinder, Movements, goals } = require('mineflayer-pathfinder');
const { GoalGetToBlock, GoalNear } = goals;
const rcon = require('./rcon');
const vec3 = require('vec3');
const db = require('./db');
require('dotenv').config();

const mcData = require('minecraft-data')('1.19.2');

let matches = new Map();

class MCBot {
  constructor(name, player, map) {
    this.name = name;
    this.player = player;
    this.map = map;
    this.startTime;
    this.endTime;

    this.initBot();
  }

  /**
   * Creates bot instance and loads plugins.
   */
  initBot() {
    this.bot = mineflayer.createBot({
      host: process.env.SRV_HOST || 'localhost',
      port: process.env.SRV_PORT || portNum,
      username: this.name
    });

    this.bot.loadPlugin(pathfinder);
    this.bot.loadPlugin(toolPlugin);

    this.initEvents();
  }

  /**
   * Initialize all event listeners.
   */
  initEvents() {
    this.bot.on('login', () => {
      let socket = this.bot._client.socket;
      console.log(`[${this.bot.username}] connected on ${socket.server ? socket.server : socket._host}.`);
    });

    this.bot.on('end', () => {
      console.log(`[${this.bot.username}] disconnected.`);
    })

    this.bot.once('spawn', async () => {
      console.log(`[${this.bot.username}] spawned.`);
      this.bot.pathfinder.setMovements(new Movements(this.bot));
      this.bot.pathfinder.canDig = false;
      this.bot.pathfinder.placeCost = 5;
      this.startTime = this.bot.time.age;
    });
  }

  /**
   * Add rail placed listener.
   */
  addGameOverListener(missingBlock) {
    this.bot.world.on('blockUpdate', (oldBlock, newBlock) => {
      if (newBlock.position.x == missingBlock.position.x
        && newBlock.position.y == missingBlock.position.y
        && newBlock.position.z == missingBlock.position.z) {
        /* Check if rail */
        setTimeout(async () => {
          if (newBlock.name == 'rail') {
            const score = (this.bot.time.age - this.startTime) / 20;
            // await this.bot.quit();
            winGame(this.bot, this.player, this.map.name, score);
          }
        }, 1000);
      }
    });
  }

  /**
   * Prompts bot to start playing game.
   */
  async play() {
    await this.bot.waitForTicks(20);

    const missingBlock = await this.getMissingRail();
    if (!missingBlock) return;
    this.addGameOverListener(missingBlock);
    await this.replaceRail(missingBlock.position);

    /* 1. Collect wood */
    await this.mineNode('oak_log', 4);

    // /* 2. Make wooden pickaxe */
    await this.craftItem('oak_planks', 4);
    await this.craftItem('stick', 1);
    let bench = await this.getCraftingTable();
    await this.craftItem('wooden_pickaxe', 1, bench);

    // /* 3. Upgrade to stone pickaxe */
    await this.mineNode('stone', 3);
    bench = await this.getCraftingTable();
    await this.craftItem('stone_pickaxe', 1, bench);

    // /* 4. Craft furnace */
    await this.mineNode('stone', 9);
    await this.mineNode('coal_ore', 4);
    await this.mineNode('iron_ore', 4);
    const furnaceBlock = await this.getFurnace();

    // /* 5. Smelt iron ore */
    const furnace = await this.bot.openFurnace(furnaceBlock);
    await furnace.putFuel(720, null, 4);
    await furnace.putInput(727, null, 4);

    // furnace.on('update', async e => {
    //   console.log(furnace.progress == 1)
    // Go to furnace
    // let { position } = furnace;
    // let goal = new GoalGetToBlock(position.x, position.y, position.z);
    // await bot.pathfinder.goto(goal);

    // await furnace.takeOutput();
    // });
    /* 3. Upgrade to iron pickaxe */
    // bench = await getCraftingTable(bot);
    // await craftItem(bot, 'iron_pickaxe', 1, bench);
    //    Logic to detect missing tracks 

  }

  /**
   * Teleports bot to its starting position (defiend in constructor).
   */
  async tp() {
    const int = setInterval(async () => {
      if (this.bot.player) {
        await rcon.run(`tp ${this.bot.username} ${this.map.spawn.bot.x} ${this.map.spawn.bot.y} ${this.map.spawn.bot.z}`);
        clearInterval(int);
      }
    }, 1000)
  }

  /**
   * Makes bot craft items.
   * 
   * @param {String} itemName the name identifier of the item to craft.
   * @param {Number} count    the quantity of items to craft; defaults to 1.
   * @param {Block}  bench    the crafting table to use, if recipe requires one.
   */
  async craftItem(itemName, count = 1, bench = null) {
    const item = mcData.itemsByName[itemName];
    if (!item) throw new Error('Unknown item type');

    const recipe = this.bot.recipesFor(item.id, null, count, bench)[0];
    if (!recipe) return;
    console.log("crafting " + itemName);

    try {
      await this.bot.craft(recipe, count, bench);
      console.log("crafting")
      const craftedItem = this.bot.inventory.findInventoryItem(item.id, null);
      await this.bot.waitForTicks(10);
      await this.bot.equip(craftedItem);
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
   * @return {Block} the block representing the crafting table.
   */
  async getCraftingTable() {
    let bench = this.bot.findBlock({
      matching: 167,
      maxDistance: 64
    });

    if (!bench) {
      /* No nearby bench. Check if already in inventory */
      bench = this.bot.inventory.findInventoryItem(257, null);
      if (!bench) {
        /* Not in inventory, craft bench */
        console.log('bench not found, crafting one!');
        await craftItem(this.bot, 'crafting_table');
        await this.bot.waitForTicks(10);
      }

      /* Randomly chose a nearby spot to place the bench */
      const bPos = this.bot.findBlocks({
        matching: 8,
        maxDistance: 15,
        count: 10,
        useExtraInfo: block => {
          /* On top of the block there must be nothing */
          let { position } = block;
          let check = this.bot.blockAt(vec3(position.x, position.y + 1, position.z));
          if (check.name == 'air') return true;
        }
      })[Math.floor(Math.random() * 10) + 1];
      const baseBlock = this.bot.blockAt(bPos);

      /* Place crafting table */
      bench = this.bot.inventory.findInventoryItem(257, null);
      await this.bot.equip(bench);
      await this.bot.placeBlock(baseBlock, vec3(0, 1, 0));
      console.log('bench placed!');

      const newBenchPos = vec3(baseBlock.position.x, baseBlock.position.y + 1, baseBlock.position.z);
      return this.bot.blockAt(newBenchPos);
    }

    /* Nearby bench found. Go to it */
    console.log('going to bench ' + bench.position);
    const { position } = bench;
    const goal = new GoalGetToBlock(position.x, position.y, position.z);
    await this.bot.pathfinder.goto(goal);

    return bench;
  }

  /**
   * Makes the bot go near a furnace if it exists within 64 blocks.
   * If a furnace does not exist and it is not in the bot inventory,
   * then the bot crafts one and places it nearby.
   * 
   * @return {Block} the block representing the furnace.
   */
  async getFurnace() {
    let furnace = this.bot.findBlock({
      matching: 170,
      maxDistance: 64
    });

    if (!furnace) {
      /* No nearby bench. Check if already in inventory */
      furnace = this.bot.inventory.findInventoryItem(259, null);
      if (!furnace) {
        /* Not in inventory, craft furnace */
        console.log('furnace not found, crafting one!');
        let bench = await getCraftingTable(this.bot);
        await craftItem(this.bot, 'furnace', 1, bench);
        await this.bot.waitForTicks(10);
      }

      /* Randomly chose a nearby spot to place the furnace */
      const bPos = this.bot.findBlocks({
        matching: 8,
        maxDistance: 15,
        count: 10,
        useExtraInfo: block => {
          /* On top of the block there must be nothing */
          let { position } = block;
          let check = this.bot.blockAt(vec3(position.x, position.y + 1, position.z));
          if (check.name == 'air') return true;
        }
      })[Math.floor(Math.random() * 10) + 1];
      const baseBlock = this.bot.blockAt(bPos);

      /* Place furnace */
      furnace = this.bot.inventory.findInventoryItem(259, null);
      await this.bot.equip(furnace);
      await this.bot.placeBlock(baseBlock, vec3(0, 1, 0));
      console.log('furnace placed!');

      const newPos = vec3(baseBlock.position.x, baseBlock.position.y + 1, baseBlock.position.z);
      return this.bot.blockAt(newPos);
    }

    /* Nearby furnace found. Go to it */
    console.log('going to furnace' + furnace.position);
    const { position } = furnace;
    const goal = new GoalGetToBlock(position.x, position.y, position.z);
    await this.bot.pathfinder.goto(goal);

    return furnace;
  }

  /**
   * Makes the bot mine resources.
   * 
   * @param {String} blockName the name identifier of the block to mine.
   * @param {Number} maxCount  the number of blocks to mine; defaults to 1.
   */
  async mineNode(blockName, maxCount = 1) {
    console.log('mining ' + blockName)
    const b = mcData.blocksByName[blockName];
    if (!b) throw new Error('Unknown block type');

    let minedCount = 0;
    while (minedCount < maxCount) {
      /* Prefer resources that are in sight of the bot */
      let block = this.bot.findBlock({
        matching: b.id,
        maxDistance: 64,
        useExtraInfo: block => {
          if (block.position.y < 173) return false;
          if (this.bot.canSeeBlock(block)) return true;
        }
      });

      /* If bot can't see resource, find nearest one */
      if (!block) {
        block = this.bot.findBlock({
          matching: b.id,
          maxDistance: 64
        });
      }

      /* No resource available, stop */
      if (!block) return;

      /* Go near block to mine */
      let { position } = block;
      const goal = new GoalGetToBlock(position.x, position.y, position.z);
      await this.bot.pathfinder.goto(goal);

      if (this.bot.canDigBlock(block)) {
        const cb = e => {
          if (e.position.distanceTo(block.position.offset(0.5, 0.5, 0.5)) <= 0.5) {
            let { position } = e;
            const goal = new GoalNear(position.x, position.y, position.z, 0)
            this.bot.pathfinder.setGoal(goal);
          }
        };

        this.bot.on('itemDrop', cb);
        try {
          /* Mine resource */
          await this.bot.tool.equipForBlock(block);
          await this.bot.dig(block);
          /* Wait for items to drop */
          await this.bot.waitForTicks(10);
          ++minedCount;
        } finally {
          this.bot.removeListener('itemDrop', cb);
        }
      }
    }
    console.log(`finished mining ${minedCount} ${blockName} nodes!`);
  }

  /**
   * Gets the position of the missing track and replaces it.
   * Starts search from center of plot.
   * 
   * @return {Vec3} the position of the missing rail.
   */
  async getMissingRail() {
    /* Find all 100 rails in 50 block radius */
    const rails = this.bot.findBlocks({
      point: vec3(this.map.center.x, this.map.center.y, this.map.center.z), //center of plot
      matching: mcData.blocksByName['rail'].id,
      maxDistance: 100,
      count: 100
    });
    if (!rails) return;

    let pos;
    /* Find common axis */
    if (rails[0].x == rails[1].x) {
      // x axis common
      let sorted = rails.sort((a, b) => a.z < b.z ? -1 : 1);
      /* Find missing spot */
      for (let i = 0; i < sorted.length - 1; ++i) {
        if (sorted[i].z === sorted[i + 1].z - 2) {
          pos = vec3(sorted[i].x, sorted[i].y, sorted[i].z + 1);
          console.log(`Missing rail at position ${pos}`);
          break;
        }
      }
    } else {
      // z axis common
      let sorted = rails.sort((a, b) => a.x < b.x ? -1 : 1);
      /* Find missing spot */
      for (let i = 0; i < sorted.length - 1; ++i) {
        if (sorted[i].x === sorted[i + 1].x - 2) {
          pos = vec3(sorted[i].x + 1, sorted[i].y, sorted[i].z);
          console.log(`Missing rail at position ${pos}`);
          break;
        }
      }
    }
    if (!pos) return console.log('no missing rail');

    /* Get missing rail block */
    return this.bot.blockAt(pos);
  }

  async replaceRail(pos) {
    const rail = this.bot.inventory.findInventoryItem(687, null);
    if (!rail) return;

    /* Go to missing rail */
    const goal = new GoalGetToBlock(pos.x, pos.y, pos.z);
    await this.bot.pathfinder.goto(goal);

    /* Place rail block */
    const baseBlock = this.bot.blockAt(vec3(pos.x, pos.y - 1, pos.z));
    await this.bot.equip(rail);
    await this.bot.placeBlock(baseBlock, vec3(0, 1, 0));
    // await this.bot.quit();
    winGame(this.bot, this.player, this.map.name);
  }
}

const winGame = async (bot, player, map, score = null) => {
  console.log(!matches.get(map));

  if (!matches.get(map)) {
    console.log('won')
    return;
  }

  matches.set(map, false);
  await rcon.run(`clear ${player}`);
  console.log('Won by player? ' + score);
  await rcon.run(`tp ${player} 0 174 0`);

  setTimeout(async () => {
    await rcon.run(`clear ${bot.username}`);
    await bot.quit();
    await rcon.run(`title ${player} title {"text":"${score ? "You Won!" : "You Lost!"}","color":"red"}`);
  }, 500);

  /* Reset map */
  setTimeout(async () => {
    await resetMap(map);
  }, 1000);

  if (score) {
    /* Add score */
    const id = await db.insert(player, score);
    console.log(id);
  }
}

const resetMap = async map => {
  await rcon.run(`/schem load ${map}`);
  await rcon.run(`/world world`);
  setTimeout(async () => {
    await rcon.run(`/paste -o`);
  }, 500)
}

module.exports = { MCBot, matches };
