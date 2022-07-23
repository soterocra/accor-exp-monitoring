const telegram = require('./telegram');
const repository = require('./accorrepository');

const admPassword = process.env.ADMIN_INFO_PASS + ' ';

exports.admInfo = async (text) => {
    if (!this.admValidate(text)) return;

    let chats = await repository.searchAllDataExperiencesChats();

    for (let chatId of chats) {
        await sendMessage(chatId, event.message.text.replace(admPassword, ''));
    }
}

exports.admValidate = (text) => {
    return text.startsWith(admPassword);
} 