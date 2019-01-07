# Project Proposal
#### Daan Visser (11277750)
##### Minor Programmeren

## Problem statement

Veel mensen houden ervan om films te kijken, maar doen dit minder vaak dan ze eigenlijk zouden willen. Het probleem is
dat deze persoon niet genoeg motivatie hebben om zichzelf er toe te zetten aan een film te beginnen. Deze app zal vooral
filmliefhebbers aanspreken die (nog) meer films willen kijken en er ook van houden om hun gekeken films te 'loggen' of te
recenseren in een (persoonlijk) dagboek.

## Solution

De oplossing is een app die gebruikers beloont voor het kijken van (veel) films en ze in staat stelt om hun kijkgeschiedenis
bij te houden.

<img src="/doc/visual sketch.jpg" width="900">

###### Main features (minimum viable product)

-Progressiebalk die gevuld wordt wanneer een film 'gelogd' wordt. Als de balk helemaal gevuld is gaat de gebruiker een
'level' omhoog.  
-Profielpagina die o.a. het level, aantal gekeken films en aantal uren aan film gekeken van de gebruiker laat zien.  
-Films kunnen gelogd worden in een soort dagboek/journal.  
-De gebruiker kan films opzoeken om deze vervolgens te loggen, of om informatie te krijgen over de film.  
-De gebruiker kan films toevoegen aan een watchlist om later nog te gaan kijken.

###### Optional features
-De gebruiker kan bepaalde films die nog niet uit zijn gekomen 'volgen'. De gebruiker krijgt een melding als deze film is 
uitgekomen in de bioscoop of op dvd/bluray.  
-De gebruiker kan andere gebruikers volgen om hun profielen te bekijken en te zien welke films ze hebben gekeken.
-Profielen laten ook informatie zien over wat voor soort films de gebruiker precies kijkt, etc.
-De app kan de gebruiker een film aanraden om te kijken op basis van eerder gekeken films.

## Prerequisites

###### Data sources

-The Movie Database API (https://www.themoviedb.org/documentation/api)
-Letterboxd API (http://api-docs.letterboxd.com/)

De TMD API zal worden gebruikt om informatie aan de gebruiker te tonen als deze een bepaalde film opzoekt. Belangrijke
zaken zijn bijvoorbeeld het jaar waarin de film is uitgekomen, de poster, regisseur en vergelijkbare films. De Letterboxd API kan worden gebruikt om ratings en reviews van films te verkrijgen en te laten zien aan de gebruiker.

###### External components

SQLite is nodig om een database te maken met alle gebruikers van de app.

###### Similar apps

De Letterboxd app is vergelijkbaar met het idee van deze app. Met de Letterboxd app kunnen gebruikers ook hun gekeken films
bijhouden en recensies achterlaten. Met mijn eigen app kan dit ook, maar er is een progressiesysteem aan gebonden die gebruikers
stimuleert om meer films te gaan kijken/reviewen. Ook is mijn app meer bedoeld als persoonlijk 'dagboek' i.p.v. een sociaal
netwerk.

###### Hardest parts

Een ding dat wellicht problemen op kan leveren is de manier waarop de gebruiker 'zoekt' naar films. Bij een API-verzoek 
naar informatie over een film moet de (juiste) titel van de film duidelijk zijn. Het liefst zou je willen dat de gebruiker
een aantal letters invoert en dat de titels van films die beginnen met deze letters dan als suggestie gegeven worden. Dit is
echter lastig te doen aangezien je dan een database zou moeten hebben met alle filmtitels die er bestaan/bekend zijn. Zonder deze functie zal de gebruiker de filmtitel precies goed moeten spellen bij een zoekopdracht.  
  
  Het weergeven van een progressiebalk die gevuld wordt is wellicht moeilijk te implementeren, aangezien ik nog niet echt ervaring heb met zo'n soort visualisatie met Android.
  
  Om de gebruiker een melding te geven over bepaalde films is het nodig om goed op te zoeken hoe dit moet, aangezien we nog niet
  eerder met zulke meldingen gewerkt hebben.

