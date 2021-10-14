package workshop.music_workshop.services;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import workshop.music_workshop.model.entities.ArticleEntity;
import workshop.music_workshop.model.entities.Genre;
import workshop.music_workshop.model.entities.UserEntity;
import workshop.music_workshop.model.view.ArticleViewModel;
import workshop.music_workshop.repositories.ArticleRepository;
import workshop.music_workshop.repositories.UserRepository;
import workshop.music_workshop.services.ArticleServiceImpl;

@ExtendWith(MockitoExtension.class)
class ArticleServiceImplTest {

    private UserEntity testUser1, testUser2;
    private ArticleEntity testArticleEntity1, testArticleEntity2;


    private ArticleServiceImpl serviceToTest;

    @Mock
    ArticleRepository mockArticleRepository;

    @Mock
    UserRepository mockUserRepository;

    @BeforeEach
    public void init() {

        testUser1 = new UserEntity();
        testUser1.setUsername("user 1");

        testArticleEntity1 = new ArticleEntity();
        testArticleEntity1.setTitle("article 1");
        testArticleEntity1.setImageUrl("image 1");
        testArticleEntity1.setGenre(Genre.METAL);
        testArticleEntity1.setContent("content 1");
        testArticleEntity1.setUserEntity(testUser1);

        testUser2 = new UserEntity();
        testUser2.setUsername("user 2");

        testArticleEntity2 = new ArticleEntity();
        testArticleEntity2.setTitle("article 2");
        testArticleEntity2.setImageUrl("image 2");
        testArticleEntity2.setGenre(Genre.JAZZ);
        testArticleEntity2.setContent("content 2");
        testArticleEntity2.setUserEntity(testUser2);

        serviceToTest = new ArticleServiceImpl(new ModelMapper(), mockArticleRepository, mockUserRepository);
    }

    @Test
    public void testFindAll() {

        when(mockArticleRepository.findAll()).thenReturn(List.of(testArticleEntity1, testArticleEntity2));

        List<ArticleViewModel> allModels = serviceToTest.findAllArticles();

        Assertions.assertEquals(2, allModels.size());

        ArticleViewModel model1 = allModels.get(0);
        ArticleViewModel model2 = allModels.get(1);

        // verify model 1
        Assertions.assertEquals(testArticleEntity1.getTitle(), model1.getTitle());
        Assertions.assertEquals(testArticleEntity1.getImageUrl(), model1.getImageUrl());
        Assertions.assertEquals(testArticleEntity1.getGenre(), model1.getGenre());
        Assertions.assertEquals(testArticleEntity1.getContent(), model1.getContent());
        Assertions.assertEquals(testUser1.getUsername(), model1.getAuthor());

        // verify model 2
        Assertions.assertEquals(testArticleEntity2.getTitle(), model2.getTitle());
        Assertions.assertEquals(testArticleEntity2.getImageUrl(), model2.getImageUrl());
        Assertions.assertEquals(testArticleEntity2.getGenre(), model2.getGenre());
        Assertions.assertEquals(testArticleEntity2.getContent(), model2.getContent());
        Assertions.assertEquals(testUser2.getUsername(), model2.getAuthor());
    }

    @Test
    void testLatestArticle() {
        when(mockArticleRepository.findAllOrderByCreatedOn()).thenReturn(List.of(testArticleEntity1));

        List<ArticleViewModel> articleViewOpt = serviceToTest.findLatestArticle();

        Assertions.assertFalse(articleViewOpt.isEmpty());
        ArticleViewModel actualModel = articleViewOpt.get(0);

        Assertions.assertEquals(testArticleEntity1.getTitle(), actualModel.getTitle());
        Assertions.assertEquals(testArticleEntity1.getContent(), actualModel.getContent());
        // and so on...
    }

    @Test
    void testLatestArticle_NotFound() {
        when(mockArticleRepository.findAllOrderByCreatedOn()).thenReturn(new ArrayList<>());//.thenReturn(Optional.empty());

        List<ArticleViewModel> articleViewOpt = serviceToTest.findLatestArticle();
        Assertions.assertTrue(articleViewOpt.isEmpty());
    }

    // TODO: create test for article creation.
}
