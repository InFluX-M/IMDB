package com.example.imdb.controller;

import com.example.imdb.model.TitleType;
import com.example.imdb.model.responses.MovieResponse;
import com.example.imdb.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


//@ContextConfiguration("/applicationContext.xml")
@ContextConfiguration(classes = {MovieController.class})
@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private MovieService service;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    void addMovie() {

    }

    @Test
    void updateMovie() {
    }

    @Test
    void deleteMovie() {
    }

    @Test
    void getMovies() throws Exception {

        String uri = "/movies";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Logger logger = Logger.getLogger("MovieControllerTest");
        logger.info("1::"+mvcResult.getResponse().getContentAsString());
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        Product[] productlist = super.mapFromJson(content, Product[].class);
//        assertTrue(productlist.length > 0);

    }

    @Test
    void getMovie() {
    }

    @Test
    void getDirectors() {
    }

    @Test
    void getActors() {
    }

    @Test
    void getRating() {
    }

    @Test
    void getMoviesByActor() {
    }

    @Test
    void getMoviesByDirector() {
    }

    @Test
    void getMoviesByTitle() {
    }

    @Test
    void getMoviesByType() {
    }

    @Test
    void getMoviesByGenre() {
    }

    @Test
    void getMoviesByYear() {
    }

    @Test
    void getMoviesByEndYear() {
    }

    @Test
    void testGetMoviesByYear() {
    }

    @Test
    void testGetMoviesByEndYear() {
    }
}