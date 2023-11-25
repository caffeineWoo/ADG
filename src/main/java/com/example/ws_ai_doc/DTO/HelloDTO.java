package com.example.ws_ai_doc.DTO;

public class HelloDTO {
    private String name;
    private String email;

    public String getName(){
        return name;
    }
    public void setName( String name){
        this.name = name;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail( String email){
        this.email = email;
    }

    @Override
    public String toString() {
        return "HelloDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
