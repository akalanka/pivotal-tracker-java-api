/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pivotaltracker.api.xstream.converters;

import com.pivotaltracker.api.domain.model.Message;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author eivar
 */
public class MessageConverterTest {

    private final String MESSAGE_CONTENT = "test message";

    /**
     * Test of marshal method, of class MessageConverter.
     */
    @Test
    public void testMarshal() {
        HierarchicalStreamWriter writerMock = mock(HierarchicalStreamWriter.class);
        Message message = new Message();
        message.setContent(MESSAGE_CONTENT);
        new MessageConverter().marshal(message, writerMock, null);
        verify(writerMock).startNode("message");
        verify(writerMock).setValue(MESSAGE_CONTENT);
        verify(writerMock).endNode();
    }

    /**
     * Test of unmarshal method, of class MessageConverter.
     */
    @Test
    public void testUnmarshal() {
        HierarchicalStreamReader readerMock = mock(HierarchicalStreamReader.class);
        when(readerMock.getValue()).thenReturn(MESSAGE_CONTENT);
        Message actual = (Message) new MessageConverter().unmarshal(readerMock, null);
        verify(readerMock).moveDown();
        verify(readerMock).getValue();
        verify(readerMock).moveUp();
        Assert.assertEquals(actual.getContent(), MESSAGE_CONTENT);
    }

    /**
     * Test of canConvert method, of class MessageConverter.
     */
    @Test
    public void testCanConvert() {
        Assert.assertTrue(new MessageConverter().canConvert(Message.class));
    }
}
