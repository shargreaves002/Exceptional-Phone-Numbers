package com.zipcodewilmington.phone;

import com.zipcodewilmington.exceptions.InvalidPhoneNumberFormatException;
import com.zipcodewilmington.tools.RandomNumberFactory;

import java.util.logging.Logger;

/**
 * Created by leon on 5/1/17.
 */
public final class PhoneNumberFactory {
    private static final Logger logger = Logger.getGlobal();

    private PhoneNumberFactory() {
        /**This constructor is private
         * This class is uninstantiable */
    }

    /**
     * @param phoneNumberCount - number of PhoneNumber objects to instantiate
     * @return array of randomly generated PhoneNumber objects
     */
    public static PhoneNumber[] createRandomPhoneNumberArray(int phoneNumberCount) {
        PhoneNumber[] phoneNumbers = new PhoneNumber[phoneNumberCount];
        for (int x = 0; x < phoneNumberCount; x++){
            phoneNumbers[x] = createRandomPhoneNumber();
        }
        return phoneNumbers;
    }

    /**
     * @return an instance of PhoneNumber with randomly generated phone number value
     */
    public static PhoneNumber createRandomPhoneNumber() {
        return createPhoneNumberSafely(RandomNumberFactory.createInteger(100, 999), RandomNumberFactory.createInteger(100, 999), RandomNumberFactory.createInteger(1000, 9999));
    }


    /**
     * @param areaCode          - 3 digit code
     * @param centralOfficeCode - 3 digit code
     * @param phoneLineCode     - 4 digit code
     * @return a new phone number object
     */
    public static PhoneNumber createPhoneNumberSafely(int areaCode, int centralOfficeCode, int phoneLineCode) {
        PhoneNumber phoneNumber;
        String number1 = Integer.toString(areaCode);
        String number2 = Integer.toString(centralOfficeCode);
        String number3 = Integer.toString(phoneLineCode);
        String phoneNumberString = "(" + number1 + ")-" + number2 + "-" + number3;
        try {
            phoneNumber = createPhoneNumber(phoneNumberString);
            return phoneNumber;
        } catch (InvalidPhoneNumberFormatException e) {
            logger.info(phoneNumberString + " is not a valid phone number.");
            return null;
        }
    }

    /**
     * @param phoneNumberString - some String corresponding to a phone number whose format is `(###)-###-####`
     * @return a new phone number object
     * @throws InvalidPhoneNumberFormatException - thrown if phoneNumberString does not match acceptable format
     */
    public static PhoneNumber createPhoneNumber(String phoneNumberString) throws InvalidPhoneNumberFormatException {
        logger.info("Attempting to create a new phone number object with a value of " + phoneNumberString);
        return new PhoneNumber(phoneNumberString);
    }
}
