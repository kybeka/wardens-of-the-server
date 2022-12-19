/**
 * @file Manages all the interaction with the database.
 * @author Alessandro Zanzi <zanzia@usi.ch>
 * 
 */

const mysql = require('mysql2');
require('dotenv').config();

const pool = mysql.createPool({
  host: process.env.DB_HOST,
  database: process.env.DB_NAME,
  user: process.env.DB_USER,
  password: process.env.DB_PWD,
  connectionLimit: process.env.DB_CONN_LIM
});

/**
 * Inserts in the database a new entry for the score of the player.
 * 
 * @param  {String} player the name of the player.
 * @param  {Number} score  the score fo the player.
 * @param  {String} map    the map the game was played in.
 * @return {Number}        id of inserted score.  
 */
const insert = async (player, score, map) => {
  try {
    const [results] = await pool.promise().query(`INSERT INTO scores (player, score, map) VALUES (?, ?, ?);`, [player, score, map]);
    return results.insertId;
  } catch (err) {
    throw err;
  }
}

/**
 * Returns entries in descending order.
 * Optionally limit number of scores.
 * 
 * @param  {Number} lim optional. Only return lim entries.
 * @return {Array}  an array of entries.  
 */
const getAll = async (lim = false) => {
  try {
    const [rows] = await pool.promise().query(`SELECT * FROM scores ORDER BY score ASC${lim ? ' LIMIT ' + parseInt(lim) : ''};`);
    return rows;
  } catch (err) {
    throw err;
  }
}

/**
 * Returns the score entry for the given id.
 * 
 * @param  {Number} id of the entry.
 * @return {Object} an object representing the entry.  
 */
const getById = async id => {
  try {
    const [rows] = await pool.promise().query(`SELECT * FROM scores WHERE id = ?;`, [parseInt(id)]);
    return rows[0];
  } catch (error) {
    throw error;
  }
}

/**
 * Returns all the scores for the given player in descending order.
 * Optionally limit number of scores.
 * 
 * @param  {String} player name of the player.
 * @param  {Number} lim optional. Only return lim entries.
 * @return {Array}  an array contianing all the scores for the given player.  
 */
const getByPlayer = async (player, lim = false) => {
  try {
    const [rows] = await pool.promise().query(`SELECT * FROM scores WHERE player = ? ORDER BY score ASC${lim ? ' LIMIT ' + parseInt(lim) : ''};`, [player]);
    return rows;
  } catch (error) {
    throw error;
  }
}

module.exports = {
  insert,
  getAll,
  getById,
  getByPlayer
};
