const urlParams = new URLSearchParams(window.location.search);
const gameId = urlParams.get('gameId');


const gamePromise = axios.get("http://localhost:8080/api/games/" + gameId);

gamePromise
//.then(x => x.json()) // this is necessary when using fetch
    .then(function(response) {

        const game = response.data; // In axios you get a response object with the data inside

        // This function will be called when the data comes
        // At this point, games contains the data that the end-point sends (the list of games)

        let gameContainer = document.getElementById("game-container");



        const goBack = document.createElement("a");
        goBack.textContent = `Go back to games list`;
        goBack.href = "http://localhost:8080/games.html";
        gameContainer.appendChild(goBack);


        const gameNumber = document.createElement("h1");
        gameNumber.textContent = `Game ${game.id}`;
        gameContainer.appendChild(gameNumber);


        const gameState = document.createElement("p");
        gameState.textContent = `State: ${game.state}`;
        gameContainer.appendChild(gameState);

        const gamePlayers = document.createElement("p");
        gamePlayers.textContent = "Players: " + game.playerNames;
        gameContainer.appendChild(gamePlayers);





    });