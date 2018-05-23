package org.rest.exception;

/**
 * Created by XiuYin.Cui on 2018/5/23.
 */
public class UserDTOException extends RuntimeException {

    private String name;


    public UserDTOException() {

    }

    public UserDTOException(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
