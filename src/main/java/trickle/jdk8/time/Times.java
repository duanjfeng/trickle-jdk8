package trickle.jdk8.time;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.logging.Logger;

/**
 * <p>
 * Title: Times
 * <p>
 * Description: Time representation and computation API demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class Times {

    private static final Logger GLOBAL = Logger.getGlobal();

    /**
     * key.1. 时间点，Instant
     */
    private static void instant() {
        // 时间原点
        Instant epoch = Instant.EPOCH;
        GLOBAL.info(() -> "epoch: " + epoch.toString());
        // 当前时间点
        Instant now = Instant.now();
        GLOBAL.info(() -> "now: " + now.toString());
        // parse
        Instant instant = Instant.parse("2017-07-13T24:00:00Z");
        GLOBAL.info(() -> "instant: " + instant.toString());
    }

    /**
     * key.2. 时间间隔，Duration
     */
    private static void duration() {
        Duration duration = Duration.between(Instant.EPOCH, Instant.now());
        GLOBAL.info(() -> "days to epoch: " + String.valueOf(duration.toDays()));
        Duration plused = duration.plusDays(1);
        GLOBAL.info(() -> "days to epoch 2: " + String.valueOf(plused.toDays()));
    }

    /**
     * key.3. 本地日期/时间，LocalDate/LocalTime/LocalDateTime
     */
    private static void local() {
        LocalDate now = LocalDate.now();
        GLOBAL.info(() -> "now date: " + now.toString());
        boolean leapYear = now.isLeapYear();
        GLOBAL.info(() -> "now date isLeapYear: " + String.valueOf(leapYear));
        LocalDate of = LocalDate.of(1970, 1, 1);
        GLOBAL.info(() -> "of date: " + of.toString());
        LocalDate parse = LocalDate.parse("2017-07-13");
        GLOBAL.info(() -> "parse date: " + parse.toString());

        LocalTime nowTime = LocalTime.now();
        GLOBAL.info(() -> "now time: " + nowTime.toString());

        LocalDateTime nowDateTime = LocalDateTime.now();
        GLOBAL.info(() -> "now dateTime: " + nowDateTime.toString());
    }

    /**
     * key.4. 本地时间间隔，Period
     */
    private static void period() {
        LocalDate epoch = LocalDate.of(1970, 1, 1);
        LocalDate epochPlus = LocalDate.ofEpochDay(1);
        Period period = Period.between(epoch, epochPlus);
        GLOBAL.info(() -> "period: " + period.toString());
        GLOBAL.info(() -> "period days: " + String.valueOf(period.getDays()));
    }

    /**
     * key.5. 带时区的日期/时间，ZonedDateTime/ZoneId
     */
    private static void zoned() {
        ZonedDateTime now = ZonedDateTime.now();
        GLOBAL.info(() -> "now: " + now.toString());
        ZoneId zone = now.getZone();
        GLOBAL.info(() -> "zone: " + zone.toString());
        LocalDateTime localDateTime = now.toLocalDateTime();
        GLOBAL.info(() -> "localDateTime: " + localDateTime.toString());

        LocalDateTime nowDateTime = LocalDateTime.now();
        ZonedDateTime atZone = nowDateTime.atZone(zone);
        GLOBAL.info(() -> "atZone: " + atZone.toString());
    }

    /**
     * key.6. 时间调整，TemporalAdjusters/TemporalAdjuster
     */
    private static void adjuster() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextOrSame = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.THURSDAY));
        GLOBAL.info(() -> "nextOrSame: " + nextOrSame.toString());
        LocalDateTime previous = now.with(TemporalAdjusters.previous(DayOfWeek.THURSDAY));
        GLOBAL.info(() -> "previous: " + previous.toString());
        LocalDateTime firstDayOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
        GLOBAL.info(() -> "firstDayOfMonth: " + firstDayOfMonth.toString());
    }

    public static void main(String[] args) {
        instant();
        duration();
        local();
        zoned();
        period();
        adjuster();
    }

}
