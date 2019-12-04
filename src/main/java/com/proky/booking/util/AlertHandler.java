package com.proky.booking.util;

import com.proky.booking.util.constans.Attributes;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Component
public class AlertHandler {
    public void setAlertData(boolean alertSuccess, String alertMessage, Model model) {
        String alertType = alertSuccess ? Attributes.ALERT_SUCCESS : Attributes.ALERT_ERROR;

        model.addAttribute(alertType, true);
        model.addAttribute(Attributes.ALERT_MESSAGE, alertMessage);
    }

    public void setAlertData(boolean alertSuccess, String alertMessage, RedirectAttributes redirectAttributes) {
        String alertType = alertSuccess ? Attributes.ALERT_SUCCESS : Attributes.ALERT_ERROR;

        redirectAttributes.addFlashAttribute(alertType, true);
        redirectAttributes.addFlashAttribute(Attributes.ALERT_MESSAGE, alertMessage);
    }
}
