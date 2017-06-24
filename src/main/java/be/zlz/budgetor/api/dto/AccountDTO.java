package be.zlz.budgetor.api.dto;

import be.zlz.budgetor.api.domain.Account;

public class AccountDTO {

    private long id;

    private long currentValue;

    private String nickName;

    public AccountDTO(){

    }

    public AccountDTO(Account account){
        this.id = account.getId();
        this.currentValue = account.getCurrentValue();
        this.nickName = account.getNickName();
    }

    public long getId() {
        return id;
    }

    public long getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(long currentValue) {
        this.currentValue = currentValue;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
