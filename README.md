# accor-exp-monitoring Project

Para usar o projeto, setar as seguintes variáveis de ambiente:

- AWS_ACCESS_KEY_ID
- AWS_SECRET_ACCESS_KEY
- AWS_REGION


A seguinte linha nos properties da aplicação:

```shell
quarkus.class-loading.reloadable-artifacts=software.amazon.awssdk:dynamodb-enhanced
```

Resolve um bug no dev mode do quarkus ao qual não recarrega corretamente a dependência do dynamo. Em versões mais recentes provavelmente poderá ser eliminada.

Objeto antigo do banco para testes:

```json
[
	{
		"link": "https://limitlessexperiences.accor.com/all-psg-where-it-all-started-quarto-tematico-do-jogador-marquinhos",
		"name": "Where it ALL started - Quarto Temático do Jogador Marquinhos (PSG) no Pullman SP Vila Olímpia",
		"price": "24000"
	},
	{
		"link": "https://limitlessexperiences.accor.com/peca-de-teatro-homens-no-div-rio-de-janeiro-sabado-11-06-2-ingressos",
		"name": "Peça de Teatro 'Homens no Divã' (Rio de Janeiro) (sábado, 25/06) 2 ingressos",
		"price": "3000"
	},
	{
		"link": "https://limitlessexperiences.accor.com/experienciasall_qceviche_01",
		"name": "[SP] Degustação QCeviche! + aula de Pisco Sour (p/2) - ibis Styles Barra Funda",
		"price": "3000"
	},
	{
		"link": "https://limitlessexperiences.accor.com/sp-pullman-sp-guarulhos-airport-clube-base",
		"name": "[SP] Pullman SP Guarulhos Airport: Day Use Clube Base (p/1)",
		"price": "2200"
	},
	{
		"link": "https://limitlessexperiences.accor.com/sp-ibis-paulinia-kart-e-diaria-p-2",
		"name": "[SP] Ibis Paulinia: Kart e diária com café da manhã (p/2)",
		"price": "7000"
	},
	{
		"link": "https://limitlessexperiences.accor.com/sp-novotel-s-o-paulo-morumbi-dinner-on-the-bridge-p-2",
		"name": "[SP] Novotel São Paulo Morumbi: Dinner on the Bridge (p/2)",
		"price": "3100"
	},
	{
		"link": "https://limitlessexperiences.accor.com/experienciasall_skydayuse_nl",
		"name": "[SP] SKY Rooftop: Day use com piscina (p/2) - Novotel Leme",
		"price": "2000"
	},
	{
		"link": "https://limitlessexperiences.accor.com/sp-pullman-sp-guarulhos-airport-signature-base-steakhouse-dish-p-1",
		"name": "[SP] Pullman SP Guarulhos Airport: Prato Signature Base Steakhouse (p/1)",
		"price": "2500"
	},
	{
		"link": "https://limitlessexperiences.accor.com/sc-novotel-florianopolis-spa-day-coffe-break-optional",
		"name": "[SC] Novotel Florianópolis: SPA Day (coffee break opcional)",
		"price": "5600"
	},
	{
		"link": "https://limitlessexperiences.accor.com/sp-pullman-sp-ibirapuera-birthday-party-p-2",
		"name": "[SP] Pullman SP Ibirapuera: Birthday Party! (p/2)",
		"price": "18100"
	},
	{
		"link": "https://limitlessexperiences.accor.com/peca-de-teatro-homens-no-div-rio-de-janeiro-domingo-12-06-2-ingressos",
		"name": "Peça de Teatro 'Homens no Divã' (Rio de Janeiro) (domingo, 26/06) 2 ingressos",
		"price": "3000"
	},
	{
		"link": "https://limitlessexperiences.accor.com/sp-novotel-s-o-jose-dos-campos-picnic-ecobag-p-2",
		"name": "[SP] Novotel São José dos Campos: Picnic na Ecobag (p/2)",
		"price": "7400"
	},
	{
		"link": "https://limitlessexperiences.accor.com/experienciasall_qceviche_03",
		"name": "[PR] Degustação QCeviche! + aula de Pisco Sour (p/2) - Novotel Batel, Curitiba",
		"price": "3000"
	},
	{
		"link": "https://limitlessexperiences.accor.com/experienciasall_qceviche_02",
		"name": "[SP] Degustação QCeviche! + aula de ceviche (p/2) - ibis Styles Faria Lima",
		"price": "3000"
	},
	{
		"link": "https://limitlessexperiences.accor.com/experienciasall_chaboulangerie_grand",
		"name": "[SP] Chá da Tarde Premium na Boulangerie (p/2) - Grand Mercure Ibirapuera",
		"price": "2000"
	},
	{
		"link": "https://limitlessexperiences.accor.com/sp-pullman-sp-ibirapuera-love-stay-p-2",
		"name": "[SP] Pullman SP Ibirapuera: Love Stay (p/2)",
		"price": "21700"
	},
	{
		"link": "https://limitlessexperiences.accor.com/peca-de-teatro-homens-no-div-rio-de-janeiro-sexta-10-06-2-ingressos",
		"name": "Peça de Teatro 'Homens no Divã' (Rio de Janeiro) (sexta, 24/06) 2 ingressos",
		"price": "3000"
	},
	{
		"link": "https://limitlessexperiences.accor.com/rj-fairmont-rj-copacabana-jantar-ao-luar",
		"name": "[RJ] Fairmont RJ Copacabana: Jantar ao luar",
		"price": "39000"
	},
	{
		"link": "https://limitlessexperiences.accor.com/all-psg-where-it-all-started-pacote-quarto-tematico-jogador-marquinhos-jantar",
		"name": "Where it ALL started - Pacote: Quarto Temático Jogador Marquinhos (PSG) no Pullman SP Vila Olímpia + Jantar",
		"price": "35000"
	},
	{
		"link": "https://limitlessexperiences.accor.com/pacote-vive-l-amour-mercure-curitiba-7-de-setembro-jun-a-dez-2022",
		"name": "PACOTE \"VIVE L'AMOUR\" - Mercure Curitiba 7 de Setembro (JUN a DEZ 2022)",
		"price": "15000"
	},
	{
		"link": "https://limitlessexperiences.accor.com/sp-novotel-s-o-paulo-morumbi-day-use-p-2",
		"name": "[SP] Novotel São Paulo Morumbi: Day use (p/2)",
		"price": "2400"
	}
]
```



This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./gradlew build
```
It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./gradlew build -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./gradlew build -Dquarkus.package.type=native
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/accor-exp-monitoring-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling.

## Related Guides

- RESTEasy Reactive ([guide](https://quarkus.io/guides/resteasy-reactive)): A JAX-RS implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
