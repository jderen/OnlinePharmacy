package com.app.pharmacy.controller;

import com.app.pharmacy.model.*;
import com.app.pharmacy.model.dao.*;
import com.app.pharmacy.model.dto.TransactionDto;
import com.app.pharmacy.model.dto.UserDto;
import com.app.pharmacy.model.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
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

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        User user = userDao.findById(id).orElseThrow(NullPointerException::new);
        return UserDto.getUserDtoByUser(user);
    }

    // napisac metode do Dao
    @GetMapping("/{id}/projects")
    public List<TransactionDto> getTransactionsByUserId(@PathVariable Long id){
        List<Transaction> projects = projectMembersDao.getProjectsByUserId(id);
        return projects
                .stream()
                .map(ProjectDto::getProjectDtoByProject)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/activities")
    public List<TransactionDto> getTransactionsByUserId(@PathVariable Long id){
        List<Activity> activities=activityResultDao.getActivitiesByUserId(id);
        return activities
                .stream()
                .map(ActivityDto::getActivityDtoByActivity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/role")
    public Map<String, Object> isEmployee(@PathVariable Long id){
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
