/*
 * This file is generated by jOOQ.
 */
package ru.tinkoff.edu.java.scrapper.domain.jooq.tables.pojos;


import jakarta.validation.constraints.Size;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.annotation.processing.Generated;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.17.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Link implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String link;
    private OffsetDateTime lastchecktime;
    private OffsetDateTime lastupdatetime;

    public Link() {}

    public Link(Link value) {
        this.id = value.id;
        this.link = value.link;
        this.lastchecktime = value.lastchecktime;
        this.lastupdatetime = value.lastupdatetime;
    }

    @ConstructorProperties({ "id", "link", "lastchecktime", "lastupdatetime" })
    public Link(
        @NotNull Long id,
        @NotNull String link,
        @NotNull OffsetDateTime lastchecktime,
        @Nullable OffsetDateTime lastupdatetime
    ) {
        this.id = id;
        this.link = link;
        this.lastchecktime = lastchecktime;
        this.lastupdatetime = lastupdatetime;
    }

    /**
     * Getter for <code>LINK.ID</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>LINK.ID</code>.
     */
    public void setId(@NotNull Long id) {
        this.id = id;
    }

    /**
     * Getter for <code>LINK.LINK</code>.
     */
    @jakarta.validation.constraints.NotNull
    @Size(max = 1000000000)
    @NotNull
    public String getLink() {
        return this.link;
    }

    /**
     * Setter for <code>LINK.LINK</code>.
     */
    public void setLink(@NotNull String link) {
        this.link = link;
    }

    /**
     * Getter for <code>LINK.LASTCHECKTIME</code>.
     */
    @NotNull
    public OffsetDateTime getLastchecktime() {
        return this.lastchecktime;
    }

    /**
     * Setter for <code>LINK.LASTCHECKTIME</code>.
     */
    public void setLastchecktime(@NotNull OffsetDateTime lastchecktime) {
        this.lastchecktime = lastchecktime;
    }

    /**
     * Getter for <code>LINK.LASTUPDATETIME</code>.
     */
    @Nullable
    public OffsetDateTime getLastupdatetime() {
        return this.lastupdatetime;
    }

    /**
     * Setter for <code>LINK.LASTUPDATETIME</code>.
     */
    public void setLastupdatetime(@Nullable OffsetDateTime lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Link other = (Link) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.link == null) {
            if (other.link != null)
                return false;
        }
        else if (!this.link.equals(other.link))
            return false;
        if (this.lastchecktime == null) {
            if (other.lastchecktime != null)
                return false;
        }
        else if (!this.lastchecktime.equals(other.lastchecktime))
            return false;
        if (this.lastupdatetime == null) {
            if (other.lastupdatetime != null)
                return false;
        }
        else if (!this.lastupdatetime.equals(other.lastupdatetime))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.link == null) ? 0 : this.link.hashCode());
        result = prime * result + ((this.lastchecktime == null) ? 0 : this.lastchecktime.hashCode());
        result = prime * result + ((this.lastupdatetime == null) ? 0 : this.lastupdatetime.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Link (");

        sb.append(id);
        sb.append(", ").append(link);
        sb.append(", ").append(lastchecktime);
        sb.append(", ").append(lastupdatetime);

        sb.append(")");
        return sb.toString();
    }
}
