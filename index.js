// // import { readFile } from 'fs/promises';

// // var { readFile } = require('fs/promises');

// // const colors = require('colors');

// const fs = require('fs');
// const mineflayer = require('mineflayer');
// // const { mineflayer: mineflayerViewer } = require('prismarine-viewer');

// let botArgs = {
//     host: '51.210.154.234',
//     port: '25604',
//     version: '1.19.2'
// }


// class MCBot {
//     // Constructor
//     constructor(username, password, auth) {
//         this.username = username;
//         this.password = password;
//         this.auth = auth;
//         this.host = botArgs["host"];
//         this.port = botArgs["port"];
//         this.version = botArgs["version"];
        
//         // Initialize the bot
//         this.initBot();
//     } 

//     // Init bot instance
//     initBot() {
//         this.bot = mineflayer.createBot({
//             "username": this.username,
//             "password": this.password,
//             "auth": this.auth,
//             "host": this.host,
//             "port": this.port,
//             "version": this.version

//         });

//         //Add to list
//         botNames.push(this.bot.username);

//         //Initialize bot events
//         // this.initEvents();
//     }

// }



// // const ACCOUNT = JSON.parse(
// //     await readFile(
// //         new URL('./ACCOUNT.json', import.meta.url)
// //     )
// // );



// const ACCOUNT = function() {
//     console.log("yoohoo");
//     return JSON.parse(
//         fs.readFileSync('./acc.json', (err, data) => {
//         console.log("dammm");
//         console.log(data);
//     }))
// }

// let bots = [];
// let botNames = [];
// for(const ACC of ACCOUNT()) {
//     let newBot = new MCBot(ACC.username, ACC.password, ACC.auth)
//     bots.push(newBot);
// }




const mineflayer = require('mineflayer');

const portNum = 41955;  //DON'T FORGET TO CHANGE THE PORT NUMBER

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

// bot.on('physicTick', lookAtNearestPlayer);

function COLLECTING (collector, collected) {
    if (collector.type === 'player') {
      const item = collected.getDroppedItem()
      bot.chat(`${collector.username !== bot.username ? ("I'm so jealous. " + collector.username) : 'I '} collected ${item.count} ${item.displayName}`)
    }
}

bot.on('playerCollect', COLLECTING);

// function isIronBlock(block) {
//     return block.name === "iron_block";
// }

// function digIron() {
//     let block = bot.findBlock({
//         matching: isIronBlock,
//         maxDistance: 1
//     });

//     if(block) bot.dig(block);
//     else console.log("didn't dig iron");
// }

// function chopWood() {
//     let block = bot.findBlock({
//         matching: 111,
//         maxDistance: 1
//     });

//     if (block) bot.dig(block);
//     else console.log("didn't chop wood");
// }


// async function digResource() {
//     let blockPosition = bot.entity.position.offset(0,-1,0);
//     let block = bot.blockAt(blockPosition);
//     await bot.dig(block);
//     bot.chat("Resource dug!");
// }

// bot.on('spawn', digIron);

//____________________________________________________//

// const { pathfinder } = require('mineflayer-pathfinder');


// const bot2 = mineflayer.createBot({
//     host: 'localhost',
//     port: portNum,
//     username: 'pathfinding_bot'
// });





