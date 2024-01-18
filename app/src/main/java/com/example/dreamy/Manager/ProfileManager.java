package com.example.dreamy.Manager;

import com.example.dreamy.Model.User;

public class ProfileManager {
    private static ProfileManager instant;
    private User myAccount ;

    public ProfileManager() {
    }

    public static ProfileManager getInstant(){
        if (instant==null){
            instant=new ProfileManager();
        }
        return instant;
    }



    public User getMyAccount() {
        return this.myAccount;
    }

    public void setMyAccount(User myAccount) {
        this.myAccount = myAccount;
    }
}
