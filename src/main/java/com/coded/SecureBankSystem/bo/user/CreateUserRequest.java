package com.coded.SecureBankSystem.bo.user;

//@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateUserRequest {
    private String name;
    private String email;
    private String phone;
    private String status ;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.String getEmail() {
        return email;
    }

    public void setEmail(java.lang.String email) {
        this.email = email;
    }

    public java.lang.String getPhone() {
        return phone;
    }

    public void setPhone(java.lang.String phone) {
        this.phone = phone;
    }


}
