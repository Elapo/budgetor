package be.zlz.budgetor.api.dto;

public class PasswordDTO {

    private String currentPass;

    private String newPass;

    private String resetToken;

    public PasswordDTO() {
    }

    public PasswordDTO(String currentPass, String newPass, String resetToken) {
        this.currentPass = currentPass;
        this.newPass = newPass;
        this.resetToken = resetToken;
    }

    public String getCurrentPass() {
        return currentPass;
    }

    public void setCurrentPass(String currentPass) {
        this.currentPass = currentPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }
}
