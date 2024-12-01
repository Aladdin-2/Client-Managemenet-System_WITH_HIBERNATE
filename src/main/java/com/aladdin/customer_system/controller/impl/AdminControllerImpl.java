package com.aladdin.customer_system.controller.impl;

import com.aladdin.customer_system.controller.IAdminController;
import com.aladdin.customer_system.service.IAdminService;
import com.aladdin.customer_system.service.impl.AdminServiceImpl;
import com.aladdin.customer_system.tdo.DTOAdmin;
import com.aladdin.customer_system.tdo.DTOAdminIU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "Aladdin/holding/admin")
public class AdminControllerImpl implements IAdminController {

    @Autowired
    private IAdminService adminService;

    @Override
    @PostMapping(path = "/addAdmin")
    public DTOAdmin addAdmin(@RequestBody DTOAdminIU adminIU) {

        return adminService.addAdmin(adminIU);
    }

    @Override
    @GetMapping(path = "/allAdmin")
    public List<DTOAdmin> getAdmin() {
        return adminService.getAdmin();
    }

    @Override
    @GetMapping(path = "/find{id}")
    public DTOAdmin findAdmin(@PathVariable(name = "id") Integer id) {
        return adminService.findAdmin(id);
    }

    @Override
    @GetMapping(path = "/find-with=params")
    public List<DTOAdmin> findAdminByNameAndFirstName(@RequestParam(value = "firstName") String firstName,
                                                      @RequestParam(value = "lastName") String lastName) {
        return adminService.findAdminByNameAndFirstName(firstName, lastName);
    }

    @Override
    @GetMapping(path = "/sort-admin")
    public List<DTOAdmin> getSortAdmin() {
        return adminService.getSortAdmin();
    }

    @Override
    @PutMapping(path = "/update/{id}")
    public DTOAdmin updateAdmin(@PathVariable(name = "id") Integer id, @RequestBody DTOAdminIU dtoAdminIU) {
        return adminService.updateAdmin(id, dtoAdminIU);
    }

    @Override
    @DeleteMapping(path = "/delete/{id}")
    public void deleteAdmin(@PathVariable(name = "id") Integer id) {

    }

    @Override
    @DeleteMapping(path = "/delete-all")
    public void deleteAllAdmin() {

    }
}
