package fr.spotify.review.services;

import fr.spotify.review.entities.Artist;
import fr.spotify.review.entities.searcharray.ArtistUtils;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

//@DataJpaTest
@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
@NoArgsConstructor
public class ArtistServiceTest {

    @Autowired
    private ArtistService artistService;

    @AfterEach
    public void after(){
        artistService.deleteAll();
    }


    @Test
    public void should_find_no_artists_as_database_is_empty() {
        List artists = artistService.findAll();

        assert(artists.size()==0);
    }

    @Test
    public void should_store_an_artist() {
        Artist artist = artistService.save(new Artist("Noir Désir"));

        assert(artist.getName()=="Noir Désir");
        Artist artist2=artistService.findByName("Noir Désir").orElseThrow();
        assert(artist.equals(artist2));

    }

    @Test
    public void should_find_all_artists() {
        List<Artist> initialArtists=  new ArrayList();

        Artist artist1 = new Artist("Nirvana");
        artistService.save(artist1);
        initialArtists.add(artist1);

        Artist artist2 = new Artist("Luke");
        artistService.save(artist2);
        initialArtists.add(artist2);

        Artist artist3 = new Artist("Garbage");
        artistService.save(artist3);
        initialArtists.add(artist3);

        List<Artist> artists = artistService.findAll();

        assert(artists.size()==3);
        assert(artistService.existsByName("Nirvana"));
        assert(artistService.existsByName("Luke"));
        assert(artistService.existsByName("Garbage"));
        assert(artist1.equals(ArtistUtils.findById( artists, artist1.getId())));

    }


    @Test
    public void should_find_artist_by_id() {
        Artist artist1 = new Artist("Nirvana");
        artistService.save(artist1);

        Artist artist2 = new Artist("Luke");
        artistService.save(artist2);

        Artist artist3 = new Artist("Garbage");
        artistService.save(artist3);

        Artist foundArtist = artistService.findById(artist2.getId()).get();

        assert(foundArtist.equals(artist2));
    }

    @Test
    public void should_find_artists_by_name_containing() {
        Artist artist1 = new Artist("Nirvana");
        artistService.save(artist1);

        Artist artist2 = new Artist("Luke");
        artistService.save(artist2);

        Artist artist3 = new Artist("Garbage");
        artistService.save(artist3);

        Artist artist4 = new Artist("Loreena McKennitt");
        artistService.save(artist4);

        Artist artist5 = new Artist("The Corrs");
        artistService.save(artist5);


        List artists = artistService.findByNameContains("a");

        assert(artists.size()==3);
        assert(artist1.equals(ArtistUtils.findById(artists, artist1.getId())));
        assert(ArtistUtils.findById(artists, artist2.getId())==null);
        assert(artist3.equals(ArtistUtils.findById(artists, artist3.getId())));
        assert(artist4.equals(ArtistUtils.findById(artists, artist4.getId())));
        assert(ArtistUtils.findById(artists, artist5.getId())==null);
    }

    @Test
    public void should_update_artist_by_id() {
        Artist artist1 = new Artist("Nirvana");
        artistService.save(artist1);

        Artist artist2 = new Artist("Luke");
        artistService.save(artist2);

        Artist artist3 = new Artist("Garbage");
        artistService.save(artist3);

        Artist art = artistService.findById(artist2.getId()).get();
        art.setName("The Corrs");
        artistService.save(art);

        Artist checkArt = artistService.findById(artist2.getId()).get();

        assert(checkArt.getId().equals(artist2.getId()));
        assert(checkArt.getName().equals("The Corrs"));
    }

    @Test
    public void should_delete_artist_by_id() {
        Artist artist1 = new Artist("Nirvana");
        artistService.save(artist1);

        Artist artist2 = new Artist("Luke");
        artistService.save(artist2);

        Artist artist3 = new Artist("Garbage");
        artistService.save(artist3);

        artistService.deleteById(artist2.getId());

        List artists = artistService.findAll();

        assert(artists.size()==2);
        assert(artist1.equals(ArtistUtils.findById(artists, artist1.getId())));
        assert(artist3.equals(ArtistUtils.findById(artists, artist3.getId())));
        assert(ArtistUtils.findById(artists, artist2.getId())==null);
        assert(artistService.existsById(artist1.getId()));
        assert(!artistService.existsById(artist2.getId()));
        assert(artistService.existsById(artist3.getId()));
    }

    @Test
    public void should_delete_all_artists() {
        Artist artist1 = new Artist("Nirvana");
        artistService.save(artist1);

        Artist artist2 = new Artist("Luke");
        artistService.save(artist2);

        Artist artist3 = new Artist("Garbage");
        artistService.save(artist3);

        assert(artistService.findAll().size()==3);

        artistService.deleteAll();

        assert(artistService.findAll().size()==0);
    }
}