/**
 * @file Manages all the server side interactions using RCON.
 * @author Alessandro Zanzi <zanzia@usi.ch>
 * 
 */

const util = require('minecraft-server-util');
require('dotenv').config();

const client = new util.RCON();

/**
 * Starts the RCON connection with the game server.
 */
(async () => {
  try {
    await client.connect(process.env.SRV_HOST, parseInt(process.env.SRV_RCON_PORT));
    await client.login(process.env.SRV_RCON_PWD);
    console.log('Connected to RCON!');
  } catch (err) {
    console.error('Error connecting to RCON server');
    throw err;
  }
})();

/**
 * Runs a command on the server console using RCON.
 * 
 * @param {String} cmd the command to execute.
 */
const run = async cmd => {
  try {
    await client.run(cmd);
    console.log(`Executed command: "${cmd}"`);
  } catch (err) {
    console.error('Error running command on RCON rcon server');
    throw err;
  }
}

/**
 * Closes completely the RCON connection to the game server.
 */
const close = async () => {
  try {
    await client.close();
    console.log('RCON connection closed successfully');    
  } catch (err) {
    console.error('Error closing RCON connection');
    throw err;
  }
}

/* Listen for all messages emitted from the game server console */
/* client.on('message', msg => {
  console.log(msg);
}); */

/**
 * Checks whether a given player is online or not.
 * 
 * @param  {String}  player the name of the player.
 * @return {Boolean}        true if the player is online.  
 */
 const isPlayerOnline = async player => {
  try {
    let { players } = await util.status(process.env.SRV_HOST, parseInt(process.env.SRV_PORT));
    for (let p of players.sample) {
      if (p.name == player)
        return true;
    }
    return false;
  } catch (err) {
    console.error('Error getting server status');
    throw err;
  }
}

module.exports = {
  run,
  close,
  isPlayerOnline
}