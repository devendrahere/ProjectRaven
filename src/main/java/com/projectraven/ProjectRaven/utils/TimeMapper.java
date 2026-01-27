package com.projectraven.ProjectRaven.utils;

import java.time.Instant;

public class TimeMapper {
    private TimeMapper(){

    }
    public static long toEpochMillis(Instant instant){
        return instant==null?0L:instant.toEpochMilli();
    }
}
