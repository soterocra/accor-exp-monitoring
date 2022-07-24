const schedule = require('node-schedule');

const express = require('express');

const scraper = require('./accorscraping');
const telegram = require('./telegram');
const welcomeIntention = require('./welcomeintention');
const searchIntention = require('./searchintention');
const donateIntention = require('./donateintention');
const admInfoIntention = require('./adminfointention');

const job = schedule.scheduleJob('*/2 * * * *', function(){
    console.log("Initializing Scrape. " + new Date());
    scraper.scrapeExperiences();
});


const app = express();
const port = 4444;

app.use(express.json()) 
app.listen(port, () => {
    console.log(`accor-exp-monitoring listening on port ${port}`)
})


app.post('/telegram-webhook', (req, res) => {
    this.handler(req.body);
    res.send({});
})


exports.handler = async (event) => {

    console.log("Inicio: " + new Date());
    console.log("Evento:");
    console.log(JSON.stringify(event));

    if (event.message.text === '/start') {
        console.log("Welcoment intention identified.");
        await welcomeIntention.welcomeMessage(event);
    } else if (event.message.text === '/buscar') {
        console.log("Search intention identified");
        await searchIntention.search(event);
    } else if (event.message.text === '/doar') {
        console.log("Donate intention identified");
        await donateIntention.donateMessage(event);
    } else if (admInfoIntention.admValidate(event.message.text)) {
        console.log("Adm info intention identified");
        await admInfoIntention.admInfo(event.message.text)
    } else {
        await telegram.sendMessage(event.message.chat.id, 'Ainda não sei responder esse tipo de mensagem, me desculpe! \u{1F605}');
    }

    console.log("Fim: " + new Date());

};




