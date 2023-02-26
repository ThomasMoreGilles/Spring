package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class VenueController {
    @Autowired
    private VenueRepository venueRepository;

    @GetMapping({"/venuedetails", "/venuedetails/","/venuedetails/{id}"})
    public String venueDetails(Model model,
                               @PathVariable(required = false) Integer id) {
        if (id==null) return "venuedetails";
        Optional<Venue> optionalVenue = venueRepository.findById(id);
        if (optionalVenue.isPresent()) {
            model.addAttribute("venue", optionalVenue.get());
        }
        return "venuedetails";
    }

    @GetMapping({"/venuelist", "/venuelist/","/venuelist/{word}", "/venuelist/{word}/"})
    public String venueList(Model model,
                            @PathVariable(required = false) String word) {
        Iterable<Venue> allVenues = venueRepository.findAll();
        model.addAttribute("venues", allVenues);
        return "venuelist";
    }

    @GetMapping("/venuelist/outdoor/{outdoor}")
    public String venueListOutdoorYes(Model model,
                                      @PathVariable(required = false) String outdoor) {
        Iterable<Venue> venues;
        if (outdoor!=null && outdoor.equals("all")) {
            venues = venueRepository.findAll();
            model.addAttribute("filter", "all");
        } else {
            boolean isOutdoor = true;
            if (outdoor!=null && (outdoor.equals("no") || outdoor.equals("false"))) isOutdoor = false;
            venues = venueRepository.findByOutdoor(isOutdoor);
            model.addAttribute("filter", isOutdoor ? "yes" : "no");
        }
        model.addAttribute("venues", venues);
        return "venuelist";
    }
}
