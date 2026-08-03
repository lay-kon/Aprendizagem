let funcionarios = []
let editId = null

function criarFuncionarios() {
    const listaFuncionarios = document.getElementById('employee-list')
    listaFuncionarios.innerHTML = ''

    if (funcionarios.length === 0) {
        listaFuncionarios.innerHTML = '<tr><td colspan="3" class="empty">Nenhum funcionário cadastrado.</td></tr>'
        return
    }

    funcionarios.forEach((funcionario) => {
        const row = document.createElement('tr')
        row.innerHTML = `
            <td>${funcionario.name}</td>
            <td>${funcionario.email}</td>
            <td class="actions-row">
                <button type="button" class="small-btn" onclick="startEdit('${funcionario.id}')">Editar</button>
                <button type="button" class="small-btn small-btn-danger" onclick="deletarFuncionario('${funcionario.id}')">Excluir</button>
            </td>
        `
        listaFuncionarios.appendChild(row)
    })
}

function resetForm() {
    document.getElementById('employee-form').reset()
    document.getElementById('cancel-edit').style.display = 'none'
    document.querySelector('#employee-form button[type="submit"]').textContent = 'Salvar Funcionário'
    editId = null
}

function startEdit(id) {
    const funcionario = funcionarios.find((item) => item.id === id)
    if (!funcionario) return

    document.getElementById('name-func').value = funcionario.name
    document.getElementById('email-func').value = funcionario.email
    document.querySelector('#employee-form button[type="submit"]').textContent = 'Atualizar Funcionário'
    document.getElementById('cancel-edit').style.display = 'inline-block'
    editId = id
}

function deletarFuncionario(id) {
    if (!window.confirm('Deseja realmente excluir este funcionário?')) return

    funcionarios = funcionarios.filter((item) => item.id !== id)
    criarFuncionarios()
    if (editId === id) resetForm()
}

function handleSubmit(event) {
    event.preventDefault()

    const name = document.getElementById('name-func').value.trim()
    const email = document.getElementById('email-func').value.trim()

    if (!name || !email) {
        alert('Preencha todos os campos.')
        return
    }

    if (editId) {
        funcionarios = funcionarios.map((item) => 
            item.id === editId ? { ...item, name, email } : item
        )
    } else {
        funcionarios.push({
            id: Date.now().toString(),
            name,
            email,
        })
    }

    criarFuncionarios()
    resetForm()
}

criarFuncionarios()

const form = document.getElementById('employee-form')
if (form) form.addEventListener('submit', handleSubmit)

const cancelButton = document.getElementById('cancel-edit')
if (cancelButton) cancelButton.addEventListener('click', resetForm)