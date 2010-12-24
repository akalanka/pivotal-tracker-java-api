/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pivotaltracker.api.xstream.converters;

import com.pivotaltracker.api.domain.model.StoryType;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 *
 * @author eivar
 */
public final class StoryTypeConverter implements Converter
{

    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context)
    {
        writer.setValue(source.toString());
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context)
    {
        return StoryType.valueOf(reader.getValue().toUpperCase());
    }

    @Override
    public boolean canConvert(Class type)
    {
        return StoryType.class.isAssignableFrom(type);
    }
}
