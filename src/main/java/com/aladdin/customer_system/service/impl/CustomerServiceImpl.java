package com.aladdin.customer_system.service.impl;

import com.aladdin.customer_system.entity.Admin;
import com.aladdin.customer_system.entity.Customer;
import com.aladdin.customer_system.repository.CustomerRepository;
import com.aladdin.customer_system.service.ICustomerService;
import com.aladdin.customer_system.tdo.DTOAdmin;
import com.aladdin.customer_system.tdo.DTOCustomer;
import com.aladdin.customer_system.tdo.DTOCustomerIU;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public DTOCustomer addCustomers(@Valid DTOCustomerIU dtoCustomerIU) {
        DTOCustomer dtoCustomer = new DTOCustomer();
        Customer customer = new Customer();
        DTOAdmin dtoAdmin = new DTOAdmin();
        Admin admin = new Admin();

        BeanUtils.copyProperties(dtoCustomerIU, dtoCustomer);
        BeanUtils.copyProperties(dtoCustomerIU, customer);
        BeanUtils.copyProperties(dtoCustomerIU.getDtoAdminIU(), dtoAdmin);
        BeanUtils.copyProperties(dtoCustomerIU.getDtoAdminIU(), admin);

        dtoCustomer.setAdmin(dtoAdmin);
        customer.setAdmin(admin);
        customerRepository.save(customer);

        return dtoCustomer;
    }


    @Override
    public List<DTOCustomer> getCustomers() {
        List<DTOCustomer> dtoCustomer = new ArrayList<>();
        for (Customer customer : customerRepository.findAll()) {
            DTOCustomer dtoCustomer1 = new DTOCustomer();
            BeanUtils.copyProperties(customer, dtoCustomer1);
            dtoCustomer.add(dtoCustomer1);
        }
        return dtoCustomer;
    }

    @Override
    public List<DTOCustomer> getAllCustomerWithAdmin() {
        List<DTOCustomer> dtoCustomers = new ArrayList<>();
        List<Customer> customers = customerRepository.findAll();
        if (!customerRepository.findAll().isEmpty()) {
            for (Customer customer : customers) {

                DTOCustomer dtoCustomer = new DTOCustomer();
                DTOAdmin dtoAdmin = new DTOAdmin();

                BeanUtils.copyProperties(customer, dtoCustomer);
                BeanUtils.copyProperties(customer.getAdmin(), dtoAdmin);
                dtoCustomer.setAdmin(dtoAdmin);
                dtoCustomers.add(dtoCustomer);
            }
            return dtoCustomers;
        }
        return null;
    }


    @Override
    public DTOCustomer findCustomer(Integer id) {
        Optional<Customer> findCustomer = customerRepository.findById(id);
        if (findCustomer.isPresent()) {
            DTOCustomer dtoCustomer = new DTOCustomer();
            BeanUtils.copyProperties(findCustomer.get(), dtoCustomer);
            return dtoCustomer;
        }
        return null;
    }

    @Override
    public DTOCustomer findCustomerWithAdmin(Integer id) {
        Optional<Customer> findCustomer = customerRepository.findById(id);
        if (findCustomer.isPresent()) {
            DTOCustomer dtoCustomer = new DTOCustomer();
            DTOAdmin dtoAdmin = new DTOAdmin();

            BeanUtils.copyProperties(findCustomer.get(), dtoCustomer);
            BeanUtils.copyProperties(findCustomer.get().getAdmin(), dtoAdmin);
            dtoCustomer.setAdmin(dtoAdmin);
            return dtoCustomer;
        }
        return null;
    }


    @Override
    public List<DTOCustomer> findCustomerByNameAndFirstName(String firstName, String lastName) {
        List<DTOCustomer> findingCustomer = new ArrayList<>();
        if (findingCustomer.isEmpty()) {
            System.out.println("Table is empty!");
        }
        List<DTOCustomer> customers = getCustomers();

        for (DTOCustomer dtoCustomer : customers) {
            boolean matchesFirstName = firstName != null && dtoCustomer.getFirstName().equalsIgnoreCase(firstName);
            boolean matchesLastName = lastName != null && dtoCustomer.getLastName().equalsIgnoreCase(lastName);

            if ((matchesFirstName && matchesLastName) || (matchesFirstName && lastName == null) || (matchesLastName && firstName == null)) {
                findingCustomer.add(dtoCustomer);
            }
        }
        return findingCustomer;
    }

    @Override
    public List<DTOCustomer> findCustomerWithAdminByNameAndFirstName(String firstName, String lastName) {
        List<DTOCustomer> allCustomers = getAllCustomerWithAdmin();

        if (allCustomers.isEmpty()) {
            return Collections.emptyList();
        }

        List<DTOCustomer> filteredCustomers = new ArrayList<>();
        for (DTOCustomer dtoCustomer : allCustomers) {
            boolean matchFirstName = firstName != null && !firstName.isBlank() && dtoCustomer.getFirstName().equalsIgnoreCase(firstName);
            boolean matchLastName = lastName != null && !lastName.isBlank() && dtoCustomer.getLastName().equalsIgnoreCase(lastName);

            if ((matchFirstName && matchLastName) || (matchFirstName && lastName == null) || (matchLastName && firstName == null)) {
                filteredCustomers.add(dtoCustomer);
            }
        }
        return filteredCustomers.isEmpty() ? Collections.emptyList() : filteredCustomers;
    }

    @Override
    public List<DTOCustomer> getSortCustomers() {
        List<DTOCustomer> sortCustomer = getCustomers();
        if (sortCustomer != null) {
            Collections.sort(sortCustomer, Comparator.comparing(DTOCustomer::getRating));
        }
        return sortCustomer;
    }

    @Override
    public DTOCustomer updateCustomer(Integer id, DTOCustomerIU customer) {
        Optional<Customer> optional = customerRepository.findById(id);

        if (optional.isPresent()) {
            Customer customer1 = optional.get();
            BeanUtils.copyProperties(customer, customer1);
            Customer dbCustomer = customerRepository.save(customer1);
            DTOCustomer dtoCustomer = new DTOCustomer();
            BeanUtils.copyProperties(dbCustomer, dtoCustomer);
            return dtoCustomer;
        }
        return null;
    }

    @Override
    public DTOCustomer updateCustomerAndAdmin(Integer id, DTOCustomerIU dtoCustomerI) {
        Optional<Customer> optional = customerRepository.findById(id);
        if (optional.isPresent()) {
            Customer customer = optional.get();
            Admin admin = optional.get().getAdmin();
            BeanUtils.copyProperties(dtoCustomerI, customer);
            BeanUtils.copyProperties(dtoCustomerI.getDtoAdminIU(), admin);
            customer.setAdmin(admin);
            Customer updateCustomer = customerRepository.save(customer);

            DTOCustomer dtoCustomer = new DTOCustomer();
            DTOAdmin dtoAdmin = new DTOAdmin();
            BeanUtils.copyProperties(updateCustomer, dtoCustomer);
            BeanUtils.copyProperties(updateCustomer.getAdmin(), dtoAdmin);
            dtoCustomer.setAdmin(dtoAdmin);

            return dtoCustomer;
        }

        return null;
    }

    @Override
    public void customersReportRating() {
        List<DTOCustomer> customers = getCustomers();

        if (customers.isEmpty()) {
            System.out.println("Table is empty!");
            return;
        }

        double highestRating = Double.MIN_VALUE;
        double lowestRating = Double.MAX_VALUE;
        double totalRating = 0;

        for (DTOCustomer dtoCustomer : customers) {
            double rating = dtoCustomer.getRating();

            if (rating > highestRating) {
                highestRating = rating;
            }
            if (rating < lowestRating) {
                lowestRating = rating;
            }

            totalRating += rating;
        }

        double averageRating = totalRating / customers.size();

        System.out.println("Highest rating -> " + highestRating);
        System.out.println("Lowest rating -> " + lowestRating);
        System.out.println("Average rating -> " + averageRating);
    }


    @Override
    public void deleteCustomer(Integer id) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Delete customer? (yes/no)");

        String response = scanner.nextLine().trim().toLowerCase();
        if ("yes".equals(response)) {
            customerRepository.deleteById(id);
            System.out.println("Customer deleted successfully.");
        } else if ("no".equals(response)) {
            System.out.println("Customer deletion cancelled.");
        } else {
            System.out.println("Invalid response. Please type 'yes' or 'no'.");
        }
    }

    @Override
    public void deleteCustomerWithAdmin(Integer id) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Delete customer? (yes/no)");

        String response = scanner.nextLine().trim().toLowerCase();
        if ("yes".equals(response)) {
            customerRepository.deleteById(id);
            System.out.println("Customer deleted successfully.");
        } else if ("no".equals(response)) {
            System.out.println("Customer deletion cancelled.");
        } else {
            System.out.println("Invalid response. Please type 'yes' or 'no'.");
        }
    }

    @Override
    public void deleteAllCustomers() {
        customerRepository.deleteAll();
        jdbcTemplate.execute("ALTER TABLE customer AUTO_INCREMENT = 1");
        jdbcTemplate.execute("ALTER TABLE admin AUTO_INCREMENT = 1");
    }
}

