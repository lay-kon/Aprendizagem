
/* Credenciais do administrador*/
const adminCredentials = {
    email: 'admin@empresa.com',
    password: 'admin123',
}


/* Função login*/
function login(event) {

    event.preventDefault()

    const email = document.getElementById('email').value

    const password = document.getElementById('password').value

    if (email === adminCredentials.email && password === adminCredentials.password) {

        sessionStorage.setItem('isLoggedIn', 'true')
        
        /* Redireciona para a página do dashboard */
        window.location.replace('dashboard.html')
        return
    }

    alert('Credenciais inválidas. Use admin@empresa.com / admin123')
}


/* Função logout*/
function logout() {
    /* Remove a informação de login da sessão */
    sessionStorage.removeItem('isLoggedIn')

    window.location.replace('login.html')
}



/* Função para verificar se o usuário está logado
   Protege as páginas para não deixar entrar sem login */
function requireAuth() {
    /* Verifica se a página atual é a de login */
    const isLoginPage = window.location.pathname.endsWith('login.html')
    
    /* Verifica se há permissão de login salva na sessão */
    const isLoggedIn = sessionStorage.getItem('isLoggedIn') === 'true'

    /* Se estamos na página de login */
    if (isLoginPage) {
        /* E o usuário já está logado, redireciona para dashboard */
        if (isLoggedIn) {
            window.location.replace('dashboard.html')
        }
        /* Se não está logado, deixa na página de login normalmente */
        return
    }

    /* Se NÃO está na página de login */
    if (!isLoggedIn) {
        /* E o usuário não está logado, redireciona para login */
        window.location.replace('login.html')
    }
    /* Se está logado, deixa acessar normalmente */
}



/* Verifica se a página ainda está carregando */
if (document.readyState === 'loading') {
    /* Se ainda está carregando, aguarda o evento DOMContentLoaded */
    document.addEventListener('DOMContentLoaded', () => {
        /* Verifica a autenticação */
        requireAuth()
        
        /* Pega o formulário de login */
        const loginForm = document.getElementById('login-form')
        if (loginForm) {
            /* Adiciona o evento de envio do formulário */
            loginForm.addEventListener('submit', login)
        }
    })
} else {
    /* Se já carregou, executa direto */
    requireAuth()
    
    const loginForm = document.getElementById('login-form')
    if (loginForm) {
        loginForm.addEventListener('submit', login)
    }
}