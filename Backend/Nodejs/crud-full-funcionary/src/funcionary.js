const db = require('./database');

function addFuncionary(funcionary, callback) {
  const sql = 'INSERT INTO funcionarios (firstName, lastName, cargo, salario) VALUES (?, ?, ?, ?)';
  db.query(sql, [funcionary.firstName, funcionary.lastName, funcionary.cargo, funcionary.salario], (err, result) => {
    if (err) return callback(err);
    callback(null, { id: result.insertId, ...funcionary });
  });
}

function getAllFuncionaries(callback) {
  const sql = 'SELECT * FROM funcionarios';
  db.query(sql, (err, results) => {
    if (err) return callback(err);
    callback(null, results);
  });
}

function getFuncionaryById(id, callback) {
  const sql = 'SELECT * FROM funcionarios WHERE id = ?';
  db.query(sql, [id], (err, results) => {
    if (err) return callback(err);
    callback(null, results[0]);
  });
}

function updateFuncionary(id, funcionary, callback) {
  const sql = 'UPDATE funcionarios SET firstName = ?, lastName = ?, cargo = ?, salario = ? WHERE id = ?';
  db.query(sql, [funcionary.firstName, funcionary.lastName, funcionary.cargo, funcionary.salario, id], (err, result) => {
    if (err) return callback(err);
    callback(null, result.affectedRows);
  });
}

function deleteFuncionary(id, callback) {
  const sql = 'DELETE FROM funcionarios WHERE id = ?';
  db.query(sql, [id], (err, result) => {
    if (err) return callback(err);
    callback(null, result.affectedRows);
  });
}

module.exports = {
  addFuncionary,
  getAllFuncionaries,
  getFuncionaryById,
  updateFuncionary,
  deleteFuncionary
};
