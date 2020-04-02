### Trainer API

Implémentation de l'API POKEMON TYPE

### Lancer le projet

Via un IDE ou via cmd 
```
mvn clean install && mvn spring-boot:run
```

Port par défaut : 8080

### Routes

Liste des routes dispo sur le swagger (/swagger-ui.html)

GET /pokemon-types/ getAllPokemonTypes
GET /pokemon-types/{id} getPokemonTypeFromId
GET /pokemon-types/name getPokemonTypeFromName
GET /pokemon-types/types getPokemonTypeFromTypes

###Notes

J'ai mis de base les urls en local dans mes applications.properties
