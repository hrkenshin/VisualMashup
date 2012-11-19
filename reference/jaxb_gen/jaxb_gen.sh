#!bin/sh

echo -e "\n##### xjc : generate java code from xml schema .....\n"
xjc -p org.opensocial.gadget -no-header ../../src/main/resources/schema/gadget/gadgets_v0_9.xsd
xjc -p org.uengine.visualmashup.model -no-header ../../src/main/resources/schema/mashup/visual-mashup.xsd