import React from 'react';
import MovieSearch from './components/MovieSearch';
import './App.css';

// Todo below options in the API

const genres: string[] = [
  "All",
  "Action",
  "Adventure",
  "Animation",
  "Biography",
  "Crime",
  "Drama",
  "Fantasy",
  "History",
  "Mystery",
  "Romance",
  "Sci-Fi",
  "Thriller",
  "Western"
];

const languages: string[] = [
  "All",
  "English",
  "French",
  "German",
  "Hebrew",
  "Italian",
  "Japanese",
  "Latin",
  "Mandarin",
  "Old English",
  "Polish",
  "Quenya",
  "Sindarin",
  "Spanish",
];

function App() {
  return (
    <div className="App">
      <MovieSearch genres={genres} languages = {languages}/>
    </div>
  );
}

export default App;
