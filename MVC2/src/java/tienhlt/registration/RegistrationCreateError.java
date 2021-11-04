/*
 * © 2021 tienhuynh.lttn
 * All rights reserved!
 * For more information, please contact via my email: tien.huynhlt.tn@gmail.com
 */
package tienhlt.registration;

import java.io.Serializable;

/**
 *
 * @author Huynh Le Thuy Tien
 */
public class RegistrationCreateError implements Serializable{
    private String usernameIsExisted;
    private String usernameLengthViolent;
    private String passwordViolent;
    private String firstNameLengthViolent;
    private String middleNameLengthViolent;
    private String lastNameLengthViolent;
    private String confirmNotMatch;

    public RegistrationCreateError() {
    }

    /**
     * @return the usernameIsExisted
     */
    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    /**
     * @param usernameIsExisted the usernameIsExisted to set
     */
    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }

    /**
     * @return the usernameLengthViolent
     */
    public String getUsernameLengthViolent() {
        return usernameLengthViolent;
    }

    /**
     * @param usernameLengthViolent the usernameLengthViolent to set
     */
    public void setUsernameLengthViolent(String usernameLengthViolent) {
        this.usernameLengthViolent = usernameLengthViolent;
    }

    /**
     * @return the passwordViolent
     */
    public String getPasswordViolent() {
        return passwordViolent;
    }

    /**
     * @param passwordViolent the passwordViolent to set
     */
    public void setPasswordViolent(String passwordViolent) {
        this.passwordViolent = passwordViolent;
    }

    /**
     * @return the firstNameLengthViolent
     */
    public String getFirstNameLengthViolent() {
        return firstNameLengthViolent;
    }

    /**
     * @param firstNameLengthViolent the firstNameLengthViolent to set
     */
    public void setFirstNameLengthViolent(String firstNameLengthViolent) {
        this.firstNameLengthViolent = firstNameLengthViolent;
    }

    /**
     * @return the middleNameLengthViolent
     */
    public String getMiddleNameLengthViolent() {
        return middleNameLengthViolent;
    }

    /**
     * @param middleNameLengthViolent the middleNameLengthViolent to set
     */
    public void setMiddleNameLengthViolent(String middleNameLengthViolent) {
        this.middleNameLengthViolent = middleNameLengthViolent;
    }

    /**
     * @return the lastNameLengthViolent
     */
    public String getLastNameLengthViolent() {
        return lastNameLengthViolent;
    }

    /**
     * @param lastNameLengthViolent the lastNameLengthViolent to set
     */
    public void setLastNameLengthViolent(String lastNameLengthViolent) {
        this.lastNameLengthViolent = lastNameLengthViolent;
    }

    /**
     * @return the confirmNotMatch
     */
    public String getConfirmNotMatch() {
        return confirmNotMatch;
    }

    /**
     * @param confirmNotMatch the confirmNotMatch to set
     */
    public void setConfirmNotMatch(String confirmNotMatch) {
        this.confirmNotMatch = confirmNotMatch;
    }
}
