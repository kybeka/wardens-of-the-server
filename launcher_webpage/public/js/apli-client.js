const scores = [
  {
    "player": "test",
    "score": 123
  },
  {
    "player": "test1",
    "score": 12
  },
  {
    "player": "test2",
    "score": 456
  },
  {
    "player": "test3",
    "score": 32
  },
  {
    "player": "test4",
    "score": 12332
  }
];

const getTopScores = async () => {
  // const data = await fetch('http://localhost:8888/api/highscore');
  const data = scores.slice();
  return data;
}

module.exports = {
  getTopScores
};