const telegram = require('./telegram');

exports.donateMessage = async (event) => {
    await telegram.sendMessage(event.message.chat.id, 'Se você gostou do bot, te convido a \u{1F4B0} <b>contribuir com os custos</b> \u{1F4B8} de manutenção do projeto. Caso se sinta à vontade, deixarei abaixo minha chave aleatória Pix:');
    await telegram.sendMessage(event.message.chat.id, '83fa7c98-7eae-4cb6-a15e-bbcdd8fa3792');
    await telegram.sendMessage(event.message.chat.id, 'Obrigado! \u{1F64F}');
}