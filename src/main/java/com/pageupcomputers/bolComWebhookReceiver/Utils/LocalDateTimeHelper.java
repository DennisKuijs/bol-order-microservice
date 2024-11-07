package com.pageupcomputers.bolComWebhookReceiver.Utils;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

public class LocalDateTimeHelper {

    /**
     * Instanstiate the Logger class
     */
    public static Logger logger = LoggerFactory.getLogger(LocalDateTimeHelper.class);

    /**
     * Function to convert a Date object into a LocalDateTime object
     * @param dateTime
     * @return LocalDateTime based on the Date object that was sent
     */
    public static LocalDateTime convertDateToLocalDateTime(Date dateTime) {
        logger.debug(String.format("Date contents: %s", dateTime));
        return dateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * Function to convert a OffsetDateTime (as String) into a LocalDateTime object
     * @param offSetDateTime
     * @return LocalDateTime based on the OffsetDateTime that was sent as a string
     */
    public static LocalDateTime convertOffsetDateTimeToLocalDateTime(String offSetDateTime) {
        logger.debug(String.format("offSetDateTime contents: %s", offSetDateTime));
        return OffsetDateTime.parse(offSetDateTime).atZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
    }
}
 