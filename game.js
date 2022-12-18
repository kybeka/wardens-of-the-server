const express = require('express');
const cors = require('cors');
const rcon = require('./rcon');
const maps = require('./maps.json');
const { MCBot, matches } = require('./bot');

const app = express();
app.use(cors({ origin: '*' }));
app.use(express.json({ limit: '4MB' }));

app.post('/play', async (req, res) => {
  const { player, map } = req.body;
  // TODO: check map name
  if (player === undefined || player === null || player === '')
    return res.status(404).end();
  /* Check if player is online */
  if (!await rcon.isPlayerOnline(player)) {
    /* Player not online */
    return res.send({ message: 'player not found' });
  }
  /* Player is online */
  /* Check if map is available */
  if (matches.get(map)) {
    return res.send({ message: 'map already in use' });
  }
  /* Flag map as in use */
  matches.set(map, true);
  console.log('[game] starting...')
  startGame(player, map);
  return res.send({ message: 'game started' });
});

const startGame = async (player, map) => {
  /* Create new bot */
  console.log(maps)
  console.log(map)
  const bot = new MCBot(map, player, maps[map]);
  await rcon.run(`title ${player} subtitle {"text":"Fast! ...replace the missing rail!","color":"blue"}`);
  await rcon.run(`title ${player} title {"text":"Game starts!","color":"red"}`);
  setTimeout(async () => {
    /* Teleport bot and player to plot */
    await bot.tp();
    bot.play();
    await rcon.run(`tp ${player} ${maps[map].spawn.player.x} ${maps[map].spawn.player.y} ${maps[map].spawn.player.z}`);
  }, 1000);
}

app.listen(process.env.WEB_PORT, () => {
  console.log(`Listening on port ${process.env.WEB_PORT}`)
});
