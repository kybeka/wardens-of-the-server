// server for the game launcher website

const express = require('express');
const app = express();
const port = 8888;
var path = require('path')

// renders ejs
app.set("view engine", "ejs");

// makes express search for all webpage assets in the public folder
app.use(express.static(path.join(__dirname, 'public'), {homepage: "homepage.ejs"}));

app.get("/", function(req,res) {
    res.redirect("/homepage");
});

app.get("/homepage", function(req, res) {
    res.render("homepage", {current: "homepage"});
});

app.get("/customize", function(req, res) {
    res.render("homepage", {current: "customize"});
});

app.listen(port);