/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pivotaltracker.api.xstream.converters;

import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author eivar
 */
public class CalendarConverterTest {

    final String TEST_TIMEZONE_STRING = "CST";
    final String TEST_DATE_STRING = "2010/01/16 17:39:10 " + TEST_TIMEZONE_STRING;
    private Calendar TEST_CALENDAR;

    @BeforeClass
    public void setUp() {
        TimeZone timeZone = TimeZone.getTimeZone(TEST_TIMEZONE_STRING);
        TEST_CALENDAR = Calendar.getInstance(timeZone);
        TEST_CALENDAR.set(2010, Calendar.JANUARY, 16, 17, 39, 10);
        TEST_CALENDAR.set(Calendar.MILLISECOND, 0);
    }

    /**
     * Test of canConvert method, of class CalendarConverter.
     */
    @Test
    public void testCanConvert() {
        Assert.assertTrue(new CalendarConverter().canConvert(Calendar.class));
        Assert.assertFalse(new CalendarConverter().canConvert(Date.class));
    }

    /**
     * Test of marshal method, of class CalendarConverter.
     */
    @Test
    public void testMarshal() {
        HierarchicalStreamWriter writerMock = mock(HierarchicalStreamWriter.class);
        new CalendarConverter().marshal(TEST_CALENDAR, writerMock, null);
        verify(writerMock).setValue(TEST_DATE_STRING);
    }

    /**
     * Test of unmarshal method, of class CalendarConverter.
     */
    @Test
    public void testUnmarshal() {
        HierarchicalStreamReader readerMock = mock(HierarchicalStreamReader.class);
        when(readerMock.getValue()).thenReturn(TEST_DATE_STRING);
        Calendar actual = (Calendar) new CalendarConverter().unmarshal(readerMock, null);
        Calendar expected = TEST_CALENDAR;

        verify(readerMock).getValue();
        Assert.assertEquals(actual, expected);
    }
}
