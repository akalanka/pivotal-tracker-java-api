/**
 * JOX requieres a container variable named as the root tag (for example 
 * <code>private IntegrationGroup integrations</code> and it uses java beans convention names
 * which forces to use certain method names like setIntegration (even when it is an array setter) for xml parser to work correctly
 */
package com.pivotaltracker.api.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author eivar
 */
@XStreamAlias("integrations")
public final class IntegrationGroup extends Group
{

    @XStreamImplicit(itemFieldName = "integration")
    private List<Integration> integrations = new ArrayList<Integration>();

    public IntegrationGroup(Integration... integrations)
    {
        this.setIntegration(integrations);
    }

    public Integration[] getIntegration()
    {
        return this.integrations.toArray(new Integration[0]);
    }

    public void setIntegration(Integration[] integrations)
    {
        this.integrations.clear();
        this.integrations.addAll(Arrays.asList(integrations));
    }
}
