const axios = require('axios');
const webdriver = require("selenium-webdriver");
const { Builder, By } = webdriver;

require("chromedriver");
chrome = require('selenium-webdriver/chrome');

var config = {
  method: 'post',
  url: 'https://api.telegram.org/bot5378824272:AAGdgAsMYQQM685Ndx9kU3Q9GBON_IWR9o0/sendMessage',
  headers: { 
    'Content-Type': 'application/json'
  },
  data : {}
};

const AWS = require('aws-sdk');
AWS.config.update({region: 'us-east-1'})

const deviceFarmArn = 'arn:aws:devicefarm:us-west-2:315904066994:testgrid-project:88dad987-e450-4f34-a4ac-9affcb426571';

let devicefarm = new AWS.DeviceFarm({ region: "us-west-2" });
let ddb = new AWS.DynamoDB({apiVersion: '2012-08-10'});

let dynamoExperiencesParams = {
    TableName: 'accor-experiences',
    // Item: {
    //     'link': {S: ''},
    //     'name': {S: ''},
    //     'price': {N: ''}
    // }
}

let dynamoExperiencesChatsParams = {
    TableName: 'accor-experiences-chats',
}

const url = 'https://limitlessexperiences.accor.com';
const variations = [
    {
        'variationName': 'Entreterimento',
        'variationUrl': '/passion-vibes',
    },
    {
        'variationName': 'Viagem',
        'variationUrl': '/passion-travel',
    },
    {
        'variationName': 'Gastronomia',
        'variationUrl': '/accor-taste',
    },
    {
        'variationName': 'Esportes',
        'variationUrl': '/passion-sports',
    }
]
const urlParams = '?place_of_event=16224';

class ExperienceAccor {
    constructor(name, link, price) {
        this.name = name;
        this.link = link;
        this.price = price;
    }
}

