# Copyright 2017 Yahoo Holdings. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root.
vespa_add_library(searchsummary_docsummary OBJECT
    SOURCES
    array_attribute_combiner_dfw.cpp
    attribute_combiner_dfw.cpp
    attribute_field_writer.cpp
    resultclass.cpp
    resultconfig.cpp
    resultpacker.cpp
    urlresult.cpp
    getdocsumargs.cpp
    docsumstate.cpp
    docsumfieldwriter.cpp
    docsumwriter.cpp
    keywordextractor.cpp
    attributedfw.cpp
    dynamicteaserdfw.cpp
    docsumconfig.cpp
    rankfeaturesdfw.cpp
    summaryfeaturesdfw.cpp
    juniperproperties.cpp
    textextractordfw.cpp
    geoposdfw.cpp
    tokenizer.cpp
    positionsdfw.cpp
    linguisticsannotation.cpp
    searchdatatype.cpp
    struct_map_attribute_combiner_dfw.cpp
    summaryfieldconverter.cpp
    AFTER
    searchsummary_config
)
