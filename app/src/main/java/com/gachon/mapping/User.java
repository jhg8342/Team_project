package com.gachon.mapping;

import android.location.Address;

public class User {
    public String uid;
    public String address;
    public String content;
    public String email;
    public String title;


    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }
    public String getTitle(){
        return title;
    }

    public void setTitle(String Title){
        this.title = Title;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String Email){
        this.email = Email;
    }

    public String getUid(){
        return uid;
    }

    public void setUid(String Uid){
        this.uid = Uid;
    }

    public User(String Uid ,String Address, String Content, String Title) {
        this.uid = Uid;
        this.address = Address;
        this.content = Content;
        this.title = Title;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String Address) {
        this.address = Address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String email) {
        this.content = email;
    }





    @Override
    public String toString() {
        return "Diary{" +
                "Email='" + email + '\'' +
                "address='" + address + '\'' +
                "content='" + content + '\'' +
                "title='" + title + '\'' +
                '}';
    }
}