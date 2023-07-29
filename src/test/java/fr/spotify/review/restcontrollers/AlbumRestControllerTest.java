package fr.spotify.review.restcontrollers;

import fr.spotify.review.entities.Album;
import fr.spotify.review.entities.Artist;
import fr.spotify.review.repositories.AlbumRepository;
import fr.spotify.review.repositories.ArtistRepository;
import fr.spotify.review.services.AlbumService;
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

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:test.properties")
@NoArgsConstructor
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AlbumRestControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private AlbumService albumService;
    @Autowired
    private AlbumRepository albumRepository;

    @InjectMocks
    private ArtistService artistService;
    @Autowired
    private ArtistRepository artistRepository;

    private Artist artist1, artist2, artist3, artist4, artist5, artist6, artist7;
    private Album album1, album2, album3, album4, album5, album6, album7;
    @BeforeAll
    public void setUp() {

        albumService=new AlbumService(albumRepository);
        artistService=new ArtistService(artistRepository);

        artist1 = artistService.save(new Artist(1L, "Nirvana", null));
        artist2 = artistService.save(new Artist(2L, "Luke", null));
        artist3 = artistService.save(new Artist(3L, "Garbage", null));
        artist4 = artistService.save(new Artist(4L, "Florence & The Machine", null));
        artist5 = artistService.save(new Artist(5L, "The Corrs", null));
        artist6 = artistService.save(new Artist(6L, "Izïa", null));
        artist7 = artistService.save(new Artist(7L, "Noir Désir", null));

        album1 = albumService.save(new Album(1L, "Nevermind", artist1, null));
        album2 = albumService.save(new Album(2L, "Pornographie", artist2, null));
        album3 = albumService.save(new Album(3L, "Beautiful Garbage", artist3, null));
        album4 = albumService.save(new Album(4L, "Lungs", artist4, null));
        album5 = albumService.save(new Album(5L,"In Blue", artist5, null));
        album6 = albumService.save(new Album(6L,"So Much Trouble", artist6, null));
        album7 = albumService.save(new Album(7L,"666.667 Club", artist7, null));
    }


    @Test
    public void shouldReturnAlbum() throws Exception {
//        when(albumService.findById(2L)).thenReturn(Optional.of(album2));
        mockMvc.perform(get("/api/album/{id}", 2L)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.name").value(album2.getName()))
                .andDo(print());
    }


    @Test
    public void testGetAlbums() throws Exception {
        mockMvc.perform(get("/api/album"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAlbumOK() throws Exception {
        mockMvc.perform(get("/api/album/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAlbumKO404Expected() throws Exception {
        mockMvc.perform(get("/api/album/12"))
                .andExpect(status().isNotFound());
    }

}