package be.thomasmore.party.controllers;
import be.thomasmore.party.model.Client;
import be.thomasmore.party.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import java.time.LocalTime;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
@Controller
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    @GetMapping({"/newClient"})
    public String greetingNewClient(Model model) {
        Optional<Client> optionalClient = clientRepository.findById(1);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            String timeOfDayGreeting = getTimeOfDayGreeting();
            String genderPrefix = client.getGender() == 'F' ? "mevrouw " : "meneer ";
            String fullName = client.getName();
//            String firstName = fullName.split(" ")[0];
            String lastName = fullName.split(" ")[1];
            String clientCode = generateClientCode(client);

            String greetingMessage = String.format("%s %s, %s", timeOfDayGreeting, genderPrefix, lastName);
            model.addAttribute("greetingMessage", greetingMessage);
            model.addAttribute("clientCode", clientCode);
        } else {
            model.addAttribute("errorMessage", "deze klant kan niet gevonden worden");
        }
        model.addAttribute("newClient");
        return "newClient";
    }
    @GetMapping({"/showCode"})
    public String showSecretCode(Model model) {

        Optional<Client> optionalClient = clientRepository.findById(1);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            String fullName = client.getName();
            String firstName = fullName.split(" ")[0];
            String lastName = fullName.split(" ")[1];
            String clientCode = generateClientCode(client);

            String greetingMessage = String.format("%s %s, uw secretcode is %s", firstName, lastName, clientCode);
            model.addAttribute("greetingMessage", greetingMessage);
        } else {
            model.addAttribute("errorMessage", "deze klant kan niet gevonden worden");
        }
        model.addAttribute("showSecretCode");
        return "showCode";
    }
    private String generateClientCode(Client client) {
        String firstName = client.getName().substring(0, 2).toLowerCase();
        String lastName = client.getName().substring(client.getName().length() - 1).toLowerCase();
        String birthDay = String.format("%02d", client.getBirthdate().getDayOfMonth());
        int birthYear = client.getBirthdate().getYear();
        int randomNumber = ThreadLocalRandom.current().nextInt(1, birthYear + 1);
        return firstName + lastName + birthDay + randomNumber;
    }
    private String getTimeOfDayGreeting() {
        LocalTime currentTime = LocalTime.now();
        String timeOfDayGreeting;
        if (currentTime.isBefore(LocalTime.NOON)) {
            timeOfDayGreeting = "goedemorgen ";
        } else if (currentTime.isBefore(LocalTime.of(17, 0))) {
            timeOfDayGreeting = "goedemiddag ";
        } else {
            timeOfDayGreeting = "goedenavond ";
        }
        return  timeOfDayGreeting;
    }
}