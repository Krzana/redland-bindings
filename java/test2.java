// -*- Mode: java; c-basic-offset: 2 -*-
//
// test2.c - Redland Java interface test code using all Java classes
//
// $Id$
//
// Copyright (C) 2001 David Beckett - http://purl.org/net/dajobe/
// Institute for Learning and Research Technology - http://www.ilrt.org/
// University of Bristol - http://www.bristol.ac.uk/
// 
// This package is Free Software or Open Source available under the
// following licenses (these are alternatives):
//   1. GNU Lesser General Public License (LGPL)
//   2. GNU General Public License (GPL)
//   3. Mozilla Public License (MPL)
// 
// See LICENSE.html or LICENSE.txt at the top of this package for the
// full license terms.
// 
// 
//

import org.librdf.redland.*;

class test2 {
  
  public static void main(String[] args) {

    System.out.println("Test starting");

    World world=new World();
    world.open();
    
    Storage storage=new Storage(world, "hashes", "test",
                                "hash-type='bdb',dir='.',new='yes'");
    if(storage == null) {
      System.out.println("Failed to create RDF storage");
      System.exit(1);
    }

    Model model=new Model(world, storage, "");
    if(model == null) {
      System.out.println("Failed to create RDF model");
      System.exit(1);
    }
    
    Parser parser=new Parser(world, "raptor", "", null);
    if(parser == null) {
      System.out.println("Failed to create RDF parser");
      System.exit(1);
    }
    
    URI uri=new URI(world, "file:../perl/dc.rdf");
    if(parser.parse(uri, uri, model)) {
      System.out.println("Failed to parse " + uri + " into model");
      System.exit(1);
    }

    // These need not be done in Java if garbage collection
    // works in the right order
    parser=null;
    model=null;
    storage=null;
    world=null;

    System.out.println("Test finished");
  }
}
