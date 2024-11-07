package com.pageupcomputers.bolComWebhookReceiver;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import com.pageupcomputers.bolComWebhookReceiver.Utils.LocalDateTimeHelper;

public class LocalDateTimeHelperTests {
    
    @Test
    public void LocalDateTimeHelper_convertDateToLocalDateTime_ReturnsLocalDateTime() {
        
        /**
         * Arrange
         */
        Date date = new GregorianCalendar(2023, Calendar.OCTOBER, 3).getTime();
        
        /**
         * Act
         */
        LocalDateTime localDateTime = LocalDateTimeHelper.convertDateToLocalDateTime(date);

        /**
         * Assert
         */
        Assertions.assertThat(localDateTime).isNotNull();
    }

    @Test
    public void LocalDateTimeHelper_convertStringToLocalDateTime_ReturnsLocalDateTime() {
        
        /**
         * Arrange
         */
        String offsetDateTime = "2023-09-07T11:53:55+02:00";

        /**
         * Act
         */
        LocalDateTime localDateTime = LocalDateTimeHelper.convertOffsetDateTimeToLocalDateTime(offsetDateTime);

        /**
         * Assert
         */
        Assertions.assertThat(localDateTime).isNotNull();
    }
}
