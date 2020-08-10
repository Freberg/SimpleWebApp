import React, { Component } from 'react';
import Movie from './Movie';

interface MovieData {
  rows: Array<Movie>;
}

interface MovieData {
  rows: Array<Movie>;
}

const tableHeaders: string[] = ["Title", "Released", "Genre", "Language", "Director", "Actors"]

export default function MovieTable({rows} : MovieData) {
  rows.map(row => {
    console.log(row)
  })

  return (
    <div>
      <table id='movieTable'>
        <tbody>
          <tr>
            {tableHeaders.map((header, index) => {
              return <th key={index}>{header}</th>
            })}
          </tr>
          {rows.map((movie, index) => {
            return (            
              <tr key={movie.imdbID}>
                <td>{movie.Title}</td>
                <td>{movie.Released}</td>
                <td>{movie.Genre.join(", ")}</td>
                <td>{movie.Language.join(", ")}</td>
                <td>{movie.Director}</td>
                <td>{movie.Actors}</td>
              </tr>
            )
          })}
        </tbody>
      </table>
    </div>
  );
}