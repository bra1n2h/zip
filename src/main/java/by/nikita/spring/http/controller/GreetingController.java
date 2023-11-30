package by.nikita.spring.http.controller;

import by.nikita.spring.database.entity.Role;
import by.nikita.spring.dto.UserReadDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@SessionAttributes({"user"})
@RequestMapping("/api/v1")
public class GreetingController {

    @GetMapping("/hello")
    public String hello(
                        Model model,
                        UserReadDto userReadDto) {
        model.addAttribute("user", userReadDto);
        return "greeting/hello";
    }

    @ModelAttribute("roles")
    public List<Role> getRole(){
        return Arrays.asList(Role.values());
    }

    @GetMapping("/hello/{id}")
    public String hello(@RequestParam("age") int age,
                        @RequestHeader("accept") String accept,
                        @CookieValue("JSESSIONID") String jsessionId,
                        @PathVariable("id") int id,
                        Model model,
                        UserReadDto userReadDto) {
        model.addAttribute("user", userReadDto);
        return "greeting/hello";
    }

    @GetMapping("/bye")
    public String bye(@SessionAttribute("user") UserReadDto user){
        return "greeting/bye";
    }

}