exports.handler = async (event) => {

    console.log("Inicio: " + new Date());
    console.log("Evento:");
    console.log(JSON.stringify(event));

    let result = new Set();
    let resultTmp = new Set();
    let resultLink = new Set();


    if (event.hasOwnProperty("source") && event.source === 'aws.events')  {
    
        const testGridUrlResult = await devicefarm.createTestGridUrl({
            projectArn: deviceFarmArn,
            expiresInSeconds: 300
        }).promise();
    
        let chromeOptions = new chrome.Options()
                .headless()
                .windowSize({ width: 1986, height: 1392 })
                .excludeSwitches('enable-logging')
                .addArguments("--incognito")
                .addArguments("--disable-dev-shm-usage")
                .addArguments("--no-sandbox")
                .addArguments("--remote-debugging-port=9222")
                .addArguments("--disable-gpu")
                .addArguments("--disable-dev-tools");

        let driver = await new Builder()
            .usingServer(testGridUrlResult.url)
            .withCapabilities(chromeOptions.toCapabilities())
            // .setChromeOptions(new chrome.Options()
            //     .headless()
            //     .windowSize({ width: 1986, height: 1392 })
            //     .excludeSwitches('enable-logging')
            //     .addArguments("--incognito")
            // )
            .build();
        
        driver.manage().window().setRect({width: 1986, height: 1392});
    
        let firstTime = true;
    
        for (let variation of variations) {
    
            const urlFinal = url + variation.variationUrl + urlParams;
            await driver.get(urlFinal);
            driver.wait(function() {
                return driver.executeScript('return document.readyState').then(function(readyState) {
                  return readyState === 'complete';
                });
            });
    
            if (firstTime) {
                await driver.findElement(By.xpath("//*[@id='accept-cookie']")).click()
                driver.wait(function() {
                    return driver.executeScript('return document.readyState').then(function(readyState) {
                      return readyState === 'complete';
                    });
                });
                await driver.findElement(By.xpath("//button[@id=\'switcher-delivery-country-trigger-nav\']/span")).click();
                let delivery = await driver.findElement(By.xpath('//*[@id="switcher-delivery-country-trigger-nav"]/span')).getAttribute('class');
                if (delivery != 'view-BR') {
                    await driver.findElement(By.xpath("//a[contains(text(),\'Br\')]")).click()
                    driver.wait(function() {
                        return driver.executeScript('return document.readyState').then(function(readyState) {
                          return readyState === 'complete';
                        });
                    });
                }
                await driver.findElement(By.xpath("//button[@id='switcher-store-trigger-language']/span")).click();
                await driver.findElement(By.css("#dropdown-language .view-accor_pt_br .lang-name")).click();
                driver.wait(function() {
                    return driver.executeScript('return document.readyState').then(function(readyState) {
                      return readyState === 'complete';
                    });
                });
                await driver.findElement(By.css("#switcher-store-trigger > span")).click();
                await driver.findElement(By.xpath("//a[contains(text(),'América do Sul')]")).click();
                driver.wait(function() {
                    return driver.executeScript('return document.readyState').then(function(readyState) {
                      return readyState === 'complete';
                    });
                });
                await driver.findElement(By.css("#switcher-store-trigger-language > span")).click();
                await driver.findElement(By.xpath("//span[contains(.,'Portuguese (Brazilian)')]")).click();
                driver.wait(function() {
                    return driver.executeScript('return document.readyState').then(function(readyState) {
                      return readyState === 'complete';
                    });
                });
                await driver.get(urlFinal);
                driver.wait(function() {
                    return driver.executeScript('return document.readyState').then(function(readyState) {
                      return readyState === 'complete';
                    });
                });
                
                firstTime = false;
            }
    
            await driver.executeScript(function() {
                class ExperienceAccor {
                    constructor(name, link, price) {
                        this.name = name;
                        this.link = link;
                        this.price = price;
                    }
                }
    
                let experiences = [];
    
                let descriptionsAndLinks = document.getElementsByClassName('product-item-link');
                for (let item of descriptionsAndLinks) {
                    const eventAccor = new ExperienceAccor(item.innerHTML.trim(), item.href)    
                    experiences.push(eventAccor);
                }                
    
                let prices = document.getElementsByClassName("price-wrapper");
                for (let i = 0; i < prices.length; i++) {
                    experiences[i].price = prices[i].getAttribute("data-price-amount");
                }
                
                return experiences;
            }).then(function (returnedValue) {
                returnedValue.forEach(v => resultTmp.add(v));
                returnedValue.forEach(v => resultLink.add(v.link));
            });
    
        }
    
        await driver.quit();
        
        for (let link of resultLink) {
            result.add(new ExperienceAccor(null, link));
        }
        
        for (let r of result) {
            for (let tmp of resultTmp) {
                if (tmp.link === r.link) {
                    r.name = tmp.name;
                    r.price = tmp.price;
                    break;
                }
            }
        }
        
        const tabletResult = await searchAllDataExperiences();
    
        const resultList = Array.from(result);
    
        const accorAdditions = resultList.filter(x => {
            let tableResultContainsItem = false;
            for (let item of tabletResult) {
                if (x.link === item.link) tableResultContainsItem = true;
            }
            return !tableResultContainsItem;
        });
        
        const accorRemovals = tabletResult.filter(x => {
            let resultListContainsItem = false;
            for (let item of resultList) {
                if (x.link === item.link) resultListContainsItem = true;
            }
            return !resultListContainsItem;
        });
        
        
        // console.log('Dentro da Base');
        // console.log(tabletResult);
    
        // console.log('Encontrados');
        // console.log(resultList);
    
        // console.log('Novos');
        // console.log(accorAdditions);
    
        // console.log('Removidos');
        // console.log(accorRemovals);
    
        // let text = "Foram encontradas modificações na lista de Experiências da Accor. Segue: + \n\n";
    
        for (let item of accorRemovals) {
            console.log("deletando..." + item);
            await deleteDataExperiences(item);
        }
        
        for (let item of accorAdditions) {
            console.log("inserindo..." + item);
            await insertDataExperiences(item);
        }
    
        let chats = await searchAllDataExperiencesChats();
        
        let promiseResponses = [];
        for (let chatId of chats) {
            let promiseChat = new Promise((resolve) => {
                for (let item of accorRemovals) {
                    let text = "<b>Remoção</b> \u{274C} \u{274C} \u{274C} \n\n<b>Nome do Evento:</b> " + item.name + "\n\n<b>Pontos Necessários:</b> \u{1F4B0} " + item.price + "\n\n<b>Link:</b> \u{1F310} " + item.link + "\n";
                    sendMessage(chatId, text);
                }
                
                for (let item of accorAdditions) {
                    let text = "<b>Adição</b> \u{2705} \u{2705} \u{2705} \n\n<b>Nome do Evento:</b> " + item.name + "\n\n<b>Pontos Necessários:</b> \u{1F4B0} " + item.price + "\n\n<b>Link:</b> \u{1F310} " + item.link + "\n";
                    sendMessage(chatId, text);
                }
            });
            
            promiseResponses.push(promiseChat);
            
        }
        await Promise.all(promiseResponses);

    } else if (event.message.text === '/start') {
        
        let username = '';
        if (event.message.hasOwnProperty("from") && event.message.from.hasOwnProperty("username")) username = event.message.from.username;
        
        let firstName = '';
        if (event.message.hasOwnProperty("from") && event.message.from.hasOwnProperty("first_name")) firstName = event.message.from.first_name;
        
        let lastName = '';
        if (event.message.hasOwnProperty("from") && event.message.from.hasOwnProperty("last_name")) lastName = event.message.from.last_name;
        
        await insertDataExperiencesChat(event.message.chat.id, username, firstName, lastName);
        await sendMessage(event.message.chat.id, '\u{2705} A partir de agora você será notificado por aqui de todas as experiências All Accor!\n\nSua identificação na base do bot é: ' + event.message.chat.id);
        await sendMessage(event.message.chat.id, 'Olá! \u{1F603}\n\nEste bot foi criado com o intuito de pesquisar automaticamente as esperiências <b>All Accor</b> que foram inseridas ou removidas no site. Através dele você saberá das modificações em no máximo 5 minutos após serem feitas. \u{1F389}\n\nDesenvolvi buscando ajudar a todos que se interessem pelas experiências All e com o tempo podemos adicionar mais e mais funcionalidades.\n\n Se você gostou do bot, te convido a \u{1F4B0} <b>contribuir com os custos</b> \u{1F4B8} de manutenção do projeto. Caso se sinta à vontade, deixarei abaixo minha chave aleatória Pix:');
        await sendMessage(event.message.chat.id, '83fa7c98-7eae-4cb6-a15e-bbcdd8fa3792');
        await sendMessage(event.message.chat.id, '\u{26A0} Para tirar todas as suas dúvidas, não deixe de assistir o vídeo:\n\nhttps://youtu.be/jJ6PgjIVnQM');
    } else if (event.message.text === '/buscar') {
        let tableData = await searchAllDataExperiences();
        for (let item of tableData) {
            let text = "<b>Resultado da Busca</b> \u{1F50D} \u{1F50D} \u{1F50D} \n\n<b>Nome do Evento:</b> " + item.name + "\n\n<b>Pontos Necessários:</b> \u{1F4B0} " + item.price + "\n\n<b>Link:</b> \u{1F310} " + item.link + "\n";
            await sendMessage(event.message.chat.id, text);
        }
    } else if (event.message.text === '/doar') {
        await sendMessage(event.message.chat.id, 'Se você gostou do bot, te convido a \u{1F4B0} <b>contribuir com os custos</b> \u{1F4B8} de manutenção do projeto. Caso se sinta à vontade, deixarei abaixo minha chave aleatória Pix:');
        await sendMessage(event.message.chat.id, '83fa7c98-7eae-4cb6-a15e-bbcdd8fa3792');
        await sendMessage(event.message.chat.id, 'Obrigado! \u{1F64F}'); 
    } else if (event.message.text === '/informacao') {
        let chats = await searchAllDataExperiencesChats();
        
        let promiseResponses = [];
        for (let chatId of chats) {
            await sendMessage(chatId, 'Olá Pessoal, passando primeiramente para agradecer aos doadores do último mês, graças a eles foi possível manter nosso Bot funcionando nos últimos dias. São eles:\n\n\u{2764} Jair\n\u{2764} Denis\n\u{2764} Lais\n\u{2764} José\n\u{2764} Fábio\n\u{2764} Carla\n\nNosso bot custa em torno de R$ 350,00 por mês. Está acima da expectativa e por isso durante essa semana ele vai ter uma frequência de verificação de 20min ao invés de 5min. Utilizarei essa semana para melhorar a performance de custos e voltar para a frequência de 5min.\n\nFico feliz em saber que já tiveram resgates graças ao BOT.\n\nAproveito o espaço para dizer que sou Arquiteto de Software e Desenvolvedor, trabalho no ramo a vários anos, estou aberto para projetos freelance de quaisquer tamanhos, pode me chamar no Telegram @soterocra ou no Linkedin: https://www.linkedin.com/in/soterocra/');
        }
    } else {
        await sendMessage(event.message.chat.id, 'Ainda não sei responder esse tipo de mensagem, me desculpe! \u{1F605}');
    }

    const response = {
        statusCode: 200,
        body: JSON.stringify(Array.from(result)),
    };


    console.log("Fim: " + new Date());

    return response;
};


