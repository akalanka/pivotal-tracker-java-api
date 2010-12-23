/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pivotaltracker.api.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.Calendar;

/**
 *
 * @author eivar
 */
@XStreamAlias("attachment")
public class Attachment
{

    private Integer id;
    private String filename;
    private String description;
    @XStreamAlias("uploaded_by")
    private String uploadeBy;
    @XStreamAlias("uploaded_at")
    private Calendar uploadedAt;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getFilename()
    {
        return filename;
    }

    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    public String getUploadeBy()
    {
        return uploadeBy;
    }

    public void setUploadeBy(String uploadeBy)
    {
        this.uploadeBy = uploadeBy;
    }

    public Calendar getUploadedAt()
    {
        return uploadedAt;
    }

    public void setUploadedAt(Calendar uploadedAt)
    {
        this.uploadedAt = uploadedAt;
    }
}
