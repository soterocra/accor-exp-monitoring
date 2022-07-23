const telegram = require('./telegram');
const repository = require('./accorrepository');

exports.welcomeMessage = async (event) => {

    let username = '';
    if (event.message.hasOwnProperty("from") && event.message.from.hasOwnProperty("username")) username = event.message.from.username;

    let firstName = '';
    if (event.message.hasOwnProperty("from") && event.message.from.hasOwnProperty("first_name")) firstName = event.message.from.first_name;

    let lastName = '';
    if (event.message.hasOwnProperty("from") && event.message.from.hasOwnProperty("last_name")) lastName = event.message.from.last_name;

    await repository.insertDataExperiencesChat(event.message.chat.id, username, firstName, lastName);
    await telegram.sendMessage(event.message.chat.id, '\u{2705} A partir de agora você será notificado por aqui de todas as experiências All Accor!\n\nSua identificação na base do bot é: ' + event.message.chat.id);
    await telegram.sendMessage(event.message.chat.id, 'Olá! \u{1F603}\n\nEste bot foi criado com o intuito de pesquisar automaticamente as esperiências <b>All Accor</b> que foram inseridas ou removidas no site. Através dele você saberá das modificações em no máximo 5 minutos após serem feitas. \u{1F389}\n\nDesenvolvi buscando ajudar a todos que se interessem pelas experiências All e com o tempo podemos adicionar mais e mais funcionalidades.\n\n Se você gostou do bot, te convido a \u{1F4B0} <b>contribuir com os custos</b> \u{1F4B8} de manutenção do projeto. Caso se sinta à vontade, deixarei abaixo minha chave aleatória Pix:');
    await telegram.sendMessage(event.message.chat.id, '83fa7c98-7eae-4cb6-a15e-bbcdd8fa3792');
    await telegram.sendMessage(event.message.chat.id, '\u{26A0} Para tirar todas as suas dúvidas, não deixe de assistir o vídeo:\n\nhttps://youtu.be/jJ6PgjIVnQM');
}