<div align="center">
  <br><br>
  <img src="/Assets/currency_logo.svg" alt="Currency_logo" width="260"></img>
  <br>
  <h4>Versione attuale: 1.1.3</h4><br>
  <p>
    <a href="#intro">Introduzione</a>&nbsp•
    <a href="#realizzazione">Realizzazione</a>&nbsp•
    <a href="#guida">Guida per l'uso</a>&nbsp•
    <a href="https://michdev7.github.io/Progetto-OOP/">Javadoc</a>&nbsp•
    <a href="#riconoscimenti">Riconoscimenti</a> 
  </p>
</div><br><br>

## Introduzione <a name="intro"></a>
Currency Trend API è un servizio web di <code>REST API</code> che offre informazioni riguardo i valori delle quotazioni delle valute più importanti con relative statistiche. Il software, in questione, è parte di un progetto realizzato per l'esame del corso di Programmazione ad Oggetti 2019/2020 dell'Università Politecnica delle Marche, con lo scopo di apprendere tutte le nozioni della Programmazione ad Oggetti per mezzo del linguaggio Java. 

**La fonte dei dati distribuiti è https://fxmarketapi.com/**.

Di seguito è possibile leggere la consegna:
> Si gestisca il banner(schermo) di una banca, creato apposta per tenere traccia dei valori delle valute più importanti. Il banner deve aggiornarsi ogni 10 minuti, visualizzando vecchio valore e nuovo valore della valuta, la valuta che ha perso più valore e la valuta che ha guadagnato più valore. Inoltre lo schermo è touch, quindi l'utente della banca potrà clickare su una valuta e visualizzare diverse infomazioni riguardanti la valuta scelta.
Elaborare statistiche sull'andamento del valore di una valuta, media e varianza delle stesse. Statistiche sulle valute che hanno perso/guadagnato più valore nelle ultime settimane e nell'ultimo mese.
 
## Realizzazione <a name="realizzazione"></a>
> Vai al paragrafo : <a href="#modellazione">Modellazione dati e OOP</a> | <a href="#uml">Diagrammi UML</a>
### Tecnolgie di sviluppo
Per realizzare il servizio di API, viene utilizzato il framework per Java **Spring** con relative dipendenze per la creazione di un web server *Apache Tomcat* in grado di comunicare ed interscambiare i dati tramite le richieste <code>HTTP</code> inoltrate dall'utente. Per l'elaborazione dei dati, invece è utilizzato il framework  **FasterXML/Jackson** per effettuare il parsing dei dati ottenuti.
Il software è stato scritto completamente in Java, utilizzando come editor di sviluppo Visual Studio Code fornito di estensione **Java for VSCode** e come software per il versioning del codice, **Git**.

### Modellazione dati e OOP<a name="modellazione"><a/>
La progettazione del software ha previsto dei punti chiave su cui basare l'intera modellazione dei dati e del codice scritto; in particolare, oltre ad avere tenuto una rigida attenzione all'uso delle risorse fisiche e alla velocità di esecuzione(fondamentali per un REST web service), il punto chiave principale è stato l'utilizzo di quasi tutti i concetti della programmazione ad oggetti e all'ottimizzazione delle strutture dati, nonché il vero obiettivo del progetto.

La modellazione dei dati, quindi, si è basata sull'aspetto concettuale che essi rappresentano. Dovendo gestire dati che hanno una relazione con coppie di valute, si è pensato di creare una classe<code>CurrencyPair</code> per generalizzare questa relazione che successivamente viene estesa da altre sottoclassi utilizzate per strutture dati di vario genere come le quotazioni in tempo reale<code>LiveQuote</code>, le quotazioni storiche<code>HistoricalQuote</code> e i <code>Report</code> statistici delle coppia di valute. A causa di questa differenza ma allo stesso tempo somiglianza tra i diversi tipi di dato trattati dal software, è stata colta l'occasione per la creazione di classi e interfacce con tipi generici <code>T</code> per definire alcuni comportamenti generali dei dati trattati, utili per il loro ordinamento, confronto ed elaborazione, dimostrando la flessibilità e i vantaggi della programmazione ad oggetti.

### Diagrammi UML <a name="uml">

#### Diagramma dei casi d'uso
<img src="/uml/useCaseDiagram.png" alt="useCaseDiagram" width="100%"></img>

