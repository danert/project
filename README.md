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

###### Main features (minimum viable product)

-Progressiebalk die gevuld wordt wanneer een film 'gelogd' wordt. Als de balk helemaal gevuld is gaat de gebruiker een
'level' omhoog.  
-Profielpagina die o.a. het level, aantal gekeken films en aantal uren aan film gekeken van de gebruiker laat zien.  
-Films kunnen gelogd worden in een soort dagboek/journal.  
-De gebruiker kan films opzoeken om deze vervolgens te loggen, of om informatie te krijgen over de film.

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
zaken zijn bijvoorbeeld het jaar waarin de film is uitgekomen, de poster, regisseur en vergelijkbare films. De Letterboxd API kan worden gebruikt
om ratings en reviews van films te verkrijgen en te laten zien aan de gebruiker.

###### External components

SQLite is nodig om een database te maken met alle gebruikers van de app.

###### Similar apps

De Letterboxd app is vergelijkbaar met het idee van deze app. Met de Letterboxd app kunnen gebruikers ook hun gekeken films
bijhouden en recensies achterlaten. Met mijn eigen app kan dit ook, maar er is een progressiesysteem aan gebonden die gebruikers
stimuleert om meer films te gaan kijken/reviewen. Ook is mijn app meer bedoeld als persoonlijk 'dagboek' i.p.v. een sociaal
netwerk.

###### Hardest parts



