package POJO.Login;
import java.util.List;

public class LoginResponse {
    private boolean isSuccess;
    private boolean twoFactorRequired;
    private String accessToken;
    private String refreshToken;
    private boolean isLockedOut;
    private boolean isPasswordExpired;
    private List<String> roleNames;

    // No-args constructor
    public LoginResponse() {}

    // Getters and Setters
    public boolean isSuccess() {
        return isSuccess;
    }
    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public boolean isTwoFactorRequired() {
        return twoFactorRequired;
    }
    public void setTwoFactorRequired(boolean twoFactorRequired) {
        this.twoFactorRequired = twoFactorRequired;
    }

    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public boolean isLockedOut() {
        return isLockedOut;
    }
    public void setLockedOut(boolean lockedOut) {
        isLockedOut = lockedOut;
    }

    public boolean isPasswordExpired() {
        return isPasswordExpired;
    }
    public void setPasswordExpired(boolean passwordExpired) {
        isPasswordExpired = passwordExpired;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }
    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }
}
