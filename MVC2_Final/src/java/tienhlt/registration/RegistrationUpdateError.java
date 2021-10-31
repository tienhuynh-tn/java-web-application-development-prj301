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
    private String passwordViolent;

    public RegistrationUpdateError() {
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

    
}
