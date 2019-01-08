# Design Document

### Design Sketch

<img src="/doc/designsketchjpg.jpg" width="900">

### Classes and utility modules

| FilmLog |
| ------------- |
| filmTitle (string) |
| filmRating (int) |
| filmReview (string) |
| filmDate (string) |
| |
| addTitle() |
| addRating() |
| addReview() |
| addDate() |

| Profile |
| ------------- |
| name (string) |
| levelProgress (int) |
| timeWatched (int) |
| favoriteFilms (list) |
| |
| addTitle() |
| addRating() |
| addReview() |

| Film |
| ------------- |
| filmTitle (string) |
| filmDirector (string) |
| filmPlot (string) |
| filmPoster (image) |
| filmScore (int) |
| |
| addTitle() |
| addScore() |
| addDirector() |
| addPlot() |
| addPoster() |

| WatchHistoryAdapter |
| ------------- |
| context |
| watchedFilms[]|
||
| getView() |


Ik zal de API van The Movie Database (https://www.themoviedb.org/documentation/api) gebruiken om informatie over bepaalde films op te halen. Als de gebruiker een bepaalde film opzoekt, krijgt hij bijvoorbeeld de filmposter en de regisseur te zien. Ook bij het zoeken van films is de API nodig. Het is de bedoeling dat de gebruiker bij het zoeken naar films een aantal letters invult, waarna er met behulp van autosuggestie een aantal suggesties getoond worden (men vult bijvoorbeeld 'the shawsh' in, en krijgt gelijk 'The Shawshank Redemption' te zien zodat de gebruiker niet de hele titel hoeft te typen). Gelukkig heeft Android Studio een ingebouwde class die de autosuggest functie mogelijk maakt (https://developer.android.com/reference/android/widget/AutoCompleteTextView). Dit kan wellicht wel moeilijkheid opleveren, aangezien je dan een lijst zou moeten hebben met alle filmtitels die er bestaan.
  
  Als ik tijd over heb zou ik nog een vriendensysteem willen implementeren waarmee het mogelijk is om andere profielen te volgen en onder andere hun progressiepunten en hun gekeken films te zien. Hiervoor zou het handig zijn om gebruik te maken van een online database waarin profielen opgeslagen zitten. Hier een voorbeeld van hoe zo'n database eruit zou kunnen zien:
    
| user_id | name | progression | movieswatched | achievements | favoritefilms |
| ------------- | ------------- | ------------- | ------------- | ------------- | ------------- |
| int | string | int | list(strings) | list(strings) | list(strings) |

Bij het kijken van films is het de bedoeling dat de progressiebalk op de homepage en profielpagina gevuld wordt (en weer gereset als een nieuw level bereikt is). Android Studio heeft hier gelukkig een ingebouwde class voor (https://developer.android.com/reference/android/widget/ProgressBar). Het is de bedoeling dat na ieder bereikt level het aantal films dat bekeken moet worden om een level omhoog te gaan ook stijgt. 




