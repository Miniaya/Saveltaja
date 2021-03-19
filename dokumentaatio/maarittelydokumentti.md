# Määrittelydokumentti

### Tietojenkäsittelytieteen kandidaattiohjelma (TKT), kieli: suomi

Säveltäjän idea on tehdä uusia kappaleita annetun opetusaineiston pohjalta käyttäen hyödyksi Markovin ketjuja.

**HUOM!** Tämä on alustava määrittelydokumentti, joka tulee muuttumaan työn edetessä. Täältä ei siis vielä välttämättä löydy kaikkea,
mitä työssä tullaan tarvitsemaan.

## Työssä toteutettavat tietorakenteet ja algoritmit
### Tietorakenteet

* Binäärihakupuu (Maksimipuu)
  * Nuottiosajonojen jälkeisen nuotin esiintymistodennäköisyydet

### Algoritmit

* Hakualgoritmi 
  * Nuottiosajonojen etsiminen aineistosta
* Algoritmi, joka muodostaa uuden kappaleen
  * Tarkastellaan edellistä / edellisiä nuotteja, jonka perusteella päätetään, mikä nuotti tulee seuraavaksi
  * Mahdollisesti myös, mikä on nuotin pituus (koko-, puoli-, neljäsosa- vai kahdeksasosanuotti)

## Aika- ja tilavaativuudet (tavoite)

* Binäärihakupuu: 
  * Aikavaativuus: O(log n) (Hakualgoritmi)
  * Tilavaativuus: O(n)
* Kappaleen muodostaminen:
  * Aikavaativuus: O(n)?
  * tilavaativuus: ??
  
 ## Ohjelmalle annettavat syötteet ja ohjelman tuottamat tulosteet
 
 ### Syötteet
 
 * Kappaleen pituus (nuotteina)
 * Markovin ketjun aste
 * (Sävellaji)
 
 ### Tulosteet
 
 * Nuotit Lilypond-ohjelman ymmärtämässä muodossa

## Lähteet

* [Aurora Tulilaulu - Datamusikalisaatio (Pro gradu - tutkielma](https://helda.helsinki.fi/bitstream/handle/10138/229070/Datamusikalisaatio.pdf?sequence=3&isAllowed=y)
* [Roger B. Dannenberg - Music Generation and Algorithmic Composition](https://www.cs.cmu.edu/~music/cmsip/slides/05-algo-comp.pdf)
* [David Temperley - A Probabilistic Model of Melody Perception](https://onlinelibrary.wiley.com/doi/full/10.1080/03640210701864089)
