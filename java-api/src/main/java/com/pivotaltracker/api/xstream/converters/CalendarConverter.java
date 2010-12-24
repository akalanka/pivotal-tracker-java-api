/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pivotaltracker.api.xstream.converters;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

public final class CalendarConverter implements Converter {

    private String defaultDateFormat = "yyyy/MM/dd HH:mm:ss z";
    private List<String> acceptableDateFormats = new ArrayList<String>();
    private boolean lenient = false;

    /**
     * Construct a CalendarConverter.
     *
     * @param defaultFormat the default format
     * @param acceptableFormats fallback formats
     * @param lenient the lenient setting of {@link SimpleDateFormat#setLenient(boolean)}
     * @since 1.3
     */
    public CalendarConverter(String defaultFormat, boolean lenient, String... acceptableFormats) {
        this.defaultDateFormat = defaultFormat;
        this.lenient = lenient;
        this.acceptableDateFormats = Arrays.asList(acceptableFormats);
    }

    /**
     * Construct a DateConverter with lenient set off.
     * Uses the default acceptable date format only
     */
    public CalendarConverter() {
    }

    @Override
    public boolean canConvert(Class clazz) {
        return Calendar.class.isAssignableFrom(clazz);
    }

    @Override
    public void marshal(Object value, HierarchicalStreamWriter writer,
            MarshallingContext context) {
        Calendar calendar = (Calendar) value;
        Date date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(defaultDateFormat);
        formatter.setTimeZone(calendar.getTimeZone());
        writer.setValue(formatter.format(date));
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader,
            UnmarshallingContext context) {
        return parseStringToCalendar(reader.getValue());
    }

    private Calendar parseStringToCalendar(String dateString) {
        GregorianCalendar calendar = new GregorianCalendar(createTimeZone(dateString));
        calendar.setTime(parseStringToDate(dateString));
        return calendar;
    }

    private Date parseStringToDate(String dateString) {
        try {
            return parseWithDefaultFormat(dateString);
        } catch (ParseException ex) {
            TimeZone timezone = createTimeZone(dateString);
            for (String format : this.acceptableDateFormats) {
                try {
                    createDateFormat(format, timezone).parse(dateString);
                } catch (ParseException e2) {
                    // no worries, let's try the next format.
                }
            }
            // no dateFormats left to try
            throw new ConversionException("Cannot parse date " + dateString);
        }
    }

    private SimpleDateFormat createDateFormat(String pattern, TimeZone timezone) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        simpleDateFormat.setLenient(lenient);
        simpleDateFormat.setTimeZone(timezone);
        return simpleDateFormat;
    }

    private Date parseWithDefaultFormat(String dateString) throws ParseException {
        return createDateFormat(defaultDateFormat, createTimeZone(dateString)).parse(dateString);
    }

    private TimeZone createTimeZone(String dateStringInDefaultFormat) {
        return TimeZone.getTimeZone(getTimeZoneID(dateStringInDefaultFormat));
    }

    private String getTimeZoneID(String dateStringInDefaultFormat) {
        return dateStringInDefaultFormat.split(" ")[2];
    }
}
