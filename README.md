# ShopStantly - Webshop
EAI-Projekt HS2018 | Vlado Repic | Severin Müller | Dokumentation

## Inhaltsverzeichnis
* [Beschreibung und Projektziele](https://github.com/ShopStantly/webshop#beschreibung-und-projektziele)
* [Virtual Assistant](https://github.com/ShopStantly/webshop#virtual-assistant)
* [Datenbank](https://github.com/ShopStantly/webshop#datenbank)
* [Microservices](https://github.com/ShopStantly/webshop#microservices)

## Beschreibung und Projektziele
Das Projekt erfüllt die folgenden Anforderungen:
* Vier separate Microservices ([Webshop](https://github.com/ShopStantly/webshop), [Payment](https://github.com/ShopStantly/payment), [Inventory](https://github.com/ShopStantly/inventory), [Shipping](https://github.com/ShopStantly/shipping))
* Jeder Microservice hat eine eigene Datenbank ([Demo Daten können hier heruntergeladen werden](https://github.com/ShopStantly/database))
* REST-Implementation
* Webshop
  * Der Benutzer kann einen Artikel bestellen, diese Bestellung wird automatisch an die nötigen Microservices kommuniziert. Jeder Microservice erhält nur die nötigen Informationen (Bspw. weiss das Payment System nicht, was genau bestellt wurde. Das Inventory System weiss nicht, wie viel ein Artikel kostet. Und das Shipping System weiss nicht, welche Artikel sich in einem Paket befinden.)
* Payment
  * Integration eines Drittanbieters für die Zahlungsabwicklung
  * Loyalty Punkte werden abgezogen
* Inventory
  * Lagerverwaltung
  * Fake picking
  * Generierung eines Rüstscheins
* Shipping
  * Benachrichtigung für die Lieferung mit einem Drittanbieter (Post, DHL etc.)
  * Generierung einer Tracking ID

## Virtual Assistant
Unsere ursprüngliche Idee zum Ablauf haben wir mittels Botsociety designt. Die Prozess-Idee ist hier zu finden: https://app.botsociety.io/s/5bb1e265a8bf6f12d88d99a8?p=c2f3ae740e93264b775fd5f502966cf4e90290ff

## Datenbank

Webshop DB:

![alt Webshop DB](https://raw.githubusercontent.com/ShopStantly/webshop/master/assets/Order%20Management%20DB.png "Webshop DB")

Payment DB:

![alt Payment DB](https://raw.githubusercontent.com/ShopStantly/webshop/master/assets/Payment%20DB.png "Payment DB")

Inventory DB:

![alt Inventory DB](https://raw.githubusercontent.com/ShopStantly/webshop/master/assets/inventory%20db.png "Inventory DB")

Shipping DB:

![alt Shipping DB](https://raw.githubusercontent.com/ShopStantly/webshop/master/assets/Shipping%20DB.png "Shipping DB")

## Microservices
Nachfolgend wird der Bestellprozess inkl. die Kommunikation mit den Microservices erläutert. Beispiele zur Reproduktion mit unseren Demo-Daten werden *kursiv* dargestellt. Zur Simulation der API-Abfragen haben wir [Postman](https://www.getpostman.com/) verwendet.

### Webshop
Weil der Benutzer via Google Assistent bestellt, gehen wir davon aus, dass die Benutzerauthentifizierung bereits durchgeführt wurde. In unserem Beispiel ist ein Benutzer namens "Hans" vorhanden. Er hat noch 12 Franken auf seinem Konto und verfügt über 233 Loyalty Punkte (z.B. Cumulus).

![alt Datenbank User](https://raw.githubusercontent.com/ShopStantly/webshop/master/assets/githubdoku__paymentdb_hans.png "Datenbank User")

In unserem Shop werden zwei Artikel geführt: Milch (2.-) und Müesli (5.-).

![alt Datenbank Shop](https://raw.githubusercontent.com/ShopStantly/webshop/master/assets/githubdoku__shopdb_milkmuesli.png "Datenbank Shop")

Wenn der Benutzer einen Artikel bestellt, wird überprüft, ob der Benutzer genug Geld auf seinem Konto hat. Falls ja wird der Bestellprozess fortgesetzt, ansonsten abgebrochen.

*Bestellung auslösen mittels Postman: http://localhost:8080/ mit Body "milk" oder "muesli"*
Resultat ist entweder
**200: Order was places successfully** oder
**400: You do not have enough balance**.

Damit haben wir gezeigt, dass eine beidseitige Kommunikation (Anfragen und Empfangen) zwischen Shop und Payment Microservice funktioniert. 

### Payment
Um verschiedene Fälle darzustellen haben wir implementiert, dass zwar der Kontostand überprüft wird aber die Rechnung noch nicht verbucht wird (bspw. wenn Rechnung aus irgend einem Grund später verbucht werden sollte). Dafür haben wir implementiert, dass pro Bestellung ein Loyalty Punkt abgezogen wird (je nach System macht es mehr Sinn, einen Punkt dazuzurechnen statt abzuziehen).

Um zu zeigen, dass der Kontostand tatsächlich vom Webshop abgerufen wird, kann auch eine Abfrage mittels Postman erstellt werden: *http://localhost:8081/user/balance mit Body "hans" gibt den aktuellen Kontostand (12.-) aus.*

### Inventory
Bei einer Bestellung wird der Lagerbestand um die bestellte Menge reduziert. 

![alt Datenbank Inventory](https://raw.githubusercontent.com/ShopStantly/webshop/master/assets/githubdoku__inventorydb_lager.png "Datenbank Inventory")

Zusätzlich wird ein Rüstschein (packing slip) generiert, welcher die Informationen für einen Kommissionierer bereit stellt. Der Rüstschein wird als .txt-Datei in den Hauptpfad vom Inventory-System gespeichert.

![alt Packing Slip](https://raw.githubusercontent.com/ShopStantly/webshop/master/assets/githubdoku__inventorydb_packingslip.png "Packing Slip")

### Shipping
Für den Shipping Microservice ist eine Tracking-ID und der aktuelle Status relevant (false = in Bearbeitung, true = ausgeliefert).

![alt Shipping Tracking](https://raw.githubusercontent.com/ShopStantly/webshop/master/assets/githubdoku__shippingdb_tracking.png "Shipping Tracking")

Zudem wird eine Benachrichtigung für die Auslieferung an einen Drittanbieter ausgelöst. In diesem Demo-Fall wird die Message mittels System.out.print in der Konsole ausgegeben.

![alt Benachrichtigung Auslieferung](https://raw.githubusercontent.com/ShopStantly/webshop/master/assets/githubdoku__shippingdb_syso.png "Benachrichtigung Auslieferung")
