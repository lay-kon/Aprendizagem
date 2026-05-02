/* ===================================
   ARQUIVO: func-ger.js
   DESCRIÇÃO: Sistema CRUD (Criar, Ler, Atualizar, Deletar) funcionários
   ================================== */

/* Array que armazena todos os funcionários */
let employees = []

/* Variável que guarda o ID do funcionário que está sendo editado
   null significa que não está editando nada */
let editId = null

/* ===== FUNÇÕES DE ARMAZENAMENTO ===== */

/* Função para buscar funcionários do localStorage (armazenamento do navegador)
   localStorage é como um banco de dados simples do navegador */
function getEmployees() {
    /* Tenta pegar dados salvos com a chave 'employees' */
    const stored = localStorage.getItem('employees')
    
    /* Se houver dados, converte de texto para array
       Se não houver, retorna um array vazio */
    return stored ? JSON.parse(stored) : []
}

/* Função para salvar funcionários no localStorage */
function saveEmployees() {
    /* Converte o array de funcionários em texto e salva */
    localStorage.setItem('employees', JSON.stringify(employees))
}

/* ===== FUNÇÃO PARA MOSTRAR OS FUNCIONÁRIOS ===== */

/* Função que atualiza a tabela com os funcionários */
function renderEmployees() {
    /* Pega o elemento HTML onde será mostrada a lista */
    const employeeList = document.getElementById('employee-list')
    
    /* Limpa a lista (apaga tudo que tinha antes) */
    employeeList.innerHTML = ''

    /* Se não há funcionários, mostra mensagem vazia */
    if (employees.length === 0) {
        employeeList.innerHTML = '<tr><td colspan="3" class="empty">Nenhum funcionário cadastrado.</td></tr>'
        return
    }

    /* Para cada funcionário no array */
    employees.forEach((employee) => {
        /* Cria uma nova linha na tabela */
        const row = document.createElement('tr')
        
        /* Preenche a linha com os dados do funcionário */
        row.innerHTML = `
            <td>${employee.name}</td>
            <td>${employee.email}</td>
            <td class="actions-row">
                <button type="button" class="small-btn" onclick="startEdit('${employee.id}')">Editar</button>
                <button type="button" class="small-btn small-btn-danger" onclick="deleteEmployee('${employee.id}')">Excluir</button>
            </td>
        `
        
        /* Adiciona a linha à tabela */
        employeeList.appendChild(row)
    })
}

/* ===== FUNÇÕES DE EDIÇÃO ===== */

/* Função para limpar o formulário e voltar ao estado normal */
function resetForm() {
    /* Limpa todos os campos do formulário */
    document.getElementById('employee-form').reset()
    
    /* Esconde o botão cancelar */
    document.getElementById('cancel-edit').style.display = 'none'
    
    /* Volta o texto do botão para "Salvar Funcionário" */
    document.querySelector('#employee-form button[type="submit"]').textContent = 'Salvar Funcionário'
    
    /* Limpa o ID de edição */
    editId = null
}

/* Função para começar a editar um funcionário
   id = o ID do funcionário a ser editado */
function startEdit(id) {
    /* Procura o funcionário com esse ID */
    const employee = employees.find((item) => item.id === id)
    
    /* Se não encontrar, retorna */
    if (!employee) return

    /* Preenche o campo de nome com o nome atual */
    document.getElementById('name-func').value = employee.name
    
    /* Preenche o campo de email com o email atual */
    document.getElementById('email-func').value = employee.email
    
    /* Muda o texto do botão para "Atualizar Funcionário" */
    document.querySelector('#employee-form button[type="submit"]').textContent = 'Atualizar Funcionário'
    
    /* Mostra o botão cancelar */
    document.getElementById('cancel-edit').style.display = 'inline-block'
    
    /* Salva o ID para saber qual está sendo editado */
    editId = id
}

/* ===== FUNÇÃO PARA DELETAR ===== */

/* Função para deletar (remover) um funcionário
   id = o ID do funcionário a ser removido */
function deleteEmployee(id) {
    /* Pede confirmação ao usuário */
    const confirmed = window.confirm('Deseja realmente excluir este funcionário?')
    
    /* Se o usuário clicou em "Cancelar", não faz nada */
    if (!confirmed) return

    /* Remove o funcionário do array
       filter mantém apenas os que NÃO têm esse ID */
    employees = employees.filter((item) => item.id !== id)
    
    /* Salva a lista atualizada */
    saveEmployees()
    
    /* Atualiza a tabela */
    renderEmployees()
    
    /* Se estava editando esse funcionário, reseta o formulário */
    if (editId === id) {
        resetForm()
    }
}

/* ===== FUNÇÃO PRINCIPAL DO FORMULÁRIO ===== */

/* Função que é chamada quando o formulário é enviado
   event = o evento do formulário */
function handleSubmit(event) {
    /* Previne o recarregamento da página */
    event.preventDefault()

    /* Pega os elementos dos inputs */
    const nameInput = document.getElementById('name-func')
    const emailInput = document.getElementById('email-func')
    
    /* Pega os valores e remove espaços extras */
    const name = nameInput.value.trim()
    const email = emailInput.value.trim()

    /* Valida se os campos não estão vazios */
    if (!name || !email) {
        alert('Preencha todos os campos.')
        return
    }

    /* Se já tem um ID de edição, está ATUALIZANDO */
    if (editId) {
        /* Mapeia (percorre) o array e atualiza o funcionário */
        employees = employees.map((item) => {
            /* Se é o funcionário sendo editado */
            if (item.id === editId) {
                /* Retorna o mesmo objeto, mas com dados novos */
                return { ...item, name, email }
            }
            /* Caso contrário, retorna o funcionário sem mudanças */
            return item
        })
    } else {
        /* Se não tem ID de edição, está CRIANDO novo funcionário */
        employees.push({
            id: Date.now().toString(), /* Cria um ID único baseado no tempo */
            name,
            email,
        })
    }

    /* Salva o array atualizado */
    saveEmployees()
    
    /* Atualiza a tabela */
    renderEmployees()
    
    /* Limpa o formulário */
    resetForm()
}

/* ===== FUNÇÃO DE INICIALIZAÇÃO ===== */

/* Função que inicializa o CRUD quando a página carrega */
function initCrud() {
    /* Carrega os funcionários salvos */
    employees = getEmployees()
    
    /* Mostra os funcionários na tabela */
    renderEmployees()

    /* Pega o formulário */
    const form = document.getElementById('employee-form')
    if (form) {
        /* Adiciona evento para quando o formulário for enviado */
        form.addEventListener('submit', handleSubmit)
    }

    /* Pega o botão de cancelar */
    const cancelButton = document.getElementById('cancel-edit')
    if (cancelButton) {
        /* Adiciona evento para quando clicar em cancelar */
        cancelButton.addEventListener('click', resetForm)
    }
}

/* Verifica se a página ainda está carregando */
if (document.readyState === 'loading') {
    /* Se estiver carregando, aguarda terminar */
    document.addEventListener('DOMContentLoaded', initCrud)
} else {
    /* Se já carregou, inicializa direto */
    initCrud()
}

