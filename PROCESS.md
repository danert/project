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

#### Donderdag 17 januari

Vandaag is het (eindelijk) gelukt om een bug te fixen waardoor de app vaak crashte bij het zoeken naar films. Bij sommige zoekresultaten is er namelijk geen datum bekend waarop de film is uitgekomen. Deze films laten voortaan alleen hun titel zien bij de app (zonder jaar van uitgave). Verder heb ik de functie om films te verwijderen uit de watchlist (en dus de database) geïmplementeerd. Deze werkt helaas net aan niet door een vervelende bug. 

#### Vrijdag 17 januari

Vandaag ben ik bezig geweest met het verwijderen van entries uit de database. Dit moet bijvoorbeeld gebeuren wanneer de gebruiker iets uit zijn/haar watchlist verwijdert of een film kijkt die in de watchlist staat. Je kunt nu al films verwijderen uit je watchlist. Verder heb ik besloten dat ik waarschijnlijk eerst het vriendensysteem van de app ga implementeren voordat ik aan 'achievements' ga beginnen, omdat het sociale aspect van de app mij belangrijker lijkt dan de achievements. Met het vriendensysteem zul je andere profielen kunnen volgen en hun progressie en gekeken films bekijken.

#### Maandag 21 januari

Vandaag ben ik verder gegaan met het verwijderen van entries uit de database. Als je een film kijkt die in je watchlist staat, wordt deze nu ook automatisch verwijderd uit de watchlist. Door de filterfunctie van de rester-server te implementeren is mijn code voor het verwijderen van entries uit de database een stuk korter geworden aangezien je niet meer alle entries af hoeft te gaan om (het id van) een film te vinden. Verder heb ik ook het design van de app wat verbeterd door kleuren en lettertypen te veranderen. De app is nu al een stuk fijner om naar te kijken, maar het het uiteindelijke design van de app zal er nog een stuk beter uit gaan zien (is de bedoeling :)). 

<img src="/doc/firstdesign.jpg" width="250">

De gebruiker kan films meerdere keren 'loggen' en voor iedere keer toch progressiepunten verdienen. Ik was eerst van plan om de gebruiker iedere film maar één keer te laten loggen, maar ik heb besloten dat het kijken van een film die je al eerder hebt gezien toch ook 'gewoon telt'. Om te benadrukken dat het gaat om verschillende kijkbeurten zal ik nog implementeren dat iedere recensie ook een datum toont waarop de film is bekeken. Ook is de gebruiker in staat om een film die al bekeken is aan zijn/haar watchlist toe te voegen, omdat de gebruiker deze bijvoorbeeld nog een keer wil kijken of het gewoon lang geleden is dat de film is bekeken.

#### Dinsdag 22 januari

Als de gebruiker nu de app opent, moet deze inloggen voordat deze op de homepage terecht komt. Als de gebruiker nog geen account heeft kan deze er een aanmaken. Voordat deze functie geïmplementeerd was, zat er nog maar één profiel in de database (gehardcoded). Nu dit 'profielensysteem' in de app zit, wordt het ook makkelijker om de functie te implementeren dat men andere gebruikers kan gaan volgen. Nadeel is nu wel dat de gebruiker iedere keer dat de app wordt opgestart opnieuw moet inloggen. Het is de bedoeling dat dit nog verholpen wordt zodat de gebruiker maar één keer op een apparaat hoeft in te loggen.

<img src="/doc/loginscreen.jpg" width="250">

Op het loginscherm is nu een soort logo te zien. In werkelijkheid is dit eigenlijk een simpele TextView met een lettertype gevonden op internet (Timepiece). Later is het nog mogelijk om zelf een logo te ontwerpen, maar voor nu past dit logo goed bij (het design van) de app. 

#### Woensdag 23 januari

