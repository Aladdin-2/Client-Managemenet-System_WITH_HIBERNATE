package com.aladdin.customer_system.controller.impl;

import com.aladdin.customer_system.controller.IAdminController;
import com.aladdin.customer_system.tdo.DTOAdmin;
import com.aladdin.customer_system.tdo.DTOAdminIU;

import java.util.List;

public class AdminControllerImpl implements IAdminController {
    @Override
    public DTOAdmin addAdmin(DTOAdminIU adminIU) {
        return null;
    }

    @Override
    public List<DTOAdmin> getAdmin() {
        return List.of();
    }

    @Override
    public DTOAdmin findAdmin(Integer id) {
        return null;
    }

    @Override
    public List<DTOAdmin> findAdminByNameAndFirstName(String firstName, String lastName) {
        return List.of();
    }

    @Override
    public List<DTOAdmin> getSortAdmin() {
        return List.of();
    }

    @Override
    public DTOAdmin updateAdmin(Integer id, DTOAdminIU dtoAdminIU) {
        return null;
    }

    @Override
    public void adminReportRating() {

    }

    @Override
    public void deleteAdmin(Integer id) {

    }

    @Override
    public void deleteAllAdmin() {

    }
}
