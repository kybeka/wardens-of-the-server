// server for the game launcher website

const express = require('express');
const app = express();
const port = 8888;
var path = require('path')
const db = require('./db');

// renders ejs
app.set("view engine", "ejs");

// makes express search for all webpage assets in the public folder
app.use(express.static(path.join(__dirname, 'public'), {homepage: "homepage.ejs"}));

app.get('/api/highscores', async (req, res) => {
  const data = await db.getAll(10);
  return res.send(data);
});

app.get("/", async (req, res) => {
    const scores = await db.getAll(10);
    res.render("homepage", {current: "homepage", scores});
});

app.get("/customize", async (req, res) => {
    const scores = await db.getAll(10);
    res.render("homepage", {current: "customize", scores});
});

app.listen(port);