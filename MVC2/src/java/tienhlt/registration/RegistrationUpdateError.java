/*
 * © 2021 tienhuynh.lttn
 * All rights reserved!
 * For more information, please contact via my email: tien.huynhlt.tn@gmail.com
 */
package tienhlt.registration;

/**
 *
 * @author Huynh Le Thuy Tien
 */
public class RegistrationUpdateError {
    private String passwordLengthViolent;

    public RegistrationUpdateError() {
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
}
