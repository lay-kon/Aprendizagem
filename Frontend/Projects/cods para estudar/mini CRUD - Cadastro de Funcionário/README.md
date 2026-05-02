<!-- 
    ============================================
                    Mini CRUD
    ============================================
    
    Bem-vindo! Este é um projeto de CRUD básico para 
    gerenciar funcionários.

-->

# Mini CRUD - Funcionários

## O que é este projeto?

Um **CRUD** é um termo que significa:
- **C**reate (Criar) - adicionar novo funcionário
- **R**ead (Ler) - visualizar funcionários
- **U**pdate (Atualizar) - editar dados do funcionário
- **D**elete (Deletar) - remover funcionário

Este projeto tem um sistema de login seguro e um painel onde você pode fazer todas essas operações.

---

## 📁 Estrutura de Pastas

```
projeto/
├── index.html             ← Arquivo que redireciona para login
├── img/                   ← Pasta para imagens
├── pages/                 ← Página HTML do projeto
│   ├── login.html         ← Tela de login
│   ├── dashboard.html     ← Tela inicial (home)
│   └── func-ger.html      ← Gerenciamento de funcionários
├── scripts/               ← Arquivo JavaScript (lógica)
│   ├── auth.js            ← Sistema de login e logout
│   └── func-ger.js        ← Sistema CRUD de funcionários
└── styles/                ← Estilos CSS (visual)
    ├── global.css         ← Estilos gerais e cores
    ├── layout.css         ← Estrutura (header, nav)
    ├── componentes.css    ← Botões, cards, tabelas
    └── responsivo.css     ← Adapta para celular/tablet
```

---

## 🔐 Login (Como Funciona)

**Credenciais padrão:**
- E-mail: `admin@empresa.com`
- Senha: `admin123`

**O que acontece quando faz login:**
1. JavaScript verifica se o email e senha estão corretos
2. Se estiverem, salva na memória do navegador que você está logado
3. Redireciona para a página dashboard (home)

**Logout:**
- Quando clica em "Sair", remove a permissão de acesso
- Redireciona para a página de login

---

## 📝 Como Usar o CRUD

### ➕ **Adicionar Funcionário**
1. Preencha o nome e email nos campos
2. Clique em "Salvar Funcionário"
3. O funcionário aparece na tabela abaixo

### 👀 **Visualizar/Listar**
- Na tabela você vê todos os funcionários cadastrados
- Os dados ficam salvos no navegador (localStorage)

### ✏️ **Editar Funcionário**
1. Clique no botão "Editar" na linha do funcionário
2. Os dados aparecem no formulário
3. Modifique o que quiser
4. Clique em "Atualizar Funcionário"

### ❌ **Deletar/Remover Funcionário**
1. Clique no botão "Excluir" na linha do funcionário
2. Um alerta pede confirmação
3. Se confirmar, o funcionário é removido


---

## 🎨 Como os Estilos Estão Organizados

**global.css:**
- Define cores padrão
- Remove espaçamento padrão
- Define fonte geral

**layout.css:**
- Header (cabeçalho)
- Navegação
- Main (conteúdo principal)

**componentes.css:**
- Cards (caixas)
- Botões
- Formulários
- Tabelas

**responsivo.css:**
- Media queries
- Adapta para celular (até 480px)
- Adapta para tablet (640px - 768px)
- Adapta para desktop (1024px+)

---

## 🚀 Como Executar

1. Abra o arquivo `index.html` em um navegador
2. Você será redirecionado para `pages/login.html`
3. Faça login com as credenciais
4. Pronto! Use o sistema

---

## 🔧 Como Editar

### Para editar o estilo visual:
Abra os arquivos em `styles/`:
- Se quer mudar cores → global.css
- Se quer mudar layout → layout.css
- Se quer mudar botões → componentes.css

### Para mudar a lógica:
Abra os arquivos em `scripts/`:
- Login → auth.js
- CRUD → func-ger.js

### Para mudar o HTML:
Abra os arquivos em `pages/`:
- Login → login.html
- Home → dashboard.html
- Gerenciar → func-ger.html

---

## 📖 Conceitos Usados

### **HTML (Estrutura)**
- `<form>` - criar formulários
- `<input>` - campos de entrada
- `<button>` - botões
- `<table>` - tabelas
- `<div>` - divisões/caixas

### **CSS (Visual)**
- `display: flex` - alinha itens horizontalmente
- `display: grid` - cria tabelas com colunas
- `media queries` - adapta para diferentes telas
- Classes e IDs - para estilos específicos

### **JavaScript (Lógica)**
- `localStorage` - salva dados
- Funções - blocos de código reutilizável
- Arrays - listas de dados
- Event listeners - reage a cliques/ações