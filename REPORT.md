## Final Report

<img src="/doc/finalhomepage.jpg" width="250">

Cinemaster is een app waarmee gebruikers films kunnen ‘loggen’ in een soort dagboek en hier progressiepunten mee kunnen verdienen. Ook kunnen ze de progressie en kijkgeschiedenis van hun vrienden bekijken.

#### Activities overview
<img src="/doc/finalreportscheme.jpg" width="900">

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

#### PostRequests

Het project bevat vier verschillende PostRequests die worden gebruikt om informatie aan de online database toe te voegen. CreateAccountRequest 

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
