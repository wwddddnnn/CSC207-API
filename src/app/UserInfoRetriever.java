package app;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

public class UserInfoRetriever {
    static String username;
    static String userHash;
    static Date startDate;

    static int startDateEpoch;

    private UserInfoRetriever(){}

    public static void store(HashMap<String, Object> connectResult) {
        username = (String) connectResult.get("username");
        userHash = (String) connectResult.get("userHash");
        startDate = new Date();
        long epochUnupdated = startDate.getTime() / 1000;
        int epochDate = (int) epochUnupdated;
        startDateEpoch = epochDate;
    }

    public static String getUsername(){
        return username;
    }
    public static String getUserHash(){
        return userHash;
    }
    public static Date getStartDate(){
        return startDate;
    }
    public static int getStartDateEpoch(){
        return startDateEpoch;
    }
}
