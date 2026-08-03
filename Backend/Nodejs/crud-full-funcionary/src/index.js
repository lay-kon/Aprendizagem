const express = require('express');
const funcionary = require('./funcionary');

const app = express();
const port = 3000;

app.use(express.json());

// Rota de teste simples
app.get('/', (req, res) => {
  res.json({ mensagem: 'API funcionando Senhora Professora! CRUD completo (ITEL mata 😭😭😭😭🤧)' });
});

app.post('/funcionarios', (req, res) => {
  const dados = req.body;
  if (!dados.firstName || !dados.lastName || !dados.cargo || !dados.salario) {
    return res.status(400).json({ erro: 'firstName, lastName, cargo e salario são obrigatórios' });
  }

  funcionary.addFuncionary(dados, (err, novoFuncionario) => {
    if (err) return res.status(500).json({ erro: 'Erro ao salvar funcionario' });
    res.status(201).json(novoFuncionario);
  });
});

app.get('/funcionarios', (req, res) => {
  funcionary.getAllFuncionaries((err, funcionarios) => {
    if (err) return res.status(500).json({ erro: 'Erro ao buscar funcionarios' });
    res.json(funcionarios);
  });
});

app.get('/funcionarios/:id', (req, res) => {
  const id = Number(req.params.id);
  funcionary.getFuncionaryById(id, (err, funcionario) => {
    if (err) return res.status(500).json({ erro: 'Erro ao buscar funcionario' });
    if (!funcionario) return res.status(404).json({ erro: 'Funcionário não encontrado' });
    res.json(funcionario);
  });
});

app.put('/funcionarios/:id', (req, res) => {
  const id = Number(req.params.id);
  const dados = req.body;
  if (!dados.firstName || !dados.lastName || !dados.cargo || !dados.salario) {
    return res.status(400).json({ erro: 'firstName, lastName, cargo e salario são obrigatórios' });
  }

  funcionary.updateFuncionary(id, dados, (err, affectedRows) => {
    if (err) return res.status(500).json({ erro: 'Erro ao atualizar funcionario' });
    if (!affectedRows) return res.status(404).json({ erro: 'Funcionário não encontrado' });
    res.json({ mensagem: 'Funcionário atualizado com sucesso' });
  });
});

app.delete('/funcionarios/:id', (req, res) => {
  const id = Number(req.params.id);
  funcionary.deleteFuncionary(id, (err, affectedRows) => {
    if (err) return res.status(500).json({ erro: 'Erro ao excluir funcionario' });
    if (!affectedRows) return res.status(404).json({ erro: 'Funcionário não encontrado' });
    res.json({ mensagem: 'Funcionário excluído com sucesso' });
  });
});

app.listen(port, () => {
  console.log(`API rodando em http://localhost:${port}`);
});
