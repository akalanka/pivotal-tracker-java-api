/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pivotaltracker.api.xstream.converters;

import com.pivotaltracker.api.domain.model.Message;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 *
 * @author eivar
 */
public class MessageConverter implements Converter {

    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context)
    {
        Message message = (Message)source;
        writer.startNode("message");
        writer.setValue(message.getContent());
        writer.endNode();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context)
    {
        Message message = new Message();
        reader.moveDown();
        message.setContent(reader.getValue());
        reader.moveUp();
        return message;
    }

    @Override
    public boolean canConvert(Class type)
    {
        return Message.class.equals(type);
    }

}
