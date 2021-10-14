package workshop.music_workshop.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import workshop.music_workshop.model.entities.ArticleEntity;
import workshop.music_workshop.model.entities.UserEntity;
import workshop.music_workshop.model.services.ArticleServiceModel;
import workshop.music_workshop.model.view.ArticleViewModel;
import workshop.music_workshop.repositories.ArticleRepository;
import workshop.music_workshop.repositories.UserRepository;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ModelMapper modelMapper;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleServiceImpl(ModelMapper modelMapper,
                              ArticleRepository articleRepository,
                              UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ArticleViewModel> findLatestArticle() {

        List<ArticleViewModel> articleViewModels = new ArrayList<>();
        for(ArticleEntity out : this.articleRepository.findAllOrderByCreatedOn()){
            ArticleViewModel articleViewModel = this.modelMapper.map(out, ArticleViewModel.class);
            articleViewModel.setAuthor(out.getUserEntity().getUsername());
            articleViewModels.add(articleViewModel);
        }
        return articleViewModels;
    }

    @Override
    public List<ArticleViewModel> findAllArticles() {

        List<ArticleViewModel> articleViewModels = new ArrayList<>();
        for(ArticleEntity out : this.articleRepository.findAll()){
            ArticleViewModel articleViewModel = this.modelMapper.map(out, ArticleViewModel.class);
            articleViewModel.setAuthor(out.getUserEntity().getUsername());
            articleViewModels.add(articleViewModel);
        }
        return articleViewModels;
    }

    @Override
    public void createArticle(ArticleServiceModel articleServiceModel) {

        ArticleEntity articleEntity = modelMapper.map(articleServiceModel, ArticleEntity.class);
        articleEntity.setCreatedOn(LocalDate.now());

        UserEntity creator = userRepository.
                findByUsername(articleServiceModel.getUser());
        if(creator == null){
            throw new IllegalArgumentException("Creator " + articleServiceModel.getUser() + " could not be found");
        }

        articleEntity.setUserEntity(creator);

        articleRepository.save(articleEntity);
    }
}
