package org.uengine.visualmashup.service.provider;

import java.beans.Introspector;
import java.io.*;
import java.lang.ref.WeakReference;
import java.net.*;

import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 * Class that defines convenience methods for common, simple use of JAXB.
 * <p/>
 * <p/>
 * Methods defined in this class are convenience methods that combine several
 * basic operations in the {@link javax.xml.bind.JAXBContext}, {@link javax.xml.bind.Unmarshaller}, and
 * {@link javax.xml.bind.Marshaller}.
 * <p/>
 * They are designed to be the prefered methods for developers new to JAXB. They
 * have the following characterstics:
 * <p/>
 * <ol>
 * <li>Generally speaking, the performance is not necessarily optimal. It is
 * expected that people who need to write performance critical code will use the
 * rest of the JAXB API directly.
 * <li>Errors that happen during the processing is wrapped into
 * {@link javax.xml.bind.DataBindingException} (which will have {@link javax.xml.bind.JAXBException} as its
 * {@link Throwable#getCause() cause}. It is expected that people who prefer
 * the checked exception would use the rest of the JAXB API directly.
 * </ol>
 * <p/>
 * <p/>
 * In addition, the <tt>unmarshal</tt> methods have the following
 * characteristic:
 * <p/>
 * <ol>
 * <li>Schema validation is not performed on the input XML. The processing will
 * try to continue even if there are errors in the XML, as much as possible.
 * Only as the last resort, this method fails with {@link javax.xml.bind.DataBindingException}.
 * </ol>
 * <p/>
 * <p/>
 * Similarly, the <tt>marshal</tt> methods have the following characteristic:
 * <ol>
 * <li>The processing will try to continue even if the Java object tree does
 * not meet the validity requirement. Only as the last resort, this method fails
 * with {@link javax.xml.bind.DataBindingException}.
 * </ol>
 * <p/>
 * <p/>
 * <p/>
 * All the methods on this class require non-null arguments to all parameters.
 * The <tt>unmarshal</tt> methods either fail with an exception or return a
 * non-null value.
 *
 * @author Kohsuke Kawaguchi
 * @since 2.1
 */
public final class GadgetModuleJAXB {
    /**
     * No instanciation is allowed.
     */
    private GadgetModuleJAXB() {
    }

    /**
     * To improve the performance, we'll cache the last {@link javax.xml.bind.JAXBContext} used.
     */
    private static final class Cache {
        final Class<?> type;
        final JAXBContext context;

        public Cache(Class<?> type) throws JAXBException {
            this.type = type;
            this.context = JAXBContext.newInstance(type);
        }
    }

    /**
     * Cache. We don't want to prevent the {@link Cache#type} from GC-ed, hence
     * {@link java.lang.ref.WeakReference}.
     */
    private static volatile WeakReference<Cache> cache;

    private static final String schemaLocation = "/schema/gadget/gadgets_v0_9.xsd";

    private static Schema schema = null;

    /**
     * 스키마 초기화 : Validation 을 유효화할려면 Marshal, Unmarshal 전에 이 메소드를 실행한다.
     */
    public static void initSchema() {
        Logger logger = LoggerFactory.getLogger(GadgetModuleJAXB.class);
        try {
            if (schema == null) {
                SchemaFactory sf = SchemaFactory
                        .newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
                schema = sf.newSchema(GadgetModuleJAXB.class.getResource(schemaLocation));

                if (logger.isDebugEnabled()) {
                    logger.debug("gadgets_v0_9.xsd Schema Initialized! : " + schemaLocation);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Obtains the {@link javax.xml.bind.JAXBContext} from the given type, by using the cache if
     * possible.
     * <p/>
     * <p/>
     * We don't use locks to control access to {@link #cache}, but this code
     * should be thread-safe thanks to the immutable {@link Cache} and
     * {@code volatile}.
     */
    private static <T> JAXBContext getContext(Class<T> type) throws JAXBException {
        WeakReference<Cache> c = cache;
        if (c != null) {
            Cache d = c.get();
            if (d != null && d.type == type)
                return d.context;
        }

        // overwrite the cache
        Cache d = new Cache(type);
        cache = new WeakReference<Cache>(d);

        return d.context;
    }

    /**
     * @param <T>
     * @param type
     * @return <code>Unmarshaller</code>
     * @throws javax.xml.bind.JAXBException
     * @throws org.xml.sax.SAXException
     */
    private static <T> Unmarshaller createUnmarshaller(Class<T> type)
            throws JAXBException {
        Unmarshaller unmarshaller = getContext(type).createUnmarshaller();
        if (schema != null) unmarshaller.setSchema(schema);

        return unmarshaller;
    }

    /**
     * Reads in a Java object tree from the given XML input.
     *
     * @param xml Reads the entire file as XML.
     */
    public static <T> T unmarshal(File xml, Class<T> type) {
        try {
            JAXBElement<T> item = createUnmarshaller(type).unmarshal(
                    new StreamSource(xml), type);
            return item.getValue();
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        }
    }

    /**
     * Reads in a Java object tree from the given XML input.
     *
     * @param xml The resource pointed by the URL is read in its entirety.
     */
    public static <T> T unmarshal(URL xml, Class<T> type) {
        try {
            JAXBElement<T> item = createUnmarshaller(type).unmarshal(toSource(xml),
                    type);
            return item.getValue();
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        } catch (IOException e) {
            throw new DataBindingException(e);
        }
    }

    /**
     * Reads in a Java object tree from the given XML input.
     *
     * @param xml The URI is {@link java.net.URI#toURL() turned into URL} and then follows
     *            the handling of <tt>URL</tt>.
     */
    public static <T> T unmarshal(URI xml, Class<T> type) {
        try {
            JAXBElement<T> item = createUnmarshaller(type).unmarshal(toSource(xml),
                    type);
            return item.getValue();
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        } catch (IOException e) {
            throw new DataBindingException(e);
        }
    }

    /**
     * Reads in a Java object tree from the given XML input.
     *
     * @param xml The string is first interpreted as an absolute <tt>URI</tt>. If
     *            it's not {@link java.net.URI#isAbsolute() a valid absolute URI}, then it's
     *            interpreted as a <tt>File</tt>
     */
    public static <T> T unmarshal(String xml, Class<T> type) {
        try {
            JAXBElement<T> item = createUnmarshaller(type).unmarshal(toSource(xml),
                    type);
            return item.getValue();
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        } catch (IOException e) {
            throw new DataBindingException(e);
        }
    }

    /**
     * Reads in a Java object tree from the given XML input.
     *
     * @param xml The entire stream is read as an XML infoset. Upon a successful
     *            completion, the stream will be closed by this method.
     */
    public static <T> T unmarshal(InputStream xml, Class<T> type) {
        try {
            JAXBElement<T> item = createUnmarshaller(type).unmarshal(toSource(xml),
                    type);
            return item.getValue();
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        } catch (IOException e) {
            throw new DataBindingException(e);
        }
    }

    /**
     * Reads in a Java object tree from the given XML input.
     *
     * @param xml The character stream is read as an XML infoset. The encoding
     *            declaration in the XML will be ignored. Upon a successful
     *            completion, the stream will be closed by this method.
     */
    public static <T> T unmarshal(Reader xml, Class<T> type) {
        try {
            JAXBElement<T> item = createUnmarshaller(type).unmarshal(toSource(xml),
                    type);
            return item.getValue();
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        } catch (IOException e) {
            throw new DataBindingException(e);
        }
    }

    /**
     * Reads in a Java object tree from the given XML input.
     *
     * @param xml The XML infoset that the {@link javax.xml.transform.Source} represents is read.
     */
    public static <T> T unmarshal(Source xml, Class<T> type) {
        try {
            JAXBElement<T> item = createUnmarshaller(type).unmarshal(toSource(xml),
                    type);
            return item.getValue();
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        } catch (IOException e) {
            throw new DataBindingException(e);
        }
    }

    /**
     * Creates {@link javax.xml.transform.Source} from various XML representation. See
     * {@link #unmarshal} for the conversion rules.
     */
    private static Source toSource(Object xml) throws IOException {
        if (xml == null)
            throw new IllegalArgumentException("no XML is given");

        if (xml instanceof String) {
            try {
                xml = new URI((String) xml);
            } catch (URISyntaxException e) {
                xml = new File((String) xml);
            }
        }
        if (xml instanceof File) {
            File file = (File) xml;
            return new StreamSource(file);
        }
        if (xml instanceof URI) {
            URI uri = (URI) xml;
            xml = uri.toURL();
        }
        if (xml instanceof URL) {
            URL url = (URL) xml;
            return new StreamSource(url.toExternalForm());
        }
        if (xml instanceof InputStream) {
            InputStream in = (InputStream) xml;
            return new StreamSource(in);
        }
        if (xml instanceof Reader) {
            Reader r = (Reader) xml;
            return new StreamSource(r);
        }
        if (xml instanceof Source) {
            return (Source) xml;
        }
        throw new IllegalArgumentException("I don't understand how to handle "
                + xml.getClass());
    }

    /**
     * Writes a Java object tree to XML and store it to the specified location.
     *
     * @param jaxbObject The Java object to be marshalled into XML. If this object is a
     *                   {@link javax.xml.bind.JAXBElement}, it will provide the root tag name and the
     *                   body. If this object has {@link javax.xml.bind.annotation.XmlRootElement} on its class
     *                   definition, that will be used as the root tag name and the given
     *                   object will provide the body. Otherwise, the root tag name is
     *                   {@link java.beans.Introspector#decapitalize(String) infered} from
     *                   {@link Class#getSimpleName() the short class name}. This
     *                   parameter must not be null.
     * @param xml        XML will be written to this file. If it already exists, it will be
     *                   overwritten.
     * @throws javax.xml.bind.DataBindingException
     *          If the operation fails, such as due to I/O error, unbindable
     *          classes.
     */
    public static void marshal(Object jaxbObject, File xml) {
        _marshal(jaxbObject, xml);
    }

    /**
     * Writes a Java object tree to XML and store it to the specified location.
     *
     * @param jaxbObject The Java object to be marshalled into XML. If this object is a
     *                   {@link javax.xml.bind.JAXBElement}, it will provide the root tag name and the
     *                   body. If this object has {@link javax.xml.bind.annotation.XmlRootElement} on its class
     *                   definition, that will be used as the root tag name and the given
     *                   object will provide the body. Otherwise, the root tag name is
     *                   {@link java.beans.Introspector#decapitalize(String) infered} from
     *                   {@link Class#getSimpleName() the short class name}. This
     *                   parameter must not be null.
     * @param xml        The XML will be {@link java.net.URLConnection#getOutputStream() sent} to
     *                   the resource pointed by this URL. Note that not all <tt>URL</tt>s
     *                   support such operation, and exact semantics depends on the
     *                   <tt>URL</tt> implementations. In case of
     *                   {@link java.net.HttpURLConnection HTTP URLs}, this will perform HTTP POST.
     * @throws javax.xml.bind.DataBindingException
     *          If the operation fails, such as due to I/O error, unbindable
     *          classes.
     */
    public static void marshal(Object jaxbObject, URL xml) {
        _marshal(jaxbObject, xml);
    }

    /**
     * Writes a Java object tree to XML and store it to the specified location.
     *
     * @param jaxbObject The Java object to be marshalled into XML. If this object is a
     *                   {@link javax.xml.bind.JAXBElement}, it will provide the root tag name and the
     *                   body. If this object has {@link javax.xml.bind.annotation.XmlRootElement} on its class
     *                   definition, that will be used as the root tag name and the given
     *                   object will provide the body. Otherwise, the root tag name is
     *                   {@link java.beans.Introspector#decapitalize(String) infered} from
     *                   {@link Class#getSimpleName() the short class name}. This
     *                   parameter must not be null.
     * @param xml        The URI is {@link java.net.URI#toURL() turned into URL} and then follows
     *                   the handling of <tt>URL</tt>. See above.
     * @throws javax.xml.bind.DataBindingException
     *          If the operation fails, such as due to I/O error, unbindable
     *          classes.
     */
    public static void marshal(Object jaxbObject, URI xml) {
        _marshal(jaxbObject, xml);
    }

    /**
     * Writes a Java object tree to XML and store it to the specified location.
     *
     * @param jaxbObject The Java object to be marshalled into XML. If this object is a
     *                   {@link javax.xml.bind.JAXBElement}, it will provide the root tag name and the
     *                   body. If this object has {@link javax.xml.bind.annotation.XmlRootElement} on its class
     *                   definition, that will be used as the root tag name and the given
     *                   object will provide the body. Otherwise, the root tag name is
     *                   {@link java.beans.Introspector#decapitalize(String) infered} from
     *                   {@link Class#getSimpleName() the short class name}. This
     *                   parameter must not be null.
     * @param xml        The string is first interpreted as an absolute <tt>URI</tt>. If
     *                   it's not {@link java.net.URI#isAbsolute() a valid absolute URI}, then it's
     *                   interpreted as a <tt>File</tt>
     * @throws javax.xml.bind.DataBindingException
     *          If the operation fails, such as due to I/O error, unbindable
     *          classes.
     */
    public static void marshal(Object jaxbObject, String xml) {
        _marshal(jaxbObject, xml);
    }

    /**
     * Writes a Java object tree to XML and store it to the specified location.
     *
     * @param jaxbObject The Java object to be marshalled into XML. If this object is a
     *                   {@link javax.xml.bind.JAXBElement}, it will provide the root tag name and the
     *                   body. If this object has {@link javax.xml.bind.annotation.XmlRootElement} on its class
     *                   definition, that will be used as the root tag name and the given
     *                   object will provide the body. Otherwise, the root tag name is
     *                   {@link java.beans.Introspector#decapitalize(String) infered} from
     *                   {@link Class#getSimpleName() the short class name}. This
     *                   parameter must not be null.
     * @param xml        The XML will be sent to the given {@link java.io.OutputStream}. Upon a
     *                   successful completion, the stream will be closed by this method.
     * @throws javax.xml.bind.DataBindingException
     *          If the operation fails, such as due to I/O error, unbindable
     *          classes.
     */
    public static void marshal(Object jaxbObject, OutputStream xml) {
        _marshal(jaxbObject, xml);
    }

    /**
     * Writes a Java object tree to XML and store it to the specified location.
     *
     * @param jaxbObject The Java object to be marshalled into XML. If this object is a
     *                   {@link javax.xml.bind.JAXBElement}, it will provide the root tag name and the
     *                   body. If this object has {@link javax.xml.bind.annotation.XmlRootElement} on its class
     *                   definition, that will be used as the root tag name and the given
     *                   object will provide the body. Otherwise, the root tag name is
     *                   {@link java.beans.Introspector#decapitalize(String) infered} from
     *                   {@link Class#getSimpleName() the short class name}. This
     *                   parameter must not be null.
     * @param xml        The XML will be sent as a character stream to the given
     *                   {@link java.io.Writer}. Upon a successful completion, the stream will be
     *                   closed by this method.
     * @throws javax.xml.bind.DataBindingException
     *          If the operation fails, such as due to I/O error, unbindable
     *          classes.
     */
    public static void marshal(Object jaxbObject, Writer xml) {
        _marshal(jaxbObject, xml);
    }

    /**
     * Writes a Java object tree to XML and store it to the specified location.
     *
     * @param jaxbObject The Java object to be marshalled into XML. If this object is a
     *                   {@link javax.xml.bind.JAXBElement}, it will provide the root tag name and the
     *                   body. If this object has {@link javax.xml.bind.annotation.XmlRootElement} on its class
     *                   definition, that will be used as the root tag name and the given
     *                   object will provide the body. Otherwise, the root tag name is
     *                   {@link java.beans.Introspector#decapitalize(String) infered} from
     *                   {@link Class#getSimpleName() the short class name}. This
     *                   parameter must not be null.
     * @param xml        The XML will be sent to the {@link javax.xml.transform.Result} object.
     * @throws javax.xml.bind.DataBindingException
     *          If the operation fails, such as due to I/O error, unbindable
     *          classes.
     */
    public static void marshal(Object jaxbObject, Result xml) {
        _marshal(jaxbObject, xml);
    }

    public static String marshal(Object jaxbObject) {
        StringWriter sw = new StringWriter();
        _marshal(jaxbObject, sw);
        return sw.toString();
    }

    /**
     * Writes a Java object tree to XML and store it to the specified location.
     * <p/>
     * <p/>
     * This method is a convenience method that combines several basic operations
     * in the {@link javax.xml.bind.JAXBContext} and {@link javax.xml.bind.Marshaller}. This method is designed
     * to be the prefered method for developers new to JAXB. This method has the
     * following characterstics:
     * <p/>
     * <ol>
     * <li>Generally speaking, the performance is not necessarily optimal. It is
     * expected that those people who need to write performance critical code will
     * use the rest of the JAXB API directly.
     * <li>Errors that happen during the processing is wrapped into
     * {@link javax.xml.bind.DataBindingException} (which will have {@link javax.xml.bind.JAXBException} as its
     * {@link Throwable#getCause() cause}. It is expected that those people who
     * prefer the checked exception would use the rest of the JAXB API directly.
     * </ol>
     *
     * @param jaxbObject The Java object to be marshalled into XML. If this object is a
     *                   {@link javax.xml.bind.JAXBElement}, it will provide the root tag name and the
     *                   body. If this object has {@link javax.xml.bind.annotation.XmlRootElement} on its class
     *                   definition, that will be used as the root tag name and the given
     *                   object will provide the body. Otherwise, the root tag name is
     *                   {@link java.beans.Introspector#decapitalize(String) infered} from
     *                   {@link Class#getSimpleName() the short class name}. This
     *                   parameter must not be null.
     * @param xml        Represents the receiver of XML. Objects of the following types are
     *                   allowed.
     *                   <p/>
     *                   <table>
     *                   <tr>
     *                   <th>Type</th>
     *                   <th>Operation</th>
     *                   </tr>
     *                   <tr>
     *                   <td>{@link java.io.File}</td>
     *                   <td>XML will be written to this file. If it already exists, it will be
     *                   overwritten.</td>
     *                   </tr>
     *                   <tr>
     *                   <td>{@link java.net.URL}</td>
     *                   <td>The XML will be {@link java.net.URLConnection#getOutputStream() sent} to the
     *                   resource pointed by this URL. Note that not all <tt>URL</tt>s support
     *                   such operation, and exact semantics depends on the <tt>URL</tt>
     *                   implementations. In case of {@link java.net.HttpURLConnection HTTP URLs}, this will
     *                   perform HTTP POST.</td>
     *                   </tr>
     *                   <tr>
     *                   <td>{@link java.net.URI}</td>
     *                   <td>The URI is {@link java.net.URI#toURL() turned into URL} and then follows the
     *                   handling of <tt>URL</tt>. See above.</td>
     *                   </tr>
     *                   <tr>
     *                   <td>{@link String}</td>
     *                   <td>The string is first interpreted as an absolute <tt>URI</tt>. If
     *                   it's not {@link java.net.URI#isAbsolute() a valid absolute URI}, then it's
     *                   interpreted as a <tt>File</tt></td>
     *                   </tr>
     *                   <tr>
     *                   <td>{@link java.io.OutputStream}</td>
     *                   <td>The XML will be sent to the given {@link java.io.OutputStream}. Upon a
     *                   successful completion, the stream will be closed by this method.</td>
     *                   </tr>
     *                   <tr>
     *                   <td>{@link java.io.Writer}</td>
     *                   <td>The XML will be sent as a character stream to the given {@link java.io.Writer}.
     *                   Upon a successful completion, the stream will be closed by this method.</td>
     *                   </tr>
     *                   <tr>
     *                   <td>{@link javax.xml.transform.Result}</td>
     *                   <td>The XML will be sent to the {@link javax.xml.transform.Result} object.</td>
     *                   </tr>
     *                   </table>
     * @throws javax.xml.bind.DataBindingException
     *          If the operation fails, such as due to I/O error, unbindable
     *          classes.
     */
    @SuppressWarnings(value = {"unchecked"})
    private static void _marshal(Object jaxbObject, Object xml) {
        try {
            JAXBContext context;

            if (jaxbObject instanceof JAXBElement) {
                context = getContext(((JAXBElement<?>) jaxbObject).getDeclaredType());
            } else {
                Class<?> clazz = jaxbObject.getClass();
                XmlRootElement r = clazz.getAnnotation(XmlRootElement.class);
                context = getContext(clazz);
                if (r == null) {
                    // we need to infer the name
                    jaxbObject = new JAXBElement(new QName(inferName(clazz)), clazz,
                            jaxbObject);
                }
            }

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            if (schema != null) m.setSchema(schema);

            m.marshal(jaxbObject, toResult(xml));
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        } catch (IOException e) {
            throw new DataBindingException(e);
        }
    }

    private static String inferName(Class<?> clazz) {
        return Introspector.decapitalize(clazz.getSimpleName());
    }

    /**
     * Creates {@link javax.xml.transform.Result} from various XML representation. See
     * {@link #_marshal(Object, Object)} for the conversion rules.
     */
    private static Result toResult(Object xml) throws IOException {
        if (xml == null)
            throw new IllegalArgumentException("no XML is given");

        if (xml instanceof String) {
            try {
                xml = new URI((String) xml);
            } catch (URISyntaxException e) {
                xml = new File((String) xml);
            }
        }
        if (xml instanceof File) {
            File file = (File) xml;
            return new StreamResult(file);
        }
        if (xml instanceof URI) {
            URI uri = (URI) xml;
            xml = uri.toURL();
        }
        if (xml instanceof URL) {
            URL url = (URL) xml;
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            con.setDoInput(false);
            con.connect();
            return new StreamResult(con.getOutputStream());
        }
        if (xml instanceof OutputStream) {
            OutputStream os = (OutputStream) xml;
            return new StreamResult(os);
        }
        if (xml instanceof Writer) {
            Writer w = (Writer) xml;
            return new StreamResult(w);
        }
        if (xml instanceof Result) {
            return (Result) xml;
        }
        throw new IllegalArgumentException("I don't understand how to handle "
                + xml.getClass());
    }
}