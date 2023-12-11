async function cadastrarPessoa() {
    let form = document.getElementById('cadastroForm');

    // Função para formatar a data no formato desejado
    function formatarDataParaJSON(data) {
        const date = new Date(data);
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        const hours = String(date.getHours()).padStart(2, '0');
        const minutes = String(date.getMinutes()).padStart(2, '0');
        return `${year}-${month}-${day}T${hours}:${minutes}`;
    }

    // Obtém a data do campo e a formata corretamente
    const dataNascimento = formatarDataParaJSON(document.querySelector('#dtnascimento').value);

    let options = {
        method: "POST",
        headers: {"Content-type": "application/json"},
        body: JSON.stringify({
            id: 0,
            nome: document.querySelector('#nome').value,
            cpf: document.querySelector('#cpf').value,
            email: document.querySelector('#email').value,
            dataNascimento: dataNascimento, // Utiliza a data formatada corretamente
            login: document.querySelector('#login').value,
            senha: document.querySelector('#senha').value
        })
    };

    try {
        const resultado = await fetch('http://localhost:8080/senhor_financas_war_exploded/rest/usuario/cadastrar', options);

        if (!resultado.ok) {
            throw new Error(`HTTP error! Status: ${resultado.status}`);
        }

        const respostaTexto = await resultado.text();
        console.log("Resposta do servidor (texto):", respostaTexto);

        const pessoa = JSON.parse(respostaTexto);
        console.log("Pessoa recebida:", pessoa);

        if (pessoa.idUsuario !== 0) {
            alert("Cadastro realizado com sucesso.");
            window.location.href = '/senhor_financas_war_exploded/';
        } else {
            alert("Houve um problema no cadastro da pessoa.");
        }

        form.reset();
    } catch (error) {
        console.error("Erro durante a requisição:", error.message);
        alert("Erro durante a requisição. Consulte o console para obter mais detalhes.");
    }
}

document.getElementById('cadastroForm').addEventListener('submit', function (event) {
    event.preventDefault();
    cadastrarPessoa();
});
