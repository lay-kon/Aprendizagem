const mysql = require('mysql2');

// Configuração simples do MySQL. Ajuste com seu usuário, senha e nome do banco.
const connection = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: 'mysql@MIC365',
  database: 'crud_funcionario'
});

connection.connect((err) => {
  if (err) {
    console.error('Erro ao conectar ao MySQL:', err.message);
    return;
  }
  console.log('Conectado ao MySQL');
});

module.exports = connection;
