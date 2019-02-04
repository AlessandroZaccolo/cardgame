const urlParams = new URLSearchParams(window.location.search);
const gameId = urlParams.get('gameId');


const gamePromise = axios.get("http://localhost:8080/api/games/" + gameId);

gamePromise
    .then(function(response) {
            const game = response.data; // In axios you get a response object with the data inside
            displayGame(game);

    }).catch(function (error) {
            console.log("There was an error!", error);
            displayError(error);

    });

function displayGame(game) {

        let gameContainer = document.getElementById("game-container");

        const gameNumber = document.createElement("h1");
        gameNumber.textContent = `Game ${game.id}`;
        gameContainer.appendChild(gameNumber);


        const gameState = document.createElement("p");
        gameState.textContent = `State: ${game.state}`;
        gameContainer.appendChild(gameState);

        const gamePlayers = document.createElement("p");
        gamePlayers.textContent = "Players: " + game.playerNames;
        gameContainer.appendChild(gamePlayers);


}

function displayError(error) {

        let gameContainer = document.getElementById("game-container");

        const gameError = document.createElement("p");
        gameError.textContent = "There is an error: " + error;
        gameContainer.appendChild(gameError);


}