const express = require('express');
const mysql = require('mysql2');
const cors = require('cors');

const app = express();
const port = 8081;

app.use(cors());

const connection = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: '1234',
  database: 'milestone_db',
});

connection.connect((err) => {
  if (err) {
    console.error('Error connecting to the database: ', err);
    return;
  }
  console.log('Connected to the database');
});

app.get('/api/data', (req, res) => {
  const query = 'SELECT * FROM h2h_oap'; // Replace with your table name
  connection.query(query, (err, rows) => {
    if (err) {
      console.error('Error fetching data from the database: ', err);
      res.status(500).json({ error: 'Internal Server Error' });
      return;
    }
    res.json(rows);
  });
});

app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
