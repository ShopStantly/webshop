# ShopStantly - Webshop
EAI-Projekt HS2018 | Vlado Repic | Severin Müller | Dokumentation

## Inhaltsverzeichnis
* [Beschreibung und Projektziele](https://github.com/ShopStantly/webshop#beschreibung-und-projektziele)
* [Requirements](https://github.com/ShopStantly/webshop#requirements)
* [Virtual Assistant](https://github.com/ShopStantly/webshop#virtual-assistant)
* [Datenbank](https://github.com/ShopStantly/webshop#datenbank)
* [Microservices](https://github.com/ShopStantly/webshop#microservices)

## Beschreibung und Projektziele

## Requirements
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
Prozess-Idee: https://app.botsociety.io/s/5bb1e265a8bf6f12d88d99a8?p=c2f3ae740e93264b775fd5f502966cf4e90290ff

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

![alt Datenbank Shop](https://raw.githubusercontent.com/ShopStantly/webshop/master/assets/githubdoku__shopdb_milkmuesli.png "Datenbank Shop

Wenn der Benutzer einen Artikel bestellt, wird überprüft, ob der Benutzer genug Geld auf seinem Konto hat. Falls ja wird der Bestellprozess fortgesetzt, ansonsten abgebrochen.

*Bestellung auslösen mittels Postman: http://localhost:8080/ mit Body "milk" oder "muesli"*
Resultat ist entweder **200: Order was places successfully** oder **400: You do not have enough balance**.

Damit haben wir gezeigt, dass eine beidseitige Kommunikation (Anfragen und Empfangen) zwischen Shop und Payment Microservice funktioniert. 

### Payment

### Inventory

### Shipping
