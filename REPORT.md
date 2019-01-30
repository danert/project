




<img src="/doc/finalreportscheme.jpg" width="900">

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
