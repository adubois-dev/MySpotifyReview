package fr.spotify.review.restcontrollers;


import fr.spotify.review.entities.Artist;
import fr.spotify.review.repositories.ArtistRepository;
import fr.spotify.review.services.ArtistService;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@WebMvcTest(ArtistRestController.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@NoArgsConstructor
public class ArtistRestControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ArtistService artistService;
    @Autowired
    private ArtistRepository artistRepository;
    @BeforeAll
    public void setUp() {

        artistService=new ArtistService(artistRepository);

        Artist artist1 = new Artist(1L, "Nirvana", null);
        artistService.save(artist1);

        Artist artist2 = new Artist(2L, "Luke", null);
        artistService.save(artist2);

        Artist artist3 = new Artist(3L, "Garbage", null);
        artistService.save(artist3);

        Artist artist4 = new Artist(4L, "Loreena McKennitt", null);
        artistService.save(artist4);

        Artist artist5 = new Artist(5L, "The Corrs", null);
        artistService.save(artist5);

    }


    @Test
    public void shouldReturnArtist() throws Exception {
        Long id =6L;
        Artist artist= new Artist(6L, "TOOL", null);
        artistService.save(artist);
//        when(artistService.findById(id)).thenReturn(Optional.of(artist));
        mockMvc.perform(get("/api/artist/{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(artist.getName()))
                .andDo(print());
    }


    @Test
    public void testGetArtists() throws Exception {
        mockMvc.perform(get("/api/artist"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetArtistOK() throws Exception {
        mockMvc.perform(get("/api/artist/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetArtistKO404Expected() throws Exception {
        mockMvc.perform(get("/api/artist/12"))
                .andExpect(status().isNotFound());
    }

}