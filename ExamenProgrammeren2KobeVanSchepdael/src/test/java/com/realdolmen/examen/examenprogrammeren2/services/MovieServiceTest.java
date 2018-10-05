package com.realdolmen.examen.examenprogrammeren2.services;

import com.realdolmen.examen.examenprogrammeren2.exceptions.NoQueryPossibleException;
import com.realdolmen.examen.examenprogrammeren2.repositories.MovieRepository;
import com.realdolmen.examen.examenprogrammeren2.domain.Movie;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {
    //TODO
    //Opdracht 3 Unit testen met Mockito : Er is al een deel van de service test opgesteld. Alle gegevens die je nodig hebt staan al ingevuld.
    //26 : annoteer alle methoden met de juiste annotaties, en private attributen (waar nodig), zodat ze aanzien worden als test methoden. Boven sommige methoden staan tips, bekijk ze goed. 
    //27 : tracht alle methoden die hieronder beschreven zijn in te vullen zodat ze slagen. Tips kan je vinden in de methoden zelf.
    
    private MovieService movieService;
    private List<Movie> movies;
    private Movie movieObjectToTest;
    
    @Mock
    private MovieRepository movieRepository;

    @Before
    public void init() {
        movieService = new MovieService(movieRepository);
        movies = new ArrayList<>();
        movieObjectToTest = new Movie(1,"comedy", "Ace ventura");
    }

    //TODO maak een test die nagaat of MovieService de juiste methode opvraagt bij MovieRepository, 
    //zorg voor de duidelijke structuur van een test methode
    @Test
    public void findAllMoviesTest() throws Exception {
        List<Movie> movies = new ArrayList<>();
        when(movieRepository.find("SELECT*FROM movies")).thenReturn(movies);
        List<Movie> result = movieService.findAllMovies();
        assertEquals(result, movies);
    }
    
    //TODO maak een test die nagaat of de MovieService de juiste methode opvraagt bij MovieRepository
    //Zorg dat MovieRepository een NoQueryPossibleException gooit
    @Test
    public void findAllMoviesTestNoQueryPossibleExceptionThrow() throws Exception{
        List<Movie> movies = new ArrayList<>();
        when(movieRepository.find("SELECT*FROM movies")).thenThrow(NoQueryPossibleException.class);
        List<Movie> result = movieService.findAllMovies();
       
        
    }
    
    @Test
    public void findMovieByIdTest() throws Exception {
        List<Movie> movies = new ArrayList<>();
        Movie movie = null;
        movies.add(movie);
        when(movieRepository.find("SELECT*FROM movies where id=0")).thenReturn(movies);
        List<Movie> moviesResult = new ArrayList<>();
        Movie result = movieService.findMovieById(0);
        moviesResult.add(result);
        
        assertEquals(movies, moviesResult);
    }
    
    //TODO Maak een test die ervoor zorgt dat het resultaat van de getAllPalindromeTitles() één of meerdere resultaten kan weergegeven.
    //onthou dat in de methode getAllPalindromeTitles een andere private methode wordt aangesproken die op zijn beurt nog een andere methode van de MovieService aanspreekt
    //zorg dat je er zeker rekening mee houdt dat ook hier ergens MovieRepository iets gevraagd zal worden.
    //vb van movies met palindrome titles = "boob", "aka","dad","ROTOR"
    //Voeg met andere woorden een of meerdere movies toe die een titel hebben dat een palindrome voorsteld
    @Test
    public void getAllPalindromeTitlesTestTitleAddedToList() throws NoQueryPossibleException {
        List<Movie> movies = new ArrayList<>();
        Movie movie = new Movie("boob");
        String movieTitle = movie.getTitle();
        List<String> palindromes = new ArrayList<>();
        palindromes.add(movieTitle);
        when(movieRepository.find("SELECT * FROM movies  WHERE title like = palindrome")).thenReturn(movies);
        
        List<String> result = movieService.getAllPalindromeTitles();
        result.add("boob");
        assertEquals(result,palindromes);
    }
   
    //TODO test de methode getAllPalindromeTitles, waarbij de MovieRepository methode die wordt opgeroepen een NoQueryPossibleException gooit
    //kijk goed naar de methodes in MovieService, kijk wat er gebeurd en zorg dat je resultaat net is zoals er verwacht wordt
    //Onthou hierbij dat private methoden niet getest worden, maar de publieke methode 
    @Test
    public void getAllPalindromeTitlesTestNoQueryPossibleExceptionThrownAndCatchedTitleNotAdded() {
        
    }
    
    
    //TODO test the isAPalindrome method gebruik hiervoor "saippuakivikauppias"
    //nice to know, this is the longest palindrome according to the guiness book of records
    @Test
    public void isAPalinDromeTestTrue() {
        String string = new String("saippuakivikauppias");
        String stringWrong = new String("saippuakifsqfqsfvikauppias");
        movieService.isAPalindrome(string);
        assertTrue(movieService.isAPalindrome(string));
        assertFalse(movieService.isAPalindrome(stringWrong));
    }   

}
