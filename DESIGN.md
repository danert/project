# Design Document

### Design Sketch

<img src="/doc/designsketchjpg.jpg" width="900">

### Classes

| FilmLog |
| ------------- |
| filmTitle (string) |
| filmRating (int) |
| filmReview (string) |
| |
| addTitle() |
| addRating() |
| addReview() |

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

Ik zal de API van The Movie Database gebruiken om informatie over bepaalde films op te halen. Als de gebruiker een bepaalde film opzoekt, krijgt hij bijvoorbeeld de filmposter en de regisseur te zien. Ook bij het zoeken van films is de API nodig. Het is de bedoeling dat de gebruiker bij het zoeken naar films een aantal letters invult, waarna er met behulp van autosuggestie een aantal suggesties getoond worden (men vult bijvoorbeeld 'the shawsh' in, en krijgt gelijk 'The Shawshank Redemption' te zien zodat de gebruiker niet de hele titel hoeft te typen). Dit kan wellicht wel moeilijkheid opleveren, aangezien je dan een lijst zou moeten hebben met alle filmtitels die er bestaan.
  
  Als ik tijd over heb zou ik nog een vriendensysteem willen implementeren waarmee het mogelijk is om andere profielen te volgen en onder andere hun progressiepunten en hun gekeken films te zien. Hiervoor zou het handig zijn om gebruik te maken van een online database waarin profielen opgeslagen zitten. Hier een voorbeeld van hoe zo'n database eruit zou kunnen zien:
    
| user_id | name | progression | movieswatched | achievements | favoritefilms |
| ------------- | ------------- | ------------- | ------------- | ------------- | ------------- |
| int | string | int | list(strings) | list(strings) | list(strings) |