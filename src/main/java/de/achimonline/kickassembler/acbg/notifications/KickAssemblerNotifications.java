package de.achimonline.kickassembler.acbg.notifications;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;

public class KickAssemblerNotifications {

    private static final String GROUP_ID = "KICKASS_NOTIFICATIONS";
    private static final String TITLE = "Kick Assembler";

    public static void notifyError(String content) {
        Notifications.Bus.notify(new Notification(GROUP_ID, TITLE, content, NotificationType.ERROR));
    }

}
