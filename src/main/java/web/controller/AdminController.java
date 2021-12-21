package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.dao.RoleDao;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/")
public class AdminController {
    private final UserService userService;
    private final RoleService roleDao;

    @Autowired
    public AdminController(UserService userService, RoleService roleDao) {
        this.userService = userService;
        this.roleDao = roleDao;
    }

    @GetMapping("/admin")
    public String adminMain(ModelMap model) {
        model.addAttribute("userAll", userService.getAllUsers());
        return "admin";
    }


    @GetMapping("/adduser")
    public String addUser(Model model) {
        model.addAttribute("roles", roleDao.getAllRoles());
        model.addAttribute("user", new User());
        return "adduser";
    }

    @PostMapping("/adduser")
    public String createUser(@ModelAttribute("user") User user, @RequestParam(value = "nameRoles") String[] nameRoles
    ) {

        user.setRoles(roleDao.getSetOfRoles(nameRoles));
        userService.saveUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("roles",roleDao.getAllRoles());
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "nameRoles") String [] nameRoles) {
        user.setRoles(roleDao.getSetOfRoles(nameRoles));

        userService.edit(user);
        return "redirect:/admin";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }
}
