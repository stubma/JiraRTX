package ut.luma.jira.plugin.rtxnotifier;

import org.junit.Test;
import luma.jira.plugin.rtxnotifier.MyPluginComponent;
import luma.jira.plugin.rtxnotifier.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}