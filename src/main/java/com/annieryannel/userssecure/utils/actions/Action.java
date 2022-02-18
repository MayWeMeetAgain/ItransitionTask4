package com.annieryannel.userssecure.utils.actions;

import com.annieryannel.userssecure.service.UserService;

import java.util.List;

public interface Action {
    void execute(UserService userService, List<String> users);
}
