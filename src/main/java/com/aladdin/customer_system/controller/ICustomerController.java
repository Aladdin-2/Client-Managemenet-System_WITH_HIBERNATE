package com.aladdin.customer_system.controller;

import com.aladdin.customer_system.tdo.DTOCustomer;
import com.aladdin.customer_system.tdo.DTOCustomerIU;

import java.util.List;

public interface ICustomerController {

    DTOCustomer addCustomers(DTOCustomerIU customerIU);

    DTOCustomer getCustomerWithEntrepreneur(Integer id);

    List<DTOCustomer> getCustomers();

    DTOCustomer findCustomer(Integer id);

    List<DTOCustomer> findCustomerByNameAndFirstName(String firstName, String lastName);

    List<DTOCustomer> getSortCustomers();

    DTOCustomer updateCustomer(Integer id, DTOCustomerIU customer);

    void customersReportRating();

    void deleteCustomer(Integer id);

    void deleteAllCustomer();

}
