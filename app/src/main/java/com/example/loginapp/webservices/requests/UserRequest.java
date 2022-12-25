package com.example.loginapp.webservices.requests;

import java.io.Serializable;

public class UserRequest implements Serializable {

        private String username;
        private String password;
        private int organizationId;

        public UserRequest(String username, String password) {
                this.username = username;
                this.password = password;
                this.organizationId = 5;
        }

        public String getUsername() {
                return username;
        }

        public String getPassword() {
                return password;
        }

        public int getOrganizationId() {
                return organizationId;
        }

}
