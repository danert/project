## Process Book


#### Woensdag 9 januari

Vandaag al een eind op weg gekomen bij het maken van het prototype. Bijna alle schermen/activities zijn aanwezig en de navigatie tussen deze schermen is ook bijna compleet. Veel schermen hebben al meerdere views waardoor je een redelijk beeld krijgt van hoe de app er later uit gaat zien. Verder heb ik opgezocht hoe men gebruik kan maken van een SearchView in Android Studio om bijvoorbeeld films op te zoeken (in mijn geval). Ik ben erachter gekomen hoe ik de API van The Movie Database kan gebruiken om zoekopdrachten uit te voeren (https://developers.themoviedb.org/3/getting-started/search-and-query-for-details). Hierdoor is het niet nodig voor de gebruiker om filmtitels precies (goed) in te typen. Ook heb ik beseft dat het waarschijnlijk het beste is om alle profielen inclusief hun watchlist, kijkgeschiedenis en progressie in een database op te slaan.

#### Donderdag 10 januari

Vandaag is het gelukt om het prototype af te krijgen. Om een beeld te krijgen van hoe de app later gaat werken, zie je een voorbeeldlijstje met films als je naar de watchlist of kijkgeschiedenis gaat. Nadat het prototype af was, heb ik gewerkt aan de zoekfunctie. Met behulp van de The Movie Database API kun je nu een zoekopdracht invoeren, waarna er suggesties worden getoond in de vorm van verschillende filmtitels.

#### Vrijdag 11 januari

Vandaag heb ik onder andere de zoekfunctie van de app verbeterd. Als je nu een zoekterm invult, krijg je suggesties voor films te zien inclusief hun poster en jaar van uitgave. Als je op een suggestie klikt, word je doorgestuurd naar een pagina die meer info over de film laat zien, onder andere een korte samenvatting.

<img src="/doc/searchfunctionscreenshot.jpg" width="250"> <img src="/doc/movieinfoscreenshot.jpg" width="250">

Het project bestaat nu uit acht activities en één custom adapter. Ook heb ik ervoor gekozen om een class te maken genaamd MovieInfo om hierin zaken als de poster, titel en plot van een specifieke film op te slaan om deze zo makkelijk tussen activities over te brengen. Als je zo bijvoorbeeld op een suggestie klikt, is het niet nodig om opnieuw gebruik te maken van de API om informatie over de gekozen film op te zoeken.

#### Maandag 14 januari

Vandaag heb ik me vooral gefocust op het werken met een database. De gebruiker is nu in staat om, na het opzoeken van een film, deze film toe te voegen aan zijn of haar watchlist. Deze worden online opgeslagen waarna de gebruiker de watchlist kan bekijken. Iedere gebruiker/profiel krijgt zijn eigen watchlist in de database, dus gebruiker 'daan' krijgt /daanwatchlist als 'site' toegewezen.  

De database krijgt waarschijnlijk een tabel waarin alle aangemaakte profielen/accounts worden opgeslagen (inclusief o.a. wachtwoord en aantal minuten aan film gekeken). Verder krijgt ieder profiel een aparte tabel voor de watchlist en kijkgeschiedenis.

#### Dinsdag 15 januari

Vandaag verder gewerkt met de online database. De gebruiker kan nu ook een film die bekeken is 'loggen' door deze een aantal sterren te geven met een eventuele recensie(tekst) erbij. Deze worden nu opgeslagen in de database.

#### Woensdag 16 januari

De gebruiker kan nu een overzicht krijgen met gemaakte logs/recensies. Ook kan de gebruiker nu op de homepage zien welk 'level' hij is, en hoeveel films er nog bekeken/gelogd moeten worden om het volgende level te bereiken. De gebruiker gaat bij iedere 10 gekeken films een level omhoog. Bijna alle main features van de app zijn nu compleet.
