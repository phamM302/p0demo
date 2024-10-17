package com.revature.models;

public class LoginDTO {
    //we just need this object to store id and first_name to help with login
    private int admin_id;
    private String first_name;

    public LoginDTO(int admin_id, String first_name) {
        this.admin_id = admin_id;
        this.first_name = first_name;
    }

    public LoginDTO() {
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "admin_id=" + admin_id +
                ", first_name='" + first_name + '\'' +
                '}';
    }
}
