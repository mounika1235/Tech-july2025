package in.spring;

import in.dtos.RegisterDTO;
import in.dtos.LoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes("loggedInUser") // Session attribute for logged in user
public class UserController {

    private Map<String, RegisterDTO> userDb = new HashMap<>();

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("registerDTO", new RegisterDTO());
        return "register";
    }

    @PostMapping("/registerdata")
    public String handleRegister(@ModelAttribute("registerDTO") RegisterDTO registerDTO, Model model) {
        if (userDb.containsKey(registerDTO.getEmail())) {
            model.addAttribute("error", "User already exists.");
            return "register";
        }

        // Store user in memory (plain password)
        userDb.put(registerDTO.getEmail(), registerDTO);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "login";
    }

    @PostMapping("/logindata")
    public String handleLogin(@ModelAttribute("loginDTO") LoginDTO loginDTO, Model model) {
        RegisterDTO registeredUser = userDb.get(loginDTO.getEmail());

        if (registeredUser == null) {
            model.addAttribute("error", "User not found.");
            return "login";
        }

        if (!registeredUser.getPassword().equals(loginDTO.getPassword())) {
            model.addAttribute("error", "Invalid password.");
            return "login";
        }

        // Set session attribute for profile
        model.addAttribute("loggedInUser", registeredUser);
        return "redirect:/profile";
    }

    @GetMapping("/profile")
    public String showProfilePage(@ModelAttribute("loggedInUser") RegisterDTO user, Model model) {
        model.addAttribute("user", user);
        return "profile";
    }
}
