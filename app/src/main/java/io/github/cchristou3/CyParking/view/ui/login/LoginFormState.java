package io.github.cchristou3.CyParking.view.ui.login;

import androidx.annotation.Nullable;

/**
 * Purpose: <p>Data validation state of the login form.</p>
 *
 * @author Charalambos Christou
 * @version 1.0 1/11/20
 */
class LoginFormState {
    @Nullable
    private final Integer usernameError;
    @Nullable
    private final Integer passwordError;
    @Nullable
    private final Integer roleError;

    private final boolean isDataValid;

    /**
     * Constructor used when there is an error in the LoginState (E.g. pass too short, no username, etc.)
     *
     * @param usernameError The id of the error related to the username.
     * @param passwordError The id of the error related to the password.
     * @param roleError     The id of the error related to the role.
     */
    LoginFormState(@Nullable Integer usernameError, @Nullable Integer passwordError, @Nullable Integer roleError) {
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.roleError = roleError;
        this.isDataValid = false;
    }

    /**
     * Constructor used when the LoginState is valid.
     *
     * @param isDataValid true of the data in the form is valid
     */
    LoginFormState(boolean isDataValid) {
        this.usernameError = null;
        this.passwordError = null;
        this.roleError = null;
        this.isDataValid = isDataValid;
    }

    /**
     * Getters & Setters
     */
    @Nullable
    Integer getUsernameError() {
        return usernameError;
    }

    @Nullable
    Integer getPasswordError() {
        return passwordError;
    }

    boolean isDataValid() {
        return isDataValid;
    }

    @Nullable
    public Integer getRoleError() {
        return roleError;
    }
}