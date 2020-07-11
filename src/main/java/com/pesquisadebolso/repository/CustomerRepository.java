package com.pesquisadebolso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pesquisadebolso.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

    public List<Customer> findByCompanyId(final Integer idCompany);

    @Query(nativeQuery = true, value = "select * from customers c " +
                                       "inner join users u on u.id = c.user_id " +
                                       "where u.role = 'CT' and company_id= :companyId " +  
                                       "and mail = :mail and password = :password ")
    public List<Customer> findByCustomersByUser(@Param("companyId") final Integer companyId, 
                                                @Param("mail") final String mail, 
                                                @Param("password") final String password);
    
}