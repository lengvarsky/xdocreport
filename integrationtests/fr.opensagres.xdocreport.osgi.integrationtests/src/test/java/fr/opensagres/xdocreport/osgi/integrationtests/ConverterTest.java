/**
 * Copyright (C) 2011-2012 The XDocReport Team <xdocreport@googlegroups.com>
 *
 * All rights reserved.
 *
 * Permission is hereby granted, free  of charge, to any person obtaining
 * a  copy  of this  software  and  associated  documentation files  (the
 * "Software"), to  deal in  the Software without  restriction, including
 * without limitation  the rights to  use, copy, modify,  merge, publish,
 * distribute,  sublicense, and/or sell  copies of  the Software,  and to
 * permit persons to whom the Software  is furnished to do so, subject to
 * the following conditions:
 *
 * The  above  copyright  notice  and  this permission  notice  shall  be
 * included in all copies or substantial portions of the Software.
 *
 * THE  SOFTWARE IS  PROVIDED  "AS  IS", WITHOUT  WARRANTY  OF ANY  KIND,
 * EXPRESS OR  IMPLIED, INCLUDING  BUT NOT LIMITED  TO THE  WARRANTIES OF
 * MERCHANTABILITY,    FITNESS    FOR    A   PARTICULAR    PURPOSE    AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE,  ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package fr.opensagres.xdocreport.osgi.integrationtests;

import static org.junit.Assert.assertNotNull;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.options;
import static org.ops4j.pax.exam.CoreOptions.systemProperty;
import static org.ops4j.pax.exam.CoreOptions.wrappedBundle;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.CoreOptions;
import org.ops4j.pax.exam.Customizer;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.ExamReactorStrategy;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.ops4j.pax.exam.spi.reactors.AllConfinedStagedReactorFactory;

import fr.opensagres.xdocreport.converter.ConverterRegistry;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.IConverter;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.document.DocumentKind;

@RunWith( JUnit4TestRunner.class )
@ExamReactorStrategy(AllConfinedStagedReactorFactory.class)
public class ConverterTest
{

    /*
     * You can configure all kinds of stuff. You will learn about most of it on the project wiki. Here's a typical
     * example: - add a log service to your runtime - add custom bundles via the mvn handler - add additional, non
     * bundlized dependencies. (wrapping on the fly)
     */
    @Configuration
    public static Option[] configure()
    {

        // XXX pass -Dproject.version=XXX int the IDE, otherwise maven will
        // inject Its version
        // final String projectVersion = System.getProperty("project.version");

        return options(

                        // uncomment for "remote debugging"
        		//CoreOptions.vmOption("-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5006"),
                        // equinox(),
        		
        				CoreOptions.junitBundles(),
                        systemProperty( "org.ops4j.pax.logging.DefaultServiceLog.level" ).value( "DEBUG" ),

                        mavenBundle( "fr.opensagres.xdocreport", "fr.opensagres.xdocreport.core" ).versionAsInProject(),
                        // converter API
                        mavenBundle( "fr.opensagres.xdocreport", "fr.opensagres.xdocreport.converter" ).versionAsInProject(),
                        // converter Iml
                        mavenBundle( "fr.opensagres.xdocreport", "fr.opensagres.xdocreport.converter.odt.odfdom" ).versionAsInProject().noStart(),

                        mavenBundle( "fr.opensagres.xdocreport", "fr.opensagres.xdocreport.converter.docx.xwpf" ).versionAsInProject().noStart(),

                        // 3rd parties extensions...
                        mavenBundle( "fr.opensagres.xdocreport", "org.odftoolkit.odfdom.converter.core" ).versionAsInProject(),
                        mavenBundle( "fr.opensagres.xdocreport", "org.odftoolkit.odfdom.converter.pdf" ).versionAsInProject(),
                        mavenBundle( "fr.opensagres.xdocreport", "org.odftoolkit.odfdom.converter.xhtml" ).versionAsInProject(),
                        mavenBundle( "fr.opensagres.xdocreport", "fr.opensagres.xdocreport.itext.extension" ).versionAsInProject(),

                        mavenBundle( "fr.opensagres.xdocreport", "org.apache.poi.xwpf.converter.core" ).versionAsInProject(),
                        mavenBundle( "fr.opensagres.xdocreport", "org.apache.poi.xwpf.converter.pdf" ).versionAsInProject(),
                        mavenBundle( "fr.opensagres.xdocreport", "org.apache.poi.xwpf.converter.xhtml" ).versionAsInProject(),
                        // 3rd parties modules...
                        
                        
                        wrappedBundle( mavenBundle( "org.apache.poi", "poi", "3.8" ) ),
                        wrappedBundle( mavenBundle( "org.apache.poi", "poi-ooxml", "3.8" ) ).exports("org.apache.poi.openxml4j.opc","org.apache.poi.xwpf.usermodel"),
                        wrappedBundle( mavenBundle( "org.apache.servicemix.bundles", "org.apache.servicemix.bundles.xmlbeans", "2.4.0_5" ) ),
                        wrappedBundle( mavenBundle( "org.apache.poi", "ooxml-schemas", "1.1" ) ),
                        wrappedBundle( mavenBundle( "org.odftoolkit", "odfdom-java", "0.8.7" ) ),
                        wrappedBundle( mavenBundle( "com.lowagie", "itext", "2.1.7" ) )

//                        new Customizer()
//                        {
//
//                            @Override
//                            public void customizeEnvironment( File workingFolder )
//                            {
//                                System.out.println( "Hello World: " + workingFolder.getAbsolutePath() );
//                            }
//                        }
        		);
    }

    @Test
    public void findFromODTToPDFViaITextConverter()
        throws Exception
    {
        try
        {

            Options o = Options.getFrom( DocumentKind.ODT ).to( ConverterTypeTo.PDF ).via( ConverterTypeVia.ODFDOM );

            // Test if converter is not null
            IConverter converter = ConverterRegistry.getRegistry().getConverter( o );

            assertNotNull( converter );

        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    @Test
    public void findFromDOCXToPDFViaITextConverter()
        throws Exception
    {
        try
        {
            Options o = Options.getFrom( DocumentKind.DOCX ).to( ConverterTypeTo.PDF ).via( ConverterTypeVia.XWPF );

            // Test if converter is not null
            IConverter converter = ConverterRegistry.getRegistry().getConverter( o );

            assertNotNull( converter );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }

    }

    // @Ignore
    // @Test
    // public void findFromODTToPDFViaITextConverterViaRegistry(
    // BundleContext context) throws Exception {
    // Options o = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF)
    // .via(ConverterTypeVia.ITEXT);
    // // context.createFilter(arg0)
    // // Test if converter is not null
    // // context.getServiceReferences(arg0, arg1)
    // // ServiceReference reference =
    // // context.getServiceReference(IConverter.class.getName());
    // // TODO: Filter filter = new Filter("");
    // // ServiceTracker tracker = new ServiceTracker(context, filter,
    // // customizer);
    // // IConverter converter=reference.
    // // assertNotNull(converter);
    //
    // }
}
