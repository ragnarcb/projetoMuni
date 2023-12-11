document.addEventListener('DOMContentLoaded', () => {
    const formReceitas = document.getElementById('formReceitas');

    if (formReceitas) {
        formReceitas.addEventListener('submit', async function (event) {
            event.preventDefault();

            const cadastrarBotao = document.getElementById('cadastrarBotao');

            if (cadastrarBotao) {
                cadastrarBotao.disabled = true;

                try {
                    await cadastrarNovaReceita();
                } finally {
                    cadastrarBotao.disabled = false;
                }
            }
        });
    }

    const buscarBotao = document.getElementById('buscarBotao');
    if (buscarBotao) {
        buscarBotao.addEventListener('click', function (event) {
            event.preventDefault();
            atualizarTabelaReceitas();
        });
    }

    // Adiciona um evento de clique ao botão "Cadastrar"
    const cadastrarBotao = document.getElementById('cadastrarBotao');
    if (cadastrarBotao) {
        cadastrarBotao.addEventListener('click', function (event) {
            event.preventDefault(); // Evita o comportamento padrão do formulário
            cadastrarNovaReceita(); // Chama a função para cadastrar a receita
        });
    }

    // Adiciona um evento de clique aos botões de editar
    const botoesEditar = document.querySelectorAll('.botao-editar');
    botoesEditar.forEach((botao) => {
        botao.addEventListener('click', function (event) {
            event.preventDefault();

            // Encontrar a linha da tabela
            const linha = event.target.closest('tr');

            // Certifique-se de que a linha existe
            if (linha) {
                // Encontrar o elemento com o ID na primeira célula da linha
                const idReceitaElement = linha.querySelector('td[data-id]');

                // Certifique-se de que o elemento com o ID existe
                if (idReceitaElement) {
                    const idReceita = idReceitaElement.dataset.id;

                    // Redirecionar para a página de edição com o ID
                    console.log('ID da Receita:', idReceita);
                    window.location.href = `./editarReceita.html?id=${idReceita}`;
                } else {
                    console.error('Elemento de ID não encontrado na linha da tabela.');
                }
            } else {
                console.error('Linha da tabela não encontrada.');
            }
        });
    });

    atualizarTabelaReceitas();
});

async function atualizarTabelaReceitas() {
    const tabelaReceitasBody = document.getElementById('tbodyReceitas');

    if (!tabelaReceitasBody) {
        console.error('Elemento com ID "tbodyReceitas" não encontrado.');
        return;
    }

    try {
        // Recupera o ID do usuário logado da sessionStorage
        const idUsuarioLogado = parseInt(sessionStorage.getItem('idUsuarioLogado'), 10);

        if (isNaN(idUsuarioLogado)) {
            console.error('ID do usuário logado não encontrado ou inválido.');
            return;
        }

        const response = await fetch(
            `http://localhost:8080/senhor_financas_war_exploded/rest/receita/listar/${idUsuarioLogado}`
        );

        if (!response.ok) {
            throw new Error(`Erro na resposta do servidor: ${response.status} ${response.statusText}`);
        }

        const data = await response.json();

        // Limpar o conteúdo apenas se houver dados
        if (data.length > 0) {
            tabelaReceitasBody.innerHTML = '';

            data.forEach((receita) => {
                let tr = tabelaReceitasBody.insertRow();

                let td_id = tr.insertCell();
                let td_descricao = tr.insertCell();
                let td_data = tr.insertCell();
                let td_valor = tr.insertCell();
                let td_acoes = tr.insertCell();

                td_id.innerText = receita.idReceita;
                td_descricao.innerText = receita.descricao;
                td_data.innerText = formatarData(receita.dataReceita);
                td_valor.innerText = formatarValor(receita.valor);

                let botoesAcoes = document.createElement('div');
                botoesAcoes.className = 'botoes-acoes';

                let botaoEditar = document.createElement('button');
                botaoEditar.type = 'button';
                botaoEditar.className = 'botao-editar';
                botaoEditar.textContent = 'Editar';
                botaoEditar.addEventListener('click', () => editarReceita(receita));
                botoesAcoes.appendChild(botaoEditar);

                let botaoExcluir = document.createElement('button');
                botaoExcluir.type = 'button';
                botaoExcluir.className = 'botao-excluir';
                botaoExcluir.textContent = 'Excluir';
                botaoExcluir.addEventListener('click', () => excluirReceita(receita));
                botoesAcoes.appendChild(botaoExcluir);

                td_acoes.appendChild(botoesAcoes);
            });
        }
    } catch (error) {
        console.error('Erro ao obter a lista de receitas', error);
    }
}

function formatarData(data) {
    const date = new Date(data);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    return `${year}-${month}-${day}T${hours}:${minutes}`;
}

function formatarValor(valor) {
    return valor.toFixed(2);
}

function editarReceita(receita) {
    console.log('Editar receita:', receita);
    // Implemente a lógica de edição da receita
}

async function excluirReceita(receita) {
    if (!receita || !receita.idReceita) {
        console.error('ID da receita não encontrado');
        return;
    }

    const idReceita = receita.idReceita;

    let options = {
        method: 'DELETE',
        headers: {'Content-type': 'application/json'},
    };

    try {
        const response = await fetch(
            `http://localhost:8080/senhor_financas_war_exploded/rest/receita/deletar`,
            {
                ...options,
                body: JSON.stringify({idReceita: idReceita}),
            }
        );

        if (!response.ok) {
            throw new Error(
                `Erro na resposta do servidor: ${response.status} ${response.statusText}`
            );
        }

        console.log('Receita excluída com sucesso');
        atualizarTabelaReceitas();
    } catch (error) {
        console.error('Erro ao excluir a receita', error);
    }
}

function cadastrarNovaReceita() {
    const descricaoInput = document.getElementById('descricao');
    const dtreceitaInput = document.getElementById('dtreceita');
    const valorInput = document.getElementById('valor');

    if (!descricaoInput || !dtreceitaInput || !valorInput) {
        console.error('Erro: Elemento de input não encontrado.');
        return;
    }

    // Recupera o ID do usuário logado da sessionStorage
    const idUsuarioLogado = parseInt(sessionStorage.getItem('idUsuarioLogado'), 10);

    if (isNaN(idUsuarioLogado)) {
        console.error('ID do usuário logado não encontrado ou inválido.');
        return;
    }

    const novaReceita = {
        descricao: descricaoInput.value,
        dataReceita: new Date(dtreceitaInput.value).toISOString(),
        valor: parseFloat(valorInput.value),
        idUsuario: idUsuarioLogado
    };

    console.log('Nova receita:', novaReceita);

    return fetch('http://localhost:8080/senhor_financas_war_exploded/rest/receita/cadastrar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(novaReceita),
    })
        .then(async (response) => {
            if (!response.ok) {
                throw new Error(`Erro na resposta do servidor: ${response.status} ${response.statusText}`);
            }
            const data = await response.json();
            console.log('Nova receita cadastrada com sucesso:', data);
            atualizarTabelaReceitas();
        })
        .catch((error) => {
            console.error('Erro ao cadastrar a nova receita:', error);
        });
}

async function editarReceita(receita) {
    if (!receita || !receita.idReceita) {
        console.error('ID da receita não encontrado');
        return;
    }

    const idReceita = receita.idReceita;
    const descricao = receita.descricao;
    const dataReceita = receita.dataReceita;
    const valor = receita.valor;

    window.location.href = `./editarReceitas.html?id=${idReceita}&descricao=${descricao}&dataReceita=${dataReceita}&valor=${valor}`;
}

