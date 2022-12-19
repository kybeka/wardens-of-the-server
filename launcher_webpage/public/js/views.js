//EJS Compiled Views - This file was automatically generated on Sun Dec 18 2022 22:25:02 GMT+0100 (Central European Standard Time)
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
  , __lines = "<form method=\"POST\" action=\"http://localhost:9000/play\">\n    <div class=\"form-item\">\n        <label for=\"map\">Choose your terrain:</label>\n        <select name=\"map\" id=\"map\" required>\n            <option disabled selected>Choose</option>\n            <option value=\"nyc\">New York</option>\n            <option value=\"santorini\">Santorini</option>\n            <option value=\"tokyo\">Tokyo</option>\n            <option value=\"neom\">Neom</option>\n        </select>\n    </div>\n    <div class=\"form-item\">\n        <label for=\"level\">Choose the difficulty:</label>\n        <select name=\"level\" id=\"level\">\n            <option value=\"easy\" selected>Easy</option>\n            <option value=\"medium\">Medium</option>\n            <option value=\"hard\">Hard</option>\n        </select>\n    </div>\n\n    <input type=\"text\" name=\"player\" id=\"player\" placeholder=\"Minecraft username\">\n    <input id=\"start-button\" type=\"submit\" value=\"Launch Minecraft!\">\n</form>"
  , __filename = undefined;
try {
  var __output = "";
  function __append(s) { if (s !== undefined && s !== null) __output += s }
  with (locals || {}) {
    ; __append("<form method=\"POST\" action=\"http://localhost:9000/play\">\n    <div class=\"form-item\">\n        <label for=\"map\">Choose your terrain:</label>\n        <select name=\"map\" id=\"map\" required>\n            <option disabled selected>Choose</option>\n            <option value=\"nyc\">New York</option>\n            <option value=\"santorini\">Santorini</option>\n            <option value=\"tokyo\">Tokyo</option>\n            <option value=\"neom\">Neom</option>\n        </select>\n    </div>\n    <div class=\"form-item\">\n        <label for=\"level\">Choose the difficulty:</label>\n        <select name=\"level\" id=\"level\">\n            <option value=\"easy\" selected>Easy</option>\n            <option value=\"medium\">Medium</option>\n            <option value=\"hard\">Hard</option>\n        </select>\n    </div>\n\n    <input type=\"text\" name=\"player\" id=\"player\" placeholder=\"Minecraft username\">\n    <input id=\"start-button\" type=\"submit\" value=\"Launch Minecraft!\">\n</form>")
    ; __line = 23
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
  , __lines = "<article>\n    <h2>Welcome to Build & Cross!</h2>\n    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. A deleniti ipsa nemo enim optio, dolore dolor omnis minus incidunt quas doloremque eum esse velit? Expedita blanditiis sit labore voluptate quasi.</p>\n</article>\n\n<br />\n\n<div class=\"img-descr\">\n    <h4 class=\"img-txt\"> Minecraft:</h4>\n    <img src=\"img/minecraft-img.jpg\" alt=\"Minecraft\" height=\"400\" width=\"400\" />\n</div>"
  , __filename = undefined;
try {
  var __output = "";
  function __append(s) { if (s !== undefined && s !== null) __output += s }
  with (locals || {}) {
    ; __append("<article>\n    <h2>Welcome to Build & Cross!</h2>\n    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. A deleniti ipsa nemo enim optio, dolore dolor omnis minus incidunt quas doloremque eum esse velit? Expedita blanditiis sit labore voluptate quasi.</p>\n</article>\n\n<br />\n\n<div class=\"img-descr\">\n    <h4 class=\"img-txt\"> Minecraft:</h4>\n    <img src=\"img/minecraft-img.jpg\" alt=\"Minecraft\" height=\"400\" width=\"400\" />\n</div>")
    ; __line = 11
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
  , __lines = "<h4>Top 10 Players</h4>\n<table>\n  <tr>\n    <th>Player</th>\n    <th>Score</th>\n  </tr>\n  <% for (let s of scores) { %>\n    <tr>\n      <td>\n        <%= s.player %>\n          </th>\n      <td>\n        <%= s.score %>\n          </th>\n    </tr>\n    <% } %>\n</table>"
  , __filename = undefined;
try {
  var __output = "";
  function __append(s) { if (s !== undefined && s !== null) __output += s }
  with (locals || {}) {
    ; __append("<h4>Top 10 Players</h4>\n<table>\n  <tr>\n    <th>Player</th>\n    <th>Score</th>\n  </tr>\n  ")
    ; __line = 7
    ;  for (let s of scores) { 
    ; __append("\n    <tr>\n      <td>\n        ")
    ; __line = 10
    ; __append(escapeFn( s.player ))
    ; __append("\n          </th>\n      <td>\n        ")
    ; __line = 13
    ; __append(escapeFn( s.score ))
    ; __append("\n          </th>\n    </tr>\n    ")
    ; __line = 16
    ;  } 
    ; __append("\n</table>")
    ; __line = 17
  }
  return __output;
} catch (e) {
  rethrow(e, __lines, __filename, __line, escapeFn);
}

}