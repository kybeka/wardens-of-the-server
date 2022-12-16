const express = require('express');
const rcon = require('./rcon');
const maps = require('./maps.json');
const mineflayer = require('mineflayer');

// const bot = require('./bot');

const app = express();

app.use(express.json({ limit: '4MB' }));

// TODO: move to separate file
class MCBot {
  constructor(name, spawn) {
    this.bot = mineflayer.createBot({
      host: process.env.SRV_HOST || 'localhost',
      port: process.env.SRV_PORT || portNum,
      username: name
    });

    this.spawn = spawn;
  }

  async tp() {
    const int = setInterval(async () => {
      if (this.bot.player) {
        await rcon.run(`tp ${this.bot.username} ${this.spawn.x} ${this.spawn.y} ${this.spawn.z}`);
        clearInterval(int);
      }
    }, 1000)
    
  }
}

app.post('/play', async (req, res) => {
  const { player } = req.body;
  if (player === undefined || player === null || player === '')
    return res.status(404).end();
  /* Check if player is online */
  if (!await rcon.isPlayerOnline(player)) {
    /* Player not online */
    return res.send({ message: 'player not found' });
  }
  /* Player is online */
  res.send({ message: 'game started' });
  await startGame(player);
});

// let bots = [];

const startGame = async player => {
  /* Teleport player to plot */
  await rcon.run(`tp ${player} ${maps.spawn.player.x} ${maps.spawn.player.y} ${maps.spawn.player.z}`);
  /* Create new bot */
  const bot = new MCBot('jef', maps.spawn.bot);
  await bot.tp();
}

app.listen(8888, () => {
  console.log(`Listening on port 8888`)
})