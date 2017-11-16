package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.exception.ServiceException;

import java.util.List;

public interface CustomerService {
    List<Customer> findAllCustomerByAccountId(int accountId);

    Customer findByCustomerId(int customerId);

    /**
     * 查找所有的客户来源
     * @return
     */
    List<String> findAllSource ();

    /**
     * 查找所有的行业
     * @return
     */
    List<String> findAllTrade ();

    void saveNewCustomer(Customer customer);

    /**
     * 修改客户信息
     * @param customer
     */
    void update(Customer customer);

    void deleteCustomerByCustomerId(Integer customerId);

    List<Customer> findAll();

    List<Customer> findAllPublicCustomer();

}
