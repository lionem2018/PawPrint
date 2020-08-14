package com.haru.pawprint.util;

import com.haru.pawprint.CalendarActivity;
import com.haru.pawprint.adapter.CalendarRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
/**
 * reference: https://woochan-dev.tistory.com/27
 */
public class RecordCalendar {
    public static final int DAYS_OF_WEEK = 7;
    public static final int LOW_OF_CALENDAR = 6;

    public int prevMonthTailOffset = 0;
    public int nextMonthHeadOffset = 0;
    public int currentMonthMaxDate = 0;

    private Calendar calendar = Calendar.getInstance();
    private CalendarActivity calendarActivity;

    public ArrayList<Integer> data = new ArrayList<>();

    public RecordCalendar(){
        calendar.setTime(new Date());
    }

    /**
     * Init calendar.
     * @param activity
     */
    public void initBaseCalendar(CalendarActivity activity){
        this.calendarActivity = activity;
        makeMonthDate();
    }

    /**
     * Change to prev month.
     */
    public void changeToPrevMonth(){
        if(calendar.get(Calendar.MONTH) == 0){
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
            calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        }else{
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        }
        makeMonthDate();
    }

    /**
     * Change to next month.
     */
    public void changeToNextMonth() {
        if(calendar.get(Calendar.MONTH) == Calendar.DECEMBER){
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
            calendar.set(Calendar.MONTH, 0);
        }else {
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        }
        makeMonthDate();
    }

    /**
     * Change month
     * @param year: year to change
     * @param month: month to change
     */

    public void changeMonth(int year, int month){
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        makeMonthDate();
    }

    /**
     * make month date.
     */
    private void makeMonthDate() {

        data.clear();

        calendar.set(Calendar.DATE, 1);

        currentMonthMaxDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        prevMonthTailOffset = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        makePrevMonthTail((Calendar)calendar.clone());
        makeCurrentMonth(calendar);

        nextMonthHeadOffset = LOW_OF_CALENDAR * DAYS_OF_WEEK - (prevMonthTailOffset + currentMonthMaxDate);
        makeNextMonthHead();

        calendarActivity.refreshCurrentMonth(calendar);
    }

    /**
     * Generate data for the last month displayed before the first day of the current calendar.
     */
    private void makePrevMonthTail(Calendar calendar) {
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        int maxDate = calendar.getActualMaximum(Calendar.DATE);
        int maxOffsetDate = maxDate - prevMonthTailOffset;

        for (int i = 1; i <= prevMonthTailOffset; i++)
            data.add(++maxOffsetDate);
    }

    /**
     * Generate data for the current calendar.
     */
    private void makeCurrentMonth(Calendar calendar) {
        for (int i = 1; i <= calendar.getActualMaximum(Calendar.DATE); i++)
            data.add(i);
    }

    /**
     * Generate data for the next month displayed before the last day of the current calendar.
     */
    private void makeNextMonthHead() {
        int date = 1;

        for (int i = 1; i <= nextMonthHeadOffset; i++)
            data.add(date++);
    }

    public Calendar getCalendarInstance(){
        return calendar;
    }
}
