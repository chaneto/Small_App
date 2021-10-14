package workshop.music_workshop.web;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import workshop.music_workshop.model.binding.AlbumAddBindingModel;
import workshop.music_workshop.model.entities.AlbumEntity;
import workshop.music_workshop.model.services.AlbumServiceModel;
import workshop.music_workshop.model.view.AlbumViewModel;
import workshop.music_workshop.services.AlbumService;
import workshop.music_workshop.services.ArtistService;
import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    private final ModelMapper mapper;
    private final AlbumService albumService;
    private final ArtistService artistService;

    public AlbumController(ModelMapper mapper, AlbumService albumService, ArtistService artistService) {
        this.mapper = mapper;
        this.albumService = albumService;
        this.artistService = artistService;
    }

    @ModelAttribute("albumAddBindingModel")
    public AlbumAddBindingModel createBindingModel(){
        return new AlbumAddBindingModel();
    }

    @GetMapping("/add")
    public String addAlbum(Model model){
      model.addAttribute("artists", this.artistService.getAllName());
      model.addAttribute("date", LocalDate.now());
        return "add-album";
    }

    @PostMapping("/add")
    public String addAllllbum(@Valid AlbumAddBindingModel albumAddBindingModel,
                           RedirectAttributes redirectAttributes, BindingResult bindingResult,
                           @AuthenticationPrincipal UserDetails principal){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("albumAddBindingModel", albumAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.albumAddBindingModel", bindingResult);
            return "redirect:add";
        }
        AlbumServiceModel albumServiceModel = this.mapper.map(albumAddBindingModel, AlbumServiceModel.class);
       albumServiceModel.setUser(principal.getUsername());
        this.albumService.createAlbum(albumServiceModel);
        return "redirect:/home";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model){
        AlbumEntity albumEntity = this.albumService.findById(id).orElse(null);
        AlbumViewModel albumViewModel = this.mapper.map(albumEntity, AlbumViewModel.class);
        albumViewModel.setArtist(albumEntity.getArtistEntity().getName());
        model.addAttribute("album", albumViewModel);
        return "details";
    }
}
