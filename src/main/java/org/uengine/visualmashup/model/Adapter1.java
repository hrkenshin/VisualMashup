
package org.uengine.visualmashup.model;

import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter1
    extends XmlAdapter<String, Date>
{


    public Date unmarshal(String value) {
        return (org.uengine.visualmashup.common.util.DateUtil.parseXmlDateTime(value));
    }

    public String marshal(Date value) {
        return (org.uengine.visualmashup.common.util.DateUtil.printXmlDateTime(value));
    }

}
