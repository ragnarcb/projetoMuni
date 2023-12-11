// script.js

document.addEventListener('DOMContentLoaded', function () {
    // Esta função será chamada quando o DOM estiver completamente carregado
    const form = document.querySelector('form');

    form.addEventListener('submit', function (event) {
        event.preventDefault(); // Impede o envio padrão do formulário

        // Obtenha os valores do formulário
        const descricao = document.getElementById('descricao').value;
        const dtreceita = document.getElementById('dtreceita').value;
        const valor = document.getElementById('valor').value;

        // Construa o objeto de dados para a requisição POST
        const data = {
            descricao: descricao,
            dtreceita: dtreceita,
            valor: valor
        };

        // Realize a requisição POST usando fetch
        fetch('http://localhost:8080/senhor_financas_war/rest/receita/cadastrar', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao cadastrar receita.');
            }
            return response.json();
        })
        .then(data => {
            console.log('Receita cadastrada com sucesso:', data);
            // Adicione qualquer lógica adicional que você deseja executar após o cadastro bem-sucedido
        })
        .catch(error => {
            console.error('Erro ao cadastrar receita:', error.message);
        });
    });
});
