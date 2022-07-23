const AWS = require('aws-sdk');

AWS.config.update({ region: 'us-east-1' })

const ddb = new AWS.DynamoDB({ apiVersion: '2012-08-10' });

const dynamoExperiencesParams = {
    TableName: 'accor-experiences-dev',
}

const dynamoExperiencesChatsParams = {
    TableName: 'accor-experiences-chats-dev',
}

class ExperienceAccor {
    constructor(name, link, price) {
        this.name = name;
        this.link = link;
        this.price = price;
    }
}

exports.searchAllDataExperiences = async () => {
    const tabletResult = [];
    let items = [];

    do {
        items = await ddb.scan(dynamoExperiencesParams).promise();
        items.Items.forEach((item) => {
            const eventAccor = new ExperienceAccor(item.name.S, item.link.S, item.price.N);
            tabletResult.push(eventAccor);
        });
        dynamoExperiencesParams.ExclusiveStartKey = items.LastEvaluatedKey;
    } while (typeof items.LastEvaluatedKey !== "undefined");

    return tabletResult;
}

exports.insertDataExperiences = async (eventAccor) => {

    let params = { ...dynamoExperiencesParams };

    params["Item"] = {
        'link': { S: eventAccor.link },
        'name': { S: eventAccor.name },
        'price': { N: eventAccor.price }
    }

    await ddb.putItem(params, function (err, data) {
        if (err) {
            console.log("Error", err);
        } else {
            console.log("Success", data);
        }
    });
}

exports.deleteDataExperiences = async (eventAccor) => {

    let params = { ...dynamoExperiencesParams };

    params["Key"] = {
        'link': { S: eventAccor.link }
    }

    return await ddb.deleteItem(params, function (err, data) {
        if (err) {
            console.log("Error", err);
        } else {
            console.log("Success", data);
        }
    });
}

exports.searchAllDataExperiencesChats = async () => {
    const tabletResult = [];
    let items = [];

    do {
        items = await ddb.scan(dynamoExperiencesChatsParams).promise();
        items.Items.forEach((item) => {
            let chatId = item.chat_id.N;
            tabletResult.push(chatId);
        });
        dynamoExperiencesChatsParams.ExclusiveStartKey = items.LastEvaluatedKey;
    } while (typeof items.LastEvaluatedKey !== "undefined");

    return tabletResult;
}

exports.insertDataExperiencesChat = async (chatId, username, firstName, last) => {

    let params = { ...dynamoExperiencesChatsParams };
    params["Item"] = {
        'chat_id': { N: chatId.toString() },
        'username': { S: username },
        'fisrt_name': { S: firstName },
        'last_name': { S: last },
    }

    await ddb.putItem(params, function (err, data) {
        if (err) {
            console.log("Error", err);
        } else {
            console.log("Success", JSON.stringify(data));
        }
    });
}