#### Diagramma di sequenza per ottenere le quotazioni aggiornati
Il diagramma seguente fa riferimento alle chiamate <code> **GET** /live/currency</code>, <code> **GET** /live/quotes</code>  per ottenere serie di quotazioni aggiornate ogni 10 minuti, eventualmente filtrate secondo oppurtuni parametri, con dati statistici.  Per saperne di più, vai a <a href="#endpoints">endpoints</a>.

<img src="/uml/LiveSequenceDiagram.png" alt="liveSequenceDiagram" width="100%"></img>

#### Diagramma di sequenza per ottenere una serie di quotazioni storiche.
Il diagramma seguente fa riferimento alle chiamate <code> **POST** /historical/currency</code>, <code> **POST** /historical/quotes</code>  per ottenere una serie di quotazioni storiche riferite ad un periodo, eventualmente filtrate secondo oppurtuni parametri. Per saperne di più, vai a <a href="#endpoints">endpoints</a>.

<img src="/uml/HistoricalSequenceDiagram.png" alt="historicalSequenceDiagram" width="100%"></img>

#### Diagramma di sequenza per elaborare le statistiche
Il diagramma seguente fa riferimento alle chiamate <code> **GET** /statistics/lastweeks</code>, <code> **GET** statistics/lastmonth</code> e <code> **POST** statistics/currency</code>  per ottenere una serie di dati statistici, di un determinato periodo, per i campi *high*, *close*, *open*, *low*, selezionabili.  Per saperne di più, vai a <a href="#endpoints">endpoints</a>.

<img src="/uml/StatisticsSequenceDiagram.png" alt="statsSequenceDiagram" width="100%"></img>

#### Diagramma delle classi del package Controller
<img src="/uml/ControllerPackageDiagram.png" alt="controllerDiagram" width="100%"></img>

#### Diagramma delle classi del package Core
<img src="/uml/CorePackageDiagram.png" alt="coreDiagram" width="100%"></img>

#### Diagrammi delle classi del package Exception
<img src="/uml/ExceptionPackageDiagram.png" alt="exceptionDiagram" width="100%"></img>

#### Diagrammi delle classi del package Model
<img src="/uml/ModelPackageDiagram.png" alt="statsDiagram" width="100%"></img>

#### Diagrammi delle classi del package DataSeries in Model
<img src="/uml/DataSeriesPackageDiagram.png" alt="dataSeriesDiagram" width="100%"></img>

#### Diagrammi delle classi del package Service
<img src="/uml/ServicePackageDiagram.png" alt="serviceDiagram" width="100%"></img>

#### Diagrammi delle classi del package Adapter in Utils
<img src="/uml/DataAdapterPackageDiagram.png" alt="adapterDiagram" width="100%"></img>

#### Diagrammi delle classi del package Filter in Utils
<img src="/uml/FilterPackageDiagram.png" alt="filterDiagram" width="100%"></img>

#### Diagrammi delle classi del package Parser in Utils
<img src="/uml/ParserPackageDiagram.png" alt="parserDiagram" width="100%"></img>

#### Diagrammi delle classi del package Stats in Utils
<img src="/uml/StatisticsPackageDiagram.png" alt="statsDiagram" width="100%"></img>

#### Diagrammi delle classi del package Time in Utils
<img src="/uml/TimePackageDiagram.png" alt="timeDiagram" width="100%"></img>

#### Diagrammi delle classi del package WebClient
<img src="/uml/WebClientPackageDiagram.png" alt="webClientDiagram" width="100%"></img>

## Guida per l'uso<a name="guida"></a>
> Vai al paragrafo: <a href="#endpoints">Endpoints</a>
### Compilazione e primo avvio 
Il progetto non dispone di un file <code>.jar</code> pronto per l'uso, quindi sarà necessario compilare autonomamente il software per il suo utilizzo procedendo per queste fasi:

*  Scaricare il progetto dall'apposito pulsante su Github, in altro a destra
*  Tramite terminale, recarsi nella cartella del progetto scaricato contenente l'eseguibile di Maven ed eseguire il comando     <code>mvn clean package</code>.

Se la compilazione avrà buon esito, sarà possibile trovare il file <code>.jar</code> da eseguire, all'interno della cartella <code>target</code>. A questo punto, sempre da terminale, digitare il comando <code>java -jar /target/nomedelsoftware.jar</code>.
Il web server si avvierà e sarà in ascolto su <code>http://localhost:8080/</code>.

> Per cambiare l'access token è necessario modificare il file *config.json* e ricompilare il software.

