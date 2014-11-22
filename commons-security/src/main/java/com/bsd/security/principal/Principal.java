/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.security.principal;

import java.io.Serializable;

/**
 *
 * @author bento
 */
public class Principal
            implements java.security.Principal, Serializable
{
    private static final long serialVersionUID = 3116355877194905072L;
    private String name;

    /**
     * Creates a new Principal object.
     *
     * @param s DOCUMENT ME!
     */
    public Principal(final String s)
    {
        this.name = s;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    @Override
    public String getName()
    {
        return this.name;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    @Override
    public int hashCode()
    {
        return this.name.hashCode();
    }

    /**
     * DOCUMENT ME!
     *
     * @param o DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    @Override
    public boolean equals(final Object o)
    {
        if (o instanceof Principal)
        {
            return this.name.equals(((Principal) o).name);
        }

        return false;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    @Override
    public String toString()
    {
        return this.getClass()
                   .getName() + "=" + this.name;
    }
}
