document.addEventListener('DOMContentLoaded', function () {
    const urlParams = new URLSearchParams(window.location.search);
    const descricao = urlParams.get('descricao');
    const dataReceita = urlParams.get('dataReceita');
    const valor = urlParams.get('valor');

    const descricaoInput = document.getElementById('descricao');
    const dtreceitaInput = document.getElementById('dtreceita');
    const valorInput = document.getElementById('valor');

    if (descricaoInput && dtreceitaInput && valorInput) {
        descricaoInput.value = descricao || '';
        dtreceitaInput.value = formatarDataInput(dataReceita) || '';
        valorInput.value = valor || '';
    }
});

function formatarDataInput(data) {
    const date = new Date(data);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
}
