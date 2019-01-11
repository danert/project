## Process Book


#### Woensdag 9 januari

Vandaag al een eind op weg gekomen bij het maken van het prototype. Bijna alle schermen/activities zijn aanwezig en de navigatie tussen deze schermen is ook bijna compleet. Veel schermen hebben al meerdere views waardoor je een redelijk beeld krijgt van hoe de app er later uit gaat zien. Verder heb ik opgezocht hoe men gebruik kan maken van een SearchView in Android Studio om bijvoorbeeld films op te zoeken (in mijn geval). Ik ben erachter gekomen hoe ik de API van The Movie Database kan gebruiken om zoekopdrachten uit te voeren (https://developers.themoviedb.org/3/getting-started/search-and-query-for-details). Hierdoor is het niet nodig voor de gebruiker om filmtitels precies (goed) in te typen. Ook heb ik beseft dat het waarschijnlijk het beste is om alle profielen inclusief hun watchlist, kijkgeschiedenis en progressie in een database op te slaan.

#### Donderdag 10 januari

Vandaag is het gelukt om het prototype af te krijgen. Om een beeld te krijgen van hoe de app later gaat werken, zie je een voorbeeldlijstje met films als je naar de watchlist of kijkgeschiedenis gaat. Nadat het prototype af was, heb ik gewerkt aan de zoekfunctie. Met behulp van de The Movie Database API kun je nu een zoekopdracht invoeren, waarna er suggesties worden getoond in de vorm van verschillende filmtitels.

#### Vrijdag 11 januari

Vandaag heb ik onder andere de zoekfunctie van de app verbeterd. Als je nu een zoekterm invult, krijg je suggesties voor films te zien inclusief hun poster en jaar van uitgave. Als je op een suggestie klikt, word je doorgestuurd naar een pagina die meer info over de film laat zien, onder andere een korte samenvatting.

<img src="/doc/searchfunctionscreenshot.jpg" width="250"> <img src="/doc/movieinfoscreenshot.jpg" width="250">

Het project bestaat nu uit acht activities en één custom adapter. Ook heb ik ervoor gekozen om een class te maken genaamd MovieInfo om hierin zaken als de poster, titel en plot van een specifieke film op te slaan om deze zo makkelijk tussen activities over te brengen. Als je zo bijvoorbeeld op een suggestie klikt, is het niet nodig om opnieuw gebruik te maken van de API om informatie over de gekozen film op te zoeken.  
