const axios = require('axios');

var config = {
    method: 'post',
    url: 'https://api.telegram.org/bot5378824272:AAGdgAsMYQQM685Ndx9kU3Q9GBON_IWR9o0/sendMessage',
    headers: {
        'Content-Type': 'application/json'
    },
    data: {}
};

exports.sendMessage = async (chat_id, text) => {
    await new Promise(resolve => setTimeout(resolve, 200));

    let data = JSON.stringify({
        "chat_id": chat_id,
        "text": text,
        "parse_mode": "html"
    });

    config.data = data;

    return axios(config)
        .then(function (response) {
            console.log(JSON.stringify(response.data));
        })
        .catch(function (error) {
            console.log(error);
        });
}