package com.aladdin.customer_system.service.impl;

import com.aladdin.customer_system.repository.AdminRepository;
import com.aladdin.customer_system.service.IAdminService;
import com.aladdin.customer_system.tdo.DTOAdmin;
import com.aladdin.customer_system.tdo.DTOAdminIU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private AdminRepository entrepreneurRepository;


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
