## Final Report

<img src="/doc/finalhomepage.jpg" width="250">

Cinemaster is een app waarmee gebruikers films kunnen ‘loggen’ in een soort dagboek en hier progressiepunten mee kunnen verdienen. Ook kunnen ze de progressie en kijkgeschiedenis van hun vrienden bekijken.

#### Activities overview
<img src="/doc/technicaldesign.jpg" width="900">

Als de gebruiker de app voor de eerste keer op een apparaat opent, dan komt deze terecht op LoginActivity. Als de gebruiker de app echter afsluit terwijl deze ingelogd is, dan blijft HomepageActivity het nieuwe startscherm tot de gebruiker opnieuw uitlogt.

#### Classes

De class MovieInfo wordt gebruikt om informatie over een specifieke film op te slaan en zo makkelijk over te dragen aan de PreviewAdapter en MovieInfoActivity. MovieInfo's worden aangemaakt als de gebruiker een zoekopdracht uitvoert in FilmSearchActivity. The Movie Database API geeft dan een lijst met zoeksuggesties. Voor iedere suggestie worden de relevante attributes gepakt en geset (zie tabel).
  
  De class FilmReview bevat informatie over een film inclusief een beoordeling van de gebruiker. De class wordt gebruikt om over te dragen aan de ReviewAdapter en FilmReviewActivity. Er worden FilmReviews aangemaakt als de gebruiker ViewingHistoryActivity binnenkomt. De viewinghistory in de database van de gebruiker wordt dan bekeken, en voor iedere entry in die tabel wordt een FilmReview aangemaakt. Deze lijst met FilmReviews kan vervolgens worden gegeven aan de ReviewAdapter.

<table>
<tr><th>MovieInfo </th><th>FilmReview</th></tr>
<tr><td>

|attribute| type |
|--|--|
|movieId| string|
| releaseTitle | string | 
| moviePlot | string |
| posterUrl | string |

</td><td>

|attribute| type |
|--|--|
|movieId| string|
| releaseTitle | string | 
| reviewText | string |
| posterUrl | string |
| timeStamp | string |
| starRating | float |

</td></tr> </table>

Verder bevat het project een HelperClass met slechts één functie die door meerdere activities gebruikt wordt (removeEntry()). Deze functie neemt een Context en een url-string en doet vervolgens een VolleyRequest om de entry van de url uit de database te verwijderen.

#### Adapters
Het project maakt gebruik van twee adapters, PreviewAdapter en ReviewAdapter. De PreviewAdapter krijgt een ArrayList met MovieInfos en stopt deze in de ListViews in de WatchlistActivity en FilmSearchActivity (nadat de gebruiker een zoekopdracht heeft uitgevoerd). De adapter zorgt ervoor dat de gebruiker de titel, jaar van uitgave en poster te zien krijgt van een of meerdere films.
  De ReviewAdapter krijgt een ArrayList met FilmReviews en stopt deze in de ListViews in de ViewingHistoryActivity en de FriendDetailActivity. Deze adapter lijkt erg op de ReviewAdapter, alleen laat deze ook een gegeven beoordeling zien in de vorm van een RatingBar.

#### PostRequests

Het project bevat vier verschillende PostRequests die worden gebruikt om informatie aan de online database toe te voegen. CreateAccountRequest wordt gebruikt om een nieuw profiel toe te voegen aan de /profiles tabel. FriendListPostRequest wordt gebruikt om een profiel aan iemands vriendenlijst toe te voegen. ViewingHistoryPostRequest wordt gebruikt om een beoordeling toe te voegen aan de kijkgeschiedenis van een gebruiker. Tenslotte wordt een WatchlistPostRequest gebruikt om een film aan iemands watchlist toe te voegen. De parameters die worden meegegeven aan de POST-request zijn te zien in de tabellen hieronder (kopje Database, exclusief de id's).

#### Database
 De app maakt gebruik van een online database om informatie in op te slaan (https://github.com/stgm/rester). In de tabel genaamd
 profiles zitten alle aangemaakte accounts. Verder heeft iedere specifieke gebruiker 3 tabellen in de database. Ieder profiel 
 heeft zijn eigen viewinghistory, watchlist en friendlist (de watchlist van het profiel van 'daan' is bijvoorbeeld te vinden
 op https://ide50-danert.legacy.cs50.io:8080/daanwatchlist). Hieronder zie je voor iedere tabel waaruit iedere entry bestaat.
 
 <table>
<tr><th>profiles </th><th>userfriendlist</th></tr>
<tr><td>

|key| value |
|--|--|
|id| int|
| username | string | 
| password | string |

</td><td>

|key| value |
|--|--|
|id| int|
| friendName | string | 

</td></tr> </table>

<table>
<tr><th>userviewinghistory </th><th>userwatchlist</th></tr>
<tr><td>

|key| value |
|--|--|
|id| int|
| movieId | string | 
| reviewText | string |
| posterUrl | string | 
| timeStamp | string | 
| releaseTitle | string | 

</td><td>

|key| value |
|--|--|
|id| int|
| moviePlot | string | 
| movieId | string |
| posterUrl | string | 
| releaseTitle | string | 

</td></tr> </table>

### Challenges and changes

Als je kijkt naar mijn originele Design, dan is het principe van de app nog steeds hetzelfde gebleven. Toch zijn er veel aspecten toegevoegd of weggelaten.
  
  In mijn originele proposal heb ik het niet over de functie gehad om een account aan te kunnen maken en hiermee in te loggen. Nu zit deze functie er wel in, deze is namelijk vrijwel noodzakelijk voor het vriendensysteem van de app waarmee andere profielen gevolgd kunnen worden. Ook is het nu voor gebruikers mogelijk om in te loggen op verschillende apparaten waardoor ze hun progressie niet kwijtraken als ze bijvoorbeeld een nieuwe telefoon nemen.
  
  In mijn originele Design heb ik ook al kort beschreven hoe mijn database eruit zou kunnen zien. Ik beschreef daar een tabel met alle gebruikers van de app en hun 'eigenschappen'. Van de attributen 'achievements' en 'favoritefilms' is in de huidige app geen sprake meer. De 'progression' en 'movieswatched' komen nog wel terug, maar in een andere vorm. Ieder profiel heeft nu zijn eigen tabel met bekeken films, en de progressie wordt berekend op basis van het aantal entries in deze tabel. Verder heeft ieder profiel nog een eigen tabel voor de vriendenlijst en de watchlist, iets wat ik nog niet had beschreven in het designbestand.

Nadat de belangrijkste schermen van de app compleet waren, was het soms moeilijk om te bedenken wat de volgende stap was aangezien ik veel optionele features had aangegeven in mijn proposal. Zal ik eerst een vriendensysteem implementeren of me focussen op achievements? 

Nadat het filmzoeksysteem m.b.v. de API compleet was, moest ik bedenken of en wanneer ik de API nog meer zou gebruiken. Als je bijvoorbeeld op een film uit je watchlist klikt, is het dan de bedoeling om opnieuw gebruik te maken van de API om meer informatie over de film te krijgen? Ik besefte me dat het waarschijnlijk het handigst zou zijn om voor iedere film die de gebruiker opslaat (watchlist/viewinghistory) de informatie hiervan ook op te slaan in de database. Hierdoor is het bijvoorbeeld genoeg om één GET-request naar iemands watchlist te doen, en hoeft daarna niet meer voor iedere film in deze lijst een API-verzoek gedaan te worden. 


