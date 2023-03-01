package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userServiceList;

    @Autowired
    public UserController(UserService userServiceList) {
        this.userServiceList = userServiceList;
    }

    @GetMapping("")
    public String users(Model model) {  // получим все автомобили
        model.addAttribute("users", userServiceList.getAll());
        return "users";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {  // получим машину по id
        model.addAttribute("user", userServiceList.getById(id));
        return "show";
    }

    @GetMapping("/new")
    public String newCar(Model model){
        model.addAttribute("user", new User());
        return "new";
    }
    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user, //добавляем новую машину
                         BindingResult bindingResult){
        if(bindingResult.hasErrors())               //проверяем на валидацию
            return "new";
        userServiceList.save(user);
        return "redirect:/users"; //при добавлении возвращает нас на главную страницу с списком
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){;
        model.addAttribute("user",userServiceList.getById(id));
        return "edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, //изменяем данные по машине
                         BindingResult bindingResult,
                         @PathVariable("id") int id){
        if (bindingResult.hasErrors())     //проверяем на валидацию
            return "edit";
        userServiceList.update(user);
        return "redirect:/users";

    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){ //удаляем машину
        userServiceList.delete(id);

        return "redirect:/users";
    }
}
