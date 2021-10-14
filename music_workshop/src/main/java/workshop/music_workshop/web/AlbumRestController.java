package workshop.music_workshop.web;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import workshop.music_workshop.model.view.AlbumViewModel;
import workshop.music_workshop.repositories.AlbumRepository;

@RequestMapping("/albums")
@RestController
public class AlbumRestController {

    private final AlbumRepository albumRepository;
    private final ModelMapper modelMapper;

    public AlbumRestController(AlbumRepository albumRepository,
                               ModelMapper modelMapper) {
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api")
    public ResponseEntity<List<AlbumViewModel>> findAll() {

        List<AlbumViewModel> albumViewModels = albumRepository.
                findAll().
                stream().
                map(ae -> {
                    AlbumViewModel viewModel = modelMapper.map(ae, AlbumViewModel.class);
                    viewModel.setArtist(ae.getArtistEntity().getName());
                    return viewModel;
                }).
                collect(Collectors.toList());

        return ResponseEntity
                .ok()
                .body(albumViewModels);
    }
}