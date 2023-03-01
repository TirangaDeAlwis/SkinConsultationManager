package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WestminsterSkinConsultationManagerTest {
    @Test
    public void emailTest(){
        String email = "tiranga@gmail.com";
        boolean expected = true;
        boolean actual = WestminsterSkinConsultationManager.isEmailValid(email);
        assertEquals(expected,actual);
    }
    @Test
    public void mobileNumberTest(){
        String mobileNumber = "94723026667";
        boolean expected = true;
        boolean actual = WestminsterSkinConsultationManager.isContactNumberValid(mobileNumber);
        assertEquals(expected,actual);
    }
}