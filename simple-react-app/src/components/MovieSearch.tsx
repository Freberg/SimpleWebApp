import React, { Component } from 'react';
import Movie from './Movie';
import MovieTable from './MovieTable';
import Dropdown from 'react-bootstrap/esm/Dropdown';
import Button from 'react-bootstrap/esm/Button';

interface MovieSearchConfig {
    genres: Array<string>,
    languages: Array<string>;
}

interface MovieSearchState {
    movies : Array<Movie>
    genre: string
    language: string
}

class MovieSearch extends Component<MovieSearchConfig, MovieSearchState> {

    config: MovieSearchConfig;
    searchRef: React.RefObject<HTMLInputElement>;

    constructor(config: MovieSearchConfig, state: MovieSearchState) {
        super(config, state)
        this.searchRef = React.createRef();
        this.config = config;
        this.state = {
            movies: new Array<Movie>(),
            genre: "All",
            language: "All"
        }
    }

    onSearchClick = () => {
        const input = this.searchRef.current.value;
        const {genre, language} = this.state;

        let genreParameter = genre !== 'All' ? 'genre=' + genre: '';
        let languageParameter = language !== 'All' ? 'language=' + language: '';

        console.log(genreParameter)
        console.log(languageParameter)

        fetch(`http://localhost:8080/movies/search?freeText=${input}&${genreParameter}&${languageParameter}`)
            .then(response => {
                if (response.status !== 200) {
                    console.log(`Failed to fetch movies, error code: ${response.status}`)
                    return
                }
                response.json().then((data: Movie[]) => {
                    this.setState({
                        movies: data
                    })
                })
            })
    }

    onGenreSelect = (eventKey: string, event: Object) => {
        console.log(eventKey)
        console.log(event)
        this.setState({
            genre: eventKey
        });
    }

    onLanguageSelect = (eventKey: string, event: Object) => {
        console.log(eventKey)
        console.log(event)
        this.setState({
            language: eventKey
        });
    }

    render() {
        const {movies, genre, language} = this.state;
        const {genres, languages} = this.config;
        return (
            <div>

                <input type="text" ref={this.searchRef}/>

                <Button onClick={this.onSearchClick} className='searchButton'>
                    Search
                </Button>

                <Dropdown onSelect={this.onGenreSelect} className='searchButton'>
                    <Dropdown.Toggle id="dropdown-genre">
                        Genre | {genre}
                    </Dropdown.Toggle>
                    <Dropdown.Menu>
                        {genres.map((genre, index) => {
                            return <Dropdown.Item href="#/action-"{...index} eventKey={genre}>{genre}</Dropdown.Item>
                        })}
                    </Dropdown.Menu>
                </Dropdown>

                <Dropdown onSelect={this.onLanguageSelect} className='searchButton'>
                    <Dropdown.Toggle id="dropdown-language">
                        Language | {language}
                    </Dropdown.Toggle>
                    <Dropdown.Menu>
                        {languages.map((language, index) => {
                            return <Dropdown.Item href="#/action-"{...index} eventKey={language}>{language}</Dropdown.Item>
                        })}
                    </Dropdown.Menu>
                </Dropdown>

                <MovieTable rows = {movies}/>
            </div>
        );
    }
}

export default MovieSearch