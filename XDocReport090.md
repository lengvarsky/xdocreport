# XDocReport 0.9.0 #

Released July 25, 2011

## Prerequisites ##

Before downloading XDocReport JARs, you must understand that XDocReport is **modular** which is based on 3 modules :

  * **document** : this module defines the document kind you wish used as source (docx, odt, ...). Every **fr.opensagres.xdocreport.document....jar** manages this module.
  * **template** : this module defines the script syntax (Velocity, Freemarker) you wish use in the document source (odt, docx...) to set the fields name, script for manage loop of items, condition... Every **fr.opensagres.xdocreport.template...jar** manages this module.
  * **converter** : this module defines the capability to convert the generated report (docx, odt...) to another format (PDF, XHTML...). Every **fr.opensagres.xdocreport.converter...jar** manages this module.

To use XDocReport to manage reporting you **must download** :

  * required XDocReport API JARs :
    * **fr.opensagres.xdocreport.core**: XDocReport Core.
    * **fr.opensagres.xdocreport.document** : Document type API
    * **fr.opensagres.xdocreport.template**: Template engine API
    * **fr.opensagres.xdocreport.converter**: Converter API
    * **commons-io-1.4**: Commons IO
  * the well XDocReport implementation of **document**, **template** and **converter** according to that you wish managed (odt, docx... with Freemarker/Velocity... and convert it with PDF, XHTML...).

## Downloads ##

XDocReport releases are consumable in 2 different formats:

### Release bundle ###

The XDocReport team provides release bundles hosted on the **[Downloads](http://code.google.com/p/xdocreport/downloads/list)** tab in ZIP. As XDocReport is modular, it exists a ZIP per configuration. For instance if you wish manage Docx with Velocity and convert your report to PDF, XHTML, you must download **[docxandvelocity.converters-0.9.0-sample.zip](http://code.google.com/p/xdocreport/downloads/list)**. This ZIP contains required XDocReport and dependant (POI,...) JARs and source code with sample.

**Attention for PDF conversion**: for license raison, **we cannot set itext used for PDF in the samples** ZIP. So if you wish manage PDF conversion , please [download itext-2.1.7.jar](http://search.maven.org/#artifactdetails|com.lowagie|itext|2.1.7|jar) at hand on the Maven repository.

Here an array of XDocReport download according to you wish to do :

#### Microsoft Office ####

| **Document** | **Template** | **Converter** |
|:-------------|:-------------|:--------------|
| Docx         | Freemarker   |     | **[docxandfreemarker-0.9.0-sample.zip](http://code.google.com/p/xdocreport/downloads/list)** |
| Docx         | Velocity     |       | **[docxandvelocity-0.9.0-sample.zip](http://code.google.com/p/xdocreport/downloads/list)** |
| Docx         | Velocity     |     PDF/XHTML      | **[docxandvelocity.converters-0.9.0-sample.zip](http://code.google.com/p/xdocreport/downloads/list)** |
| PPTX         | Velocity     |     PDF/XHTML      | **[pptxandvelocity-0.9.0-sample.zip](http://code.google.com/p/xdocreport/downloads/list)** |

#### Open/Libre Office ####

| ODP       | Velocity |      | **[odpandvelocity-0.9.0-sample.zip](http://code.google.com/p/xdocreport/downloads/list)** |
|:----------|:---------|:-----|:------------------------------------------------------------------------------------------|
| ODT       | Velocity |      | **[odtandvelocity-0.9.0-sample.zip](http://code.google.com/p/xdocreport/downloads/list)** |
| ODT       | Velocity |     PDF/XHTML      | **[odtandvelocity.converters-0.9.0-sample.zip](http://code.google.com/p/xdocreport/downloads/list)** |

### Maven repository artifacts ###

The preferred method of "using" XDocReport is via Maven artifact repositories. Many build tools are able to interoperate with Maven repositories including Maven, Ivy, Gradle, etc.

XDocReport releases are available on [central Maven Repository](http://search.maven.org/#search|ga|1|xdocreport).

As explained below XDocReport is modular. You must choose :

  * Your Document dependency
  * Your Template dependency
  * Your Converter dependency

#### Document Dependency ####

If you wish use docx :

```
<dependency>
  <groupId>fr.opensagres</groupId>
  <artifactId>fr.opensagres.xdocreport.document.docx</artifactId>
  <version>0.9.0</version>
</dependency>
```

If you wish use odt :

```
<dependency>
  <groupId>fr.opensagres</groupId>
  <artifactId>fr.opensagres.xdocreport.document.odt</artifactId>
  <version>0.9.0</version>
</dependency>
```

#### Template Dependency ####

If you wish use Freemarker :

```
<dependency>
  <groupId>fr.opensagres</groupId>
  <artifactId>fr.opensagres.xdocreport.template.freemarker</artifactId>
  <version>0.9.0</version>
</dependency>
```

If you wish use Velocity :

```
<dependency>
  <groupId>fr.opensagres</groupId>
  <artifactId>fr.opensagres.xdocreport.template.velocity</artifactId>
  <version>0.9.0</version>
</dependency>
```

#### Converter Dependency ####

If you wish convert docx 2 PDF/XHTML :

```
<dependency>
  <groupId>fr.opensagres</groupId>
  <artifactId>fr.opensagres.xdocreport.converter.docx.xwpf</artifactId>
  <version>0.9.0</version>
</dependency>
```

If you wish convert odt 2 PDF/XHTML  :

```
<dependency>
  <groupId>fr.opensagres</groupId>
  <artifactId>fr.opensagres.xdocreport.converter.odt.odfdom</artifactId>
  <version>0.9.0</version>
</dependency>
```