let sendMessage = async (chat_id, text) => {
    await new Promise(resolve => setTimeout(resolve, 200));

    let data = JSON.stringify({
      "chat_id": chat_id,
      "text": text,
      "parse_mode": "html"
    });
    
    config.data = data;
    
    return axios(config)
        .then(function (response) {
            // console.log(JSON.stringify(response.data));
        })
        .catch(function (error) {
            console.log(error);
        });
}

let searchAllDataExperiences = async () => {
    const tabletResult = [];
    let items = [];
    
    do {
        items =  await ddb.scan(dynamoExperiencesParams).promise();
        items.Items.forEach((item) => {
            const eventAccor = new ExperienceAccor(item.name.S, item.link.S, item.price.N);
            tabletResult.push(eventAccor);
        });
        dynamoExperiencesParams.ExclusiveStartKey  = items.LastEvaluatedKey;
    } while(typeof items.LastEvaluatedKey !== "undefined");
    
    return tabletResult;
}

let insertDataExperiences = async (eventAccor) => {
    
    let params = {...dynamoExperiencesParams};
    
    params["Item"] = {
        'link' : {S: eventAccor.link},
        'name' : {S: eventAccor.name},
        'price' : {N: eventAccor.price}
    }

    await ddb.putItem(params, function(err, data) {
      if (err) {
        console.log("Error", err);
      } else {
        console.log("Success", data);
      }
    });
}

