# Annot8

Annot8 is a framework for extracting structured information from unstructured data.
As a simple example, this could be extracting e-mail addresses from a collection of text documents, or faces from a collection of images.

## Terminology

* Annotation - Some information highlighted in Content, for example a span of text
* Content - A “view” on an Item, for example the text extracted from a Word document
* Item - A data object for processing, for example a Word document
* Processor - A processor is an Annot8 component that processes Content in some way, for example annotating e-mail addresses
* Property - A property is a key-value pair on an Annot8 object (such as an Annotation, an Item or Content) which provides additional meta-data, for example the author of a document
* Source - An Annot8 component which creates new Item objects, for example by finding files in a folder on your hard drive

Processor and Source are known as components, and components are generally formed of three separate classes:

* Processor or Source - the code that actually does the work
* Descriptor - code to describe and create new components
* Settings (optional) - configuration information for the component (generally used by the descriptor when creating new instances)

## Core Repository

This is the core Annot8 repository, which contains the actual framework.
This repository does not contain any implementations of components, these are in the [Annot8 Components](https://github.com/annot8/annot8-components) repository.

This repository contains 4 sub-projects

* `annot8-api` - The core interfaces (API) used by Annot8
* `annot8-common` - Common tools, classes and helpers
* `annot8-implementations` - The standard implementation of Annot8 classes
* `annot8-testing` - Implementations and utilities useful when testing

## Versioning

The following approach is used to versioning within the Annot8 projects:

* The core Annot8 API will be versioned as `major.minor` (e.g. `1.0`). 
  Any minor versions will be fully backwards compatible, but major versions may not be.
* Supplementary Annot8 projects, including Annot8 implementations and components, will be versioned as `major.project-version.patch` (e.g. `1.2.1`).
  The major version will match the API version used by the project, and will have their own project version followed by a patch level to indicate bug fixes.
  These projects may be developed and released at different rates, and therefore may have different project versions.

## Licence

Code in this repository is licenced under the [Apache Software Licence 2](https://www.apache.org/licenses/LICENSE-2.0).
See the NOTICE file for any additional restrictions.