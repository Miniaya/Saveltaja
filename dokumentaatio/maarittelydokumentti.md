# Määrittelydokumentti

### Tietojenkäsittelytieteen kandidaattiohjelma (TKT), kieli: suomi

Säveltäjän idea on tehdä uusia kappaleita annetun opetusaineiston pohjalta käyttäen hyödyksi Markovin ketjuja.

**HUOM!** Tämä on alustava määrittelydokumentti, joka tulee muuttumaan työn edetessä. Täältä ei siis vielä välttämättä löydy kaikkea,
mitä työssä tullaan tarvitsemaan.

## Työssä toteutettavat tietorakenteet ja algoritmit
### Tietorakenteet

* Maksimipuu
  * Nuottiosajonojen jälkeisen nuotin esiintymistodennäköisyydet

### Algoritmit

* Hakualgoritmi 
  * Nuottiosajonojen etsiminen aineistosta
* Algoritmi, joka muodostaa uuden kappaleen
  * Tarkastellaan edellistä / edellisiä nuotteja, jonka perusteella päätetään, mikä nuotti tulee seuraavaksi
  * Mahdollisesti myös, mikä on nuotin pituus (koko-, puoli-, neljäsosa- vai kahdeksasosanuotti)
  
 ## Ohjelmalle annettavat syötteet ja ohjelman tuottamat tulosteet
 
 ### Syötteet
 
 * Kappaleen pituus (nuotteina)
 * Markovin ketjun aste
 
 ### Tulosteet
 
 * Nuotit Lilypond-ohjelman ymmärtämässä muodossa