Het inlogsysteem werkt nu een stuk beter dan gisteren. Gisteren ging het navigeren tussen schermen nog niet helemaal goed, waardoor er bijvoorbeeld null (lvl 1) stond met de verkeerde progressie. Nu werkt dit allemaal wel goed. Vandaag ben ik ook begonnen aan een systeem waarbij men andere profielen kan gaan volgen. Je kunt nu al (bestaande) profielen toevoegen zodat je deze te zien krijgt in een lijst, maar als je op een vriend drukt gebeurt er nog niks. Als je op een vriend klikt zul je in ieder geval de kijkgeschiedenis van die gebruiker kunnen/willen zien.
  
  <img src="/doc/homepageupdate.jpg" width="250"> <img src="/doc/friendscreen.jpg" width="250">
  
  Ik heb vandaag besloten dat ik geen achievements zal gaan toevoegen aan de app i.v.m. de tijd die ik nog heb. Bij het gebruik van achievements zul je namelijk voor iedere film een (groot) aantal eigenschappen bij moeten houden, hetgeen erg veel werk is. Ook heb ik besloten dat de gebruiker niet op zijn profiel kan klikken om zo specifiekere info over zijn of haar kijkgedrag te krijgen. Ik ga me de komende dagen namelijk vooral richten op het sociale aspect van de app (activiteit van vrienden volgen) en het design van de app. De app ziet er nu nog een beetje amateuristisch uit.
  
#### Donderdag 24 januari

Ondanks de Hackathon is het gelukt om de app wat extra functionaliteit te geven. Je kunt nu naast het toevoegen van vrienden deze ook verwijderen en hun profiel bekijken. Als je op een vriendenprofiel klikt krijg je hun progressie en kijkgeschiedenis op één scherm te zien. Het scherm is hierdoor wat vol en druk, later maak ik hier misschien twee aparte schermen van. Ook kan het interessant zijn om de watchlist van een vriend te bekijken. Verder blijft de gebruiker nu automatisch ingelogd op een apparaat als deze al eerder heeft ingelogd op het apparaat. Bij het openen van de app krijgt de gebruiker zo automatisch de homepage te zien. Ook kan de gebruiker nu uitloggen. Ook gebeurt er niets meer als de gebruiker op zijn eigen gebruikersnaam drukt op de homepage. Er is nu geen apart scherm meer voor het profiel, dat zit nu als het ware ingebouwd in de homepage.

  De gebruiker kan nu andere profielen volgen zonder dat dit profiel hiervoor toestemming geeft. Niet iedereen zal willen dat hun filmrecensies openbaar te lezen zijn voor anderen. Om dit op te lossen zal ik waarschijnlijk gebruikers de optie geven om hun profiel op openbaar of privé te zetten. Dit kost een stuk minder werk dan een systeem waarbij gebruikers elkaar volgverzoeken moeten sturen die geaccepteerd kunnen worden.
  
#### Vrijdag 25 januari

Vandaag heb ik de functie om reviews van jezelf te verwijderen toegevoegd. Ook laten reviews nu de datum zien waarop de film gelogd is. Ook kan de gebruiker nu volledige recensies van vrienden zien i.p.v. alleen de kijkgeschiedenis. 

<img src="/doc/frienddetail.jpg" width="250"> <img src="/doc/reviewdate.jpg" width="250">

#### Maandag 28 januari

Vandaag is vooral het design van de app aangepast. De app heeft nu een donkergrijze achtergrond met vooral witte tekst. Ik heb me hierbij laten inspireren door de app van IMDb. Het grijswitte uiterlijk van de app doet nu meer aan film denken dan het vorige design. Naast het veranderen van de kleuren heb ik ook enkele knoppen aan de ActionBar toegevoegd.
<img src="/doc/newdesign.jpg" width="250"> <img src="/doc/reviewdelete.jpg" width="250">  

Op de homepage van de app krijg je nu een korte uitleg over de werking van de app te zien als je op het vraagtekentje drukt. In plaats van een knop onderaan de app waarmee je kunt uitloggen, doe je dat nu door rechtsboven op een icoontje te klikken. Ook kun je nu vrienden en recensies verwijderen door op een prullenbak te klikken rechtsbovenaan (i.p.v. een knop onderaan). Door de ActionBar te gebruiken i.p.v. knoppen wordt er ruimte bespaard en ziet de app er moderner uit.

<img src="/doc/frienddelete.jpg" width="250"> <img src="/doc/apphelp.jpg" width="250">
