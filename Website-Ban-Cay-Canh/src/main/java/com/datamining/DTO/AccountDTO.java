package com.datamining.DTO;

import com.datamining.entity.Account;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class AccountDTO {
    private Integer id;
    private String username;
    private String password;
    private Boolean is_delete = false;
    @Temporal(TemporalType.DATE)
    private Date create_date = new Date();
    @Temporal(TemporalType.DATE)
    private Date update_date = new Date();

    public static AccountDTO convert(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setUsername(account.getUsername());
        accountDTO.setPassword(account.getPassword());
        accountDTO.setIs_delete(account.getIs_delete());
        accountDTO.setCreate_date(account.getCreate_date());
        accountDTO.setUpdate_date(account.getUpdate_date());
        return accountDTO;
    }
}
