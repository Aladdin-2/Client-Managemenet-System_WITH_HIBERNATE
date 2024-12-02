package com.aladdin.customer_system.controller.impl;

import com.aladdin.customer_system.controller.ICustomerController;
import com.aladdin.customer_system.service.ICustomerService;
import com.aladdin.customer_system.tdo.DTOCustomer;
import com.aladdin.customer_system.tdo.DTOCustomerIU;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/Aladdin/holding/customer")
public class CustomerControllerImpl implements ICustomerController {

    @Autowired
    private ICustomerService iCustomerService;


    @PostMapping(path = "/add")
    @Override
    public DTOCustomer addCustomers(@RequestBody @Valid DTOCustomerIU customerUI) {
        return iCustomerService.addCustomers(customerUI);
    }

    @Override
    @GetMapping(path = "/all")
    public List<DTOCustomer> getCustomers() {
        return iCustomerService.getCustomers();
    }

    @Override
    @GetMapping(path = "/all/customers-with-admin")
    public List<DTOCustomer> getAllCustomerWithAdmin() {
        return iCustomerService.getAllCustomerWithAdmin();
    }

    @Override
    @GetMapping(path = "/find/{id}")
    public DTOCustomer findCustomer(@PathVariable(name = "id") Integer id) {
        return iCustomerService.findCustomer(id);
    }

    @Override
    @GetMapping(path = "/find/with-admin/{id}")
    public DTOCustomer findCustomerWithAdmin(@PathVariable(name = "id") Integer id) {
        return iCustomerService.findCustomerWithAdmin(id);
    }

    @Override
    @GetMapping(path = "/with-params")
    public List<DTOCustomer> findCustomerByNameAndFirstName(@RequestParam(name = "firstName", required = false) String firstName,
                                                            @RequestParam(name = "lastName", required = false) String lastName) {
        return iCustomerService.findCustomerByNameAndFirstName(firstName, lastName);
    }

    @Override
    @GetMapping(path = "/with-params-withAdmin")
    public List<DTOCustomer> findCustomerWithAdminByNameAndFirstName(@RequestParam(name = "firstName", required = false) String firstName,
                                                                     @RequestParam(name = "lastName", required = false) String lastName) {
        return iCustomerService.findCustomerWithAdminByNameAndFirstName(firstName, lastName);
    }

    @Override
    @GetMapping(path = "/sortByRating")
    public List<DTOCustomer> getSortCustomers() {
        return iCustomerService.getSortCustomers();
    }

    @Override
    @PutMapping(path = "/update/{id}")
    public DTOCustomer updateCustomer(@PathVariable(name = "id") Integer id, @RequestBody DTOCustomerIU customer) {
        return iCustomerService.updateCustomer(id, customer);
    }

    @Override
    @PutMapping(path = "/update-with-admin/{id}")
    public DTOCustomer updateCustomerAndAdmin(@PathVariable(name = "id") Integer id, @RequestBody DTOCustomerIU dtoCustomerI) {
        return iCustomerService.updateCustomerAndAdmin(id, dtoCustomerI);
    }

    @Override
    @GetMapping(path = "/report/rating")
    public void customersReportRating() {
        iCustomerService.customersReportRating();
    }

    @Override
    @DeleteMapping(path = "/delete/{id}")
    public void deleteCustomer(@PathVariable(name = "id") Integer id) {
        iCustomerService.deleteCustomer(id);
    }

    @Override
    @DeleteMapping(path = "/deleteAll")
    public void deleteAllCustomer() {
        iCustomerService.deleteAllCustomers();
    }
}
