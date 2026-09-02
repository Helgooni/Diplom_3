package utils;

import java.util.Random;

public class UserGenerator {

    private static final Random random = new Random();

    public static String generateRandomNumber(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public static User generateValidUser() {
        String number = generateRandomNumber(6);
        return new User(
                "Tepa" + number + "@mail.ru",
                "Tepa" + number,
                "Tepa" + number
        );
    }

    public static User generateUserWithShortPassword() {
        String number = generateRandomNumber(6);
        return new User(
                "Tepa" + number + "@mail.ru",
                generateRandomNumber(5),
                "Tepa" + number
        );
    }

    public static class User {
        private String email;
        private String password;
        private String name;

        public User(String email, String password, String name) {
            this.email = email;
            this.password = password;
            this.name = name;
        }

        public String getEmail() { return email; }
        public String getPassword() { return password; }
        public String getName() { return name; }
    }
}