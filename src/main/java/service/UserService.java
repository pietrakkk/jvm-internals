package service;


import jvminternals.entities.Permission;
import jvminternals.entities.Person;
import jvminternals.entities.Role;
import jvminternals.entities.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class UserService implements UserServiceInterface {

    private List<User> users;
    private static final String SEPARATOR = ",";
    private static final String SPACE = " ";

    public UserService(List<User> users) {
        this.users = users;
    }

    public List<User> findUsersWithAddressesCountMoreThan(int numberOfAddresses) {
        return users.stream()
                .filter(u -> u.getPersonDetails().getAddresses().size() > numberOfAddresses)
                .collect(Collectors.<User>toList());
    }

    public Person findOldestPerson() {
        User u = users.stream()
                .max(Comparator.comparing(user -> user.getPersonDetails().getAge()))
                .get();
        return u.getPersonDetails();
    }

    public User findUserWithLongestUsername() {
        return users.stream()
                .max((o1, o2) -> o1.getName().length() > o1.getName().length() ? 1 : 0)
                .get();
    }

    public String getNamesAndSurnamesCommaSeparatedOfAllUsersAbove18() {
        return users.stream()
                .filter(u -> u.getPersonDetails().getAge() > 18)
                .map(user -> user.getPersonDetails().getName() + SPACE + user.getPersonDetails().getSurname())
                .collect(Collectors.joining(SEPARATOR));
    }

    public List<String> getSortedPermissionsOfUsersWithNameStartingWith(String prefix) {
        List<Permission> permissions = new ArrayList<>();

        users.stream().filter(user -> user.getName().startsWith(prefix))
                .map(user -> user.getPersonDetails().getRole().getPermissions())
                .forEach(permissions::addAll);

        return permissions
                .stream()
                .map(Permission::getName)
                .sorted(String::compareTo)
                .collect(Collectors.toList());
    }

    public double getUsersAverageAge() {
        return users
                .stream()
                .mapToDouble(user -> user.getPersonDetails().getAge())
                .average()
                .getAsDouble();
    }

    public void printCapitalizedPermissionNamesOfUsersWithSurnameStartingWith(String suffix) {
        users.stream().filter(user -> user.getName().endsWith(suffix))
                .map(u1 -> u1.getName().toUpperCase())
                .forEach(System.out::println);
    }

    public Map<Role, List<User>> groupUsersByRole() {
        return users.stream()
                .collect(Collectors.groupingBy(user -> user.getPersonDetails().getRole()));
    }

    public Map<Boolean, List<User>> partitionUserByUnderAndOver18() {
        return users.stream()
                .collect(Collectors.groupingBy(user -> user.getPersonDetails().getAge() >= 18));
    }
}
