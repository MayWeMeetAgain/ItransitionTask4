package com.annieryannel.userssecure.utils.actions.actionsImpl;

import com.annieryannel.userssecure.service.UserService;
import com.annieryannel.userssecure.utils.actions.Action;

import java.util.List;

public class DeleteAction implements Action {
    @Override
    public void execute(UserService userService, List<String> userIds) {
        for (String userId : userIds) {
            userService.deleteUser(Long.parseLong(userId));
        }
    }
}
