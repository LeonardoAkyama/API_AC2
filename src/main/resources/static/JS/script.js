const API = "http://localhost:8081";



async function cadastrarSetor() {

    const nome = document.getElementById("nomeSetor").value;

    const response = await fetch(`${API}/setores`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            nome: nome
        })
    });

    if (!response.ok) {
        alert("Erro ao cadastrar setor");
        return;
    }

    alert("Setor cadastrado!");
}



async function listarSetores() {

    const response = await fetch(`${API}/setores`);

    const setores = await response.json();

    let html = "";

    setores.forEach(setor => {

        html += `
            <div class="card">
                <h3>${setor.nome}</h3>
                <h4>Funcionários:</h4>
        `;

        if (setor.funcionarios && setor.funcionarios.length > 0) {

            setor.funcionarios.forEach(funcionario => {
                html += `<p>${funcionario.nome}</p>`;
            });

        } else {

            html += `<p>Nenhum funcionário no setor.</p>`;
        }

        html += `</div>`;
    });

    document.getElementById("listaSetores").innerHTML = html;
}



async function cadastrarFuncionario() {

    const nome = document.getElementById("nomeFuncionario").value;
    const setorId = document.getElementById("setorFuncionario").value;

    const response = await fetch(`${API}/funcionarios/${setorId}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            nome: nome
        })
    });

    if (!response.ok) {
        alert("Erro ao cadastrar funcionário");
        return;
    }

    alert("Funcionário cadastrado!");
}



async function cadastrarProjeto() {

    const descricao = document.getElementById("descricaoProjeto").value;
    const dataInicio = document.getElementById("dataInicio").value;
    const dataFim = document.getElementById("dataFim").value;

    const response = await fetch(`${API}/projetos`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            descricao: descricao,
            dataInicio: dataInicio,
            dataFim: dataFim
        })
    });

    if (!response.ok) {
        alert("Erro ao cadastrar projeto");
        return;
    }

    alert("Projeto cadastrado!");
}


async function buscarProjeto() {

    const id = document.getElementById("buscarProjetoId").value;

    const response = await fetch(`${API}/projetos/${id}/funcionario`);

    if (!response.ok) {
        alert("Projeto não encontrado");
        return;
    }

    const projeto = await response.json();

    let html = `
        <div class="card">
            <h3>${projeto.descricao}</h3>
            <p>Data Início: ${projeto.dataInicio}</p>
            <p>Data Fim: ${projeto.dataFim}</p>
            <h4>Funcionários:</h4>
    `;

    if (projeto.funcionarios && projeto.funcionarios.length > 0) {

        projeto.funcionarios.forEach(funcionario => {
            html += `<p>${funcionario.nome}</p>`;
        });

    } else {

        html += `<p>Nenhum funcionário vinculado.</p>`;
    }

    html += `</div>`;

    document.getElementById("resultadoProjeto").innerHTML = html;
}



async function vincularFuncionario() {

    const projetoId = document.getElementById("projetoIdVinculo").value;
    const funcionarioId = document.getElementById("funcionarioIdVinculo").value;

    const response = await fetch(`${API}/projetos/${projetoId}/funcionario/${funcionarioId}`, {
        method: "PUT"
    });

    if (!response.ok) {
        alert("Erro ao vincular funcionário");
        return;
    }

    alert("Funcionário vinculado!");
}



async function buscarProjetosFuncionario() {

    const funcionarioId = document.getElementById("funcionarioBuscaProjetos").value;

    const response = await fetch(`${API}/projetos/funcionario/${funcionarioId}`);

    const projetos = await response.json();

    let html = "";

    projetos.forEach(projeto => {

        html += `
            <div class="card">
                <h3>${projeto.descricao}</h3>
                <p>${projeto.dataInicio} até ${projeto.dataFim}</p>
            </div>
        `;
    });

    document.getElementById("resultadoFuncionarioProjetos").innerHTML = html;
}


async function listarProjetos() {

    const response = await fetch(`${API}/projetos`);

    const projetos = await response.json();

    let html = "";

    projetos.forEach(projeto => {

        html += `
            <div class="card">
                <h3>${projeto.descricao}</h3>
                <p>${projeto.dataInicio} até ${projeto.dataFim}</p>
            </div>
        `;
    });

    document.getElementById("listaProjetos").innerHTML = html;
}