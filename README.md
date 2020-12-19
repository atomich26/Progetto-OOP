<div align="center">
  <br><br>
  <img src="/Assets/currency_logo.svg" alt="Currency_logo" width="260"></img>
  <br>
  <h4>Versione attuale: 1.1.0</h4><br>
  <p>
    <a href="#intro">Introduzione</a>&nbsp•
    <a href="#realizzazione">Realizzazione</a>&nbsp•
    <a href="#guida">Guida per l'uso</a>&nbsp•
    <a href="https://michdev7.github.io/Progetto-OOP/">Javadoc</a>&nbsp•
    <a href="#riconoscimenti">Riconoscimenti</a> 
  </p>
</div><br><br>

## Introduzione <a name="intro"></a>

## Realizzazione <a name="realizzazione"></a>
> Vai al paragrafo : <a href="#modellazione">Modellazione dati e OOP</a> | <a href="#uml">Diagrammi UML</a>
### Sviluppo
La realizzazione del software ha necessitato di 
### Modellazione dati e OOP<a name="modellazione"><a/>
La modellazione dei dati si è basata sull'aspetto concettuale che essi rappresentano. Dovendo gestire dei dati che hanno una relazione con coppie di valute, si è pensato di creare un oggetto <code>CurrencyPair</code> per far riferimento a tutti gli oggetti sopra citati.
### Diagrammi UML <a name="uml">

#### Diagramma dei casi d'uso
<img src="/uml/useCaseDiagram.png" alt="useCaseDiagram" width="1000"></img>

#### Diagramma di sequenza per ottenere le quotazioni aggiornati
Il diagramma seguente fa riferimento alle chiamate <code> **GET** /live/currency</code>, <code> **GET** /live/quotes</code>  per ottenere serie di quotazioni aggiornate ogni 10 minuti, eventualmente filtrate secondo oppurtuni parametri, con dati statistici.  Per saperne di più, vai a <a href="#endpoints">endpoints</a>
<img src="/uml/liveDiagram.png" alt="LiveQuoteDiagram" width="1000"></img>

#### Diagramma di sequenza per ottenere una serie di quotazioni storiche.
Il diagramma seguente fa riferimento alle chiamate <code> **GET** /historical/currency</code>, <code> **GET** /historical/quotes</code>  per ottenere una serie di quotazioni storiche riferite ad un periodo, eventualmente filtrate secono oppurtuni parametri. Per saperne di più, vai a <a href="#endpoints">endpoints</a>
<img src="/uml/historicalDiagram.png" alt="HistoricalQuoteDiagram" width="1000"></img>

#### Diagramma di sequenza per elaborare le statistiche
<img src="/uml/statsDiagram.png" alt="statsDiagram" width="1000"></img>

## Guida per l'uso<a name="guida"></a>
> Vai al paragrafo: <a href="#endpoints">Endpoints</a>
### Compilazione e primo avvio 
La repository contiene tutto il necessario per compilare una propria versione del software in modo autonomo. Per fare ciò, è consigliabile avere delle conoscenze di base in MAVEN.
### Endpoints <a name="endpoints"></a>

## Riconoscimenti <a name="riconoscimenti"></a>
Questo progetto è stato realizzato da <a href="https://www.linkedin.com/in/michele-bevilacqua-732611183/">Michele Bevilacqua</a>.
