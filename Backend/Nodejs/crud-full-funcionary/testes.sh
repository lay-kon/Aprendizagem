# Testes do CRUD

## 1. Criar funcionário
curl -X POST http://localhost:3000/funcionarios \
  -H "Content-Type: application/json" \
  -d '{"firstName":"João","lastName":"Silva","cargo":"Desenvolvedor","salario":3500.00}'

## 2. Listar todos
curl http://localhost:3000/funcionarios

## 3. Buscar por ID (substitua 1 pelo ID real)
curl http://localhost:3000/funcionarios/1

## 4. Atualizar funcionário
curl -X PUT http://localhost:3000/funcionarios/1 \
  -H "Content-Type: application/json" \
  -d '{"firstName":"João","lastName":"Santos","cargo":"Senior","salario":4500.00}'

## 5. Excluir funcionário
curl -X DELETE http://localhost:3000/funcionarios/1