let deleteDataExperiences = async (eventAccor) => {
    
    let params = {...dynamoExperiencesParams};
    
    params["Key"] = {
        'link' : {S: eventAccor.link}
    }

    return await ddb.deleteItem(params, function(err, data) {
        if (err) {
            console.log("Error", err);
        } else {
            console.log("Success", data);
        }
    });
}



let searchAllDataExperiencesChats = async () => {
    const tabletResult = [];
    let items = [];
    
    do {
        items = await ddb.scan(dynamoExperiencesChatsParams).promise();
        items.Items.forEach((item) => {
            let chatId = item.chat_id.N;
            tabletResult.push(chatId);
        });
        dynamoExperiencesChatsParams.ExclusiveStartKey  = items.LastEvaluatedKey;
    } while(typeof items.LastEvaluatedKey !== "undefined");
    
    return tabletResult;
}

let insertDataExperiencesChat = async (chatId, username, firstName, last) => {
    
    let params = {...dynamoExperiencesChatsParams};
    params["Item"] = {
        'chat_id' : {N: chatId.toString()},
        'username' : {S: username},
        'fisrt_name' : {S: firstName},
        'last_name' : {S: last},
    }

    await ddb.putItem(params, function(err, data) {
      if (err) {
        console.log("Error", err);
      } else {
        console.log("Success", data);
      }
    });
}

