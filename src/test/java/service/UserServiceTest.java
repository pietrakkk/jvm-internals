package service;

import jvminternals.entities.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;


public class UserServiceTest {
    private static final String USER_WITH_1_ADDRESS = "user_with_1_address";
    private static final String USER_WITH_2_ADDRESS = "user_with_2_address_with_long_name";
    private static final String USER_WITH_WITH_AGE_UNDER_18 = "user_with_age_under_18";
    private static final String PERSON_1_SURNAME = "SURNAME1";
    private static final String PERSON_2_SURNAME = "SURNAME2";
    private static final String PERSON_3_SURNAME = "SURNAME3";
    private static final String READ_PERMISSION = "read";
    private static final String WRITE_PERMISSION = "write";
    private static final String DELETE_PERMISSION = "delete";
    private static final String ADMIN_ROLE = "admin";
    private static final String USER_ROLE = "user";
    private static final int PERSON_1_AGE = 21;
    private static final int PERSON_2_AGE = 19;
    private static final int PERSON_3_AGE_UNDER_18 = 16;
    private ArrayList<User> users;
    UserService us;


    @Test
    public void usersWithAddressesCountMoreThanReturnsValidUser() {
        int usersWithMoreThanOneAddressessCount = 1;

        List<User> users = us.findUsersWithAddressesCountMoreThan(1);

        assertEquals(users.size(), usersWithMoreThanOneAddressessCount);
        assertEquals(users.get(0).getName(), USER_WITH_2_ADDRESS);
    }


    @Test
    public void findOldestPersonReturnsValidValue() {

        assertEquals(us.findOldestPerson().getName(), USER_WITH_2_ADDRESS);
    }

    @Test
    public void findUserWithLongestUsernameReturnsValidValue() {

        assertEquals(us.findUserWithLongestUsername().getName(), USER_WITH_2_ADDRESS);
    }

    @Test
    public void getNamesAndSurnamesCommaSeparatedOfAllUsersAbove18ReturnsValidValue() {
        String expectedCommSeparatedValue = USER_WITH_2_ADDRESS + " " + PERSON_2_SURNAME + "," +
                USER_WITH_1_ADDRESS + " " + PERSON_1_SURNAME;

        assertEquals(us.getNamesAndSurnamesCommaSeparatedOfAllUsersAbove18(), expectedCommSeparatedValue);
    }


    @Test
    public void getSortedPermissionsOfUsersWithNameStartingWithReturnsValidValue() {
        String expectedUserPermissionsPrefix = "user_with_2";
        List<String> sortedRoles = Arrays.asList("delete", "read", "write");

        UserService us = new UserService(users);

        assertEquals(us.getSortedPermissionsOfUsersWithNameStartingWith(expectedUserPermissionsPrefix), sortedRoles);
    }

    @Test
    public void getUsersAverageAgeReturnsValidAvg() {
        double expectedAverage = (PERSON_1_AGE + PERSON_2_AGE + PERSON_3_AGE_UNDER_18) / 3;

        Assert.assertEquals(us.getUsersAverageAge(), expectedAverage, Double.MAX_VALUE);

    }

    @Test
    public void groupUsersByRoleReturnsValidMapRoleUserListContainsRole() {
        Map<Role, List<User>> result = us.groupUsersByRole();

        Assert.assertEquals(result.size(), 2);
    }

    @Test
    public void partitionUserByUnderAndOver18() {
        Map<Boolean, List<User>> result = us.partitionUserByUnderAndOver18();

        assertEquals(result.get(true).size(), 2);
        assertEquals(result.get(false).size(), 1);
    }

    @Before
    public void generateFixtures() {
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();

        Permission write = new Permission();
        write.setName(WRITE_PERMISSION);

        Permission read = new Permission();
        read.setName(READ_PERMISSION);

        Permission delete = new Permission();
        delete.setName(DELETE_PERMISSION);

        List<Permission> adminPermissions = new ArrayList<>();
        adminPermissions.add(read);
        adminPermissions.add(write);
        adminPermissions.add(delete);
        Role adminRole = new Role();
        adminRole.setName(ADMIN_ROLE);
        adminRole.setPermissions(adminPermissions);

        List<Permission> userPermissions = new ArrayList<>();
        userPermissions.add(read);
        Role userRole = new Role();
        userRole.setName(USER_ROLE);
        userRole.setPermissions(userPermissions);


        user1.setName(USER_WITH_2_ADDRESS);
        user2.setName(USER_WITH_1_ADDRESS);
        user3.setName(USER_WITH_WITH_AGE_UNDER_18);

        Person person1 = new Person();
        person1.setRole(adminRole);


        Person person2 = new Person();
        person2.setRole(userRole);
        Person person3 = new Person();
        person3.setRole(userRole);

        person1.setAge(PERSON_1_AGE);
        person1.setName(USER_WITH_2_ADDRESS);
        person1.setSurname(PERSON_2_SURNAME);

        person2.setName(USER_WITH_1_ADDRESS);
        person2.setSurname(PERSON_1_SURNAME);
        person2.setAge(PERSON_2_AGE);

        person3.setName(USER_WITH_WITH_AGE_UNDER_18);
        person3.setSurname(PERSON_3_SURNAME);
        person3.setAge(PERSON_3_AGE_UNDER_18);

        List<Address> addresses = new ArrayList<Address>();

        //add couple of random addresses
        for (int i = 0; i < 5; i++) {
            addresses.add(new Address());
        }

        person1.setAddresses(addresses);
        person2.setAddresses(new ArrayList<>());
        person3.setAddresses(new ArrayList<>());

        user1.setPersonDetails(person1);
        user2.setPersonDetails(person2);
        user3.setPersonDetails(person3);

        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        this.users = users;
        this.us = new UserService(users);
    }
}
