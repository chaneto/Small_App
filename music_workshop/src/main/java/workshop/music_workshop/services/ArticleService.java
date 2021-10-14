package workshop.music_workshop.services;

import workshop.music_workshop.model.services.ArticleServiceModel;
import workshop.music_workshop.model.view.ArticleViewModel;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    List<ArticleViewModel> findLatestArticle();

    List<ArticleViewModel> findAllArticles();

    void createArticle(ArticleServiceModel articleServiceModel);
}
