import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Main {
    static enum Language {
        ENGLISH, HUNGARIAN, GERMAN;
    }
    static enum Month {
        JANUARY("január", "Januar"),
        FEBRUARY("február", "Februar"),
        MARCH("március", "März");
        private String hungarianName;
        private String germanName;
        private Month(String hungarianName, String germanName) {
            this.hungarianName = hungarianName;
            this.germanName = germanName;
        }
        public String getEnglishName() {
            String name = toString();
            return name.charAt(0) + name.substring(1).toLowerCase();
        }
        public String getHungarianName() { return hungarianName; }
        public String getGermanName() { return germanName; }
        public String getName(Language language) {
            switch (language) {
                case ENGLISH: return getEnglishName();
                case HUNGARIAN: return getHungarianName();
                case GERMAN: return getGermanName();
            }
            throw new IllegalArgumentException("Unsupported language");
        }
    }
    public static enum Weekday {
        SUNDAY("vasárnap"),
        MONDAY("hétfő"),
        TUESDAY("kedd"),
        WEDNESDAY("szerda"),
        THURSDAY("csütörtök"),
        FRIDAY("péntek"),
        SATURDAY("szombat");
        private String hungarianName;
        private Weekday(String hungarianName) {
            this.hungarianName = hungarianName;
        }

        /**
         *
         * @param month
         * @param day
         * @return
         */
        public static Weekday getWeekday(int month, int day)
        {
            Calendar cal = new GregorianCalendar(
                    new GregorianCalendar().get(Calendar.YEAR),
                    month-1, day);
            switch (cal.get(Calendar.DAY_OF_WEEK)) {
                case Calendar.MONDAY: return MONDAY;
                case Calendar.TUESDAY: return TUESDAY;
                case Calendar.WEDNESDAY: return WEDNESDAY;
                case Calendar.THURSDAY: return THURSDAY;
                case Calendar.FRIDAY: return FRIDAY;
                case Calendar.SATURDAY: return SATURDAY;
                case Calendar.SUNDAY: return SUNDAY;
            }
            throw new IllegalArgumentException("Unsupported Weekday");
        }
        public boolean isThisDay(int month, int day)
        {
            return this == getWeekday(month, day);
        }
        public static boolean isEarlierThan(Weekday day1, Weekday day2)
        {
            return day1.ordinal() < day2.ordinal();
        }
        public static Weekday nextDay(Weekday day) { return nextDay(day, 1); }
        public static Weekday nextDay(Weekday day, int n) {
            int val = (day.ordinal() + n) % Weekday.values().length;
            return values()[val < 0 ? val+Weekday.values().length : val];
        }
        public String getEnglishName() {
            String name = toString();
            return name.charAt(0) + name.substring(1).toLowerCase();
        }
        public String getHungarianName() { return hungarianName; }
        public String getName(Language language) {
            switch (language) {
                case ENGLISH: return getEnglishName();
                case HUNGARIAN: return getHungarianName();
            }
            throw new IllegalArgumentException("Unsupported language");
        }
    }

    /**
     * This is the main function
     * @param args this is a zero arg function
     */
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Enum Tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 640);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.getContentPane().add(panel);
        for (Weekday day : Weekday.values()) {
            JButton button = new JButton(day.toString());
            button.setSize(600,40);
            panel.add(button);
            String msg = "English name: " + day.getEnglishName() + "\n" +
                    "Hungarian name: " + day.getHungarianName() + "\n" +
                    "Is this day Sept 19: " + day.isThisDay(9, 19) + "\n" +
                    "Is Earlier than Wednesday: " + Weekday.isEarlierThan(day, Weekday.WEDNESDAY) + "\n" +
                    "Next day: " + Weekday.nextDay(day) + "\n" +
                    "100 days later: " + Weekday.nextDay(day, 100) + "\n" +
                    "2 days before: " + Weekday.nextDay(day, -2) + "\n";
            button.addActionListener((e) -> {
                JOptionPane.showMessageDialog(frame,
                        msg
                );
            });
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame,
                            msg
                    );
                }
            });
        }
        frame.setVisible(true);
    }
}