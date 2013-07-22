import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class Dates {

    // Stolen from here: 
    //  http://stackoverflow.com/questions/1555262/calculating-the-difference-between-two-java-date-instances
    public static long getDateDiff(Date start, Date end, TimeUnit timeUnit) {
        long diffInMillis = end.getTime() - start.getTime();
        return timeUnit.convert(diffInMillis, TimeUnit.MILLISECONDS);
    }

    public static int daysAgo(Date d) {
        return daysBetween(d, new Date());
    }

    public static int daysBetween(Date d1, Date d2) {
        long diffInDays = getDateDiff(d1, d2, TimeUnit.DAYS);
        if (diffInDays > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int)diffInDays;
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage: java Dates <format> <startdate> [ <enddate> ]");
            System.exit(-1);
        }

        String fmt = args[0];
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        Date start = sdf.parse(args[1]);
        Date end = new Date();
        if (args.length > 2) {
            end = sdf.parse(args[2]);
        }
        System.out.println(String.format("From %s to %s is %d days", sdf.format(start), sdf.format(end), daysBetween(start, end)));
    }
}
