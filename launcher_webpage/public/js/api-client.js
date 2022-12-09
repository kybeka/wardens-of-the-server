
api = function () {
  const getTopScores = async () => {
    const res = await fetch('/api/highscores');
    const json = await res.json();
    return json;
  }

  return {
    getTopScores,
  }
}();
