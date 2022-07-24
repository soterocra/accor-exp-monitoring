const webdriver = require("selenium-webdriver");
const { Builder, By } = webdriver;

const chrome = require('selenium-webdriver/chrome');

const telegram = require('./telegram')
const repository = require('./accorrepository');

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

exports.scrapeExperiences = async () => {

    let result = new Set();
    let resultTmp = new Set();
    let resultLink = new Set();

    let chromeOptions = new chrome.Options()
        .windowSize({ width: 1986, height: 1392 })
        .excludeSwitches('enable-logging')
        // .addArguments("--incognito")
        .addArguments("--disable-gpu")


    let driver = await new Builder()
        .usingServer('https://3d12-179-104-41-108.sa.ngrok.io')
        .setChromeOptions(chromeOptions)
        .withCapabilities(webdriver.Capabilities.chrome())
        .build();


    //open site to set some configs
    await openSite(driver, url);
    await acceptSiteCookies(driver);
    await setSiteConfigs(driver);

    for (let variation of variations) {

        const urlFinal = url + variation.variationUrl + urlParams;

        await openSite(driver, urlFinal);

        await driver.executeScript(function () {
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
            console.log("Resultado da variação:")
            console.log(JSON.stringify(variation));
            console.log(JSON.stringify(returnedValue));
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

    const tabletResult = await repository.searchAllDataExperiences();

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

    for (let item of accorRemovals) {
        console.log("deletando..." + item);
        await repository.deleteDataExperiences(item);
    }

    for (let item of accorAdditions) {
        console.log("inserindo..." + item);
        await repository.insertDataExperiences(item);
    }

    let chats = await repository.searchAllDataExperiencesChats();

    let promiseResponses = [];
    for (let chatId of chats) {
        let promiseChat = new Promise((resolve) => {
            for (let item of accorRemovals) {
                let text = "<b>Remoção</b> \u{274C} \u{274C} \u{274C} \n\n<b>Nome do Evento:</b> " + item.name + "\n\n<b>Pontos Necessários:</b> \u{1F4B0} " + item.price + "\n\n<b>Link:</b> \u{1F310} " + item.link + "\n";
                telegram.sendMessage(chatId, text);
            }

            for (let item of accorAdditions) {
                let text = "<b>Adição</b> \u{2705} \u{2705} \u{2705} \n\n<b>Nome do Evento:</b> " + item.name + "\n\n<b>Pontos Necessários:</b> \u{1F4B0} " + item.price + "\n\n<b>Link:</b> \u{1F310} " + item.link + "\n";
                telegram.sendMessage(chatId, text);
            }
        });

        promiseResponses.push(promiseChat);

    }
    await Promise.all(promiseResponses);
}

let setSiteConfigs = async (driver) => {
    await setDeliveryToFrance(driver);
    await setDeliveryToBrazil(driver);
    await setSitePortugueseMethod(driver);
    await setSiteLatinAmerica(driver);
    await setDeliveryToBrazil(driver);
    await setSitePortugueseMethod(driver);
    await setSiteLatinAmerica(driver);
}

let openSite = async (driver, url) => {
    await driver.get(url);
    driver.wait(function () {
        return driver.executeScript('return document.readyState').then(function (readyState) {
            return readyState === 'complete';
        });
    });
    await new Promise(r => setTimeout(r, 1000));
}

let acceptSiteCookies = async (driver) => {
    await driver.findElement(By.xpath("//*[@id='accept-cookie']")).click()
    driver.wait(function () {
        return driver.executeScript('return document.readyState').then(function (readyState) {
            return readyState === 'complete';
        });
    });
    await new Promise(r => setTimeout(r, 1000));
}

let setEventLocationBrazil = async (driver, url) => {
    
    try {
        await driver.findElement(By.xpath("//a[@href='" + url + "']")).click()
    } catch (e) {
        console.log("Ou já está no Brazil ou não há eventos no Brazil para essa categoria.")
    }
        
    driver.wait(function () {
        return driver.executeScript('return document.readyState').then(function (readyState) {
            return readyState === 'complete';
        });
    });
    await new Promise(r => setTimeout(r, 1000));

}

let setDeliveryToBrazil = async (driver) => {
    await driver.findElement(By.xpath("//button[@id=\'switcher-delivery-country-trigger-nav\']/span")).click();
    await new Promise(r => setTimeout(r, 1000));
    try {
        await driver.findElement(By.xpath("//a[contains(text(),\'Br\')]")).click()
    } catch (e) {
        console.log("Provavelmente já está no Brazil, seguindo o fluxo.")
    }
        
    driver.wait(function () {
        return driver.executeScript('return document.readyState').then(function (readyState) {
            return readyState === 'complete';
        });
    });
    await new Promise(r => setTimeout(r, 1000));
}

let setDeliveryToFrance = async (driver) => {
    await driver.findElement(By.xpath("//button[@id=\'switcher-delivery-country-trigger-nav\']/span")).click();
    await new Promise(r => setTimeout(r, 1000));
    try {
        await driver.findElement(By.xpath("//a[contains(text(),\'Fra\')]")).click()
    } catch (e) {
        console.log("Provavelmente já está na Franca, seguindo o fluxo.")
    }
        
    driver.wait(function () {
        return driver.executeScript('return document.readyState').then(function (readyState) {
            return readyState === 'complete';
        });
    });
    await new Promise(r => setTimeout(r, 1000));
}

let setSitePortugueseMethod = async (driver) => {
    await driver.findElement(By.xpath("//button[@id='switcher-store-trigger-language']/span")).click();
    await new Promise(r => setTimeout(r, 1000));
    try {
        await driver.findElement(By.css("#dropdown-language .view-accor_pt_br .lang-name")).click();
    } catch (e) {
        console.log("Provavelmente já está em Portugues (metodo 1). Vamos tentar o segundo metodo.")
    }
    driver.wait(function () {
        return driver.executeScript('return document.readyState').then(function (readyState) {
            return readyState === 'complete';
        });
    });
    await new Promise(r => setTimeout(r, 1000));
    try {
        await driver.findElement(By.xpath("//span[contains(.,'Portuguese (Brazilian)')]")).click();
    } catch (e) {
        console.log("Provavelmente já está em Portugues (metodo 2), seguindo o fluxo.")
    }
    driver.wait(function () {
        return driver.executeScript('return document.readyState').then(function (readyState) {
            return readyState === 'complete';
        });
    });
    await new Promise(r => setTimeout(r, 1000));
}

let setSiteLatinAmerica = async (driver) => {
    await driver.findElement(By.css("#switcher-store-trigger > span")).click();
    await new Promise(r => setTimeout(r, 1000));
    try {
        await driver.findElement(By.xpath("//a[contains(text(),'América do Sul')]")).click();
    } catch (e) {
        console.log("Provavelmente já está na America do Sul, seguindo o fluxo.")
    }
    driver.wait(function () {
        return driver.executeScript('return document.readyState').then(function (readyState) {
            return readyState === 'complete';
        });
    });
    await new Promise(r => setTimeout(r, 1000));
    try {
        await driver.findElement(By.xpath("//a[contains(text(),'South America')]")).click();
    } catch (e) {
        console.log("Provavelmente já está na America do Sul, seguindo o fluxo.")
    }
    driver.wait(function () {
        return driver.executeScript('return document.readyState').then(function (readyState) {
            return readyState === 'complete';
        });
    });
    await new Promise(r => setTimeout(r, 2000));
}