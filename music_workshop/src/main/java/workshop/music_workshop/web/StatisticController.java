package workshop.music_workshop.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import workshop.music_workshop.services.LogService;

@Controller
@RequestMapping("/statistics")
public class StatisticController {

    private final LogService logService;
    private final ModelMapper mapper;

    public StatisticController(LogService logService, ModelMapper mapper) {
        this.logService = logService;
        this.mapper = mapper;
    }

    @GetMapping
    public String stats(Model model){
        model.addAttribute("logs", this.logService.findAllLogs());
        return "stats";
    }
}
