# Poker

## Frontend

Open [http://localhost/poker](http://localhost/poker) to see a list of already-created cards.

Open [http://localhost/poker/hand/{id}](http://localhost/poker/hand/1)
to open directly to the analysis page of a poker hand.

## Endpoints

### GET `/hands`

`RETURNS: List<HandDTO>`<br>
*List of poker hands and their cards*

### GET `/hands/{id}`

`PARAMS: Long id`<br>
`RETURNS: HandDTO`<br>
*Poker hand with the specified id and its cards*

### POST `/hands`

`RETURNS: AnalysisDTO`<br>
*Create a new poker hand and returns analysis of it*

### GET `/hands/analysis/{id}`

`PARAMS: Long id`<br>
`RETURNS: AnalysisDTO`<br>
*Analysis of the poker hand with the specified id*

### GET `/hands/compare`

`PARAMS: List<Long> ids`<br>
`RETURNS: String`<br>
*Result of playing the poker hands of the specified ids against each other*
