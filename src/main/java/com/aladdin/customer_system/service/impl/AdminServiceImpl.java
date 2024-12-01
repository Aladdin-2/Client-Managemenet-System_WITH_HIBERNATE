package com.aladdin.customer_system.service.impl;

import com.aladdin.customer_system.entity.Admin;
import com.aladdin.customer_system.repository.AdminRepository;
import com.aladdin.customer_system.service.IAdminService;
import com.aladdin.customer_system.tdo.DTOAdmin;
import com.aladdin.customer_system.tdo.DTOAdminIU;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public DTOAdmin addAdmin(DTOAdminIU adminIU) {
        DTOAdmin dtoAdmin = new DTOAdmin();
        Admin admin = new Admin();

        BeanUtils.copyProperties(adminIU, dtoAdmin);
        BeanUtils.copyProperties(adminIU, admin);
        adminRepository.save(admin);

        return dtoAdmin;
    }

    @Override
    public List<DTOAdmin> getAdmin() {
        List<DTOAdmin> allAdmin = new ArrayList<>();

        for (Admin admin : adminRepository.findAll()) {
            DTOAdmin dtoAdmin = new DTOAdmin();
            BeanUtils.copyProperties(admin, dtoAdmin);
            allAdmin.add(dtoAdmin);
        }

        return allAdmin;
    }

    @Override
    public DTOAdmin findAdmin(Integer id) {

        Optional<Admin> adminOptional = adminRepository.findById(id);

        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            DTOAdmin dtoAdmin = new DTOAdmin();
            BeanUtils.copyProperties(admin, dtoAdmin);
            return dtoAdmin;
        } else {
            return null;
        }
    }

    @Override
    public List<DTOAdmin> findAdminByNameAndFirstName(String firstName, String lastName) {
        List<DTOAdmin> findingAdmin = new ArrayList<>();
        List<DTOAdmin> dtoAdmins = getAdmin();

        for (DTOAdmin dtoAdmin : dtoAdmins) {
            boolean matchesFirstName = firstName != null && dtoAdmin.getFirstName().equalsIgnoreCase(firstName);
            boolean matchesLastName = lastName != null && dtoAdmin.getLastName().equalsIgnoreCase(lastName);

            if ((matchesFirstName && matchesLastName) || (matchesFirstName && lastName == null) || (matchesLastName && firstName == null)) {
                findingAdmin.add(dtoAdmin);
            }
        }
        return findingAdmin;
    }

    @Override
    public List<DTOAdmin> getSortAdmin() {
        List<DTOAdmin> admins = getAdmin();

        Collections.sort(admins, Comparator.comparing(DTOAdmin::getFirstName));
        return admins;
    }

    @Override
    public DTOAdmin updateAdmin(Integer id, DTOAdminIU dtoAdminIU) {
        Optional<Admin> optionalDTOAdminIU = adminRepository.findById(id);
        if (optionalDTOAdminIU.isPresent()) {
            Admin admin = new Admin();
            BeanUtils.copyProperties(dtoAdminIU, admin);
            adminRepository.save(admin);
            DTOAdmin dtoAdmin = new DTOAdmin();
            BeanUtils.copyProperties(dtoAdmin, dtoAdmin);
            return dtoAdmin;
        }
        return null;
    }


    @Override
    public void deleteAdmin(Integer id) {
        adminRepository.deleteById(id);
    }

    @Override
    public void deleteAllAdmin() {
        adminRepository.deleteAll();
        jdbcTemplate.execute("ALTER TABLE customer AUTO_INCREMENET=1");
    }
}
