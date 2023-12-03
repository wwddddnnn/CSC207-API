package app;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;

public class UserInfoRetriever {
    static String username;
    static String userHash;
    static LocalDate startDate;

    static int startDateEpoch;

    public UserInfoRetriever(HashMap<String, Object> connectResult) {
        this.username = (String) connectResult.get("username");
        this.userHash = (String) connectResult.get("userHash");
        this.startDate = (LocalDate) connectResult.get("date");
        long epochUnupdated = startDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
        int epochDate = (int) epochUnupdated/1000;
        this.startDateEpoch = epochDate;
    }

    public static String getUsername(){
        return username;
    }
    public static String getUserHash(){
        return userHash;
    }
    public static LocalDate getStartDate(){
        return startDate;
    }
    public static int getStartDateEpoch(){
        return startDateEpoch;
    }
}
