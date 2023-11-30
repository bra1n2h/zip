package by.nikita.spring.http.controller;

import by.nikita.spring.database.entity.Role;
import by.nikita.spring.dto.PageResponse;
import by.nikita.spring.dto.UserCreateEditDto;
import by.nikita.spring.dto.UserFilter;
import by.nikita.spring.dto.UserReadDto;
import by.nikita.spring.service.CompanyService;
import by.nikita.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final CompanyService companyService;

    @GetMapping()
    public String findAll(Model model, UserFilter filter, Pageable pageable){
//        model.addAttribute("users", userService.findAll(filter, pageable));
        Page<UserReadDto> page = userService.findAll(filter, pageable);
        model.addAttribute("filter", filter);
        model.addAttribute("users", PageResponse.of(page));
        return "user/users";
    }

    @GetMapping("/registration")
    public String registration(Model model, @ModelAttribute("user") UserCreateEditDto user){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("companies", companyService.findAll());
        return "user/registration";
    }

//    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id,
                           Model model,
                           @CurrentSecurityContext SecurityContext securityContext,
                           @AuthenticationPrincipal UserDetails userDetails){
        return userService.findById(id)
                .map(user -> {
                            model.addAttribute("user", user);
                            model.addAttribute("roles", Role.values());
                            model.addAttribute("companies", companyService.findAll());
                            return "user/user";
                        })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public String create(@ModelAttribute @Validated UserCreateEditDto user,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/users/registration";
        }
        UserReadDto dto = userService.create(user);
        return "redirect:/users/" + dto.getId();
    }

//    @PutMapping("/{id}")
    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute @Validated UserCreateEditDto user){
        return userService.update(id, user)
                .map(it -> "redirect:/users/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

//    @DeleteMapping("/{id}")
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        if(!userService.delete(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/users";
    }

//    @ExceptionHandler(Exception.class)
//    public String handleException(Exception exception, HttpServletRequest request){
//        log.error("Failed to return response", exception);
//        return "error/error500";
//    }
}
