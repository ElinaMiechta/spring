package com.example.rest.controller;

import com.example.rest.controller.dto.AdminDto;
import com.example.rest.controller.dto.ItemDto;
import com.example.rest.model.Item;
import com.example.rest.repository.ItemRepository;
import com.example.rest.service.AdminService;
import com.example.rest.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AdminController {
    private AdminService adminService;
    private ItemService itemService;


    public AdminController(AdminService adminService, ItemService itemService) {
        this.adminService = adminService;
        this.itemService = itemService;
    }

    @GetMapping("/registerAdmin")
    public String register(Model model) {


        model.addAttribute("admin", new AdminDto());
        return "registerAdmin";
    }


    @PostMapping("/registerAdmin")
    public String saveAdminToDB(
            @ModelAttribute("admin") @Valid AdminDto adminDto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        adminService.saveAdministratorToDB(adminDto);
        return "administrator";

    }

    @GetMapping("/loginAdmin")
    public String loginAdmin(Model model) throws Exception {


        return "loginAdmin";
    }


    @GetMapping("/admin")
    public String adminPage() {

        return "administrator";

    }


    @PostMapping("/loginAdmin")
    public String loginAdmin(@ModelAttribute("admin") @Valid AdminDto admin, BindingResult bindingResult
    ,Model model, ItemDto itemDto) throws Exception {


        if (bindingResult.hasErrors()) {
            return "loginAdmin";
        }

        adminService.findAdminByPassword(admin.getPassword());
        model.addAttribute("item",itemDto);


        return "administrator";
    }


    @GetMapping("/upload")
    public String uploadItem(Model model, ItemDto itemDto) throws Exception {
        model.addAttribute("item", itemDto);

        return "upload";

    }

    @PostMapping("/upload")
    public String addItem(@ModelAttribute("item") @Valid ItemDto item, BindingResult bindingResult) throws Exception {


        if (bindingResult.hasErrors()) {

            return "upload";
        }


        itemService.saveItemToDB(item);

        return "index";
    }


}
