const telegram = require('./telegram')
const repository = require('./accorrepository');

exports.search = async (event) => {
    let tableData = await repository.searchAllDataExperiences();
    for (let item of tableData) {
        let text = "<b>Resultado da Busca</b> \u{1F50D} \u{1F50D} \u{1F50D} \n\n<b>Nome do Evento:</b> " + item.name + "\n\n<b>Pontos Necessários:</b> \u{1F4B0} " + item.price + "\n\n<b>Link:</b> \u{1F310} " + item.link + "\n";
        await telegram.sendMessage(event.message.chat.id, text);
    }
}