package com.aladdin.customer_system.service;

import com.aladdin.customer_system.tdo.DTOAdmin;
import com.aladdin.customer_system.tdo.DTOAdminIU;
import java.util.List;

public interface IAdminService {
    DTOAdmin addAdmin(DTOAdminIU adminIU);

    List<DTOAdmin> getAdmin();

    DTOAdmin findAdmin(Integer id);

    List<DTOAdmin> findAdminByNameAndFirstName(String firstName, String lastName);

    List<DTOAdmin> getSortAdmin();

    DTOAdmin updateAdmin(Integer id, DTOAdminIU dtoAdminIU);

    void adminReportRating();

    void deleteAdmin(Integer id);

    void deleteAllAdmin();
}