### Endpoints <a name="endpoints"></a>
Gli endpoints del web service permettono di ottenere i dati richiesti con precisioni ed affidabilità, pertanto è utile leggere la documentazione prima del loro utilizzo. Il web service mette a disposizione tre rotte principali su cui ottenere dati di diverso tipo:


#### Endpoints multi-purpose
Di seguito sono elencati i possibili endpoints per ottenere dati di vario genere:

* **<code>GET /metadata</code>**
Questa rotta restituisce i metadati relativi alle serie di dati elaborati.

* **<code>GET /available</code>**
Tramite questa rotta è possibile ottenere la lista delle valute disponibili, con relativo codice ISO 4217 da utilizzare nelle chiamate sulle altre rotte.

#### Endpoints per le quotazioni aggiornate
Di seguito sono elencati i possibili endpoints per ottenere serie di di quotazioni aggiornate ogni 10 minuti, con dati statistici. I valori da inserire nei parametri <code>base</code> e <code>quote</code>, sono i codici ISO 4217 delle valute disponibili.

* **<code>GET /live/overview</code>**
Tramite questa rotta è possibile ottenere una serie delle quotazioni di tutte le coppie di valute disponibili, con dati statistici.

* **<code>GET /live/currencies</code>**
Tramite questa rotta è possibile ottenere una serie delle quotazioni di coppie di valute che hanno come valuta base il valore inserito nel parametro richiesto <code>base</code>.

* **<code>GET /live/quotes</code>**
Tramite questa rotta è possibile ottenere una serie delle quotazioni di coppie di valute che hanno come valuta base il valore inserito nel parametro richiesto<code>base</code> e come valute quotate il valore del parametro richiesto <code>quote</code>.

*Per il parametro <code>quote</code> è possibile aggiungere più valori seperati da una virgola, per esempio: CAD,EUR,USD*

#### Endpoints per le serie storiche
Di seguito sono elencati i possibili endpoints per ottenere serie storiche di quotazioni di coppie di valute di un determinato periodo di tempo, specificato nel body delle richieste. I valori da inserire nei parametri <code>base</code> e <code>quote</code>, sono i codici ISO 4217 delle valute disponibili.

* **<code>POST /historical/currency</code>**
Tramite questa richiesta è possibile ottenere serie storiche di quotazioni di coppie di valute che hanno come valuta base il valore inserito nel parametro richiest <code>base</code>. Per il body, visualizza la fine del paragrafo.

* **<code>POST /historical/quote</code>**
Tramite questa rotta è possibile ottenere una serie storica delle quotazioni di coppie di valute che hanno come valuta base il valore inserito nel parametro richiesto<code>base</code> e come valute quotate il valore del parametro richiesto <code>quote</code>.

*Per il parametro <code>quote</code> è possibile aggiungere più valori seperati da una virgola, per esempio: CAD,EUR,USD*

>Il body richiesto deve rispettare la seguente struttura: 
<code>{ "start_date" : "yyyy-MM-dd", "end_date" : "yyyy-MM-dd"}</code>.

#### Endpoints per le statistiche
Di seguito sono elencati i possibili endpoints per ottenere le statistiche sulle quotazioni. I valori da inserire nei parametri <code>base</code> e <code>quote</code>, sono i codici ISO 4217 delle valute disponibili.

* **<code>GET /statistics/lastweeks</code>**
Tramite questa richiesta è possibile ottenere una serie di statistiche sulle quotazioni, di tutte le coppie di valute, del periodo corrispondente alle due settimane precedenti alla data attuale.

* **<code>GET /statistics/lastmonth</code>**
Tramite questa richiesta è possibile ottenere una serie di statistiche sulle quotazioni, di tutte le coppie di valute, del periodo corrispondente al mese precedente alla data attuale.

* **<code>POST /statistics/currency</code>**
Tramite questa richiesta è possibile ottenere una serie di statistiche su un tipo di valore delle quotazioni di coppie di valute selezionate con gli stessi parametri <code>base</code> e <code>quote</code> delle chiamate per le serie storiche.

Una lista dei tipi di campo disponibili è presente nei metadati.

Per visualizzare la struttura del body, consulta la sezione relativa alle chiamate per le serie storiche.

## Riconoscimenti <a name="riconoscimenti"></a>
Tutto il materiale presente in questa repository è stato realizzato interamente da <a href="https://www.linkedin.com/in/michele-bevilacqua-732611183/">Michele Bevilacqua</a>.
