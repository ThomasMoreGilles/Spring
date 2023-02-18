package be.thomasmore.party.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class HomeController {
    @GetMapping({"/", "/home"})
    public String home(Model model) {
        final int calculatedValue = 45 * 34;
        model.addAttribute("calculatedValue", calculatedValue);
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        final String name = "Marc De Caluwé";
        final String street = "Ridderstraat 12";
        final String city = "Antwerpen";
        model.addAttribute("name", name);
        model.addAttribute("street", street);
        model.addAttribute("city", city);
        return "about";
    }

    @GetMapping("/pay")
    public String pay(Model model) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yy");
        String formatDateTime = now.format(format);
        LocalDateTime after = now.plusDays(30);
        String formatDateTimeAfter = after.format(format);
        model.addAttribute("formatDateTime", formatDateTime);
        model.addAttribute("formatDateTimeAfter", formatDateTimeAfter);
        return "pay";
    }
}
