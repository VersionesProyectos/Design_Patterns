package com.epam.automation.model;

public class User {
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String date;

    private User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.jobTitle = builder.jobTitle;
        this.date = builder.date;
    }

    public static class UserBuilder {
        private String firstName;
        private String lastName;
        private String jobTitle;
        private String date;

        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder jobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
            return this;
        }

        public UserBuilder date(String date) {
            this.date = date;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public UserBuilder build() {
        return  new UserBuilder();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getDate() {
        return date;
    }
}
