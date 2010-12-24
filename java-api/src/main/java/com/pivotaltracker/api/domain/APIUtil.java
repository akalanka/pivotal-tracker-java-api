package com.pivotaltracker.api.domain;

import com.pivotaltracker.api.exceptions.TrackerException;
import com.pivotaltracker.api.domain.model.AttachmentGroup;
import com.pivotaltracker.api.domain.model.IntegrationGroup;
import com.pivotaltracker.api.domain.model.IterationGroup;
import com.pivotaltracker.api.domain.model.MembershipGroup;
import com.pivotaltracker.api.domain.model.Message;
import com.pivotaltracker.api.domain.model.ProjectGroup;
import com.pivotaltracker.api.domain.model.StoryGroup;
import com.pivotaltracker.api.domain.model.UserToken;
import com.pivotaltracker.api.xstream.converters.CalendarConverter;
import com.pivotaltracker.api.xstream.converters.MessageConverter;
import com.pivotaltracker.api.xstream.converters.StoryTypeConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Calendar;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author eivar
 */
public final class APIUtil implements APIConstants
{

    private static final Logger LOGGER = LoggerFactory.getLogger(APIUtil.class);
    private static XStream xStream = null;

    private static XStream getXMLParserInstance() {
        if (xStream == null) {
            LOGGER.debug("XmlFriendlyReplacer configured to replace __ by _");
            xStream = new XStream(new XppDriver(new XmlFriendlyReplacer("__", "_"))
            {

                @Override
                public HierarchicalStreamWriter createWriter(Writer out) {
                    return new PrettyPrintWriter(out, this.xmlFriendlyReplacer())
                    {

                        @Override
                        public void startNode(String name, Class type) {
                            super.startNode(name, type);
                            if (Integer.class.isAssignableFrom(type)) {
                                addAttribute("type", "integer");
                            }
                            if (Date.class.isAssignableFrom(type)
                                    || Calendar.class.isAssignableFrom(type)) {
                                addAttribute("type", "datetime");
                            }
                        }
                    };
                }
            });
            xStream.registerConverter(new CalendarConverter());
            xStream.registerConverter(new StoryTypeConverter());
            xStream.registerConverter(new MessageConverter());
            xStream.processAnnotations(
                    new Class[]{ProjectGroup.class, IterationGroup.class,
                        IntegrationGroup.class, MembershipGroup.class,
                        AttachmentGroup.class, StoryGroup.class, Message.class,
                        UserToken.class});
            xStream.autodetectAnnotations(true);
            LOGGER.debug("Custom XStream instance created!");
        }
        return xStream;
    }

    /**
     * Deserialize an object from an XML InputStream.
     * @param xmlSource
     * @see {@link XStream.fromXML}
     */
    public static Object parseEntityFromXML(InputStream xmlSource) throws TrackerException {
        Object instance = getXMLParserInstance().fromXML(xmlSource);
        LOGGER.debug("Object deserialized by XStream: {}", instance);
        if (instance instanceof Message) {
            throw new TrackerException(((Message) instance).getContent());
        }
        return instance;
    }

    /**
     * Deserialize an object from an XML Reader.
     * @param xmlSource
     * @see {@link XStream.fromXML}
     */
    public static Object parseEntityFromXML(Reader xmlSource) {
        return getXMLParserInstance().fromXML(xmlSource);
    }

    public static <T> String marshallEntityToXML(Class<T> entityType, T instance) {
        return getXMLParserInstance().toXML(instance);
    }

    public static String createXMLDeclaration(float version, String encoding) {
        return String.format("<?xml version=\"%1.1f\" encoding=\"%s\"?>", version, encoding);
    }
}
