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
    @GetMapping(path = "/withEntr/{id}")
    public DTOCustomer getCustomerWithEntrepreneur(@PathVariable(name = "id") Integer id) {
        return iCustomerService.getCustomerWithEntrepreneur(id);
    }

    @Override
    @GetMapping(path = "/all")
    public List<DTOCustomer> getCustomers() {
        return iCustomerService.getCustomers();
    }

    @Override
    @GetMapping(path = "/find/{id}")
    public DTOCustomer findCustomer(@PathVariable(name = "id") Integer id) {
        return iCustomerService.findCustomer(id);
    }

    @Override
    @GetMapping(path = "/find-with-params")
    public List<DTOCustomer> findCustomerByNameAndFirstName(@RequestParam(name = "firstName", required = false) String firstName,
                                                            @RequestParam(name = "lastName", required = false) String lastName) {
        return iCustomerService.findCustomerByNameAndFirstName(firstName, lastName);
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
