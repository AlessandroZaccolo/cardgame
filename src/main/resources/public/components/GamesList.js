

class GamesList extends React.Component{

    constructor(props){
        super(props);

        this.state = {
           games: [],
            loadingText: "Loading games"
        };
    }
    componentDidMount(){

        // component is mounted, now I can call an API to get data
        this.fetchAndDisplayGames();


    }

    fetchAndDisplayGames() {
        axios.get("/api/games")
            .then(response => {

                const games = response.data;


                this.setState({
                    games: games
                });

            });
    }

    addGame(){
        console.log("Adding a game");

        axios.post("/api/games").then(response => {
            console.log("Game added");
            this.fetchAndDisplayGames();
        });

    }

    render(){
        return (
            <div>
                <Title text={this.props.title} />
                <p>{this.state.loadingText}</p>
                <p><button onClick={() => this.addGame()}>Add game</button></p>
                <ul>
                    {this.state.games.map(game => (
                        <li key={game.id}><a href={"/games/"+ game.id}>Game {game.id}</a> is {game.state}</li>
                    ))}

                </ul>
            </div>
        )
    }

}