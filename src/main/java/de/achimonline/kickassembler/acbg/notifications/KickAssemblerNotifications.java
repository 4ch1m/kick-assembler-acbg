package de.achimonline.kickassembler.acbg.notifications;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import org.apache.commons.lang3.StringUtils;

public class KickAssemblerNotifications {
    private static final String GROUP_ID = "KICKASS_NOTIFICATIONS";
    private static final String TITLE = "Kick Assembler";

    public static void notifyError(String... content) {
        notify(NotificationType.ERROR, content);
    }

    public static void notifyWarning(String... content) {
        notify(NotificationType.WARNING, content);
    }

    private static void notify(NotificationType notificationType, String... content) {
        Notifications.Bus.notify(new Notification(GROUP_ID, TITLE, StringUtils.join(content, System.lineSeparator()), notificationType));
    }
}
