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
@XStreamAlias("notes")
public class NoteGroup extends Group {
    @XStreamImplicit(itemFieldName = "note")
    private List<Note> notes = new ArrayList<Note>();

    public List<Note> getNotes()
    {
        return new ArrayList<Note>(notes);
    }

    public void addNote(Note note)
    {
        this.notes.add(note);
    }
}
