package com.aladdin.customer_system.controller;

import com.aladdin.customer_system.tdo.DTOAdmin;
import com.aladdin.customer_system.tdo.DTOAdminIU;

import java.util.List;

public interface IAdminController {

    DTOAdmin addAdmin(DTOAdminIU adminIU);

    List<DTOAdmin> getAdmin();

    DTOAdmin findAdmin(Integer id);

    List<DTOAdmin> findAdminByNameAndFirstName(String firstName, String lastName);

    List<DTOAdmin> getSortAdmin();

    DTOAdmin updateAdmin(Integer id, DTOAdminIU dtoAdminIU);


    void deleteAdmin(Integer id);

    void deleteAllAdmin();

}
