package workshop.music_workshop.web;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import workshop.music_workshop.model.binding.ArticleAddBindingModel;
import workshop.music_workshop.model.services.ArticleServiceModel;
import workshop.music_workshop.model.view.ArticleViewModel;
import workshop.music_workshop.services.ArticleService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final ModelMapper modelMapper;

    public ArticleController(ArticleService articleService, ModelMapper modelMapper) {
        this.articleService = articleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public String getAllArticles(Model model) {
        List<ArticleViewModel> articleOpt = articleService.findLatestArticle();
        if (!articleOpt.isEmpty()) {
            model.addAttribute("latestArticle", articleOpt);
        }
        return "all-articles";
    }

    @GetMapping("/add")
    public String addArticle() {
        return "add-article";
    }

    @ModelAttribute("articleAddBindingModel")
    public ArticleAddBindingModel articleAddBindingModel() {
        return new ArticleAddBindingModel();
    }

    @PostMapping("/add")
    public String addArticleConfirm( @Valid ArticleAddBindingModel articleAddBindingModel,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes,
                                     @AuthenticationPrincipal UserDetails principal) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("articleAddBindingModel", articleAddBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.articleAddBindingModel", bindingResult);

            return "redirect:/articles/add";
        }
        ArticleServiceModel articleServiceModel = modelMapper.map(
                articleAddBindingModel,
                ArticleServiceModel.class);
        articleServiceModel.setUser(principal.getUsername());
        articleService.createArticle(articleServiceModel);

        return "redirect:/articles/all";
    }

}
