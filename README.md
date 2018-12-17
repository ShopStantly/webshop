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
Die folgenden Anforderung erfüllt das Projekt:
* Vier separate Microservices ([Webshop](https://github.com/ShopStantly/webshop), [Payment](https://github.com/ShopStantly/payment), [Inventory](https://github.com/ShopStantly/inventory), [Shipping](https://github.com/ShopStantly/shipping))
* Jeder Microservice hat eine eigene Datenbank ([Demo Daten können hier heruntergeladen werden](https://github.com/ShopStantly/database))
* REST-Implementation
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

