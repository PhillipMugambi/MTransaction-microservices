package com.MTransaction.microservice.utils;

public class HelperUtility {
    public static String checkPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("^((\\+)?2547|2541|07|01)\\d{8}$")) {
            if (phoneNumber.startsWith("+254")) {
                return phoneNumber.substring(1);
            }
            else if (phoneNumber.startsWith("01")) {
                return "2541" + phoneNumber.substring(2);
            }
            else if (phoneNumber.startsWith("07")) {
                return "2547" + phoneNumber.substring(2);
            }
            else {
                return phoneNumber;
            }
        }
        else {
            return "Invalid phone number";
        }
    }
}
