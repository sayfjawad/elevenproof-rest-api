# language: nl
Functionaliteit: API availability tests

Als bedrijfsvoerder
Wil ik waarborgen dat al mijn endpoints beschikbaar zijn nadat ik aanpassingen heb gedaan in de code

  Achtergrond:
    Gegeven een correcte werking van de applicatie en beschikbaarheid van gegevens voor de endpoints:

  Scenario: wanneer we een werkende swagger-ui hebben dan verwachten een response van de endpoint
    Als het endpoint '/api' met GET en ACCEPT '*/*' wordt aangeroepen
