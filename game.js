const express = require('express');
const cors = require('cors');
const rcon = require('./rcon');
const maps = require('./maps.json');

const MCBot = require('./bot');

const app = express();
app.use(cors({ origin: '*' }));
app.use(express.json({ limit: '4MB' }));

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
  console.log('[game] starting...')
  await startGame(player);
});

const startGame = async player => {
  /* Create new bot */
  const bot = new MCBot('jef', maps.spawn.bot, maps.center);
  await rcon.run(`title ${player} subtitle {"text":"Fast! ...replace the missing rail!","color":"blue"}`);
  await rcon.run(`title ${player} title {"text":"Game starts!","color":"red"}`);
  setTimeout(async () => {
    /* Teleport bot and player to plot */
    await bot.tp();
    await rcon.run(`tp ${player} ${maps.spawn.player.x} ${maps.spawn.player.y} ${maps.spawn.player.z}`);
    bot.play();
  }, 2000);
}

app.listen(process.env.WEB_PORT, () => {
  console.log(`Listening on port ${process.env.WEB_PORT}`)
})