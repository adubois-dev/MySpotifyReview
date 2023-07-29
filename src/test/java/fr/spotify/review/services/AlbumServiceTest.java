package fr.spotify.review.services;

import fr.spotify.review.entities.Album;
import fr.spotify.review.entities.Artist;
import fr.spotify.review.entities.searcharray.AlbumUtils;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
@NoArgsConstructor
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AlbumServiceTest {

    @Autowired
    private ArtistService artistService;

    @Autowired
    private AlbumService albumService;

    private Artist artist1, artist2, artist3, artist4, artist5, artist6, artist7;
    private Album album1, album2, album3, album4, album5, album6, album7;

    @BeforeAll
    public void init(){

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

    @AfterAll
    public void tearDown() {

        albumService.deleteAll();
        artistService.deleteAll();

    }
    @Test
    @Order(1)
    public void should_store_an_album() {
        Long id=8L;
    Album album = new Album(id,"Des  Visages, des figures", artist7, null);
        albumService.save(album);
        assert(album.getName().equals("Des  Visages, des figures"));
        Album album2=albumService.findById(id).get();
        assert(album.equals(album2));

    }

    @Test
    @Order(2)
    public void should_find_all_albums() {

        List<Album> albums = albumService.findAll();

        assert(albums.size()==8);
        assert(albumService.existsByName("Lungs"));
        assert(albumService.existsByName("Beautiful Garbage"));
        assert(albumService.existsByName("Nevermind"));
        assert(albumService.existsById(5L));
        assert(albumService.existsById(6L));
        assert(albumService.existsById(7L));
        assert(album1.equals(AlbumUtils.findById( albums, 1L)));
        assert(album2.equals(AlbumUtils.findById( albums, 2L)));
        assert(album6.equals(AlbumUtils.findByName( albums, "So Much Trouble")));
        assert(album7.equals(AlbumUtils.findByName( albums, "666.667 Club")));

    }


    @Test
    @Order(3)
    public void should_find_album_by_id() {

        Album foundAlbum = albumService.findById(2L).orElseThrow();
        assert(foundAlbum.equals(album2));
    }

    @Test
    @Order(4)
    public void should_find_album_by_name() {

        Album foundAlbum = albumService.findByName("Lungs").orElseThrow();
        assert(foundAlbum.equals(album4));
    }


    @Test
    @Order(5)
    public void should_find_albums_by_name_containing() {

        List albums = albumService.findByNameContains("e");

        assert(albums.size()==6);
        assert(album1.equals(AlbumUtils.findById(albums, album1.getId())));
        assert(album2.equals(AlbumUtils.findById(albums, album2.getId())));
        assert(album3.equals(AlbumUtils.findById(albums, album3.getId())));
        assert(AlbumUtils.findById(albums, album4.getId())==null);
        assert(album5.equals(AlbumUtils.findById(albums, album5.getId())));
        assert(album6.equals(AlbumUtils.findById(albums, album6.getId())));
        assert(AlbumUtils.findById(albums, album7.getId())==null);
    }

    @Test
    @Order(6)
    public void should_update_album_by_id() {
        Album album = albumService.findById(album5.getId()).orElseThrow();
        album.setName("In Green");
        albumService.save(album);

        Album checkAlbum = albumService.findById(album5.getId()).orElseThrow();

        assert(checkAlbum.getId().equals(album5.getId()));
        assert(checkAlbum.getName().equals("In Green"));
    }

    @Test
    @Order(7)
    public void should_delete_album_by_id() {

        albumService.deleteById(album2.getId());

        List albums = albumService.findAll();

        assert(albums.size()==7);
        assert(album1.equals(AlbumUtils.findById(albums, album1.getId())));
        assert(album3.equals(AlbumUtils.findById(albums, album3.getId())));
        assert(AlbumUtils.findById(albums, album2.getId())==null);
        assert(albumService.existsById(album1.getId()));
        assert(!albumService.existsById(album2.getId()));
        assert(albumService.existsById(album3.getId()));
        assert(albumService.existsByName(album1.getName()));
        assert(!albumService.existsByName(album2.getName()));
        assert(albumService.existsByName(album3.getName()));
    }

    @Test
    @Order(8)
    public void should_delete_all_albums() {

        assert(albumService.findAll().size()==7);
        albumService.deleteAll();
        assert(albumService.findAll().size()==0);
    }
}