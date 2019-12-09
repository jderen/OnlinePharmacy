package com.app.pharmacy.controller;

import com.app.pharmacy.model.*;
import com.app.pharmacy.model.dao.*;
import com.app.pharmacy.model.dto.ProductDto;
import com.app.pharmacy.model.dto.TransactionDto;
import com.app.pharmacy.model.dto.UserDto;
import com.app.pharmacy.model.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserDao userDao;
    private final TransactionDao transactionDao;
    private final ProductDao productDao;

    @Autowired
    public UserController(UserDao userDao, TransactionDao transactionDao, ProductDao productDao) {
        this.userDao = userDao;
        this.transactionDao = transactionDao;
        this.productDao = productDao;
    }

    @GetMapping("/addSamples")
    public String add() {
        List<User> users = createSampleUsers();
        users.forEach(userDao::insert);
        return "{\"message\": \"samples-added\"}";
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        List<User> users = userDao.findAll();
        return users
                .stream()
                .map(UserDto::getUserDtoByUser)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/transactions")
    public List<TransactionDto> getTransactionsByUserId(@PathVariable Long id){
        List<Transaction> transactions = transactionDao.getTransactionsByUserId(id);
        List<TransactionDto> transactionDtos = transactions
                .stream()
                .map(TransactionDto::getTransactionDtoByTransaction)
                .collect(Collectors.toList());
        transactionDtos.forEach(transactionDto -> {
            List<Product> products = new ArrayList<>();
            List<ProductDto> productDtos = new ArrayList<>();
            transactionDto.getProductIds().
                    forEach(idProduct -> products.add(productDao.findById(idProduct).orElseThrow(NullPointerException::new)));
            productDtos = products.stream().map(ProductDto::getProductDtoByProduct).collect(Collectors.toList());
            transactionDto.setProductsAll(productDtos);
        });
        return transactionDtos;
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        User user = userDao.findById(id).orElseThrow(NullPointerException::new);
        return UserDto.getUserDtoByUser(user);
    }

    @GetMapping("/{id}/role")
    public Map<String, Object> findRoleOfUser(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Role role = userDao.getRole(id);
        response.put("id", id);
        response.put("role", role);
        return response;
    }


    private List<User> createSampleUsers() {
        User u1 = new User(null,null,"Michal","Banka", Role.EMPLOYEE,
                null,null,null);
        User u2 = new User(null,null,"Jan","Nowak", Role.EMPLOYEE,
                null,null,null);
        User u3 = new User(null,null,"Robert","Koza", Role.EMPLOYEE, null,null,null);
        User u4 = new User(null,null,"Jaroslaw","Kaczynski", Role.EMPLOYEE, null,null,null);
        User u5 = new User(null,null,"Mariusz","Pudzianowski", Role.EMPLOYEE, null,null,null);
        User u6 = new User(null,null,"Robert","Kubica", Role.EMPLOYEE, null,null,null);
        User u7 = new User(null,null,"Marian","Bulka", Role.EMPLOYEE, null,null,null);
        User u8 = new User(null,null,"Ola","Syr", Role.EMPLOYEE, null,null,null);
        return Arrays.asList(u1,u2,u3,u4,u5,u6,u7,u8);
    }
}
