const scraper = require('./accorscraping');
const telegram = require('./telegram');
const welcomeIntention = require('./welcomeintention');
const searchIntention = require('./searchintention');
const donateIntention = require('./donateintention');
const admInfoIntention = require('./adminfointention');

exports.handler = async (event) => {

    console.log("Inicio: " + new Date());
    console.log("Evento:");
    console.log(JSON.stringify(event));

    if (event.hasOwnProperty("source") && event.source === 'aws.events') {
        await scraper.scrapeExperiences();
    } else if (event.message.text === '/start') {
        await welcomeIntention.welcomeMessage(event);
    } else if (event.message.text === '/buscar') {
        await searchIntention.search();
    } else if (event.message.text === '/doar') {
        await donateIntention.donateMessage(event);
    } else if (admInfoIntention.admValidate(event.message.text)) {
        await admInfoIntention.admInfo(event.message.text)
    } else {
        await telegram.sendMessage(event.message.chat.id, 'Ainda não sei responder esse tipo de mensagem, me desculpe! \u{1F605}');
    }

    const response = {
        statusCode: 200,
        body: JSON.stringify({}),
    };

    console.log("Fim: " + new Date());

    return response;
};




