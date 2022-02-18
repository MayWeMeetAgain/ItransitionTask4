package com.annieryannel.userssecure.utils.actions;

import com.annieryannel.userssecure.utils.actions.actionsImpl.BlockAction;
import com.annieryannel.userssecure.utils.actions.actionsImpl.DeleteAction;
import com.annieryannel.userssecure.utils.actions.actionsImpl.UnblockAction;

public enum Actions {
    delete("delete") {
        @Override
        public Action makeAction() {
            return new DeleteAction();
        }
    },
    block("block") {
        @Override
        public Action makeAction() {
            return new BlockAction();
        }
    },
    unblock("unblock") {
        @Override
        public Action makeAction() {
            return new UnblockAction();
        }
    };

    private String button;

    public abstract Action makeAction();

    Actions(String button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return button;
    }
}