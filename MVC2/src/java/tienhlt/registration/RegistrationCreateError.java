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
    private String passwordLengthViolent;
    private String fullNameLengthViolent;
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
     * @return the passwordLengthViolent
     */
    public String getPasswordLengthViolent() {
        return passwordLengthViolent;
    }

    /**
     * @param passwordLengthViolent the passwordLengthViolent to set
     */
    public void setPasswordLengthViolent(String passwordLengthViolent) {
        this.passwordLengthViolent = passwordLengthViolent;
    }

    /**
     * @return the fullNameLengthViolent
     */
    public String getFullNameLengthViolent() {
        return fullNameLengthViolent;
    }

    /**
     * @param fullNameLengthViolent the fullNameLengthViolent to set
     */
    public void setFullNameLengthViolent(String fullNameLengthViolent) {
        this.fullNameLengthViolent = fullNameLengthViolent;
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
