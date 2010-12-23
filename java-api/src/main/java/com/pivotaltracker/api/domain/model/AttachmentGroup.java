/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pivotaltracker.api.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eivar
 */
@XStreamAlias("attachments")
public class AttachmentGroup extends Group
{

    @XStreamImplicit(itemFieldName = "attachment")
    private List<Attachment> attachments = new ArrayList<Attachment>();

    public List<Attachment> getAttachments()
    {
        return new ArrayList<Attachment>(attachments);
    }

    public void addAttchment(Attachment attachment)
    {
        this.attachments.add(attachment);
    }
}
