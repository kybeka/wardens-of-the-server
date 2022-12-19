//EJS Compiled Views - This file was automatically generated on Mon Dec 19 2022 14:13:42 GMT+0100 (Central European Standard Time)
ejs.views_include = function(locals) {
    console.log("views_include_setup",locals);
    return function(path, d) {
        console.log("ejs.views_include",path,d);
        return ejs["views_"+path.replace(/\//g,"_")]({...d,...locals}, null, ejs.views_include(locals));
    }
};
ejs.views_customize = function(locals, escapeFn, include = ejs.views_include(locals), rethrow
) {
rethrow = rethrow || function rethrow(err, str, flnm, lineno, esc) {
  var lines = str.split('\n');
  var start = Math.max(lineno - 3, 0);
  var end = Math.min(lines.length, lineno + 3);
  var filename = esc(flnm);
  // Error context
  var context = lines.slice(start, end).map(function (line, i){
    var curr = i + start + 1;
    return (curr == lineno ? ' >> ' : '    ')
      + curr
      + '| '
      + line;
  }).join('\n');

  // Alter exception message
  err.path = filename;
  err.message = (filename || 'ejs') + ':'
    + lineno + '\n'
    + context + '\n\n'
    + err.message;

  throw err;
};
escapeFn = escapeFn || function (markup) {
  return markup == undefined
    ? ''
    : String(markup)
      .replace(_MATCH_HTML, encode_char);
};
var _ENCODE_HTML_RULES = {
      "&": "&amp;"
    , "<": "&lt;"
    , ">": "&gt;"
    , '"': "&#34;"
    , "'": "&#39;"
    }
  , _MATCH_HTML = /[&<>'"]/g;
function encode_char(c) {
  return _ENCODE_HTML_RULES[c] || c;
};
;
var __line = 1
  , __lines = "<form id=\"customize-form\" method=\"POST\" action=\"http://localhost:9000/play\">\n\n        <div class=\"row\">\n            <div class=\"column\">\n                <label for=\"map\">Choose your terrain:</label>\n            </div>\n\n\n            <div class=\"column form-item\">\n                <select name=\"map\" id=\"map\" required>\n                    <option disabled selected>Choose</option>\n                    <option value=\"nyc\">New York</option>\n                    <option value=\"santorini\">Santorini</option>\n                    <option value=\"tokyo\">Tokyo</option>\n                    <option value=\"neom\">Neom</option>\n                </select>\n            </div>\n        </div>\n\n        <div class=\"row\">\n            <div class=\"column\">\n                <input type=\"text\" name=\"player\" id=\"player\" placeholder=\"Minecraft username\">\n            </div>\n\n            <div class=\"column\">\n                <input id=\"start-button\" type=\"submit\" value=\"Launch Minecraft!\">\n            </div>\n\n        </div>\n</form>\n\n"
  , __filename = undefined;
try {
  var __output = "";
  function __append(s) { if (s !== undefined && s !== null) __output += s }
  with (locals || {}) {
    ; __append("<form id=\"customize-form\" method=\"POST\" action=\"http://localhost:9000/play\">\n\n        <div class=\"row\">\n            <div class=\"column\">\n                <label for=\"map\">Choose your terrain:</label>\n            </div>\n\n\n            <div class=\"column form-item\">\n                <select name=\"map\" id=\"map\" required>\n                    <option disabled selected>Choose</option>\n                    <option value=\"nyc\">New York</option>\n                    <option value=\"santorini\">Santorini</option>\n                    <option value=\"tokyo\">Tokyo</option>\n                    <option value=\"neom\">Neom</option>\n                </select>\n            </div>\n        </div>\n\n        <div class=\"row\">\n            <div class=\"column\">\n                <input type=\"text\" name=\"player\" id=\"player\" placeholder=\"Minecraft username\">\n            </div>\n\n            <div class=\"column\">\n                <input id=\"start-button\" type=\"submit\" value=\"Launch Minecraft!\">\n            </div>\n\n        </div>\n</form>\n\n")
    ; __line = 32
  }
  return __output;
} catch (e) {
  rethrow(e, __lines, __filename, __line, escapeFn);
}

}

ejs.views_homepage = function(locals, escapeFn, include = ejs.views_include(locals), rethrow
) {
rethrow = rethrow || function rethrow(err, str, flnm, lineno, esc) {
  var lines = str.split('\n');
  var start = Math.max(lineno - 3, 0);
  var end = Math.min(lines.length, lineno + 3);
  var filename = esc(flnm);
  // Error context
  var context = lines.slice(start, end).map(function (line, i){
    var curr = i + start + 1;
    return (curr == lineno ? ' >> ' : '    ')
      + curr
      + '| '
      + line;
  }).join('\n');

  // Alter exception message
  err.path = filename;
  err.message = (filename || 'ejs') + ':'
    + lineno + '\n'
    + context + '\n\n'
    + err.message;

  throw err;
};
escapeFn = escapeFn || function (markup) {
  return markup == undefined
    ? ''
    : String(markup)
      .replace(_MATCH_HTML, encode_char);
};
var _ENCODE_HTML_RULES = {
      "&": "&amp;"
    , "<": "&lt;"
    , ">": "&gt;"
    , '"': "&#34;"
    , "'": "&#39;"
    }
  , _MATCH_HTML = /[&<>'"]/g;
function encode_char(c) {
  return _ENCODE_HTML_RULES[c] || c;
};
;
var __line = 1
  , __lines = "<article>\n    <h2>Welcome to Build & Cross!</h2>\n    <p>During this game, you, the player, are spawned onto a map in Minecraft\n        equiped with a railway that is hovewer, broken. There are resources placed throughout the world,\n        which will help you build missing rails.\n        Your mission is to place rails where they're missing and help the minecart that uses the railway pass.\n    </p>\n</article>\n\n<br />\n\n<div class=\"row\">\n    <div class=\"column\">\n\n        <div class=\"img-descr\">\n            <h4 class=\"img-txt\"> Tokyo:</h4>\n            <img src=\"img/tokyo.jpeg\" alt=\"Tokyo\" height=\"400\" width=\"400\" />\n        </div>\n\n    </div>\n    <div class=\"column\">\n\n        <div class=\"img-descr\">\n            <h4 class=\"img-txt\"> Neom:</h4>\n            <img src=\"img/neom.jpeg\" alt=\"Neom\" height=\"400\" width=\"400\" />\n        </div>\n\n    </div>\n</div>\n\n<div class=\"row\">\n    <div class=\"column\">\n\n        <div class=\"img-descr\">\n            <h4 class=\"img-txt\"> Santorini:</h4>\n            <img src=\"img/santorini.jpeg\" alt=\"Santorini\" height=\"400\" width=\"400\" />\n        </div>\n\n    </div>\n    <div class=\"column\">\n\n        <div class=\"img-descr\">\n            <h4 class=\"img-txt\"> New York City:</h4>\n            <img src=\"img/nyc.jpeg\" alt=\"New York City\" height=\"400\" width=\"400\" />\n        </div>\n\n    </div>\n</div>"
  , __filename = undefined;
try {
  var __output = "";
  function __append(s) { if (s !== undefined && s !== null) __output += s }
  with (locals || {}) {
    ; __append("<article>\n    <h2>Welcome to Build & Cross!</h2>\n    <p>During this game, you, the player, are spawned onto a map in Minecraft\n        equiped with a railway that is hovewer, broken. There are resources placed throughout the world,\n        which will help you build missing rails.\n        Your mission is to place rails where they're missing and help the minecart that uses the railway pass.\n    </p>\n</article>\n\n<br />\n\n<div class=\"row\">\n    <div class=\"column\">\n\n        <div class=\"img-descr\">\n            <h4 class=\"img-txt\"> Tokyo:</h4>\n            <img src=\"img/tokyo.jpeg\" alt=\"Tokyo\" height=\"400\" width=\"400\" />\n        </div>\n\n    </div>\n    <div class=\"column\">\n\n        <div class=\"img-descr\">\n            <h4 class=\"img-txt\"> Neom:</h4>\n            <img src=\"img/neom.jpeg\" alt=\"Neom\" height=\"400\" width=\"400\" />\n        </div>\n\n    </div>\n</div>\n\n<div class=\"row\">\n    <div class=\"column\">\n\n        <div class=\"img-descr\">\n            <h4 class=\"img-txt\"> Santorini:</h4>\n            <img src=\"img/santorini.jpeg\" alt=\"Santorini\" height=\"400\" width=\"400\" />\n        </div>\n\n    </div>\n    <div class=\"column\">\n\n        <div class=\"img-descr\">\n            <h4 class=\"img-txt\"> New York City:</h4>\n            <img src=\"img/nyc.jpeg\" alt=\"New York City\" height=\"400\" width=\"400\" />\n        </div>\n\n    </div>\n</div>")
    ; __line = 48
  }
  return __output;
} catch (e) {
  rethrow(e, __lines, __filename, __line, escapeFn);
}

}

ejs.views_includes_scoreboard = function(locals, escapeFn, include = ejs.views_include(locals), rethrow
) {
rethrow = rethrow || function rethrow(err, str, flnm, lineno, esc) {
  var lines = str.split('\n');
  var start = Math.max(lineno - 3, 0);
  var end = Math.min(lines.length, lineno + 3);
  var filename = esc(flnm);
  // Error context
  var context = lines.slice(start, end).map(function (line, i){
    var curr = i + start + 1;
    return (curr == lineno ? ' >> ' : '    ')
      + curr
      + '| '
      + line;
  }).join('\n');

  // Alter exception message
  err.path = filename;
  err.message = (filename || 'ejs') + ':'
    + lineno + '\n'
    + context + '\n\n'
    + err.message;

  throw err;
};
escapeFn = escapeFn || function (markup) {
  return markup == undefined
    ? ''
    : String(markup)
      .replace(_MATCH_HTML, encode_char);
};
var _ENCODE_HTML_RULES = {
      "&": "&amp;"
    , "<": "&lt;"
    , ">": "&gt;"
    , '"': "&#34;"
    , "'": "&#39;"
    }
  , _MATCH_HTML = /[&<>'"]/g;
function encode_char(c) {
  return _ENCODE_HTML_RULES[c] || c;
};
;
var __line = 1
  , __lines = "<h4 class=\"titles\">Top 10 Players</h4>\n<table>\n  <tr>\n    <td class=\"titles\">Player</td>\n    <td class=\"titles\">Score</td>\n    <td class=\"titles\">Map\n    <td>\n  </tr>\n  <% for (let s of scores) { %>\n    <tr>\n      <td>\n        <%= s.player %>\n      </td>\n      <td>\n        <%= s.score %>\n      </td>\n      <td>\n        <%= s.map %>\n      </td>\n    </tr>\n    <% } %>\n</table>"
  , __filename = undefined;
try {
  var __output = "";
  function __append(s) { if (s !== undefined && s !== null) __output += s }
  with (locals || {}) {
    ; __append("<h4 class=\"titles\">Top 10 Players</h4>\n<table>\n  <tr>\n    <td class=\"titles\">Player</td>\n    <td class=\"titles\">Score</td>\n    <td class=\"titles\">Map\n    <td>\n  </tr>\n  ")
    ; __line = 9
    ;  for (let s of scores) { 
    ; __append("\n    <tr>\n      <td>\n        ")
    ; __line = 12
    ; __append(escapeFn( s.player ))
    ; __append("\n      </td>\n      <td>\n        ")
    ; __line = 15
    ; __append(escapeFn( s.score ))
    ; __append("\n      </td>\n      <td>\n        ")
    ; __line = 18
    ; __append(escapeFn( s.map ))
    ; __append("\n      </td>\n    </tr>\n    ")
    ; __line = 21
    ;  } 
    ; __append("\n</table>")
    ; __line = 22
  }
  return __output;
} catch (e) {
  rethrow(e, __lines, __filename, __line, escapeFn);
}

}