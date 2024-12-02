package com.aladdin.customer_system.controller;

import com.aladdin.customer_system.tdo.DTOCustomer;
import com.aladdin.customer_system.tdo.DTOCustomerIU;

import java.util.List;

public interface ICustomerController {

    DTOCustomer addCustomers(DTOCustomerIU customerIU);

    List<DTOCustomer> getCustomers();

    List<DTOCustomer> getAllCustomerWithAdmin();

    DTOCustomer findCustomer(Integer id);

    DTOCustomer findCustomerWithAdmin(Integer id);

    List<DTOCustomer> findCustomerByNameAndFirstName(String firstName, String lastName);

    List<DTOCustomer> findCustomerWithAdminByNameAndFirstName(String firstName, String lastName);

    List<DTOCustomer> getSortCustomers();

    DTOCustomer updateCustomer(Integer id, DTOCustomerIU customer);

    DTOCustomer updateCustomerAndAdmin(Integer id, DTOCustomerIU dtoCustomerI);

    void customersReportRating();

    void deleteCustomer(Integer id);

    void deleteAllCustomer();

}
